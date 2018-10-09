package com.example.syoui.imagetab.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MyStartService extends Service {
    public static final String START_SERVICE_ACTION = "MyStartService";
    private static int COUNT = 1;
    public static final String  MSG = "MESSAGE";
    Timer mTimer = new Timer();
    TimerTask mTimeTask;
    public MyStartService() {
        Log.i("Service","construct");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Service","onCreate");

        mTimeTask = new TimerTask() {
            @Override
            public void run() {
                sendMessageToActivity("Create Serivce" + getClass().getName().toString());
            }
        };

        mTimer.schedule(mTimeTask,1000,1000);
    }

    private void sendMessageToActivity(String str){
        Intent intent = new Intent(START_SERVICE_ACTION);
        intent.putExtra(MSG,"Don't "+COUNT);
        this.COUNT = COUNT + 1;
        sendBroadcast(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimeTask.cancel();
        mTimer.cancel();
        Log.i("Service","onDestroy");
    }

}
