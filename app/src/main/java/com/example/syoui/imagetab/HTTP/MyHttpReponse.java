package com.example.syoui.imagetab.HTTP;

/**
 * Created by syoui on 2018/03/02.
 */

public class MyHttpReponse {
    byte[] message;
    int responseCode;

    public MyHttpReponse() {

    }

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }

    public String getMessageToString(String encode){

        if(message == null) return "";

        try{
            return new String(message,encode);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}
