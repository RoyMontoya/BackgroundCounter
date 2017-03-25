package com.example.rmontoya.backgroundcounter.runnable;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import static java.lang.Thread.interrupted;
import static java.lang.Thread.sleep;

public class CounterRunnable implements Runnable {

    private Handler handler;
    private int counterValue;
    public static final String COUNTER_VALUE = "count";

    public CounterRunnable(Handler handler, int counterValue) {
        this.handler = handler;
        this.counterValue = counterValue;
    }

    @Override
    public void run() {
        try {
            while (!interrupted()) {
                sleep(1000);
                counterValue++;
                handler.sendMessage(buildMessage());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    private Message buildMessage() {
        Message resultMessage = new Message();
        Bundle bundle = new Bundle();
        bundle.putInt(COUNTER_VALUE, counterValue);
        resultMessage.setData(bundle);
        return resultMessage;
    }
}
