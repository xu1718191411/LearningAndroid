package com.example.syoui.imagetab;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.syoui.imagetab.model.myDB;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SQLiteActivity extends AppCompatActivity {
    private myDB myDB = null;
    private static final String TAG  = "SQLiteActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq_lite_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


                myDB = new myDB(getApplicationContext(),"a.db",null,1);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }





    // assetに格納したDBをデフォルトのDBパスに作成し、コピーする
    private void copyDatabaseFromAsset(){
        try{
            //富士通系一部端末においてデフォルトパスを作成出来ない問題あり
            File databaseDir = new File(DB_PATH);
            if (!databaseDir.exists()) databaseDir.mkdirs();

            // asset内のDBにアクセス
            InputStream is = context.getAssets().open(DB_NAME);

            // デフォルトのDBパスに作成した空のDB
            String dbPath = DB_PATH + DB_NAME_OUT;
            OutputStream os = new FileOutputStream(dbPath);

            // コピー
            byte[] buffer = new byte[1024];
            int size;
            while((size = is.read(buffer)) > 0){
                os.write(buffer, 0, size);
            }

            // クローズ
            is.close();
            os.flush();
            os.close();
            Log.d(TAG,"DB Copy:success");
        }catch(IOException e){
            Log.e(TAG, "IOE:" + e.toString());
            Log.d(TAG, "DB Copy:failed");
        }
    }



}
