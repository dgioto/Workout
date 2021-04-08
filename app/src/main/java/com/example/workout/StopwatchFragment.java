package com.example.workout;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

//реализация OnCLickListener  фрагментом
public class StopwatchFragment extends Fragment implements View.OnClickListener {

    private int seconds = 0;
    private boolean running;
    //переменная для проверки, работал ли секундомер перед вызовом метода onStop()
    private  boolean wasRunning;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //сохраняем переменные в объект Bundle
        if (savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
    }

    //сохранить состояние секундомера, если он готовится к уничтожению
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_stopwatch,
                container, false);
        runTimer(layout);

        //связывание OnClickListener с кнопками
        Button startButton = (Button) layout.findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        Button stopButton = (Button) layout.findViewById(R.id.stop_button);
        stopButton.setOnClickListener(this);
        Button resetButton = (Button) layout.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);

        return layout;
    }

    // переносим код в метод onPause() из onStop(), что бы секундомер приостановливал
    // работу во время сварачивания приложения и приостановки
    @Override
    public void onPause() {
        super.onPause();
        //сохранить информацию о томб работал ли секундомер на момент вызова метода onStop()
        wasRunning = running;
        running = false;
    }

    // переносим код в метод onResume() из onStart(), что бы секундомер приостановливал
    // работу во время сварачивания приложения и приостановки
    @Override
    public void onResume() {
        super.onResume();
        //если секундомер работал, то отсчет времени возобновляется
        if (wasRunning) running = true;
    }

    //сохранить состояние секундомера, если он готовится к уничтожению
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    private void onClickStart(){
        running = true;
    }

    private void onClickStop(){
        running = false;
    }

    private void onClickReset(){
        running = false;
        seconds = 0;
    }

    //обновление показаний таймера
    private void runTimer(View view){
        final TextView timeView = (TextView) view.findViewById(R.id.time_view);
        //объект для выполнения кода в другом программном потоке
        final Handler handler = new Handler();
        //запускаем отдельный поток
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if(running) seconds++;
                //повторное выполнение кода с отсрочкой в 1 секунду
                handler.postDelayed(this, 1000);
            }
        });
    }

    //реализация метода onClick для интерфейса OnClickListener
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_button:
                onClickStart();
                break;
            case R.id.stop_button:
                onClickStop();
                break;
            case R.id.reset_button:
                onClickReset();
                break;
        }
    }
}