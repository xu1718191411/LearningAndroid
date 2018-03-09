package com.example.syoui.imagetab.customizeui;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by syoui on 2018/03/08.
 */

public class MyProgressDialog extends ProgressDialog{
    public MyProgressDialog(Context context) {
        super(context);
    }

    public MyProgressDialog(Context context, int theme) {
        super(context, theme);
    }
}
