package com.example.syoui.imagetab.foundation.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.syoui.imagetab.R;
import com.example.syoui.imagetab.service.BlueToothService;

public class foundation_activity_ble extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BLUETOOTH = 1;
    private String TAG;

    private BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BlueToothService.ACTION_GATT_CONNECTED.equals(action)) {
                    Log.d(TAG,"ACTION_GATT_CONNECTED ON ACTIVITY");
            } else if (BlueToothService.ACTION_GATT_DISCONNECTED.equals(action)) {
                    Log.d(TAG,"ACTION_GATT_DISCONNECTED ON ACTIVITY");
            } else if (BlueToothService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                    Log.d(TAG,"ACTION_GATT_SERVICES_DISCOVERED ON ACTIVITY");
            } else if (BlueToothService.ACTION_DATA_AVAILABLE.equals(action)) {
                    Log.d(TAG,"ACTION_DATA_AVAILABLE ON ACTIVITY");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getName();
        setContentView(R.layout.activity_foundation_ble);
        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();

        if(bluetoothAdapter == null || !bluetoothAdapter.isEnabled()){
            Intent enableBlueTooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBlueTooth,REQUEST_ENABLE_BLUETOOTH);
        }


        Intent serviceIntent = new Intent(this,BlueToothService.class);
        startService(serviceIntent);


        IntentFilter mFilter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        mFilter.addAction(BlueToothService.ACTION_GATT_CONNECTED);
        mFilter.addAction(BlueToothService.ACTION_GATT_DISCONNECTED);
        mFilter.addAction(BlueToothService.ACTION_GATT_SERVICES_DISCOVERED);
        mFilter.addAction(BlueToothService.ACTION_DATA_AVAILABLE);
        registerReceiver(mGattUpdateReceiver,mFilter);


    }





}
