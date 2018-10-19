package com.example.syoui.imagetab.foundation.popup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.syoui.imagetab.R;

public class CustomDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);
        CustomDialogFragment customDialogFragment = new CustomDialogFragment();
        customDialogFragment.show(getSupportFragmentManager(),"a");
    }
}
