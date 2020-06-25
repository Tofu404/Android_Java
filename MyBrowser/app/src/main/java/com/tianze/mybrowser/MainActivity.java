package com.tianze.mybrowser;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.tianze.mybrowser.db.entity.BookMark;
import com.tianze.mybrowser.db.entity.BookMarkMng;

public class MainActivity extends AppCompatActivity {

    private FrameLayout mContainer;
    private MyWebView mMyWebView;
    private EditText mSearchBar;
    private MyWebViewClient mMyWebViewClient;
    private MyWebChromeClient mMyWebChromeClient;
    private DownloadBroadcast mBroadcast;
    private LinearLayout mFooter;
    private ImageView mBookMark;
    private ImageView mRefresh;
    private ValueAnimator mFooterInAnimator;
    private ValueAnimator mFooterOutAnimator;

    public static void start(Context context, String inputStr) {
        Intent starter = new Intent(context, MainActivity.class);
        starter.putExtra("inputStr", inputStr);
        context.startActivity(starter);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        findWidget();
        initWebChromeClient();
        initWebViewClient();
        initWebView();
        initEvent();
        initAnimation();
        Intent intent = getIntent();
        loadUrl(intent.getStringExtra("inputStr"));
        setBookMarkStatus();

        mBroadcast = new DownloadBroadcast();
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(mBroadcast, filter);
    }

    private void findWidget() {
        mContainer = findViewById(R.id.container);
        mSearchBar = findViewById(R.id.search_bar);
        mFooter = findViewById(R.id.footer);
        mBookMark = findViewById(R.id.mark);
        mRefresh = findViewById(R.id.refresh);
    }

    private void initWebViewClient() {
        mMyWebViewClient = new MyWebViewClient();
        mMyWebViewClient.setSearchBar(mSearchBar);
        mMyWebViewClient.setContext(this);
    }

