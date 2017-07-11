package com.example.syoui.imagetab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.syoui.imagetab.R.id;

import java.util.ArrayList;


public class listViewActivity extends AppCompatActivity {


    private  ArrayList<String> foundation = new ArrayList<String>();
    private  ArrayList<String> superior = new ArrayList<String>();
    private int category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        Bundle extras = getIntent().getExtras();

        String[] categories = getResources().getStringArray(R.array.category);
        String[] category2 = getResources().getStringArray(R.array.category2);


        if (extras != null) {
            category = extras.getInt("category");
            System.out.println("passing data is ......"+category);
            //The key argument here must match that used in the other activity

            if(category == 1){
                for(int k=0;k<categories.length;k++){
                    adapter.add(categories[k]);
                }
            }else{
                for(int k=0;k<category2.length;k++){
                    adapter.add(category2[k]);
                }
            }

        }else{
            System.out.println("passing data is ......");
            adapter.add("GUITAR");
            adapter.add("NETFLIX");
            adapter.add("BREAKING DANCE");
        }


        ListView listView = (ListView) findViewById(id.listview);
        // アダプターを設定します
        listView.setAdapter(adapter);
        // リストビューのアイテムがクリックされた時に呼び出されるコールバックリスナーを登録します
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                System.out.println(position);
                ListView listView = (ListView) parent;
                // クリックされたアイテムを取得します
                String item = (String) listView.getItemAtPosition(position);
                Toast.makeText(listViewActivity.this, item, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplication(), subListViewActivity.class);

                intent.putExtra("category", category);
                intent.putExtra("type", position);

                startActivity(intent);

            }
        });
    }



}