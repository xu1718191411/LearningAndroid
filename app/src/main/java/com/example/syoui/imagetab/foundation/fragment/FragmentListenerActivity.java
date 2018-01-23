package com.example.syoui.imagetab.foundation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.syoui.imagetab.R;

import java.util.ArrayList;

public class FragmentListenerActivity extends AppCompatActivity {

    private ArrayList<String> mList = null;
    private Context mContext = null;
    private ListView mListView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_listener);

        final EditText mEditText = (EditText) findViewById(R.id.mEdit);

        mEditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if ((keyCode == KeyEvent.KEYCODE_ENTER) ||
                            (keyCode == KeyEvent.KEYCODE_DPAD_CENTER)) {
                            String res = mEditText.getText().toString();
                            ((TextView)findViewById(R.id.mText)).setText(res);
                    }
                }
                return true;
            }
        });
    }
}