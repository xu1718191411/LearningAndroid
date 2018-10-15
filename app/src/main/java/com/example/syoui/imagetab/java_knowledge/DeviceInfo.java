package com.example.syoui.imagetab.java_knowledge;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.syoui.imagetab.R;
import com.example.syoui.imagetab.Util;
import com.example.syoui.imagetab.ViewUtil;

public class DeviceInfo extends AppCompatActivity {

    private ListView deviceInfoList;
    private ArrayAdapter<String> deviceInfoAdapter;
    private Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);
        mActivity = this;
        deviceInfoList = (ListView) findViewById(R.id.deviceInfoList);

        String[] deviceInfoArrValue = getResources().getStringArray(R.array.deviceinfo);
        deviceInfoAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1, Util.converStringArray2StringArrayList(deviceInfoArrValue));
        deviceInfoList.setAdapter(deviceInfoAdapter);
        deviceInfoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    ViewUtil.dialog(mActivity,getOSLanguage());
                }
            }
        });
    }



    private String getOSLanguage(){
        String language = null;
        if(Build.VERSION.SDK_INT >= 24){
            language = Resources.getSystem().getConfiguration().getLocales().get(0).getLanguage();
        }else{
            language =  Resources.getSystem().getConfiguration().locale.getLanguage();
        }

        return language;
    }


}
