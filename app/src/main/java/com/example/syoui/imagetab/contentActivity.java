package com.example.syoui.imagetab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class contentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        String code5 = getResources().getString(R.string.code1);
        TextView textView5 = (TextView) findViewById(R.id.textView5);

        textView5.setText(code5);

        TextView textView14 = (TextView) findViewById(R.id.textView14);

        String code14 = getResources().getString(R.string.code2);
        textView14.setText(code14);

        TextView textView16 = (TextView) findViewById(R.id.textView16);
        String code3 = getResources().getString(R.string.code3);
        textView16.setText(code3);

    }
}
