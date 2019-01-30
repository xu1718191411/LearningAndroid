package com.example.syoui.imagetab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpandableListActivity extends AppCompatActivity {

    private ExpandableListView expandableList;
    private static String ITEMNAME = "ITEMNAME";
    private static String CHILDITEMNAME = "CHILDRENITEMNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list);
        expandableList = findViewById(R.id.expandable_list);


        List<Map<String,String>> parentList = new ArrayList<Map<String,String>>();
        String[] parentListElements = {"Activity","Service","BroadCast Receiver","Content Provider"};

        for(int i=0;i<parentListElements.length;i++){
            Map<String,String> hash = new HashMap<String,String>();
            hash.put(ITEMNAME,parentListElements[i]);
            parentList.add(hash);
        }

        List<List<Map<String,String>>> childrenList = new ArrayList<>();


        //https://blog.csdn.net/KevinWXT/article/details/78185544
        String[] childrenListElementsActivity = {"Intent","LifeCycle","LaunchMode","IntentFilter"};

        List<Map<String,String>> activityChildrenItem = new ArrayList<>();

        for(int i=0;i<childrenListElementsActivity.length;i++){
            Map<String,String> hash = new HashMap<String,String>();
            hash.put(CHILDITEMNAME,childrenListElementsActivity[i]);
            activityChildrenItem.add(hash);
        }

        childrenList.add(activityChildrenItem);

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(this, parentList,android.R.layout.simple_expandable_list_item_1,new String[] {ITEMNAME},new int[]{android.R.id.text1},childrenList, android.R.layout.simple_expandable_list_item_2, new String[]{CHILDITEMNAME, "DETAIL"},new int[]{android.R.id.text1, android.R.id.text2});

        expandableList.setAdapter(adapter);
    }






}
