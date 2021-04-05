package com.example.workout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class WorkoutDetailFragment extends Fragment {

    //индификатор комплекса упражнений, выбраного пользователя
    private long workoutId;

    //востанавливаем значение workoutId после уничтожения фрагмента
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            workoutId = savedInstanceState.getLong("workoutId");
        }
    }

    //вызывается, когда Андроид потребуется макет фрагмента
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // макет фрагмента задается методом inflate(), заполняет макет фрагмента,
        //преобразуя разметку XML в объекты Java
        //аргумент container содержит объект, в котором должен быть вставлен макет фрагмента
        //в последнем параметре передается объект Bundle
        return inflater.inflate(
                R.layout.fragment_workout_detail,
                container,
                false);
    }

    @Override
    public void onStart() {
        super.onStart();
        //получаем корневой объект View фрагмента
        View view = getView();
        //Полученный объект используем для получения ссылко на надписи
        if (view != null){
            TextView title = (TextView) view.findViewById(R.id.textTitle);
            Workout workout = Workout.workouts[(int) workoutId];
            title.setText(workout.getName());
            TextView description = (TextView) view.findViewById(R.id.textDescription);
            description.setText(workout.getDescription());
        }
    }

    //значение workoutId сохраняется в объекте savedInstanceState перед уничтожением фрагмента
    //и будет востановлено в методе onCreate()
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putLong("workoutId", workoutId);
    }

    public void setWorkoutId(long id){
        this.workoutId = id;
    }
}