package com.example.syoui.imagetab.java_knowledge;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.syoui.imagetab.R;
import com.example.syoui.imagetab.java_knowledge.fragment.DozeFragment;
import com.example.syoui.imagetab.java_knowledge.fragment.DozeMaintenanceFragment;

public class TestDozeModeActivity extends AppCompatActivity implements DozeMaintenanceFragment.OnFragmentInteractionListener,DozeFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_doze_mode);
        ViewPager dozePagerView = (ViewPager) findViewById(R.id.dozePagerView);
        dozePagerView.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if(position == 0){
                    return DozeMaintenanceFragment.newInstance(position,"b");
                }else{
                    return DozeFragment.newInstance("a","b");
                }

            }

            @Override
            public int getCount() {
                return 2;
            }
        });

    }

    @Override
    public void onFragmentInteraction(int position) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
