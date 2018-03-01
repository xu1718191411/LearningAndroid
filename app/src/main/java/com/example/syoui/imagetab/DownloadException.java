package com.example.syoui.imagetab;

/**
 * Created by syoui on 2018/03/01.
 */
public class DownloadException extends Exception{
    String message = "Download process failed";

    DownloadException(String message){
        super(message);
    }

    public DownloadException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
