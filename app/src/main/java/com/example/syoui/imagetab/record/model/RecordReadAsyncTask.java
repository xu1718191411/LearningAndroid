package com.example.syoui.imagetab.record.model;

import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.AsyncTask;


/**
 * Created by syoui on 2017/10/27.
 */

public class RecordReadAsyncTask extends AsyncTask<Integer, Intent, Integer> {

    private static final int AUDIO_SAMPLE_FREQ = 44100;
    private static final int AUDIO_BUFFER_SIZE = AudioRecord.getMinBufferSize(
            AUDIO_SAMPLE_FREQ, AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT);
    private static final int FRAME_BUFFER_SIZE = AUDIO_BUFFER_SIZE / 2 / 10; // 10FPS
    private short data[] = new short[FRAME_BUFFER_SIZE];
    private Boolean isPause = true;
    private AudioRecord mAudioRecord = null;
    static String TAG = "VoiceRecord:";


    private AudioRecord audioRecord = null;
    public RecordReadAsyncTask() {
        if(mAudioRecord == null){
            mAudioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,AUDIO_SAMPLE_FREQ,AudioFormat.CHANNEL_IN_MONO,AudioFormat.ENCODING_PCM_16BIT,AUDIO_BUFFER_SIZE);
        }
    }

    private void readFromData(){
        if(mAudioRecord.getRecordingState() != AudioRecord.RECORDSTATE_RECORDING){
            return;
        }
        while(audioRecord.read(data,0,FRAME_BUFFER_SIZE) == FRAME_BUFFER_SIZE){
            //Log.d(TAG,data.length+"");
        }
    }




    @Override
    protected Integer doInBackground(Integer... values) {
        if(audioRecord == null){
            return new Integer(0);
        }


        if(mAudioRecord.getRecordingState() == AudioRecord.RECORDSTATE_RECORDING){
            audioRecord.stop();
        }else{
            audioRecord.startRecording();
            readFromData();
        }




        return new Integer(1);
    }

    @Override
    protected void onProgressUpdate(Intent... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
    }
}

