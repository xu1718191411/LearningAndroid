package com.example.syoui.imagetab.service;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class BlueToothService extends Service {



    private static final int BLE_SCAN_PERIOD = 3000;

    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice mBlueToothDevice;
    public static final String ACTION_GATT_CONNECTED = "com.example.syoui.imagetab.service.ACTION_GATT_CONNECTED";
    public static final String ACTION_GATT_DISCONNECTED = "com.example.syoui.imagetab.service.ACTION_GATT_DISCONNECTED";
    public static final String ACTION_GATT_SERVICES_DISCOVERED = "com.example.syoui.imagetab.service.ACTION_GATT_SERVICES_DISCOVERED";
    public static final String ACTION_DATA_AVAILABLE  = "com.example.syoui.imagetab.service.ACTION_DATA_AVAILABLE";

    public final static String EXTRA_STATUS = "com.example.syoui.imagetab.service.EXTRA_STATUS";
    public final static String EXTRA_ADDRESS = "com.example.syoui.imagetab.service.EXTRA_ADDRESS";
    private String TAG;
    private BluetoothGatt mBluetoothGatt;

    public final static String EXTRA_DATA =
            "com.example.syoui.imagetab.service.EXTRA_DATA";



    BluetoothGattCallback mBluetoothGattCallback = new BluetoothGattCallback() {
        @Override
        public void onPhyUpdate(BluetoothGatt gatt, int txPhy, int rxPhy, int status) {
            Log.d("DEVICE","DEIVCE:onPhyUpdate");
            super.onPhyUpdate(gatt, txPhy, rxPhy, status);
        }

        @Override
        public void onPhyRead(BluetoothGatt gatt, int txPhy, int rxPhy, int status) {
            Log.d("DEVICE","DEIVCE:onPhyRead");
            super.onPhyRead(gatt, txPhy, rxPhy, status);
        }

        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            Log.d("DEVICE","DEIVCE:onConnectionStateChange");
            if(newState == BluetoothProfile.STATE_CONNECTED){
                Log.d("DEVICE","DEIVCE:STATE_CONNECTED");
            }else if(newState == BluetoothProfile.STATE_CONNECTING){
                Log.d("DEVICE","DEIVCE:STATE_CONNECTING");
            }else if(newState == BluetoothProfile.STATE_DISCONNECTED){
                Log.d("DEVICE","DEIVCE:STATE_DISCONNECTED");
            }else if(newState == BluetoothProfile.STATE_DISCONNECTING){
                Log.d("DEVICE","DEIVCE:STATE_DISCONNECTING");
            }else{
                Log.d("DEVICE","DEIVCE:ELSE");
            }





            String intentAction;
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                intentAction = ACTION_GATT_CONNECTED;
                broadcastUpdate(intentAction);
                Log.i(TAG, "Connected to GATT server.");
                Log.i(TAG, "Attempting to start service discovery:" +
                        mBluetoothGatt.discoverServices());

            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                intentAction = ACTION_GATT_DISCONNECTED;
                Log.i(TAG, "Disconnected from GATT server.");
                broadcastUpdate(intentAction);
            }

        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
            Log.d("DEVICE","DEIVCE:onServicesDiscovered");

            if (status == BluetoothGatt.GATT_SUCCESS) {
                BluetoothDevice device = gatt.getDevice();
                broadcastUpdate(ACTION_GATT_SERVICES_DISCOVERED, device.getAddress(),
                        status);
            } else {
                Log.w(TAG, "onServicesDiscovered received: " + status);
            }
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic
        characteristic, int status) {
            Log.d("DEVICE","DEIVCE:onCharacteristicRead");
            super.onCharacteristicRead(gatt, characteristic, status);
            if (status == BluetoothGatt.GATT_SUCCESS) {
                broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic);
            }
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            Log.d("DEVICE","DEIVCE:onCharacteristicWrite");
            super.onCharacteristicWrite(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            Log.d("DEVICE","DEIVCE:onCharacteristicChanged");
            super.onCharacteristicChanged(gatt, characteristic);
        }

        @Override
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            Log.d("DEVICE","DEIVCE:onDescriptorRead");
            super.onDescriptorRead(gatt, descriptor, status);
        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            Log.d("DEVICE","DEIVCE:onDescriptorWrite");
            super.onDescriptorWrite(gatt, descriptor, status);
        }

        @Override
        public void onReliableWriteCompleted(BluetoothGatt gatt, int status) {
            Log.d("DEVICE","DEIVCE:onReliableWriteCompleted");
            super.onReliableWriteCompleted(gatt, status);
        }

        @Override
        public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
            Log.d("DEVICE","DEIVCE:onReadRemoteRssi");
            super.onReadRemoteRssi(gatt, rssi, status);
        }

        @Override
        public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
            Log.d("DEVICE","DEIVCE:onMtuChanged");
            super.onMtuChanged(gatt, mtu, status);
        }
    };


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final BluetoothManager bluetoothManager = (BluetoothManager) getApplicationContext().getSystemService(BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        if(mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()){

        }else {
            scanDevices(true);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    public BlueToothService() {
        TAG = getClass().toString();
    }



    private void scanDevices(Boolean shouldScan){
        if(shouldScan){
            Handler scanHandler = new Handler();

            BluetoothLeAdvertiser adviser = mBluetoothAdapter.getBluetoothLeAdvertiser();


            mBluetoothAdapter.startLeScan(new BluetoothAdapter.LeScanCallback() {
                @Override
                public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
                    if(device == null) return;
                    if(device.getName() == null) return;
                    Log.d("DEVICE","FIND A NEW DEIVCE:"+device.getName());
                    if(device.getName().equals("SensorTag")){
                        mBlueToothDevice = device;
                        mBluetoothAdapter.stopLeScan(this);
                        connectToDevice();
                    }
                }
            });


        }
    }


    private void connectToDevice(){
        if(mBlueToothDevice != null){
            mBluetoothGatt = mBlueToothDevice.connectGatt(this, false, mBluetoothGattCallback);
        }
    }



    private void broadcastUpdate(final String action) {
        final Intent intent = new Intent(action);
        sendBroadcast(intent);
    }

    private void broadcastUpdate(final String action, final String address,
                                 final int status) {
        final Intent intent = new Intent(action);
        intent.putExtra(EXTRA_ADDRESS, address);
        intent.putExtra(EXTRA_STATUS, status);
        sendBroadcast(intent);
    }



    private void broadcastUpdate(final String action,
                                 final BluetoothGattCharacteristic characteristic) {
        final Intent intent = new Intent(action);

        // This is special handling for the Heart Rate Measurement profile. Data
        // parsing is carried out as per profile specifications.
//        if (UUID_HEART_RATE_MEASUREMENT.equals(characteristic.getUuid())) {
            int flag = characteristic.getProperties();
            int format = -1;
            if ((flag & 0x01) != 0) {
                format = BluetoothGattCharacteristic.FORMAT_UINT16;
                Log.d(TAG, "Heart rate format UINT16.");
            } else {
                format = BluetoothGattCharacteristic.FORMAT_UINT8;
                Log.d(TAG, "Heart rate format UINT8.");
            }
            final int heartRate = characteristic.getIntValue(format, 1);
            Log.d(TAG, String.format("Received heart rate: %d", heartRate));
            intent.putExtra(EXTRA_DATA, String.valueOf(heartRate));
//        } else {
//            // For all other profiles, writes the data formatted in HEX.
//            final byte[] data = characteristic.getValue();
//            if (data != null && data.length > 0) {
//                final StringBuilder stringBuilder = new StringBuilder(data.length);
//                for(byte byteChar : data)
//                    stringBuilder.append(String.format("%02X ", byteChar));
//                intent.putExtra(EXTRA_DATA, new String(data) + "\n" +
//                        stringBuilder.toString());
//            }
//        }
        sendBroadcast(intent);
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }




}
