package com.example.workout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

//активность рассширяется от класса ListFragment
public class WorkoutListFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //создаем строковый массик с названиями комплексов упражнений
        String[] names = new String[Workout.workouts.length];
        for (int i = 0; i < names.length; i++){
            names[i] = Workout.workouts[i].getName();
        }
        //создаем адаптер массива
        //получаем контекст от LayoutInflater
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                inflater.getContext(), android.R.layout.simple_list_item_1,
                names);
        //создаем адаптер массива со списковым представлением
        setListAdapter(adapter);

        // вызов метода суер класса предоставляет макет по умолчанию для ListFragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}