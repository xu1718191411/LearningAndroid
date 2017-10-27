package com.example.syoui.imagetab.record.controller;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

/**
 * Created by syoui on 2017/10/27.
 */

public class VoiceRecord {

    private AudioRecord mAudioRecord = null;
    private static final int AUDIO_SAMPLE_FREQ = 44100;
    private static final int AUDIO_BUFFER_SIZE = AudioRecord.getMinBufferSize(
            AUDIO_SAMPLE_FREQ, AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT);
    private static final int FRAME_BUFFER_SIZE = AUDIO_BUFFER_SIZE / 2 / 10; // 10FPS
    private short data[] = new short[FRAME_BUFFER_SIZE];
    private Boolean isPause = true;
    static String TAG = "VoiceRecord:";


    public VoiceRecord() {
        initialAudioRecord();
    }

    private void initialAudioRecord(){
        //int audioSource, int sampleRateInHz, int channelConfig, int audioFormat,int bufferSizeInBytes
        mAudioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,AUDIO_SAMPLE_FREQ,AudioFormat.CHANNEL_IN_MONO,AudioFormat.ENCODING_PCM_16BIT,AUDIO_BUFFER_SIZE);
    }

    public void startRecord(){
        mAudioRecord.startRecording();
        readFromData();
        isPause = false;
    }

    public void stopRecord(){
        mAudioRecord.stop();
        isPause = true;
    }

    private void readFromData(){
        while(mAudioRecord.read(data,0,FRAME_BUFFER_SIZE) == FRAME_BUFFER_SIZE){
            //Log.d(TAG,data.length+"");
        }
    }

    public Boolean getRecordingStatus(){
        if(mAudioRecord.getRecordingState() == AudioRecord.RECORDSTATE_RECORDING){
                return true;
        }else{
                return false;
        }
    }

}
