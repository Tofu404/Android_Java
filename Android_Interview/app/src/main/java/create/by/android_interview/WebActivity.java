package create.by.android_interview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends AppCompatActivity {

    @BindView(R.id.web_view)
    WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//这行代码一定要在setContentView之前，不然会闪退
        setContentView(R.layout.activity_web);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        init();
        mWebView.loadUrl(url);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        //初始化并设置参数
        mWebView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        WebSettings settings = mWebView.getSettings();

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
        mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        //隐藏垂直滚动条
        mWebView.setVerticalScrollBarEnabled(false);

        //隐藏水平滚动条
        mWebView.setHorizontalScrollBarEnabled(false);

        WebChromeClient webChromeClient = new WebChromeClient();
        mWebView.setWebChromeClient(webChromeClient);

        WebViewClient webViewClient = new WebViewClient();
        mWebView.setWebViewClient(webViewClient);
    }

    public static void start(Context context, String url) {
        Intent starter = new Intent(context, WebActivity.class);
        starter.putExtra("url", url);
        context.startActivity(starter);
    }
}
