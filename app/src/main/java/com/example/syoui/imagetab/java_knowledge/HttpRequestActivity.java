package com.example.syoui.imagetab.java_knowledge;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.syoui.imagetab.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

public class HttpRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_request);


        Button catchTheHTML = (Button) findViewById(R.id.catchTheHTML);
        catchTheHTML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute("https://goo.gl/maps/ukqGqU9EYAP2","");
            }
        });
    }
}


class MyAsyncTask extends AsyncTask<String,Void,String> {
    private URL url;
    private InputStream inputStream = null;
    private String response;

    @Override
    protected String doInBackground(String... strings) {


        System.setProperty("http.agent", "Andro");
        String currentUerAgent = System.getProperty("http.agent");


        try{
            URL url = new URL(strings[0]);
            HttpURLConnection connection = null;
            do{
                connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.setInstanceFollowRedirects(false);
                connection.connect();
                if(connection.getHeaderField("Location") != null){
                    url = new URL(connection.getHeaderField("Location"));
                }
            }while(connection.getResponseCode() == 301 || connection.getResponseCode() == 302);


            if(connection != null && connection.getResponseCode() == 200){
                inputStream = connection.getInputStream();
                response = getInputReader(inputStream);
                System.out.println(response);
            }
        }catch(SocketTimeoutException e){
            System.out.println("接続タイムアウト : " + e);
        }catch(MalformedURLException e){
            System.out.println("URL生成失敗 : " + e);
        }catch(UnsupportedEncodingException e){
            System.out.println("response body charset 解析失敗 : " + e);
        }catch(IOException e){
            System.out.println("HTTP 接続失敗 : " + e);
        }finally{
            if(inputStream != null){
                try{
                    inputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }



        return strings[0];
    }


    @Override
    protected void onPostExecute(String urlString) {
        super.onPostExecute(urlString);
    }



    private String getInputReader(InputStream stream) throws UnsupportedEncodingException, IOException{
        StringBuffer stringBuffer = new StringBuffer();
        String line = "";
        BufferedReader bufferdReader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        while((line = bufferdReader.readLine()) != null){
//			LogMy.i(TAG, line);
            stringBuffer.append(line);
        }
        stream.close();
        return stringBuffer.toString();
    }
}
