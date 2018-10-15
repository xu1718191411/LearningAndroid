package com.example.syoui.imagetab;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.syoui.imagetab.blockchain.BlockChainActivity;
import com.example.syoui.imagetab.java_knowledge.DeviceInfo;
import com.example.syoui.imagetab.launch_others.LaunchOtherAppActivity;
import com.example.syoui.imagetab.record.RecordActivity;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class KnowledgeActivity extends AppCompatActivity {
    LinearLayout mLinearLayout;
    int tab = 1;
    int totalNum = 5;
    ImageView image1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);



        Button button = (Button) findViewById(R.id.button);
        temperateDeal();


        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tab++;
                System.out.println(tab);
                int imageNum = tab % totalNum;

                switch (imageNum) {
                    case 0:
                        image1.setImageResource(R.drawable.choba);
                        break;
                    case 1:
                        image1.setImageResource(R.drawable.rufi);
                        break;
                    case 2:
                        image1.setImageResource(R.drawable.rufu2);
                        break;
                    case 3:
                        image1.setImageResource(R.drawable.soro);
                        break;
                    case 4:
                        image1.setImageResource(R.drawable.soro2);
                        break;
                }
            }
        });

        Button goToRecord = (Button)findViewById(R.id.goToRecord);
        goToRecord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), RecordActivity.class);
                startActivity(intent);
            }
        });

        Button goTolaunchOtherApp = (Button)findViewById(R.id.launchOtherApps);
        goTolaunchOtherApp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), LaunchOtherAppActivity.class);
                startActivity(intent);
            }
        });


        Button goToSpeak = (Button) findViewById(R.id.speak);
        goToSpeak.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SpeakActivity.class);
                startActivity(intent);
            }
        });


        Button slide = (Button) findViewById(R.id.slide);
        slide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SlideActivity.class);
                startActivity(intent);
            }
        });

        Button dbButton = (Button) findViewById(R.id.db);
        dbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SQLiteActivity.class);
                startActivity(intent);
            }
        });


        Button youtubeButton = (Button) findViewById(R.id.youtubeVideo);
        youtubeButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), YoutubeActivity.class);
                startActivity(intent);
            }
        });

        youtubeButton.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(getApplication(),videoActivity.class);
                startActivity(intent);
                return false;
            }
        });

        Button simpleBlockChain = (Button) findViewById(R.id.simpleBlockChain);
        simpleBlockChain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),BlockChainActivity.class);
                startActivity(intent);
            }
        });

        Button download = (Button) findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),DownloadActivity.class);
                startActivity(intent);
            }
        });


        getHtmlFromUrl("https://goo.gl/maps/7PfMVtvicbG2");
    }


    private static void temperateDeal(){
        ArrayList<String> arrayResult = new ArrayList<String>();
        String str = "ここにアクセスしてください　jjhttp://www.google.co.jp ここに来てくださいbgjhghhttp://www.google.comhttp://www.gforce.comhttp://www.cctv.com.cn";
        String res[] = str.split("(\\s|\\t|\\n)+");
        for(int i=0;i<res.length;i++){
            String _res = res[i];
            Pattern pattern = Pattern.compile("((?:https?):\\/\\/((?!https?).)+)",Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(_res);
            while(matcher.find()){
                int countRes = matcher.groupCount();
                String result = matcher.group(1);
                arrayResult.add(result);
            }
        }

    }







    private void getHtmlFromUrl(String url){
        final WebView wb = new WebView(getApplicationContext());
        wb.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String getHTML = "javascript:android.getHTML(document.documentElement.outerHTML);";
                wb.loadUrl(getHTML);
            }
        });

        wb.setVisibility(View.GONE);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.addJavascriptInterface(new JavascriptInterface(), "android");
        wb.loadUrl(url);
    }


    private class JavascriptInterface{

        public JavascriptInterface() {

        }

        @android.webkit.JavascriptInterface
        public void getHTML(String html){
            Log.d("DENSODENSODENSO",html);
        }
    }

    public void getToInfoActivity(View v){
        Intent intent = new Intent(this, DeviceInfo.class);
        startActivity(intent);

    }
}
