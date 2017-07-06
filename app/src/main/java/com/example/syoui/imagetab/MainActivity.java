package com.example.syoui.imagetab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {
    LinearLayout mLinearLayout;
    int tab = 1;
    int totalNum = 5;
    ImageView image1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        image1 = (ImageView) findViewById(R.id.anime);
        Button button = (Button) findViewById(R.id.button);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), detailActivity.class);
                startActivity(intent);
            }
        });


        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tab++;
                System.out.println(tab);
                int imageNum = tab % totalNum;

                switch (imageNum) {
                    case 0:
                        image1.setImageResource(R.drawable.choba);
                        break;
                    case 1:
                        image1.setImageResource(R.drawable.rufi);
                        break;
                    case 2:
                        image1.setImageResource(R.drawable.rufu2);
                        break;
                    case 3:
                        image1.setImageResource(R.drawable.soro);
                        break;
                    case 4:
                        image1.setImageResource(R.drawable.soro2);
                        break;
                }
            }
        });
    }



}
