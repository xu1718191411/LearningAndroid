package com.example.syoui.imagetab.record.controller;

import com.example.syoui.imagetab.record.model.RecordReadAsyncTask;

/**
 * Created by syoui on 2017/10/27.
 */

public class VoiceRecord {


    private RecordReadAsyncTask recordTask = null;

    public VoiceRecord() {
        initialAudioRecord();
    }

    private void initialAudioRecord(){
        //int audioSource, int sampleRateInHz, int channelConfig, int audioFormat,int bufferSizeInBytes
        recordTask = new RecordReadAsyncTask();
    }

    public void startRecord(){
        recordTask.execute(new Integer(1));
    }




}
