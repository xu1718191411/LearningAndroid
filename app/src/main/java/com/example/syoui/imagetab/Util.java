package com.example.syoui.imagetab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Util {

    public static List<String> converStringArray2StringArrayList(String[] stringArr){
        List<String> myList = new ArrayList();
        Collections.addAll(myList, stringArr);
        return  myList;
    }
}
