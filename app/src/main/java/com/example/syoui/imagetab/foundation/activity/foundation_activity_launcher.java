package com.example.syoui.imagetab.foundation.activity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.syoui.imagetab.R;

public class foundation_activity_launcher extends LauncherActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation_launcher);
    }

    @Override
    protected Intent getTargetIntent() {
        // TODO Auto-generated method stub
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Osaka"));
        return intent;
    }


}
