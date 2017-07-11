package com.example.syoui.imagetab.foundation.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syoui.imagetab.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.syoui.imagetab.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,LocationListener {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private double Latitude = 0;
    private double Longtitude = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        }
        else{
            locationStart();
        }

    }

    public void onGetCenter(View view){
        double latitude = mMap.getCameraPosition().target.latitude;
        double longitude = mMap.getCameraPosition().target.longitude;
        Toast.makeText(this, "中心位置\n緯度:" + latitude + "\n経度:" + longitude, Toast.LENGTH_LONG).show();
    }


    public void onSetFuji(View view){
        //double latitude = 35.000;
        //double longitude = 138.00;


        LatLng huji = new LatLng(Latitude, Longtitude);
        mMap.addMarker(new MarkerOptions().position(huji).title("latitude:"+Latitude+"longitude:"+Longtitude));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(huji));

    }

    private void locationStart(){
        Log.d("debug","locationStart()");

        // LocationManager インスタンス生成
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        final boolean network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);


//        if (gpsEnabled){
//            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        if(location !=null){
//                Latitude = location.getLatitude();
//                Longtitude = location.getLongitude();
//                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 50, this);
//            }
//
//        }else{
//
//            if (network_enabled){
//                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//                Latitude = location.getLatitude();
//                Longtitude = location.getLongitude();
//                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 50, this);
//            }
//
//        }



        if (network_enabled){
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Latitude = location.getLatitude();
            Longtitude = location.getLongitude();
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 50, this);
        }


//        if (!gpsEnabled) {
//            // GPSを設定するように促す
//            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//            startActivity(settingsIntent);
//        } else {
//            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            Latitude = location.getLatitude();
//            Longtitude = location.getLongitude();
//        }


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            Log.d("debug", "checkSelfPermission false");
            return;
        }


    }




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(1);

        double latitude = 35.0d + 21.0d/60 + 39.0d/(60*60);
        double longitude = 138.0d + 43.0d/60 + 39.0d/(60*60);
        LatLng huji = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(huji).title("latitude:"+latitude+",longitude:"+longitude));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(huji));
        mMap.getUiSettings().setZoomControlsEnabled(true);



        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                float[] results = new float[1];
                Location.distanceBetween(point.latitude, point.longitude, Latitude, Longtitude, results);
                Toast.makeText(getApplicationContext(), "関空までの距離：" + ( (Float)(results[0]/1000) ).toString() + "Km", Toast.LENGTH_LONG).show();

                LatLng from = new LatLng(point.latitude, point.longitude);
                LatLng to = new LatLng(Latitude, Longtitude);
                double direction = com.google.maps.android.SphericalUtil.computeHeading(from,to);


                String _direction;

                if(direction>=5 && direction<=85){
                    _direction = "WS";
                }else if(direction >85 && direction <= 95){
                    _direction = "W";
                }else if(direction > 95 && direction <=175){
                    _direction = "NW";
                }else if(direction>= 175 || direction <-175){
                    _direction = "N";
                }else if(direction <-95 && direction >= -175){
                    _direction = "EN";
                }else if(direction < -85 && direction >= -95){
                    _direction = "E";
                }else if(direction < -5 && direction >= -85){
                    _direction = "ES";
                }else if(direction < 0 || direction < 5){
                    _direction = "S";
                }else{
                    _direction = "UNKNOW";
                }

                MarkerOptions marker = new MarkerOptions().position(
                        new LatLng(point.latitude, point.longitude)).title( "関空までの距離：" + ( (Float)(results[0]/1000) ).toString() + "Km:現地点との方角は：" + _direction );
                mMap.addMarker(marker);

            }


        });



        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoContents(Marker marker) {
                    // TODO Auto-generated method stub
                    View view = getLayoutInflater().inflate(R.layout.info_window, null);
                    // タイトル設定
                    TextView title = (TextView)view.findViewById(R.id.info_title);
                    title.setText("経度:"+marker.getPosition().longitude+",緯度:"+marker.getPosition().latitude);

                    TextView title2 = (TextView)view.findViewById(R.id.info_distance_current);
                    title2.setText(marker.getTitle());

                    // 画像設定
                    ImageView img = (ImageView)view.findViewById(R.id.info_image);
                    img.setImageResource(R.drawable.icon1);
                    return view;
                }

                @Override
                public View getInfoWindow(Marker marker) {
                    // TODO Auto-generated method stub
                    return null;
                }
        });


        LatLng PERTH = new LatLng(-31.952854, 115.857342);
        LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
        LatLng BRISBANE = new LatLng(-27.47093, 153.0235);

        Marker mPerth = mMap.addMarker(new MarkerOptions()
                .position(PERTH).draggable(true)
                .title("Perth"));
        mPerth.setTag(0);


        Marker mSydney = mMap.addMarker(new MarkerOptions()
                .position(SYDNEY).icon(BitmapDescriptorFactory.fromResource(R.drawable.icon0))
                .title("Sydney"));
        mSydney.setTag(0);

        Marker mBrisbane = mMap.addMarker(new MarkerOptions()
                .position(BRISBANE).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .title("Brisbane"));
        mBrisbane.setTag(0);

    }

    @Override
    public void onLocationChanged(Location location) {
        // 緯度の表示
        Log.i("TESTLOG","onLocationChanged11111111");

        System.out.println("Latitude:"+location.getLatitude());
        System.out.println("Longtitude:"+location.getLongitude());

        LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        Latitude = location.getLatitude();
        Longtitude = location.getLongitude();

        Log.v("Map zoon level is ...","zoom level" + mMap.getCameraPosition().zoom);

    }


    // 結果の受け取り
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            // 使用が許可された
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("debug","checkSelfPermission true");

                locationStart();
                return;

            } else {
                // それでも拒否された時の対応
                Toast toast = Toast.makeText(this, "これ以上なにもできません", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }



    @Override
    public void onStatusChanged(String s, int status, Bundle bundle) {
        switch (status) {
            case LocationProvider.AVAILABLE:
                Log.d("debug", "LocationProvider.AVAILABLE");
                break;
            case LocationProvider.OUT_OF_SERVICE:
                Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                break;
        }
    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
