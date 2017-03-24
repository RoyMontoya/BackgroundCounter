package com.example.rmontoya.backgroundcounter.handler;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

public class CounterHandler extends HandlerThread {

    Handler handler;

    public CounterHandler() {
        super(CounterHandler.class.getName());
    }


    public void count(){

    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };
    }
}
