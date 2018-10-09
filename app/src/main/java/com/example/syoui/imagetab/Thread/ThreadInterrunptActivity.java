package com.example.syoui.imagetab.Thread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.syoui.imagetab.R;

public class ThreadInterrunptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_interrunpt);
        final MyThreadToBeInterrupted thread = new MyThreadToBeInterrupted();

        Button startButton = findViewById(R.id.start);
        Button interruptButton = findViewById(R.id.interrupt);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.start();
            }
        });

        interruptButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                thread.interrupt();
            }
        });
    }



}
