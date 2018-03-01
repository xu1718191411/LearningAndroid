package com.example.syoui.imagetab;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadActivity extends AppCompatActivity {

    private HttpURLConnection httpUrlConnection = null;
    private AsyncTask<Param,Void,Result> asyncTask = null;
    private InputStream mInputStream = null;
    private ByteArrayOutputStream mOutputStream = new ByteArrayOutputStream();
    private Handler mHandler = null;
    private static final String zipFileName  = "event.zip";
    private static final String txtFileName = "event.txt";
    private static final int SUCCESS_DOWNLOAD_AND_EXTRACT = 0x01;
    private static final int DOWNLOAD_FAILURE = 0x02;
    private static final int EXTRACT_FAILURE = 0x03;
    private static final int READ_FAILURE = 0x04;
    private static final int WRITE_FAILURE = 0x05;
    private Param param;
    class Param{
        String address;
        String password;
    }

    class Result{
        boolean result;
        String message;
    }

    private EditText addressEditText = null;
    private EditText passwordEditText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        addressEditText = (EditText) findViewById(R.id.address);
        passwordEditText = (EditText) findViewById(R.id.password);




        Button download = (Button) findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                asyncTask = new AsyncTask<Param, Void, Result>() {
                    @Override
                    protected Result doInBackground(Param... params) {

                        Result result = new Result();
                        try{

                            Param param = params[0];
                            byte[] total = download(param.address);
                            if(total.length == 0){
                                result.message = "failure in getting data from server";
                                result.result = false;
                                return result;
                            }

                            if(!writeIntoCache(total)){
                                result.message = "failure in writting the data";
                                result.result = false;
                                return result;
                            }

                            extractZipFile(param.password);

                            String res = readFromCache();
                            Log.d("String", res);

                            result.result = true;
                            result.message = res;
                            return result;

                        }catch (Exception e){
                            e.printStackTrace();
                            result.result = false;
                            if(e instanceof ZipException){
                                result.message = "password is incorrect";
                            }else{
                                result.message = "failure";
                            }
                            return result;
                        }


                    }

                    @Override
                    protected void onPostExecute(Result result) {
                        super.onPostExecute(result);
                            SendMessageToMainThread(result.message,SUCCESS_DOWNLOAD_AND_EXTRACT);
                    }


                    @Override
                    protected void onCancelled() {
                        super.onCancelled();
                        asyncTask = null;
                    }

                };

                param = new Param();
                param.address = addressEditText.getText().toString();
                param.password = passwordEditText.getText().toString();
                asyncTask.execute(param);
            }
        });



        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {


                Bundle data = msg.getData();
                String message = data.getString("message");

                TextView tv = (TextView) findViewById(R.id.message);
                tv.setText(message);

            }

        };
    }

    private void SendMessageToMainThread(String msg,int code){
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("message",msg);
        message.setData(bundle);
        message.what = code;
        mHandler.sendMessage(message);
    }

    private byte[] download(String address){

                    try{

                    //String address = "https://www.kernel.org/doc/Documentation/trace/events.txt";
                    //String address = "http://192.168.200.24:8080/event.zip";
                    URL url = new URL(address);
                    httpUrlConnection = (HttpURLConnection) url.openConnection();


                    httpUrlConnection.setRequestMethod("GET");
                    httpUrlConnection.setRequestProperty("Content-Type", "application/zip; charset=utf-8");
                    httpUrlConnection.setRequestProperty("Accept-Language", "en");
                    httpUrlConnection.setRequestProperty("X-Navicon-Application", "imagTab");
                    httpUrlConnection.setRequestProperty("X-Navicon-Os", "Android");

                    int responseCode = httpUrlConnection.getResponseCode();

                    Log.d("ResponseCode", responseCode + "");

                    if (responseCode == 200) {
                        mInputStream = httpUrlConnection.getInputStream();
                    } else {
                        mInputStream = httpUrlConnection.getErrorStream();
                        return new byte[0];
                    }

                    byte[] tmp = new byte[1024];
                    int len = 0;
                    while ((len = mInputStream.read(tmp)) > 0) {
                        mOutputStream.write(tmp, 0, len);
                    }


                    byte[] total = mOutputStream.toByteArray();

                    mInputStream.close();
                    mOutputStream.close();
                    return total;

                    }catch (Exception e){
                        return new byte[0];
                    }


    }


    private Boolean writeIntoCache(byte[] total) {


            try{
                InputStream in = new ByteArrayInputStream(total);
                FileOutputStream out = openFileOutput(zipFileName,this.getApplication().MODE_PRIVATE);
                byte[] tmp = new byte[1024];
                int len = 0;
                while((len = in.read(tmp)) > 0){
                    out.write(tmp,0,len);
                }
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }




    }

    private String readFromCache() throws Exception {




            StringBuffer sb = new StringBuffer();
            FileInputStream fileInputStream = openFileInput(txtFileName);

            BufferedInputStream bufferdInputStream = new BufferedInputStream(fileInputStream);
            InputStreamReader inputStreamReader = new InputStreamReader(bufferdInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            while((str = bufferedReader.readLine()) != null){
                    sb.append(str);
            }

            return sb.toString();

    }


    private void extractZipFile(String password) throws ZipException{

            String sourceFilePath = this.getApplicationContext().getFilesDir().getAbsolutePath()+ "/" + zipFileName;
            String destinationFilePath = this.getApplicationContext().getFilesDir().getAbsolutePath();


            ZipFile zipFile = new ZipFile(sourceFilePath);
            if(zipFile.isEncrypted()){
                zipFile.setPassword(password);
            }

            zipFile.extractAll(destinationFilePath);

    }



}



