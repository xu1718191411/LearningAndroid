package com.example.syoui.imagetab.foundation.popup;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.syoui.imagetab.R;

public class alert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        Button btn = (Button)findViewById(R.id.click);
        final AlertDialog.Builder build = new  AlertDialog.Builder(this).setTitle("title").setMessage("messages").setPositiveButton("confirm",null);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                build.show();
            }
        });
    }
}
