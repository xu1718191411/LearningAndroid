package com.example.syoui.imagetab.java_knowledge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.syoui.imagetab.R;

import java.util.ArrayList;

public class TypeConvertActivity extends AppCompatActivity {
    Contribution contribution = null;
    ArrayList<MyContribution> myContributionArrayList;
    ArrayList<Contribution> contributionArrayList;
    ArrayList<MyContribution> myRecoveryContributionArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_convert);

        MyContribution myContribution = new MyContribution("JAVA基礎","開発のコツについて","JAVA基礎開発編");
        contribution = (Contribution) myContribution;

        myContributionArrayList = new ArrayList<MyContribution>();
        contributionArrayList = new ArrayList<Contribution>();
        myRecoveryContributionArrayList = new ArrayList<MyContribution>();

        myContributionArrayList.add(new MyContribution("Java Study1","new String('good day')","String Type"));
        myContributionArrayList.add(new MyContribution("Java Study2","new Boolean(true)","Boolean Type"));
        myContributionArrayList.add(new MyContribution("Java Study3","new Integer(17)","Integer Type"));
        myContributionArrayList.add(new MyContribution("Java Study4","new ArrayList<>()","ArrayList Type"));
        myContributionArrayList.add(new MyContribution("Java Study5","new Double(3.1415926)","Double Type"));


        for(MyContribution m:myContributionArrayList){
            Log.d("myContribution",m.toString());
            contributionArrayList.add((Contribution) m);
        }

        for(Contribution m:contributionArrayList){
            Log.d("Contribution",m.toString());
            myRecoveryContributionArrayList.add((MyContribution) m);
        }

        for(MyContribution m:myRecoveryContributionArrayList){
            Log.d("Recovery Contribution",m.toString());
        }


        Button bringContributionToNextPage = (Button) findViewById(R.id.bringContributionToNextPage);
        bringContributionToNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity();
            }
        });

    }

    private void goToActivity(){
        Intent intent = new Intent(getApplication(),ReceiveTypeConvertActivity.class);
        intent.putExtra("contribution", contribution);
        intent.putExtra("contributionArrayList",contributionArrayList);
        startActivity(intent);
    }


}
