package com.example.syoui.imagetab.foundation.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.syoui.imagetab.R;

public class simpleCamera extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 0;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_camera);

        imgView = (ImageView)findViewById(R.id.imgview_id);

        Intent intent = new Intent();
        // インテントにアクションをセット
        intent.setAction("android.media.action.IMAGE_CAPTURE");
        // カメラアプリ起動
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            try {
                // 画像データの取得
                Bitmap img = (Bitmap)data.getExtras().get("data");
                imgView.setImageBitmap(img);
            } catch (Exception e) {

            }
        }
    }


}
