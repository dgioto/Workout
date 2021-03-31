package com.example.workout;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//активность рассширяется от класса ListFragment
public class WorkoutListFragment extends ListFragment {

    //создаем интерфейс слушатель к фрагменту
    static interface  Listener{
        void  itemClicked(long id);
    }
    //сохраняем ссылку на активность, с которой связывается WorkoutListFragment
    private Listener listener;

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

    //вызывается когда фрагмент связывается с активностью
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (Listener) context;
    }

    //Передает слушателю индификатор комплекса упражнений, выбранного пользователя
    //метод получает 4 параметра:
    // списковое представление, выбранный вариант списка, его позицию, индификатор записи
    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id) {
        if (listener != null)
            //сообщает слушателю,когда пользователь выбрал вариант в ListView
            listener.itemClicked(id);
    }
}