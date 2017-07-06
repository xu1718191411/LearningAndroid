package com.example.syoui.imagetab.foundation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import com.example.syoui.imagetab.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class foundation_activity_foldingListView extends AppCompatActivity {

    private static final int ITEM_NUM = 3;
    private static final int SUBITEM_NUM = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation_folding_list_view);



        // アイテムのリスト
            List<Map<String, String>> itemList = new ArrayList<Map<String, String>>();
        for(int i=0; i<ITEM_NUM; i++) {
            Map<String, String> item = new HashMap<String, String>();
            item.put("ITEM", "Item " + (i+1));
            itemList.add(item);
        }

        // 各アイテムのサブアイテムのリスト
        List<List<Map<String, String>>> allSubItemList = new ArrayList<List<Map<String, String>>>();
        // サブアイテム, 詳細のリスト
        for(int i=0; i<ITEM_NUM; i++) {
            List<Map<String, String>> subItemList = new ArrayList<Map<String, String>>();
                for(int j=0; j<SUBITEM_NUM; j++) {
                    Map<String, String> subItem = new HashMap<String, String>();
                        subItem.put("SUBITEM", "SubItem " + (i+1) + "-" + (j+1));
                        subItem.put("DETAIL", "Detail " + (i+1) + "-" + (j+1));
                        subItemList.add(subItem);
                }
                allSubItemList.add(subItemList);
            }


        // アダプタを作成
        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(this, itemList,android.R.layout.simple_expandable_list_item_1,new String[] {"ITEM"},new int[]{android.R.id.text1},allSubItemList, android.R.layout.simple_expandable_list_item_2, new String[]{"SUBITEM", "DETAIL"},new int[]{android.R.id.text1, android.R.id.text2});

        ExpandableListView listView = (ExpandableListView)findViewById(R.id.listview);
        // アダプターを設定します
        listView.setAdapter(adapter);




    }
}
