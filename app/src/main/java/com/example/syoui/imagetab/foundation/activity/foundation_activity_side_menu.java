package com.example.syoui.imagetab.foundation.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.syoui.imagetab.R;

public class foundation_activity_side_menu extends AppCompatActivity {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation_side_menu);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        String[] categories = getResources().getStringArray(R.array.category);
        for(int k=0;k<categories.length;k++){
            adapter.add(categories[k]);
        }

        ListView listView = (ListView) findViewById(R.id.listview);
        // アダプターを設定します
        listView.setAdapter(adapter);


        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        (findViewById(R.id.drawer_button)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawers();
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer,
                (Toolbar) findViewById(R.id.tool_bar), R.string.drawer_open,
                R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                Log.i("log.", "onDrawerClosed");
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                Log.i("log.", "opened");
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                // ActionBarDrawerToggleクラス内の同メソッドにてアイコンのアニメーションの処理をしている。
                // overrideするときは気を付けること。
                super.onDrawerSlide(drawerView, slideOffset);
                Log.i("log.", "onDrawerSlide : " + slideOffset);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                // 表示済み、閉じ済みの状態：0
                // ドラッグ中状態:1
                // ドラッグを放した後のアニメーション中：2
                Log.i("log.", "onDrawerStateChanged  new state : " + newState);
            }


        };



        //mDrawer.setDrawerListener(mDrawerToggle);
        mDrawer.addDrawerListener(mDrawerToggle);

        // UpNavigationアイコン(アイコン横の<の部分)を有効に
        // NavigationDrawerではR.drawable.drawerで上書き
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        // UpNavigationを有効に
        //getActionBar().setHomeButtonEnabled(true);

    }



}





