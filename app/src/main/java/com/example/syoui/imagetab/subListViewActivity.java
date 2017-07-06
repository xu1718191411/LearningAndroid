package com.example.syoui.imagetab;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class subListViewActivity extends AppCompatActivity {

    /**
     * アクティビティ生成時に呼ばれる
     * @param savedInstanceState
     */

    private int category;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_listview);

        // レイアウトからリストビューを取得
        ListView listView = (ListView)findViewById(R.id.sample_listview);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            category = extras.getInt("category");
            category = extras.getInt("type");
        }else{

        }

        String[] arrString = getResources().getStringArray(R.array.type1);


        // リストビューに表示する要素を設定
        ArrayList<SampleListItem> listItems = new ArrayList<>();
        for (int i = 0; i < arrString.length; i++) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);  // 今回はサンプルなのでデフォルトのAndroid Iconを利用
            SampleListItem item = new SampleListItem(bmp, arrString[i]);
            listItems.add(item);
        }

        // 出力結果をリストビューに表示
        SampleListAdapter adapter = new SampleListAdapter(this, R.layout.samplelist_item, listItems);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                System.out.println("position is ......."+position);
                Intent intent;
                switch (position){
                    case 0:
                        intent = new Intent(getApplication(),com.example.syoui.imagetab.foundation.activity.foundation_activity_listView.class);
                        break;
                    case 1:
                        intent = new Intent(getApplication(),com.example.syoui.imagetab.foundation.activity.foundation_activity_foldingListView.class);
                        break;
                    case 2:
                        intent = new Intent(getApplication(),com.example.syoui.imagetab.foundation.activity.foundation_activity_launcher.class);
                        break;
                    default:
                        intent = new Intent(getApplication(),com.example.syoui.imagetab.foundation.activity.foundation_activity_listView.class);
                        break;
                }


                startActivity(intent);

            }
        });




        listView.setAdapter(adapter);


    }

}