package com.example.syoui.imagetab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.syoui.imagetab.blockchain.BlockChainActivity;
import com.example.syoui.imagetab.launch_others.LaunchOtherAppActivity;
import com.example.syoui.imagetab.record.RecordActivity;


public class KnowledgeActivity extends AppCompatActivity {
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
                Intent intent = new Intent(getApplication(), TutorialActivity.class);
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

        Button goToRecord = (Button)findViewById(R.id.goToRecord);
        goToRecord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), RecordActivity.class);
                startActivity(intent);
            }
        });

        Button goTolaunchOtherApp = (Button)findViewById(R.id.launchOtherApps);
        goTolaunchOtherApp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), LaunchOtherAppActivity.class);
                startActivity(intent);
            }
        });


        Button goToSpeak = (Button) findViewById(R.id.speak);
        goToSpeak.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SpeakActivity.class);
                startActivity(intent);
            }
        });


        Button slide = (Button) findViewById(R.id.slide);
        slide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SlideActivity.class);
                startActivity(intent);
            }
        });

        Button dbButton = (Button) findViewById(R.id.db);
        dbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SQLiteActivity.class);
                startActivity(intent);
            }
        });


        Button youtubeButton = (Button) findViewById(R.id.youtubeVideo);
        youtubeButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), YoutubeActivity.class);
                startActivity(intent);
            }
        });

        youtubeButton.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(getApplication(),videoActivity.class);
                startActivity(intent);
                return false;
            }
        });

        Button simpleBlockChain = (Button) findViewById(R.id.simpleBlockChain);
        simpleBlockChain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),BlockChainActivity.class);
                startActivity(intent);
            }
        });

    }


}
