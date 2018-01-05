package com.example.syoui.imagetab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;

public class SlideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        WebView wb = (WebView) findViewById(R.id.web);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.setWebViewClient(new WebViewClient());
        wb.loadUrl("http://www.google.com");

        ListView mList = (ListView) findViewById(R.id.mList);

        ArrayList<String> data = new ArrayList<>();
        data.add("apple juice");
        data.add("orange juice");
        data.add("lemon juice");
        data.add("jinja highball");

        ArrayAdapter<String> ad = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        mList.setAdapter(ad);


        TextView slideTitle = (TextView) findViewById(R.id.slideTitle);

        SlidingUpPanelLayout slideEnginee = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        slideEnginee.setDragView(slideTitle);
    }
}
