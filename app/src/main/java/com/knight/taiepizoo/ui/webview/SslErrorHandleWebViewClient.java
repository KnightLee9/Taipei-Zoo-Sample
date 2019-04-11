package com.knight.taiepizoo.ui.webview;

import android.content.DialogInterface;
import android.net.http.SslError;
import android.support.v7.app.AlertDialog;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.knight.taiepizoo.R;



public abstract class SslErrorHandleWebViewClient extends WebViewClient {
    @Override
    public void onReceivedSslError(final WebView view, final SslErrorHandler handler,
                                   SslError error) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage(R.string.ssl_certification_error);
        builder.setPositiveButton(R.string.app_name , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handler.proceed();
            }
        });
        builder.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handler.cancel();
                onCancel();
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    public abstract void onCancel();
}