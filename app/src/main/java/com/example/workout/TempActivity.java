package com.example.workout;

import android.annotation.SuppressLint;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TempActivity extends AppCompatActivity {

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        //фрагмент добавляется только, если активность создается заново после уничтожения
        if(savedInstanceState == null){
            StopwatchFragment stopwatch = new StopwatchFragment();
            //начало транзакции фрагмента
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //добавление секундомера и включение транзакции в стек возврата
            ft.add(R.id.stopwatch_container, stopwatch);
            ft.addToBackStack(null);
            //включение переходной анимации для транзакции
            ft.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            //закрепление транзакции
            ft.commit();
        }
    }
}