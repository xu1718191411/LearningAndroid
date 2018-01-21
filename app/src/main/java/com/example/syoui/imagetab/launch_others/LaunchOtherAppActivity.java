package com.example.syoui.imagetab.launch_others;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.syoui.imagetab.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class LaunchOtherAppActivity extends AppCompatActivity {
    private ArrayAdapter<LaunchItem> launchItemsAdapter = null;
    private ArrayList<LaunchItem> launchItems = new ArrayList<LaunchItem>();
    private LaunchListAdapter mListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_other_app);


        PackageManager pm = getPackageManager();
        List<PackageInfo> pckInfoList = pm.getInstalledPackages(
                PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES);
        for(PackageInfo pckInfo : pckInfoList){
            LaunchItem oLaunchItem = null;
            if(pm.getLaunchIntentForPackage(pckInfo.packageName) != null){
                String packageName = pckInfo.packageName;
                String className = pm.getLaunchIntentForPackage(pckInfo.packageName).getComponent().getClassName()+"";
                Log.i("起動可能なパッケージ名",packageName);
                Log.i("起動可能なクラス名",className);
                oLaunchItem = new LaunchItem(true,packageName,className);
            }else{
                Log.i("----------起動不可能なパッケージ名",pckInfo.packageName);
                oLaunchItem = new LaunchItem(false,pckInfo.packageName,null);
            }
            launchItems.add(oLaunchItem);
        }

        mListAdapter = new LaunchListAdapter(this.getApplicationContext());
        mListAdapter.setmArrayList(launchItems);
        mListAdapter.setLaunchAppListener(new LaunchListAdapter.LaunchAppListener() {
            @Override
            public void onLaunch(Intent intent) {
                startActivity(intent);
            }
        });

        ListView launchListLayout = (ListView) findViewById(R.id.launchListLayout);
        launchListLayout.setAdapter(mListAdapter);

    }

}


