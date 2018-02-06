package com.example.syoui.imagetab.java_knowledge;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by syoui on 2018/02/06.
 */

public class Contribution implements Serializable{
    String category;
    Date time;
    String title;
    String content;

    public Contribution(){
        category = "general";
        time = new Date();
        title = "general";
    }


    public String getCategory() {
        return category;
    }

    public String getTime() {
        return time.toString();
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String toString() {
        return "Contribution-->Title:   " + getTitle() + "\nCategory:   " + getCategory() +"\nContent:n" + getContent() + "\nDate:   " + getTime();
    }



}
