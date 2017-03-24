package com.example.rmontoya.backgroundcounter.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import static java.lang.Thread.interrupted;
import static java.lang.Thread.sleep;

public class CounterRunnable implements Runnable {

    private Handler handler;
    private int counterValue;
    public static final String COUNTER_VALUE = "count";


    public CounterRunnable(Handler handler, int counterValue){
        this.handler = handler;
        this.counterValue = counterValue;
    }

    @Override
    public void run() {
        try {
            while (!interrupted()) {
                sleep(1000);
                counterValue++;
                Message resultMessage = new Message();
                Bundle bundle = new Bundle();
                bundle.putInt(COUNTER_VALUE, counterValue);
                resultMessage.setData(bundle);
                handler.sendMessage(resultMessage);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
