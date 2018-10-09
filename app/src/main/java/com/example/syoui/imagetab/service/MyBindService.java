package com.example.syoui.imagetab.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyBindService extends Service {
    private IBinder myBinder = new MyBinder();

    public class MyBinder extends Binder implements MyServiceInterface{

        @Override
        public int add(int a, int b) {
            return a + b;
        }

        public MyBindService getService(){
            return MyBindService.this;
        }
    }

    public MyBindService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i("Service","onBind");
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Service","onCreate");
        myBinder = new MyBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Service","onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("Service","onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i("Service","onRebind");
    }

    public int substract(int a,int b){
        return a - b;
    }
}
