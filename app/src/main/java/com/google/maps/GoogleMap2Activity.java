package com.google.maps;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.syoui.imagetab.R;

public class GoogleMap2Activity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map2);

        Fragment mapFragment = new GoogleMapWithStreetViewFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.map_container, mapFragment, "map")
                .commit();
    }


}
