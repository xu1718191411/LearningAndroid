package com.example.syoui.imagetab.blockchain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by syoui on 2018/02/08.
 */

public class Block {
    private BlockTransaction transaction;
    private String previousHash;
    private String blockHash;
    private String timeStamp;
    private int index;

    public Block(int i,BlockTransaction transaction,String previousHash) {
        this.index = i;
        this.transaction = transaction;
        this.timeStamp = transaction.getDate().getTime()+"";
        this.previousHash = previousHash;
        String content = "{'index':" + index + ",'timestamp':'" + timeStamp + "','transaction':'" + transaction.toString() + "','previous_hash':'"  + previousHash +"'}";
        String contentsHash = convertStringToSha256(content);
        this.blockHash = contentsHash;
    }

    public BlockTransaction getTransaction() {
        return transaction;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getBlockHash() {
        return blockHash;
    }

    private String convertStringToSha256(String str){
        MessageDigest md = null;
        try{
            md = MessageDigest.getInstance("SHA-256");
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        md.update(str.getBytes());
        byte[] digestResult = md.digest();

        StringBuffer sb = new StringBuffer();
        for(int i=0;i<digestResult.length;i++){
                String res = Integer.toHexString(digestResult[i] & 0XFF);
                if(res.length() == 1){
                    res = "0" + res;
                }

                sb.append(res);
        }

        return sb.toString();

    }
}


