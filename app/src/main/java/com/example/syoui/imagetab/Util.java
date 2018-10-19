package com.example.syoui.imagetab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Util {

    public static List<String> converStringArray2StringArrayList(String[] stringArr){
        List<String> myList = new ArrayList();
        Collections.addAll(myList, stringArr);
        return  myList;
    }


    public static String getInputReader(InputStream stream) throws UnsupportedEncodingException, IOException {
        StringBuffer stringBuffer = new StringBuffer();
        String line = "";
        BufferedReader bufferdReader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        while((line = bufferdReader.readLine()) != null){
//			LogMy.i(TAG, line);
            stringBuffer.append(line);
        }
        stream.close();
        return stringBuffer.toString();
    }
}
