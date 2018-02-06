package com.example.syoui.imagetab.java_knowledge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.syoui.imagetab.R;

import java.io.Serializable;
import java.util.ArrayList;

public class ReceiveTypeConvertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_type_convert);
        Intent intent = getIntent();
        Serializable contributionSerial = intent.getSerializableExtra("contribution");
        if(contributionSerial != null){
            Contribution contribution = (Contribution) contributionSerial;
            Log.d("Receive:contribution",contribution.toString());
        }

        Serializable contributionArrayListSerial = intent.getSerializableExtra("contributionArrayList");
        if(contributionArrayListSerial != null){
            ArrayList<Contribution> contributionArrayList = ( ArrayList<Contribution>)contributionArrayListSerial;
            for(Contribution m:contributionArrayList){
                Log.d("contributionArrayList",m.toString());
            }
        }


    }
}
