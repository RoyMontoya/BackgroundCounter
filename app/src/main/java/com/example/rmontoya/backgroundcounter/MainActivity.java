package com.example.rmontoya.backgroundcounter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rmontoya.backgroundcounter.runnable.CounterRunnable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startButton;
    private Button restartButton;
    private Button stopButton;
    private int counterValue = 0;
    private TextView counterResultText;
    private Handler handler;
    private Thread thread;
    private CounterRunnable counterRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        setOnClickListeners();
        createHandler();

    }

    private void createHandler() {
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                counterValue = msg.getData().getInt(CounterRunnable.COUNTER_VALUE);
                counterResultText.setText(String.valueOf(counterValue));
            }
        };
    }

    private void setOnClickListeners() {
        startButton.setOnClickListener(this);
        restartButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
    }

    private void setViews() {
        counterResultText = (TextView) findViewById(R.id.counter_text);
        startButton = (Button) findViewById(R.id.start_button);
        restartButton = (Button) findViewById(R.id.restart_button);
        stopButton = (Button) findViewById(R.id.stop_button);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopThread();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_button:
                if (thread == null) startNewThread();
                break;
            case R.id.restart_button:
                stopThread();
                counterValue = 0;
                counterResultText.setText(String.valueOf(counterValue));
                startNewThread();
                break;
            case R.id.stop_button:
                disableButtons(false);
                stopThread();
                break;
        }
    }

    private void disableButtons(boolean isRunning) {
        startButton.setEnabled(!isRunning);
        stopButton.setEnabled(isRunning);
    }

    private void startNewThread() {
        counterRunnable = new CounterRunnable(handler, counterValue);
        thread = new Thread(counterRunnable);
        thread.start();
        disableButtons(true);
    }

    private void stopThread() {
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
    }

}
