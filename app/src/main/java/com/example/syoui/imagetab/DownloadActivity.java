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

import com.example.syoui.imagetab.HTTP.MyHttpReponse;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DownloadActivity extends AppCompatActivity {

    private HttpURLConnection httpUrlConnection = null;
    private AsyncTask<Param,Void,Result> asyncTask = null;
    private InputStream mInputStream = null;
    private ByteArrayOutputStream mOutputStream = new ByteArrayOutputStream();
    private Handler mHandler = null;
    private static final String zipFileName  = "event.zip";
    private static final String zipFileFolder = "extract";
    private static final int SUCCESS_DOWNLOAD_AND_EXTRACT = 0x01;
    private Param param;
    class Param{
        String address;
        String password;
    }

    class Result{
        boolean result;
        String message;
        String extractFolderPath;
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
                            MyHttpReponse myHttpResponse = download(param.address);
                            if(myHttpResponse.getResponseCode() != 200){
                                result.message = myHttpResponse.getMessageToString("utf-8");
                                result.result = false;
                                return result;
                            }

                            if(!writeIntoCache(myHttpResponse.getMessage())){
                                result.message = "failure in writting the data";
                                result.result = false;
                                return result;
                            }

                            Result resultOfExtract = extractZipFile(param.password);
                            if(!resultOfExtract.result){
                                return resultOfExtract;
                            }

                            ArrayList<File> fileList = readFileListFromExtractFolder(resultOfExtract.extractFolderPath);

                            //String res = readFromCacheByBuffer();
                            String res = new String(readFromCacheByByte(fileList.get(0).getAbsolutePath()),"utf-8");
                            Log.d("String", res);

                            result.result = true;
                            result.message = res;
                            return result;

                        }catch (Exception e){
                            e.printStackTrace();
                            result.result = false;
                            result.message = e.getMessage();
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

    private MyHttpReponse download(String address){

                    try{

                    MyHttpReponse myHttpResponse = new MyHttpReponse();

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
                    }

                    myHttpResponse.setResponseCode(responseCode);

                    byte[] tmp = new byte[1024];
                    int len = 0;
                    while ((len = mInputStream.read(tmp)) > 0) {
                        mOutputStream.write(tmp, 0, len);
                    }


                    mInputStream.close();
                    mOutputStream.close();
                    myHttpResponse.setMessage(mOutputStream.toByteArray());
                    return myHttpResponse;

                    }catch (Exception e){
                        MyHttpReponse myHttpResponse = new MyHttpReponse();
                        myHttpResponse.setMessage(e.getMessage().getBytes());
                        myHttpResponse.setResponseCode(-1);
                        return myHttpResponse;
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

    private String readFromCacheByBuffer(String fileName) throws Exception {

            StringBuffer sb = new StringBuffer();
            FileInputStream fileInputStream = openFileInput(fileName);

            BufferedInputStream bufferdInputStream = new BufferedInputStream(fileInputStream);
            InputStreamReader inputStreamReader = new InputStreamReader(bufferdInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            while((str = bufferedReader.readLine()) != null){
                    sb.append(str);
            }

            return sb.toString();

    }

    private ArrayList<File> readFileListFromExtractFolder(String path){
        File folder = new File(path);
        ArrayList<File> fileList = new ArrayList<File>();
        if(folder.isFile()){
            fileList.add(new File(path));
            return fileList;
        }
        File[] files = folder.listFiles();

        for(int i=0;i<files.length;i++){
            if(files[i].isFile()){
                Log.d("aaa",files[i].getAbsolutePath());
                fileList.add(files[i]);
            }
        }

        return fileList;

    }

    private byte[] readFromCacheByByte(String filePath){
            //FileInputStream fileInputStream = openFileInput(txtFileName);

            try{
                //File file = new File(this.getApplicationContext().getFilesDir().getAbsolutePath()+ zipFileFolder + "/" + txtFileName);
                File file = new File(filePath);
                FileInputStream fileInputStream = new FileInputStream(file);

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] tmp = new byte[1024];
                int len = 0;
                while((len = fileInputStream.read(tmp)) > 0){
                    byteArrayOutputStream.write(tmp,0,len);
                }
                return byteArrayOutputStream.toByteArray();
            }catch (FileNotFoundException e){
                return e.getMessage().getBytes();
            }catch (SecurityException e){
                return e.getMessage().getBytes();
            }catch(Exception e){
                return new String("Exception happened at readFromCacheByByte").getBytes();
            }


    }



    private Result extractZipFile(String password){

            Result result = new Result();
            try{

            String sourceFilePath = this.getApplicationContext().getFilesDir().getAbsolutePath()+ "/" + zipFileName;
            String destinationFilePath = this.getApplicationContext().getFilesDir().getAbsolutePath()+ zipFileFolder;


            ZipFile zipFile = new ZipFile(sourceFilePath);
            if(!zipFile.isValidZipFile()){
                result.result = true;
                result.extractFolderPath = sourceFilePath;
                return result;
            }

            if(zipFile.isEncrypted()){
                zipFile.setPassword(password);
            }

            zipFile.extractAll(destinationFilePath);
                result.result = true;
                result.message = "ok";
                result.extractFolderPath = destinationFilePath;
            }catch (ZipException e){
                result.result = false;
                result.message = e.getMessage();

            }catch (Exception e){
                result.result = false;
                result.message = e.getMessage();

            }

            return result;



    }


}



