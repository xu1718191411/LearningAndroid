package com.example.syoui.imagetab.foundation.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.syoui.imagetab.R;

public class foundation_activity_layout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation_layout);

        Button naviCon = (Button) findViewById(R.id.naviCon);

        naviCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Uri uri = Uri.parse("navicon://");
                Intent i = new Intent(Intent.ACTION_VIEW, uri);

                try {
                    startActivity(i);
                }catch(ActivityNotFoundException activityNotFound){

                }

            }
        });

    }
}
