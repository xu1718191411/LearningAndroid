package com.example.syoui.imagetab.record;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.syoui.imagetab.R;
import com.example.syoui.imagetab.record.controller.VoiceRecord;

public class RecordActivity extends AppCompatActivity {

    private Button mRecordButton = null;
    private VoiceRecord mVoiceRecord = null;
    static String TAG = "RecordActivity:";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        initialObject();
        dealWithClickBind();

        mRecordButton = (Button) findViewById(R.id.record);
        mRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVoiceRecord.startRecord();
            }
        });

    }

    private void dealWithClickBind(){


    }

    private void initialObject(){
        mVoiceRecord = new VoiceRecord();

    }
}
