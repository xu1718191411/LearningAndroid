package com.example.syoui.imagetab;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeActivity extends YouTubeFailureRecoveryActivity {

    private String parseUrl = "http://172.21.32.104:8080/youtube.html";
    private MyAsyncTask asyncTask;
    private YouTubePlayer mYouTubePlayer = null;
    private Bundle mBundle = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtubeView);
        youTubeView.initialize("test", this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        if (!wasRestored) {
                //player.loadVideo("tnhjbOwLaLM");
        }

        mYouTubePlayer = player;
        asyncTask = new MyAsyncTask(parseUrl);
        asyncTask.setmLoadYoutubeUrlListener(new MyAsyncTask.LoadYoutubeUrlListener(){

            @Override
            public void onLoad(String videoId) {
                if(mYouTubePlayer != null){
                    mYouTubePlayer.loadVideo(videoId);
                }
            }
        });

        asyncTask.execute();
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtubeView);
    }



    static public String readHtmlContentFromUrl(String url){
        StringBuilder buf=new StringBuilder();
        try{
            URL des = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(des.openStream()));
            String str;

            while ((str=in.readLine()) != null) {
                buf.append(str);
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        String res = buf.toString();
        return  res;


    }
}


class MyAsyncTask extends  AsyncTask<Void, Void, String>{
    private String parseUrl = null;
    private LoadYoutubeUrlListener mLoadYoutubeUrlListener = null;
    public interface LoadYoutubeUrlListener{
        void onLoad(String videoId);
    }

    public void setmLoadYoutubeUrlListener(LoadYoutubeUrlListener _mLoadYoutubeUrlListener) {
        this.mLoadYoutubeUrlListener = _mLoadYoutubeUrlListener;
    }

    public MyAsyncTask(String parseUrl) {
        this.parseUrl = parseUrl;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            return YoutubeActivity.readHtmlContentFromUrl(parseUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String html) {
        if(html == null) return;
        Matcher matcher1 = Pattern.compile("https?:\\/\\/www\\.youtube\\.com\\/embed\\/([^\\?]*)\\?").matcher(html);
        boolean tmpFind1 = matcher1.find();
        int count = matcher1.groupCount();
        if(tmpFind1 && matcher1.groupCount()>=0){
            String matchUrlPattern = matcher1.group(0);
            String videoId = matcher1.group(1);
            String cc = videoId;
            if(mLoadYoutubeUrlListener != null){
                mLoadYoutubeUrlListener.onLoad(videoId);
            }
        }
    }
}

