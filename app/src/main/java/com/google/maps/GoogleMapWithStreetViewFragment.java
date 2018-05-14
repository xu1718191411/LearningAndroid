package com.google.maps;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.syoui.imagetab.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaView;

/**
 * Created by syoui on 2018/05/14.
 */

public class GoogleMapWithStreetViewFragment extends Fragment {


    private MapView mapView;
    private GoogleMap googleMap;
    private StreetViewPanoramaView streetView;
    private StreetViewPanorama streetPanorama;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.google_map_with_street_view_fragment, container, false);

        MapsInitializer.initialize(getActivity().getApplicationContext());
        mapView      = (MapView) v.findViewById(R.id.google_map_v2);
        mapView.onCreate(null);

//        streetView  = (StreetViewPanoramaView) v.findViewById(R.id.street_view);
//        streetView.onCreate(null);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        googleMap         = mapView.getMap();
//        streetPanorama = streetView.getStreetViewPanorama();

//        mapView.getMapAsync(new OnMapReadyCallback() {
//            @Override
//            public void onMapReady(GoogleMap _googleMap) {
//                googleMap = _googleMap;
//            }
//        });
//
//        streetView.getStreetViewPanoramaAsync(new OnStreetViewPanoramaReadyCallback() {
//            @Override
//            public void onStreetViewPanoramaReady(StreetViewPanorama _streetViewPanorama) {
//                streetPanorama = _streetViewPanorama;
//            }
//        });



    }

}
