package com.example.syoui.imagetab;

/**
 * Created by syoui on 2018/03/01.
 */
public class WriteException extends Exception{
    String message = "write success failed";
    WriteException(){

    }

    public WriteException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
