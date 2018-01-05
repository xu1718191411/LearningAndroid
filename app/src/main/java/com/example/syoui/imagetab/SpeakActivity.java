package com.example.syoui.imagetab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.syoui.imagetab.controller.HttpResponsAsync;

public class SpeakActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak);

        Button btn = (Button)findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                HttpResponsAsync hpa = new HttpResponsAsync();
                hpa.execute();
            }
        });



    }
}