    private void initWebChromeClient() {
        mMyWebChromeClient = new MyWebChromeClient();
        mMyWebChromeClient.setSearchBar(mSearchBar);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initEvent() {

        mSearchBar.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    mMyWebView.loadUrl(mSearchBar.getText().toString());
                    mSearchBar.clearFocus();
                }
                return false;
            }
        });

        mSearchBar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mSearchBar.setText(mMyWebView.getUrl());
                    mSearchBar.selectAll();
                } else {
                    mMyWebChromeClient.setSearchBarTitle();
                }
            }
        });

        //设置滑动监听器
        mMyWebView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int scrollDistance = scrollY - oldScrollY;

                if (scrollDistance > 0) {
                    //防止弹出、隐藏过于灵敏，当用户滑动50px的时候再触发动画
                    if (mFooter.getHeight() > 0 && Math.abs(scrollDistance) > 20) {
                        mFooterOutAnimator.start();
                    }
                } else {
                    //当webView没有滑动到底部，可以继续滑动的时候才可以触发动画
                    if (mFooter.getHeight() == 0 && mMyWebView.canScrollVertically(oldScrollY) && Math.abs(scrollDistance) > 20) {
                        mFooterInAnimator.start();
                    }
                }
            }
        });

        //下载监听
        mMyWebView.setDownloadListener(new DownloadListener() {

            @Override
            public void onDownloadStart(final String url, String userAgent, final String contentDisposition, final String mimetype, final long contentLength) {
                String guessFileName = URLUtil.guessFileName(url, contentDisposition, mimetype);
                if (MyUtils.downLoadFileName.contains(guessFileName)) {
                    Toast toast = Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT);
                    toast.setText("已在下载列表当中，请不要重复下载");
                    toast.show();
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(false);
                builder.setTitle("下载");
                builder.setMessage(URLUtil.guessFileName(url, contentDisposition, mimetype));

                builder.setPositiveButton("下载", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyUtils.download(MainActivity.this, url, contentDisposition, mimetype);
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.create().show();
            }
        });

        //点击收藏按钮
        mBookMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!BookMarkMng.isBookMarkExist(mMyWebView.getUrl())) {
                    markUrl();
                } else {
                    unMarkUrl();
                }
            }
        });

        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyWebView.reload();
                //旋转动画
                Animation animation = new RotateAnimation(0, 360 * 2, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(1000);
                mRefresh.startAnimation(animation);
            }
        });

    }

    private void initAnimation() {

        mFooterInAnimator = ValueAnimator.ofInt(0, MyUtils.dip2px(this, 40));
        mFooterInAnimator.setDuration(500);
        mFooterInAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = mFooter.getLayoutParams();
                layoutParams.height = animatedValue;
                mFooter.setLayoutParams(layoutParams);
            }
        });

        mFooterOutAnimator = ValueAnimator.ofInt(MyUtils.dip2px(this, 40), 0);
        mFooterOutAnimator.setDuration(500);
        mFooterOutAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = mFooter.getLayoutParams();
                layoutParams.height = animatedValue;
                mFooter.setLayoutParams(layoutParams);
            }
        });
    }

    public void setBookMarkStatus() {
        if (BookMarkMng.isBookMarkExist(mMyWebView.getUrl())) {
            mBookMark.setImageDrawable(getResources().getDrawable(R.drawable.ic_mark, null));
        }
    }

    //删除书签
    private void unMarkUrl() {
        BookMarkMng.deleteBookMark(mMyWebView.getUrl());
        mBookMark.setImageDrawable(getResources().getDrawable(R.drawable.ic_numark, null));
    }

    //添加书签
    private boolean markUrl() {
        String url = mMyWebView.getUrl();
        String title = mMyWebChromeClient.urlTitle;
        BookMark bookMark = new BookMark(null,title,url);
        mBookMark.setImageDrawable(getResources().getDrawable(R.drawable.ic_mark,null));
        return BookMarkMng.insertBookMark(bookMark);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        //初始化并设置参数
        mMyWebView = new MyWebView(getApplicationContext());
        mMyWebView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        WebSettings settings = mMyWebView.getSettings();

        //启用JavaScript功能
        settings.setJavaScriptEnabled(true);

        //将图片设置到适合webview的大小
        settings.setUseWideViewPort(true);

        //将webview缩放至适应屏幕大小
        settings.setLoadWithOverviewMode(true);

        //启用缩放功能
        settings.setSupportZoom(true);

        //设置内置的缩放控件，如果为false的话则该webview不能缩放
        settings.setBuiltInZoomControls(true);

        //隐藏非常难看的webview缩放按钮
        settings.setDisplayZoomControls(false);

        //设置缓存为默认模式
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        //设置可以访问文件
        settings.setAllowFileAccess(true);

        //支持通过js打开新的窗口
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

        //支持自动加载图片
        settings.setLoadsImagesAutomatically(true);

        //设置默认编码格式
        settings.setDefaultTextEncodingName("UTF-8");

        //本地存储
        settings.setDomStorageEnabled(true);

        // 资源混合模式
        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        //启用硬件加速
        mMyWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        //隐藏垂直滚动条
        mMyWebView.setVerticalScrollBarEnabled(false);

        //隐藏水平滚动条
        mMyWebView.setHorizontalScrollBarEnabled(false);

        //内置浏览器
        mMyWebView.setWebChromeClient(mMyWebChromeClient);
        mMyWebView.setWebViewClient(mMyWebViewClient);

        //将mMyWebView设置给mMyWebChromeClient
        mMyWebChromeClient.setMyWebView(mMyWebView);

        //将mSearchBar设置给mMyWebView
        mMyWebView.setSearchBar(mSearchBar);

        mContainer.removeAllViews();
        mContainer.addView(mMyWebView);
    }

    @Override
    public void onBackPressed() {
        if (mMyWebView.canGoBack()) {
            mMyWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private void loadUrl(String url) {
        String queryUrl = MyUtils.queryWrapper(url);
        mMyWebView.loadUrl(queryUrl);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMyWebView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMyWebView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mContainer.removeAllViews();
        mMyWebView = null;
        unregisterReceiver(mBroadcast);
    }
}