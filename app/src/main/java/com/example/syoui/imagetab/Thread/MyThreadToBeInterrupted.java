package com.example.syoui.imagetab.Thread;

public class MyThreadToBeInterrupted extends Thread {
    @Override
    public void run() {
        super.run();
        try{

            Boolean isRunning = true;

            while(isRunning){
                sleep(1);
            }

        }catch (Exception e){
            e.printStackTrace();
            return;

        }
    }
}
