package com.example.syoui.imagetab.java_knowledge;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.example.syoui.imagetab.R;

import java.util.Timer;
import java.util.TimerTask;

public class TimeTaskActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_task);
        mContext = this;
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("aaa");
        progressDialog.setMessage("bbbb");
        progressDialog.show();


        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 0x00){
                    progressDialog.dismiss();
                }else{
                    progressDialog.show();
                }
            }
        };


        Timer timer = new Timer();
        timer.schedule(new MyTimeTask(handler),2000);
    }
}


class MyTimeTask extends TimerTask{
    private Handler mHandler;

    public MyTimeTask(Handler handler) {
        mHandler = handler;
    }

    @Override
    public void run() {
        Message msg = new Message();
        msg.what = 0x00;
        mHandler.sendMessage(msg);
    }
}
