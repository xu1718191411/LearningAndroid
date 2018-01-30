package com.example.syoui.imagetab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class videoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        WebView wb = (WebView) findViewById(R.id.webView);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.loadUrl("http://172.21.32.104:8080/youtube.html");

    }
}
