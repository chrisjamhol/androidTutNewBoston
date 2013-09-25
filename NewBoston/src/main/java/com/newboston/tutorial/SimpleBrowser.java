package com.newboston.tutorial;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Chris on 23.09.13.
 */
public class SimpleBrowser extends Activity implements View.OnClickListener {
    EditText url;
    WebView ourBrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplebrowser);

        ourBrow = (WebView) findViewById(R.id.wvBrowser);

        url = (EditText) findViewById(R.id.etUrl);

        Button go = (Button) findViewById(R.id.bGo);
        Button back = (Button) findViewById(R.id.bBack);
        Button refresh = (Button) findViewById(R.id.bRefresh);
        Button forward = (Button) findViewById(R.id.bForward);
        Button clearHistory = (Button) findViewById(R.id.bHistory);
        go.setOnClickListener(this);
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);
        forward.setOnClickListener(this);
        clearHistory.setOnClickListener(this);

        ourBrow.getSettings().setJavaScriptEnabled(true);
        ourBrow.getSettings().setUseWideViewPort(true);
        ourBrow.getSettings().setLoadWithOverviewMode(true);
        ourBrow.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        try{
            ourBrow.loadUrl("http://google.com");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bGo:
                ourBrow.loadUrl(url.getText().toString());
                //hiding keyboard after using editText
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(url.getWindowToken(),0);
                break;
            case R.id.bBack:
                if(ourBrow.canGoBack())
                    ourBrow.goBack();
                break;
            case R.id.bForward:
                if(ourBrow.canGoForward())
                    ourBrow.goForward();
                break;
            case R.id.bRefresh:
                ourBrow.reload();
                break;
            case R.id.bHistory:
                ourBrow.clearHistory();
                break;
        }
    }


}
