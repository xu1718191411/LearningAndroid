package com.example.syoui.imagetab.java_knowledge;

/**
 * Created by syoui on 2018/02/06.
 */

public class MyContribution extends Contribution{
    public String subTitle;

    public MyContribution(String _title,String _content,String _subTitle){
        title = _title;
        content = _content;
        subTitle = _subTitle;
        category = "training";
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String toString(){
        return "MyContribution-->Title:   " + getTitle() + "\nsubtitle:   " + getSubTitle() + "\nCategory:   " + getCategory() +"\nContent:\n" + getContent() + "\nDate:   " + getTime();
    }
}
