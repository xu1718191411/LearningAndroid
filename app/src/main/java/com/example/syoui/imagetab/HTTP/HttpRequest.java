package com.example.syoui.imagetab.HTTP;

import android.os.AsyncTask;

import com.example.syoui.imagetab.Util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest extends AsyncTask {


    HttpURLConnection mHttpURLConnection = null;
    URL mUrl = null;
    int mResponseCode = 0;
    OutputStream mOutputStream = null;
    InputStream mInputStream = null;
    InputStream mErrorStream = null;

    public HttpRequest(String url) {
        try{
            mUrl = new URL(url);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try{

            mHttpURLConnection = (HttpURLConnection) mUrl.openConnection();
            mHttpURLConnection.setRequestMethod("GET");
            mHttpURLConnection.connect();

            mResponseCode = mHttpURLConnection.getResponseCode();
            mOutputStream = mHttpURLConnection.getOutputStream();


            if (mResponseCode == 200) {
                mInputStream = mHttpURLConnection.getInputStream();
            } else {
                mErrorStream = mHttpURLConnection.getErrorStream();
            }

            Util.getInputReader(mInputStream);

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {

        try{
            mInputStream.close();
            mErrorStream.close();
            mOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        super.onPostExecute(o);
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }
}
