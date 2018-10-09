package com.example.syoui.imagetab.ServiceActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.syoui.imagetab.R;
import com.example.syoui.imagetab.service.MyBindService;
import com.example.syoui.imagetab.service.MyServiceInterface;

public class BindServiceActivity extends AppCompatActivity {
    private MyServiceInterface msi;
    private ServiceConnection conn;
    private MyBindService mMyBindService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service);


        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i("Service","successfully binded");
                msi = (MyServiceInterface) service;
                mMyBindService = msi.getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        Intent intent = new Intent(this,MyBindService.class);
        bindService(intent,conn,BIND_AUTO_CREATE);
    }

    public void doSomeThing(View view){
        showToast(msi.add(4,5) + "");

    }

    private void showToast(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }

    public void doSomeThingInService(View view){
        if(mMyBindService != null){
            showToast(mMyBindService.substract(9,1) + "");
        }
    }
}
