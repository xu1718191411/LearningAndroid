package com.example.syoui.imagetab;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class videoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

//        VideoView view = (VideoView)findViewById(R.id.myVideo);
//        String path = "android.resource://" + getPackageName() + "/" + R.raw.photograph;
//        view.setVideoURI(Uri.parse(path));
//
//        MediaController mediaController = new MediaController(this);
//        mediaController.setAnchorView(view);
//        view.setMediaController(mediaController);
//
//        view.start();

    }
}
