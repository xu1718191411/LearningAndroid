package com.example.syoui.imagetab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        LinearLayout returnButton = (LinearLayout) findViewById(R.id.return_button);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainListViewActivity.class);
                intent.putExtra("category", 1);
                startActivity(intent);
            }
        });

        LinearLayout goto_listView_button = (LinearLayout) findViewById(R.id.goto_listView_button);
        goto_listView_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainListViewActivity.class);
                intent.putExtra("category", 2);
                startActivity(intent);
            }
        });


        LinearLayout goto_videoView_button = (LinearLayout) findViewById(R.id.goto_videoView_button);
        goto_videoView_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainListViewActivity.class);
                intent.putExtra("category", 3);
                startActivity(intent);
            }
        });


        LinearLayout goto_content_button = (LinearLayout) findViewById(R.id.goto_content_button);
        goto_content_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), contentActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout goto_knowledge_button = (LinearLayout) findViewById(R.id.goto_knowledge_button);
        goto_knowledge_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), KnowledgeActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
