package com.example.syoui.imagetab.blockchain;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.syoui.imagetab.R;

import java.util.ArrayList;

public class BlockChainActivity extends AppCompatActivity {
    ArrayList<Block> blockChain;
    ArrayList<BlockTransaction> blockTransactions;
    ListView blockChainViewList;
    BlockChainListAdapter blockChainListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_chain);

        start();
    }

    private void start(){
        initilizeArray();
        initilizeTransaction();
        dealWithTransaction();
        displayBlockChain();
    }

    private void restart(){
        clearArray();
        dealWithTransaction();
        displayBlockChain();
    }
    private void dealWithTransaction(){
        for(BlockTransaction bt:blockTransactions){
            addIntoBlockChain(bt);
        }
    }

    private void addIntoBlockChain(BlockTransaction transaction){
        Block newBlock = new Block(getNewIndex(),transaction,getPreviousHash());
        blockChain.add(newBlock);
    }

    private int getNewIndex(){

        if(blockChain == null || blockChain.size() == 0){
            return 1;
        }else{
            return blockChain.size();
        }
    }

    private String getPreviousHash(){
        if(blockChain == null || blockChain.size() == 0){
            return "0";
        }else{
            return blockChain.get(blockChain.size()-1).getBlockHash();
        }
    }

    private void displayBlockChain(){
        for(int i=0;i<blockChain.size();i++){
            Log.d("Block"+i+":",blockChain.get(i).getBlockHash());
        }

        blockChainViewList = findViewById(R.id.blockChainList);
        blockChainListAdapter = new BlockChainListAdapter(this.getApplicationContext());
        blockChainListAdapter.setmArrayList(blockChain);
        blockChainViewList.setAdapter(blockChainListAdapter);

        blockChainListAdapter.setmOnChangeBlockListener(new BlockChainListAdapter.OnChangeBlockListener(){

            @Override
            public void onBlockChanged(int index) {
                int amount = blockTransactions.get(index).getAmount();
                blockTransactions.get(index).setAmount(++amount);
                restart();
            }
        });
    }


    private void initilizeTransaction(){
        blockTransactions.add(new BlockTransaction("Wan","Join",10));
        blockTransactions.add(new BlockTransaction("Bob","Lilly",20));
        blockTransactions.add(new BlockTransaction("Bob","Gemo",20));
        blockTransactions.add(new BlockTransaction("Kelvin","david",70));
        blockTransactions.add(new BlockTransaction("Leo","Adam",10));
        blockTransactions.add(new BlockTransaction("Cheery","Sophia",100));
    }

    private void initilizeArray(){
        blockChain = new ArrayList<Block>();
        blockTransactions = new ArrayList<BlockTransaction>();
    }

    private void clearArray(){
        blockChain.clear();
    }
}
