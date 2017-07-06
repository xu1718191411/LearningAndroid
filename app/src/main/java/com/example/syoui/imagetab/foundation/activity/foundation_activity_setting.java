package com.example.syoui.imagetab.foundation.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.syoui.imagetab.R;

public class foundation_activity_setting extends AppCompatActivity {
    private static final String FILENAME = "sample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation_setting);

        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences pref = getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
                if (pref != null) {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("coffeeShow", buttonView.isChecked()).commit();
                    showSettingPreference();
                }

                Toast.makeText(getApplicationContext(),
                        String.valueOf(buttonView.isChecked()), Toast.LENGTH_SHORT).show();

            }
        });

        CheckBox restOnWeekend = (CheckBox) findViewById(R.id.restOnWeekend);

        restOnWeekend.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences pref = getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
                if (pref != null) {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("restOnWeekend", buttonView.isChecked()).commit();
                    showSettingPreference();
                }

                Toast.makeText(getApplicationContext(),
                        String.valueOf(buttonView.isChecked()), Toast.LENGTH_SHORT).show();

            }
        });


        CheckBox above500 = (CheckBox) findViewById(R.id.above500);

        above500.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences pref = getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
                if (pref != null) {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("above500", buttonView.isChecked()).commit();
                    showSettingPreference();
                }

                Toast.makeText(getApplicationContext(),
                        String.valueOf(buttonView.isChecked()), Toast.LENGTH_SHORT).show();

            }
        });

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.RGroup);



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged (RadioGroup group,int checkedId){

                Log.d("chk", "id" + checkedId);

                Toast.makeText(getApplicationContext(),
                        String.valueOf(checkedId), Toast.LENGTH_SHORT).show();

                SharedPreferences pref = getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
                if (pref != null) {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putInt("generation", checkedId).commit();
                    showSettingPreference();
                }

            }
        });


    }


    private void showSettingPreference(){
        SharedPreferences pref = getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        Boolean coffeeShowBoolean = pref.getBoolean("coffeeShow",false);
        TextView coffeeShow = (TextView) findViewById(R.id.coffeeShow);
        coffeeShow.setText(coffeeShowBoolean.toString());


        Boolean restOnWeekendShowBoolean = pref.getBoolean("restOnWeekend",false);
        TextView restOnWeekendShow = (TextView) findViewById(R.id.restOnWeekendShow);
        restOnWeekendShow.setText(restOnWeekendShowBoolean.toString());



        Boolean above500ShowBoolean = pref.getBoolean("above500",false);
        TextView above500Show = (TextView) findViewById(R.id.above500Show);
        above500Show.setText(above500ShowBoolean.toString());


        int generationShowInt = pref.getInt("generation",-1);
        TextView generationShow = (TextView) findViewById(R.id.generation);

        if(generationShowInt == 1){
            generationShow.setText("20代");
        }else{
            generationShow.setText("30代");
        }



    }


}
