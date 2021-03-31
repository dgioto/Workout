package com.example.workout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    //константа для передачи идентификатора из MainActivity в DetailActivity
    //что бы конкретное значение не фиксировалось в коде
    public static final String EXTRA_WORKOUT_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //возвращает ссылку на фрагмент.
        //В макете активности этому фрагменту присвоен индификатор detail_frag
        WorkoutDetailFragment frag = (WorkoutDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.detail_frag);
//        //выводим информацию о произвольно выбранном комплексе
//        frag.setWorkoutId(1);
        //получаем идентификатор из интента и передаем его фграгменту методом setWorkoutId
        int workoutId = (int) getIntent().getExtras().get(EXTRA_WORKOUT_ID);
        frag.setWorkoutId(workoutId);
    }
}