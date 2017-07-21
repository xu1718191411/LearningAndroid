package com.example.syoui.imagetab.foundation.popup;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.syoui.imagetab.R;

public class customize extends AppCompatActivity {

    final Context context = this;
    private Button button;
    private Button _submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_alert);


        button = (Button) findViewById(R.id.buttonShowCustomDialog);
        _submit = (Button) findViewById(R.id._submit);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.customize_layout);
                dialog.setTitle("Title...");

                TextView text = (TextView) dialog.findViewById(R.id.text);
                
                dialog.show();


                Button _submit = (Button) dialog.findViewById(R.id._submit);

                _submit.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        EditText text = (EditText) dialog.findViewById(R.id.editContent);
                        String str = text.getText().toString();
                        TextView tx = (TextView) findViewById(R.id.resultConsequence);
                        tx.setText(text.getText());
                        dialog.dismiss();
                    }
                });


            }




        });






    }
}
