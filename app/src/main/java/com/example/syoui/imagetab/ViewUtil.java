package com.example.syoui.imagetab;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class ViewUtil {

    public static void dialog(Context context,String text){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(text);

        builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }


}
