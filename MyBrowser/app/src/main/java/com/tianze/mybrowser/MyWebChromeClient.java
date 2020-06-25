package com.tianze.mybrowser;

import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebHistoryItem;
import android.webkit.WebView;
import android.widget.EditText;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MyWebChromeClient extends WebChromeClient {

    private static final int FINSH_LOAD = 100;
    private EditText mSearchBar;
    private WebView mMyWebView;
    private SwipeRefreshLayout mRefresh;
    public String urlTitle = "";

    public void setSearchBar(EditText searchBar) {
        mSearchBar = searchBar;
    }

    public void setMyWebView(WebView myWebView) {
        mMyWebView = myWebView;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        mSearchBar.setText("loading……（" + newProgress + "%）");
        if (newProgress >= FINSH_LOAD) {
            setSearchBarTitle();
        }
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        urlTitle = title;
    }

    public void setSearchBarTitle() {
        if (mMyWebView != null) {
            WebBackForwardList webBackForwardList = mMyWebView.copyBackForwardList();
            WebHistoryItem currentItem = webBackForwardList.getCurrentItem();
            if (currentItem != null) {
                mSearchBar.setText(currentItem.getTitle());
            }
        }
    }
}
