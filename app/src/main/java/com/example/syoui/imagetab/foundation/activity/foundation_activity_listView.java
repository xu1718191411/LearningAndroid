package com.example.syoui.imagetab.foundation.activity;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.syoui.imagetab.R;

public class foundation_activity_listView extends AppCompatActivity {
    private static final int ITEM_NUM = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation_list_view);

        String[] items = new String[ITEM_NUM];

        for(int i=0; i<ITEM_NUM; i++) {
            items[i] = new String("Item" + (i+1));
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        ListView listView = (ListView) findViewById(R.id.listview);
        // アダプターを設定します
        listView.setAdapter(adapter);


    }

}



