package com.example.syoui.imagetab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TutorialSectionActivity extends AppCompatActivity {

    private List<Map<String,String>> tutorial_title_list = new ArrayList<>();
    private List<List<Map<String,String>>> tutorial_section_list = new ArrayList<>();
    static String TUTORIALITEM = "TUTORIALITEM";
    static String TUTORIALCHILDRENITEM = "TUTORIALCHILDRENITEM";
    private ExpandableListView expandable_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        String[] tutorial_sections_elements = getResources().getStringArray(R.array.tutorial_section);
        String[] tutorial_section_activity_elements = getResources().getStringArray(R.array.tutorial_section_activity);
        initialUI();

        for(int i=0;i<tutorial_sections_elements.length;i++)
        {
            Map<String,String> tutorial_title = new HashMap<>();
            tutorial_title.put(TUTORIALITEM,tutorial_sections_elements[i]);
            tutorial_title_list.add(tutorial_title);
        }



        List<Map<String,String>> activity_elements_list = new ArrayList<>();

        for(int i=0;i<tutorial_section_activity_elements.length;i++)
        {
            Map<String,String> tutorial_element = new HashMap<>();
            tutorial_element.put(TUTORIALCHILDRENITEM,tutorial_section_activity_elements[i]);
            activity_elements_list.add(tutorial_element);
        }
        
        //https://blog.csdn.net/KevinWXT/article/details/78185544
        tutorial_section_list.add(activity_elements_list);


        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(this, tutorial_title_list,android.R.layout.simple_expandable_list_item_1,new String[] {TUTORIALITEM},new int[]{android.R.id.text1},tutorial_section_list, android.R.layout.simple_expandable_list_item_2, new String[]{TUTORIALCHILDRENITEM, "DETAIL"},new int[]{android.R.id.text1, android.R.id.text2});

        expandable_list.setAdapter(adapter);


    }

    private void initialUI(){
        expandable_list = findViewById(R.id.expandable_list);

    }


}
