package com.example.syoui.imagetab.foundation.sensor;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.syoui.imagetab.R;

import java.util.List;

public class sensor_list extends AppCompatActivity {

    private SensorManager manager;
    private TextView nameLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_list);

        nameLists = (TextView) findViewById(R.id.namelist_id);
        manager = (SensorManager)getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String str = "";
        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);
        for(Sensor s:sensors){
            str +=  s.getName()+ "\n";
        }
        nameLists.setText(str);
    }
}
