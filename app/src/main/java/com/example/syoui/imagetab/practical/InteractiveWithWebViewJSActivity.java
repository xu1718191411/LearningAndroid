package com.example.syoui.imagetab.practical;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.syoui.imagetab.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class InteractiveWithWebViewJSActivity extends AppCompatActivity {

    WebView wb = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactive_with_web_view_js);

        wb = (WebView) findViewById(R.id.webView);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.addJavascriptInterface(new JavascriptInterface(this.getApplicationContext()),"android");
        copyFromAssetsToCache("search.min.js");
        copyFromAssetsToCache("search.html");

        readFromAssetsJS();
        wb.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String useFunction = "javascript:kkm()";
                wb.loadUrl(useFunction);

                String test = "test";
            }
        });




    }


    private void copyFromAssetsToCache(String fileName){
        try{
            InputStream input = getAssets().open(fileName);

            FileOutputStream fileOutputStream = new FileOutputStream(new File(getCacheDir().getAbsoluteFile() + "/" + fileName));
            byte[] buffer = new byte[1024];
            int length = 0;

            while ((length = input.read(buffer)) >= 0) {
                fileOutputStream.write(buffer, 0, length);
            }

            input.close();
            fileOutputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void readFromAssetsJS(){
        try{

            wb.loadUrl("file:"+this.getCacheDir().getAbsolutePath() +"/" + "search.html");

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private class JavascriptInterface{
        Context mContext = null;
        public JavascriptInterface(Context c) {
            mContext = c;
        }

        @android.webkit.JavascriptInterface
        public void useFunctions(String res){
            Log.d("DALIDALIDALIDALI",res);
        }
    }
}