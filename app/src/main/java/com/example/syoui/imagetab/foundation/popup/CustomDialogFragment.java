package com.example.syoui.imagetab.foundation.popup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.syoui.imagetab.R;

public class CustomDialogFragment extends android.support.v4.app.DialogFragment {
    private ViewPager customDialogViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // onCreateDialogを実装したエラーが発生する
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        Dialog dialog = new Dialog(getActivity());
//        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.custom_dialog);
//
//        customDialogViewPager = (ViewPager) dialog.findViewById(R.id.customDialogViewPager);
//        customDialogViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
//            @Override
//            public Fragment getItem(int position) {
//                return CustomeDialogSubFragment.newInstance("a","b");
//            }
//
//            @Override
//            public int getCount() {
//                return 2;
//            }
//        });
//
//        return dialog;
//    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_dialog,container,false);
        customDialogViewPager = (ViewPager) view.findViewById(R.id.customDialogViewPager);
        customDialogViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return CustomeDialogSubFragment.newInstance("a","b");
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                return super.instantiateItem(container, position);
            }

        });
        return view;
    }
}
