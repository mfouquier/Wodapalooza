package com.fouq.wodapalooza;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.sql.Array;
import java.util.List;
import java.util.Random;

public class GeneratedActivity extends AppCompatActivity {

    ListView lv_workoutView;

    //Array adapter
    ArrayAdapter workoutListArrayAdapter;

    GeneratedWorkouts description;

    //Database
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Set the Up Arrow to return to MainActivity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        lv_workoutView = findViewById(R.id.lv_workoutView);

        databaseHelper = new DatabaseHelper(GeneratedActivity.this);

        showWorkoutView(databaseHelper);


    }

    /*
    /Creates an ArrayAdapter that calls DatabaseHelper method workoutPicker which queries
    /the SQL database with the generatorModel. The ArrayAdapter is returned to a ListView
    */
    public void showWorkoutView(DatabaseHelper databaseHelper) {

        workoutListArrayAdapter = new ArrayAdapter<GeneratedWorkouts>(GeneratedActivity.this,
                R.layout.list_view_layout, databaseHelper.workoutPicker(WorkoutGeneratorActivity.generatorModel));

        try {
            if (WorkoutGeneratorActivity.generatorModel.getTimeType() != null) {
                Random random = new Random();
                ArrayAdapter<GeneratedWorkouts> listItem = new ArrayAdapter<GeneratedWorkouts>(GeneratedActivity.this, R.layout.list_view_layout);
                for (int i = 0; i < 1; i++) {
                    int randInt = random.nextInt(workoutListArrayAdapter.getCount());
                    listItem.add((GeneratedWorkouts) workoutListArrayAdapter.getItem(randInt));
                }
                lv_workoutView.setAdapter(listItem);
                lv_workoutView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent openLink = new Intent(Intent.ACTION_VIEW, Uri.parse(description.getDescription()));
                        startActivity(openLink);
                    }
                });
            } else if (WorkoutGeneratorActivity.generatorModel.getDuration() == 1) {
                Random random = new Random();
                ArrayAdapter<GeneratedWorkouts> listItem = new ArrayAdapter<GeneratedWorkouts>(GeneratedActivity.this, R.layout.list_view_layout);
                for (int i = 0; i < 3; i++) {
                    int randInt = random.nextInt(workoutListArrayAdapter.getCount());
                    listItem.add((GeneratedWorkouts) workoutListArrayAdapter.getItem(randInt));
                }
                lv_workoutView.setAdapter(listItem);
            } else if (WorkoutGeneratorActivity.generatorModel.getDuration() == 2) {
                Random random = new Random();
                ArrayAdapter<GeneratedWorkouts> listItem = new ArrayAdapter<GeneratedWorkouts>(GeneratedActivity.this, R.layout.list_view_layout);
                for (int i = 0; i < 4; i++) {
                    int randInt = random.nextInt(workoutListArrayAdapter.getCount());
                    listItem.add((GeneratedWorkouts) workoutListArrayAdapter.getItem(randInt));
                }
                lv_workoutView.setAdapter(listItem);
            } else if (WorkoutGeneratorActivity.generatorModel.getDuration() == 3) {
                Random random = new Random();
                ArrayAdapter<GeneratedWorkouts> listItem = new ArrayAdapter<GeneratedWorkouts>(GeneratedActivity.this, R.layout.list_view_layout);
                for (int i = 0; i < 5; i++) {
                    int randInt = random.nextInt(workoutListArrayAdapter.getCount());
                    listItem.add((GeneratedWorkouts) workoutListArrayAdapter.getItem(randInt));
                }
                lv_workoutView.setAdapter(listItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ArrayAdapter<String> listItem = new ArrayAdapter<String>(GeneratedActivity.this, R.layout.list_view_layout);
            System.out.println("No matches to your criteria. Try again.");
            String noMatch = "No matches to your criteria. Try again.";
            listItem.add(noMatch);
            lv_workoutView.setAdapter(listItem);
        }
    }
}