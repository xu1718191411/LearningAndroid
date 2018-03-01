package com.example.syoui.imagetab;

/**
 * Created by syoui on 2018/03/01.
 */
public class ReadException extends Exception{
    String message = "read proccess failed";
    ReadException(){

    }

    public ReadException(String msg) {
        super(msg);
    }

    public ReadException(String message, Throwable cause) {
        super(message, cause);
    }

}
