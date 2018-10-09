package com.example.syoui.imagetab;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.syoui.imagetab.Thread.ThreadInterrunptActivity;
import com.example.syoui.imagetab.foundation.database.select;
import com.example.syoui.imagetab.foundation.fragment.FragmentListenerActivity;
import com.example.syoui.imagetab.foundation.popup.alert;
import com.example.syoui.imagetab.foundation.popup.confirm;
import com.example.syoui.imagetab.foundation.popup.customize;
import com.example.syoui.imagetab.foundation.popup.listViewPopUp;
import com.example.syoui.imagetab.ServiceActivity.BindServiceActivity;
import com.example.syoui.imagetab.ServiceActivity.StartServiceActivity;

import java.util.ArrayList;

public class FoundationListViewActivity extends AppCompatActivity {

    /**
     * アクティビティ生成時に呼ばれる
     * @param savedInstanceState
     */
    
    private int type;
    private int category;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawer;
    private SampleListAdapter adapter;
    private ListView mainListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_listview);

        // レイアウトからリストビューを取得
        mainListView = (ListView)findViewById(R.id.main_list_view);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawer,null, R.string.drawer_open,
                R.string.drawer_close);
        mDrawer.addDrawerListener(mDrawerToggle);

        ArrayAdapter<String> mainListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        ListView sideBarListView = (ListView) findViewById(R.id.side_list_view);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getInt("type");
            category = extras.getInt("category");
        }else{
            type = 0;
            category = 0;
        }


        String[] categories = getResources().getStringArray(R.array.category);

        if(category == 1){
            for(int k=0;k<categories.length;k++){
                mainListAdapter.add(categories[k]);
            }
        }


        sideBarListView.setAdapter(mainListAdapter);
        // アダプターを設定します


        sideBarListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    updateMainListView(position);
                    mDrawer.closeDrawers();
            }
        });

        String[] subItems = getItemString(type);
        // リストビューに表示する要素を設定
        ArrayList<SampleListItem> listItems = new ArrayList<>();
        listItems.clear();
        for (int i = 0; i < subItems.length; i++) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);  // 今回はサンプルなのでデフォルトのAndroid Iconを利用
            SampleListItem item = new SampleListItem(bmp, subItems[i]);
            listItems.add(item);
        }

        // 出力結果をリストビューに表示
        adapter = new SampleListAdapter(this, R.layout.samplelist_item, listItems);


        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                System.out.println("position is ......."+position);
                Intent intent = new Intent(getApplication(),customize.class);

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
//                            intent = new Intent(getApplication(),MapsActivity.class);
                            break;
                        case 6:
                            intent = new Intent(getApplication(),com.example.syoui.imagetab.foundation.activity.foundation_activity_layout.class);
                            break;
                        case 7:
                            intent = new Intent(getApplication(),com.example.syoui.imagetab.foundation.activity.foundation_activity_side_menu.class);
                            break;
                        case 8:
                            intent = new Intent(getApplication(),com.example.syoui.imagetab.foundation.activity.foundation_activity_get_attribute_from_xml.class);
                            break;
                        case 9:
                            intent = new Intent(getApplication(),com.example.syoui.imagetab.foundation.activity.foundation_activity_progress_dialog.class);
                            break;
                        default:
                            intent = new Intent(getApplication(),com.example.syoui.imagetab.foundation.activity.foundation_activity_layout.class);
                            break;
                    }
                }else if(type == 1){
                    //Maps
                    switch(position){
                        case 0:
                            intent = new Intent(getApplication(),com.google.maps.GoogleMapActivity.class);
                            break;
                        case 1:
                            intent = new Intent(getApplication(),com.google.maps.GoogleMap2Activity.class);
                            break;
                        default:
                            intent = new Intent(getApplication(),com.google.maps.GoogleMap3Activity.class);
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
                        case 2:
                            intent = new Intent(getApplication(),com.example.syoui.imagetab.foundation.camera.BarCodeActivity.class);
                            break;
                        default:
                            intent = new Intent(getApplication(),com.example.syoui.imagetab.foundation.camera.simpleCamera.class);
                            break;
                    }
                }else if(type == 4){
                    switch (position){
                        case 0:
                            intent = new Intent(getApplication(),alert.class);
                            break;
                        case 1:
                            intent = new Intent(getApplication(),confirm.class);
                            break;
                        case 2:
                            intent = new Intent(getApplication(),customize.class);
                            break;
                        default:
                            intent = new Intent(getApplication(),listViewPopUp.class);
                            break;
                    }
                }else if(type == 5){

                    switch (position){
                        case 0:
                            intent = new Intent(getApplication(),FragmentListenerActivity.class);
                            break;
                        default:
                            break;
                    }

                } else if(type == 6){
                    switch (position){
                        default:
                            intent = new Intent(getApplication(),select.class);
                            break;
                    }
                } else if(type == 7){
                    switch (position){
                        case 0:
                            intent = new Intent(getApplication(),ThreadInterrunptActivity.class);
                            break;
                        default:
                            intent = new Intent(getApplication(),ThreadInterrunptActivity.class);
                            break;
                    }
                }else if(type == 14){
                    switch (position){
                        case 0:
                            intent = new Intent(getApplication(), StartServiceActivity.class);
                            break;
                        case 1:
                            intent = new Intent(getApplication(), BindServiceActivity.class);
                            break;
                        default:
                            intent = new Intent(getApplication(), StartServiceActivity.class);
                            break;
                    }

                }else{
                    intent = new Intent(getApplication(),com.example.syoui.imagetab.foundation.activity.foundation_activity_ble.class);
                }


                startActivity(intent);

            }
        });


        mainListView.setAdapter(adapter);


    }


    private String[] getItemString(int pos){
        String[] itemString = {};
        if(pos == 0){
            itemString = getResources().getStringArray(R.array.type0);
        }else if(pos == 1){
            itemString = getResources().getStringArray(R.array.type1);
        }else if(pos == 2){
            itemString = getResources().getStringArray(R.array.type2);
        }else if(pos == 3){
            itemString = getResources().getStringArray(R.array.type3);
        }else if(pos == 4){
            itemString = getResources().getStringArray(R.array.type4);
        }else if(pos == 5){
            itemString = getResources().getStringArray(R.array.type5);
        }else if(pos == 6){
            itemString = getResources().getStringArray(R.array.type6);
        }else if(pos == 7){
            itemString = getResources().getStringArray(R.array.type7);
        }else if(pos == 14){
            itemString = getResources().getStringArray(R.array.type14);
        }else{
            itemString = getResources().getStringArray(R.array.type0);
        }

        return itemString;
    }


    private void updateMainListView(int pos){
        adapter.clear();
        String[] subItems = getItemString(pos);
        for (int i = 0; i < subItems.length; i++) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);  // 今回はサンプルなのでデフォルトのAndroid Iconを利用
            SampleListItem item = new SampleListItem(bmp, subItems[i]);
            adapter.add(item);
        }
        mainListView.setAdapter(adapter);
    }

}