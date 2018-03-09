package com.example.syoui.imagetab.foundation.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.syoui.imagetab.R;

public class foundation_activity_progress_dialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation_progress_dialog);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("お待ちいただく時間");
        progressDialog.setMessage("後30分を待つ必要があります");

        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "キャンセル", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        progressDialog.setCancelable(false);


        progressDialog.show();


    }
}
