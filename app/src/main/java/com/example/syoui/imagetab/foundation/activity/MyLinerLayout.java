package com.example.syoui.imagetab.foundation.activity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.syoui.imagetab.R;

/**
 * Created by syoui on 2018/01/31.
 */
class MyLinerLayout extends LinearLayout {

    private LayoutInflater mLayoutInflater;
    private Resources mResources;
    public MyLinerLayout(Context context) {
        super(context);
    }

    public MyLinerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(attrs,0);
    }

    public MyLinerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(attrs,defStyleAttr);
    }

    private void initialize(AttributeSet attrs, int defStyleAttr){
        int r = 255;
        int g = 255;
        int b = 255;
        if(attrs != null){
            String packageName = "http://www.mrway.net/syoui";
            r = attrs.getAttributeIntValue(packageName,"r",255);
            g = attrs.getAttributeIntValue(packageName,"g",255);
            b = attrs.getAttributeIntValue(packageName,"b",255);
        }

        mResources	= getContext().getResources();
        mLayoutInflater 	= LayoutInflater.from(getContext());
        View view 	= mLayoutInflater.inflate(R.layout.my_liner_layout, this);
        ((TextView) view.findViewById(R.id.color)).setText(r+":"+g+":"+b);

        int backgroundColor = Color.argb(255,r,g,b);
        ((LinearLayout) view.findViewById(R.id.colorLayout)).setBackgroundColor(backgroundColor);

    }


}
