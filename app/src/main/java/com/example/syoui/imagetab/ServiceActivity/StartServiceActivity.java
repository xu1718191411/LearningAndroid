package com.example.syoui.imagetab.ServiceActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.syoui.imagetab.R;
import com.example.syoui.imagetab.service.MyStartService;

public class StartServiceActivity extends AppCompatActivity {

    private Button startService;
    private Button stopService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service);

        startService = (Button) findViewById(R.id.startService);
        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService();
            }
        });

        stopService = (Button) findViewById(R.id.stopService);
        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService();
            }
        });

        registerReceiver();

    }

    private void startService(){
        Intent intent = new Intent(this,MyStartService.class);
        startService(intent);
    }

    private void stopService(){
        Intent intent = new Intent(this,MyStartService.class);
        stopService(intent);
    }

    private void registerReceiver(){
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyStartService.START_SERVICE_ACTION);
        this.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                    if(intent != null){
                        Bundle bundle = intent.getExtras();
                        if(bundle != null){
                            String str = bundle.getString(MyStartService.MSG);
                            showToast(str);
                        }
                    }

            }
        },intentFilter);
    }

    private void showToast(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }

}
