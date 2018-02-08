package com.example.syoui.imagetab.blockchain;

import java.util.Date;

/**
 * Created by syoui on 2018/02/08.
 */

public class BlockTransaction {
    private String from;
    private String to;
    private int amount;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BlockTransaction(String from, String to, int amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.date = new Date();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String toString(){
        return "{from:'" + from + "',to:'" + to + "',amount:" + amount + "}";
    }
}
