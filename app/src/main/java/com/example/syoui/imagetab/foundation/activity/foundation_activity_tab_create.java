package com.example.syoui.imagetab.foundation.activity;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

import com.example.syoui.imagetab.R;

public class foundation_activity_tab_create extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation_tab_create);

        // TabHostのインスタンスを取得

        TabHost tabs = getTabHost();
        // レイアウトを設定
        LayoutInflater.from(this).inflate(R.layout.activity_foundation_tab_create, tabs.getTabContentView(), true);
        // タブシートの設定
        TabHost.TabSpec tab01 = tabs.newTabSpec("TabSheet1");
        tab01.setIndicator("TabSheet1");
        tab01.setContent(R.id.sheet01_id);
        tabs.addTab(tab01);
        TabHost.TabSpec tab02 = tabs.newTabSpec("TabSheet2");
        tab02.setIndicator("TabSheet2");
        tab02.setContent(R.id.sheet02_id);
        tabs.addTab(tab02);
        TabHost.TabSpec tab03 = tabs.newTabSpec("TabSheet3");
        tab03.setIndicator("TabSheet3");
        tab03.setContent(R.id.sheet03_id);
        tabs.addTab(tab03);
        // 初期表示のタブ設定


        TabHost.TabSpec tab04 = tabs.newTabSpec("TabSheet4");
        tab04.setIndicator("TabSheet4");
        tab04.setContent(R.id.sheet04_id);
        tabs.addTab(tab04);


        tabs.setCurrentTab(0);

    }

}
