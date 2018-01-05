package com.example.syoui.imagetab.controller;

import android.os.AsyncTask;
import android.util.Log;

import com.example.syoui.imagetab.model.RequestForAITalk;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by syoui on 2017/11/15.
 */

public class HttpResponsAsync  extends AsyncTask<Void, Void, String>  {


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // doInBackground前処理
    }

    @Override
    protected String doInBackground(Void... params)  {

        RequestForAITalk req = new RequestForAITalk();
        req.setUsername("MA2017");
        req.setPassword("MnYrnxhH");
        req.setText("こんにちは。今日はいい天気ですね。くそう！");
        req.setSpeaker_name("nozomi");
        req.setVolume("1.0");
        req.setSpeed("1.0");
        req.setPitch("1.0");
        req.setRange("1.0");
        req.setExt("ogg");
        req.setStyle("{\"j\":\"1.0\"}");

        ObjectMapper mapper = new ObjectMapper();
        //String json = null;
        String json = "username=MA2017&password=MnYrnxhH&text=%E3%81%93%E3%82%93%E3%81%AB%E3%81%A1%E3%81%AF%E3%80%82%E4%BB%8A%E6%97%A5%E3%81%AF%E3%81%84%E3%81%84%E5%A4%A9%E6%B0%97%E3%81%A7%E3%81%99%E3%81%AD&speaker_name=nozomi&style=%7B%22j%22%3A%221.0%22%7D&volume=1.0&speed=1.0&pitch=1.0&range=1.0&ext=ogg./output_nozomi";
//        try{
//            json = mapper.writeValueAsString(req);
//        }catch (JsonProcessingException e){
//            e.printStackTrace();
//        }


        HttpURLConnection con = null;
        URL url = null;
        //String urlStr = "http://webapi.aitalk.jp/webapi/v2/ttsget.php";
        String urlStr = "http://127.0.0.1:1337";
        String data = json;
        int length = 0;
        try {
            length = data.getBytes("UTF-8").length;
            Log.d("HTTP_POST", "length: " + length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        try {
            url = new URL(urlStr);
            con = (HttpURLConnection) url.openConnection();

            // キャッシュを使用しない
            con.setUseCaches(false);

            // リダイレクトを許可しない
            con.setInstanceFollowRedirects(false);

            // データを読み取る
            con.setDoInput(true);

            // データを書き込む
            con.setDoOutput(true);

            // setDoOutputをtrueにするとPOST送信になるので必要ない
            //con.setRequestMethod("POST");

            // Transfer-Encoding => chunked を使わない
            con.setFixedLengthStreamingMode(length);

            // Transfer-Encoding => chunked を使う
            // con.setChunkedStreamingMode(0);

            // タイムアウト
            con.setReadTimeout(10000);
            con.setConnectTimeout(10000);


            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // 接続
            con.connect(); //




            // データの書き込み
            OutputStream out = null;
            try {
                out = con.getOutputStream();
                out.write(data.getBytes("UTF-8"));
                out.flush();
            } catch (IOException e) {
                // POST送信エラー
                e.printStackTrace();
                return null;
            } finally {
                if (out != null) {
                    out.close();
                }
            }






            // ステータスコードの取得
            final int status = con.getResponseCode();
            Log.d("HTTP_POST", "ResponseCode" + status);
            if (status == HttpURLConnection.HTTP_OK) {
                // 正常

                // データの読み取り
                InputStream in = null;
                try {
                    in = con.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                    String st = null;
                    while ( (st = br.readLine()) != null) {
                        Log.d("HTTP_POST", st);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (in != null) {
                        in.close();
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }

        return null;

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        // doInBackground後処理
    }


}
