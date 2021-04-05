package com.example.workout;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements WorkoutListFragment.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    //метод вызывался кнопкой
//    public void onShowDetails(View view){
//        Intent intent = new Intent(this, DetailActivity.class);
//        startActivity(intent);
//    }

    //открывет DetailActivity и передает идентификатор выбранного комплекса упражнения
    @Override
    public void itemClicked(long id) {
        View fragmentContainer = findViewById(R.id.fragment_container);
        if (fragmentContainer != null){
            //Добавление фрагмента в FragmentLayout
            WorkoutDetailFragment details = new WorkoutDetailFragment();
            //Начало транзакции фрагмента
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            details.setWorkoutId(id);
            //замена фрагмента
            ft.replace(R.id.fragment_container, details);
            //новый и старый фрагменты должны сопровождаться анимацией
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            //транзакция включается в стек возврата
            ft.addToBackStack(null);
            //закрепление транзакции
            ft.commit();
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, (int) id);
            startActivity(intent);
        }
    }
}