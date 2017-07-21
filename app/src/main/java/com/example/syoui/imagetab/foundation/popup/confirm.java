package com.example.syoui.imagetab.foundation.popup;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.syoui.imagetab.R;

public class confirm extends AppCompatActivity {
    Boolean res = false;
    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);



        Button btn = (Button)findViewById(R.id.click);
        text1 = (TextView)findViewById(R.id._text1);


        final AlertDialog.Builder build = new  AlertDialog.Builder(this);
        build.setTitle("title").setMessage("messages");
        build.setPositiveButton("no",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        res = false;
                        showResult();
                    }

                })
                .setNegativeButton("yes" , new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        res = true;
                        showResult();
                    }

                });

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                build.show();
            }
        });

    }


    private void showResult(){
        if(res){
            text1.setText("result is true");
        }else{
            text1.setText("result is false");
        }
    }


}
