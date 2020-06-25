package com.tianze.mybrowser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

class MyWebView extends WebView {

    private Context mContext;
    private EditText mSearchBar;

    public MyWebView(Context context) {
        super(context);
        this.mContext = context;
    }

    public void setSearchBar(EditText searchBar) {
        mSearchBar = searchBar;
    }

    @Override
    public synchronized void loadUrl(String url) {

        if (url == null || url.trim().isEmpty()) {
            Toast toast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
            toast.setText("无法加载该地址");
            toast.show();
            return;
        }
        url = MyUtils.queryWrapper(url.trim());
        super.loadUrl(url);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mSearchBar.clearFocus();
        }
        return super.onTouchEvent(event);
    }
}
