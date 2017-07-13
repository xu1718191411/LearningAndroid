package com.example.syoui.imagetab.foundation.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.syoui.imagetab.R;

import java.util.List;

public class tendency extends AppCompatActivity implements SensorEventListener {

    private SensorManager manager;
    private TextView values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tendency);

        values = (TextView)findViewById(R.id.value_id);
        manager = (SensorManager)getSystemService(SENSOR_SERVICE);
    }


    @Override
    protected void onStop() {
        super.onStop();
        manager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ORIENTATION);

            if(sensors.size() > 0) {
                Sensor s = sensors.get(0);
                manager.registerListener(this, s, SensorManager.SENSOR_DELAY_UI);
            }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ORIENTATION) {String str = "傾きセンサー値:" + "\n方位角:" + sensorEvent.values[0] + "\n傾斜角:" + sensorEvent.values[1] + "\n回転角:" + sensorEvent.values[2];
            values.setText(str);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor event, int i) {

    }
}
