package com.example.syoui.imagetab.foundation.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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




    private void displayGattServices(List<BluetoothGattService> gattServices) {
        if (gattServices == null) return;
        String uuid = null;
        String unknownServiceString = "unknown_service";
        String unknownCharaString = "unknown_characteristic";
        ArrayList<HashMap<String, String>> gattServiceData =
                new ArrayList<HashMap<String, String>>();
        ArrayList<ArrayList<HashMap<String, String>>> gattCharacteristicData
                = new ArrayList<ArrayList<HashMap<String, String>>>();
        mGattCharacteristics =
                new ArrayList<ArrayList<BluetoothGattCharacteristic>>();

        // Loops through available GATT Services.
        for (BluetoothGattService gattService : gattServices) {
            HashMap<String, String> currentServiceData =
                    new HashMap<String, String>();
            uuid = gattService.getUuid().toString();
            currentServiceData.put(
                    LIST_NAME, SampleGattAttributes.
                            lookup(uuid, unknownServiceString));
            currentServiceData.put(LIST_UUID, uuid);
            gattServiceData.add(currentServiceData);

            ArrayList<HashMap<String, String>> gattCharacteristicGroupData =
                    new ArrayList<HashMap<String, String>>();
            List<BluetoothGattCharacteristic> gattCharacteristics =
                    gattService.getCharacteristics();
            ArrayList<BluetoothGattCharacteristic> charas =
                    new ArrayList<BluetoothGattCharacteristic>();
            // Loops through available Characteristics.
            for (BluetoothGattCharacteristic gattCharacteristic :
                    gattCharacteristics) {
                charas.add(gattCharacteristic);
                HashMap<String, String> currentCharaData =
                        new HashMap<String, String>();
                uuid = gattCharacteristic.getUuid().toString();
                currentCharaData.put(
                        LIST_NAME, SampleGattAttributes.lookup(uuid,
                                unknownCharaString));
                currentCharaData.put(LIST_UUID, uuid);
                gattCharacteristicGroupData.add(currentCharaData);
            }
            mGattCharacteristics.add(charas);
            gattCharacteristicData.add(gattCharacteristicGroupData);
        }
    }



}
