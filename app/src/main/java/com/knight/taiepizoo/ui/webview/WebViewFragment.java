package com.knight.taiepizoo.ui.webview;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;

import com.knight.taiepizoo.R;
import com.knight.taiepizoo.databinding.WebViewFragmentBinding;
import com.knight.taiepizoo.ui.common.BaseFragment;

public class WebViewFragment extends BaseFragment<WebViewFragmentViewModel, WebViewFragmentBinding> {
    private static final String TAG = WebViewFragment.class.getSimpleName();
    private static final String KEY_URL = "KEY_URL";


    public static Bundle buildArgument(String url) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_URL, url);
        return bundle;
    }

    @Override
    public int getLayoutId() {
        return R.layout.web_view_fragment;
    }

    @Override
    public WebViewFragmentViewModel onCreateViewModel() {
        return new WebViewFragmentViewModel();
    }

    @Override
    protected void initView() {
        if (getArguments() == null) return;
        String url = getArguments().getString(KEY_URL);
        WebView webView = getDataBinding().webView;
        webView.setWebViewClient(new InnerWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }


    private class InnerWebViewClient extends SslErrorHandleWebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d(TAG, "url --> " + url);
            return false;
        }

        @Override
        public void onCancel() {
            Activity activity = getActivity();
            if (activity == null) return;
            activity.onBackPressed();

        }
    }
}
