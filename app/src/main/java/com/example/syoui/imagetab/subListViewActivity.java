package com.example.syoui.imagetab;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.syoui.imagetab.foundation.map.MapsActivity;

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
            type = extras.getInt("type");
        }else{

        }

        String[] arrString = {};
        if(type == 0){
            arrString = getResources().getStringArray(R.array.type1);
        }else if(type == 1){
            arrString = getResources().getStringArray(R.array.type2);
        }else if(type == 2){
            arrString = getResources().getStringArray(R.array.type3);
        }else{
            arrString = getResources().getStringArray(R.array.type4);
        }




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

                if(type == 0){
                    //Activity
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
                        case 3:
                            intent = new Intent(getApplication(),com.example.syoui.imagetab.foundation.activity.foundation_activity_setting.class);
                            break;
                        case 4:
                            intent = new Intent(getApplication(),com.example.syoui.imagetab.foundation.activity.foundation_activity_icon_listView.class);
                            break;
                        case 5:
                            intent = new Intent(getApplication(),MapsActivity.class);
                            break;
                        case 6:
                            intent = new Intent(getApplication(),com.example.syoui.imagetab.foundation.activity.foundation_activity_layout.class);
                            break;
                        case 7:
                            intent = new Intent(getApplication(),com.example.syoui.imagetab.foundation.activity.foundation_activity_side_menu.class);
                            break;
                        default:
                            intent = new Intent(getApplication(),com.example.syoui.imagetab.foundation.activity.foundation_activity_layout.class);
                            break;
                    }
                }else if(type == 1){
                    //Maps
                    switch(position){
                        case 0:
                            intent = new Intent(getApplication(),MapsActivity.class);
                            break;
                        default:
                            intent = new Intent(getApplication(),com.example.syoui.imagetab.foundation.activity.foundation_activity_listView.class);
                            break;
                    }
                }else if(type == 2){
                    switch(position){
                        case 0:
                            intent = new Intent(getApplication(),com.example.syoui.imagetab.foundation.sensor.sensor_list.class);
                            break;
                        case 1:
                            intent =new Intent(getApplication(),com.example.syoui.imagetab.foundation.sensor.acceleration.class);
                            break;
                        case 2:
                            intent =new Intent(getApplication(),com.example.syoui.imagetab.foundation.sensor.tendency.class);
                            break;
                        case 3:
                            intent =new Intent(getApplication(),com.example.syoui.imagetab.foundation.sensor.magnetism.class);
                            break;
                        case 4:
                            intent =new Intent(getApplication(),com.example.syoui.imagetab.foundation.sensor.proximity.class);
                            break;
                        case 5:
                            intent =new Intent(getApplication(),com.example.syoui.imagetab.foundation.sensor.light.class);
                            break;
                        default:
                            intent =new Intent(getApplication(),com.example.syoui.imagetab.foundation.sensor.magnetism.class);
                            break;
                    }
                }else if(type == 3){
                    switch (position){
                        case 0:
                            intent = new Intent(getApplication(),com.example.syoui.imagetab.foundation.camera.simpleCamera.class);
                            break;
                        case 1:
                            intent = new Intent(getApplication(),com.example.syoui.imagetab.foundation.camera.camera_preview.class);
                            break;
                        default:
                            intent = new Intent(getApplication(),com.example.syoui.imagetab.foundation.sensor.sensor_list.class);
                            break;
                    }
                }else{
                    intent = new Intent(getApplication(),MapsActivity.class);
                }




                startActivity(intent);

            }
        });


        listView.setAdapter(adapter);


    }

}