package com.google.maps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.syoui.imagetab.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaView;

public class GoogleMap3Activity extends AppCompatActivity {


    private MapView mapView;
    private GoogleMap googleMap;
    private StreetViewPanoramaView streetView;
    private StreetViewPanorama streetPanorama;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map3);

        MapsInitializer.initialize(getApplicationContext());
        mapView      = (MapView) findViewById(R.id.google_map_v2);
        mapView.onCreate(null);

        streetView  = (StreetViewPanoramaView) findViewById(R.id.street_view);
        streetView.onCreate(null);
    }

}
