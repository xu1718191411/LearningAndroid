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

public class acceleration extends AppCompatActivity implements SensorEventListener {

    private SensorManager manager;
    private TextView values;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceleration);

        values = (TextView)findViewById(R.id.value_id);
        manager = (SensorManager)getSystemService(SENSOR_SERVICE);


    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        // Listenerの登録解除
        manager.unregisterListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();

        // Listenerの登録
        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (sensors.size() > 0) {
            Sensor s = sensors.get(0);
            manager.registerListener(this, s, SensorManager.SENSOR_DELAY_UI);
        }
    }



    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                String str = "加速度センサー値:" + "\nX軸:" + sensorEvent.values[SensorManager.DATA_X] + "\nY軸:" + sensorEvent.values[SensorManager.DATA_Y] + "\nZ軸:" + sensorEvent.values[SensorManager.DATA_Z];
                values.setText(str);
       }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {




    }
}
