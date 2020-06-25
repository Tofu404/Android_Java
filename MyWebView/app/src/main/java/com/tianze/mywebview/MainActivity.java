package com.tianze.mywebview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 判断字符串是否为URL
     *
     * @param urls 需要判断的String类型url
     * @return true:是URL；false:不是URL
     */


    public static final String SEARCH_ENGINE_BAIDU = "http://www.baidu.com/s?wd=";
    public static final String URL_ENCODING = "UTF-8";
    public static final String URL_ABOUT_BLANK = "about:blank";
    public static final String URL_SCHEME_ABOUT = "about:";
    public static final String URL_SCHEME_MAIL_TO = "mailto:";
    public static final String URL_SCHEME_FILE = "file://";
    public static final String URL_SCHEME_HTTP = "http://";
    public static final String URL_PREFIX_GOOGLE_PLAY = "www.google.com/url?q=";
    public static final String URL_SUFFIX_GOOGLE_PLAY = "&sa";
    public static final String URL_PREFIX_GOOGLE_PLUS = "plus.url.google.com/url?q=";
    public static final String URL_SUFFIX_GOOGLE_PLUS = "&rct";
    private static final long DOUBLE_CLICK_TIME = 2000;
    private EditText mSearchBar;
    private ImageView mRefesh;
    private FrameLayout mWebViewContainer;
    private WebView mMyWebView;
    private ImageView mGoBack;
    private ImageView mGoForward;
    private String mHomePageUrl = "https://www.baidu.com/";
    private String mKeyWord;
    private String mSearchUrl;
    private long pressTiem = 0;

    public static boolean isURL(String url) {
        if (url == null) {
            return false;
        }

        url = url.toLowerCase(Locale.getDefault());
        if (url.startsWith(URL_ABOUT_BLANK)
                || url.startsWith(URL_SCHEME_MAIL_TO)
                || url.startsWith(URL_SCHEME_FILE)) {
            return true;
        }

        String regex = "^((ftp|http|https|intent)?://)"                      // support scheme
                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp的user@
                + "(([0-9]{1,3}\\.){3}[0-9]{1,3}"                            // IP形式的URL -> 199.194.52.184
                + "|"                                                        // 允许IP和DOMAIN（域名）
                + "([0-9a-z_!~*'()-]+\\.)*"                                  // 域名 -> www.
                + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\."                    // 二级域名
                + "[a-z]{2,6})"                                              // first level domain -> .com or .museum
                + "(:[0-9]{1,4})?"                                           // 端口 -> :80
                + "((/?)|"                                                   // a slash isn't required if there is no file name
                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(url).matches();
    }

    public static String queryWrapper(Context context, String query) {
        // Use prefix and suffix to process some special links
        String temp = query.toLowerCase(Locale.getDefault());

        if (isURL(query)) {
            if (query.startsWith(URL_SCHEME_ABOUT) || query.startsWith(URL_SCHEME_MAIL_TO)) {
                return query;
            }

            if (!query.contains("://")) {
                query = URL_SCHEME_HTTP + query;
            }
            return query;
        }

        try {
            query = URLEncoder.encode(query, URL_ENCODING);
        } catch (UnsupportedEncodingException u) {
            u.printStackTrace();
        }

        return SEARCH_ENGINE_BAIDU + query;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        initWebView();

        //加载首页
        mMyWebView.loadUrl(mHomePageUrl);
        String url = mMyWebView.getUrl();
        Log.e("tianze", "onCreate: 现在的网址 == >" + url);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        mMyWebView.canGoBack();
        mMyWebView.canGoForward();
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
    }

    private void initEvent() {
        //为按钮添加点击事件
        mGoBack.setOnClickListener(this);
        mGoForward.setOnClickListener(this);
        mRefesh.setOnClickListener(this);

        //监听搜索框文本的变化
        mSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mKeyWord = mSearchBar.getText().toString();

                //判断用户输入的字符串是网址还是普通字符
                mSearchUrl = queryWrapper(MainActivity.this, mKeyWord);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //点击键盘上的搜索然后进行收缩
        mSearchBar.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    //隐藏键盘
                    ((InputMethodManager) Objects.requireNonNull(getSystemService(INPUT_METHOD_SERVICE)))
                            .hideSoftInputFromWindow(Objects.requireNonNull(MainActivity.this.getCurrentFocus())
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    //执行搜索
                    mMyWebView.loadUrl(mSearchUrl);
                    String url = mMyWebView.getUrl();
                    Log.e("tianze", "onCreate: 现在的网址 == >" + url);
                }
                return false;
            }
        });

    }

    private void initView() {
        //找到控件
        mSearchBar = findViewById(R.id.search_bar);
        mRefesh = findViewById(R.id.refesh);
        mWebViewContainer = findViewById(R.id.web_view_container);
        mGoBack = findViewById(R.id.go_back);
        mGoForward = findViewById(R.id.go_forward);

        //构建一个webView
        mMyWebView = new WebView(MainActivity.this);
        mMyWebView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        WebViewClient webViewClient = new WebViewClient() {
        };
        mMyWebView.setWebViewClient(webViewClient);
        mMyWebView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mWebViewContainer.addView(mMyWebView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back:
                mMyWebView.goBack();
                break;
            case R.id.go_forward:
                mMyWebView.goForward();
                break;
            case R.id.refesh:
                mMyWebView.reload();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if (mMyWebView.canGoBack()) {
            mMyWebView.goBack();
        } else {
            if (currentTime - pressTiem >= DOUBLE_CLICK_TIME) {
                Toast toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
                toast.setText("再按一次退出程序");
                toast.show();
                pressTiem = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
        }
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
        mWebViewContainer.removeAllViews();
        mMyWebView.destroy();
    }
}