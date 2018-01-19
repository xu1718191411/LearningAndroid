package com.example.syoui.imagetab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Button returnButton = (Button) findViewById(R.id.return_button);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), listViewActivity.class);
                intent.putExtra("category", 1);
                startActivity(intent);
            }
        });

        Button goto_listView_button = (Button) findViewById(R.id.goto_listView_button);
        goto_listView_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), listViewActivity.class);
                intent.putExtra("category", 2);
                startActivity(intent);
            }
        });


        Button goto_videoView_button = (Button) findViewById(R.id.goto_videoView_button);
        goto_videoView_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), videoActivity.class);
                startActivity(intent);
            }
        });


        Button goto_content_button = (Button) findViewById(R.id.goto_content_button);
        goto_content_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), contentActivity.class);
                startActivity(intent);
            }
        });

        Button goto_knowledge_button = (Button) findViewById(R.id.goto_knowledge_button);
        goto_knowledge_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), KnowledgeActivity.class);
                startActivity(intent);
            }
        });


    }

}
