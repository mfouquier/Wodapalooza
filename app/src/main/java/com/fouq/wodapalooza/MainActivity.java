package com.fouq.wodapalooza;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity implements delayFragment.delayTimeListener {

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseHelper = new DatabaseHelper(MainActivity.this);
        databaseHelper.populateExercisesTable();
        databaseHelper.populateHIITTable();
    }

    //The Toolbar Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.timermenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delay_timer:
                // User chose the "Settings" item, show the app settings UI...
                delayMenuClicked();
                return true;
            case R.id.reset_icon:
                //Set the reset function
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    //Delay Menu Clicked --> delayFragment
    public void delayMenuClicked(){
        delayFragment delay = new delayFragment();
        delay.show(getSupportFragmentManager(),"set delay time" );
    }
    //Stopwatch Button Clicked --> Stopwatch Activity
    public void onStopwatchClick(View view){
        Intent intent = new Intent(this, StopwatchActivity.class);
        startActivity(intent);
    }
    //AMRAP Button Clicked --> AMRAP Activity
    public void onAmrapClick(View view){
        Intent intent = new Intent(this, AmrapActivity.class);
        startActivity(intent);
    }
    //Workout Generator Clicked -- WorkoutGenerator Activity
    public void onWorkoutGeneratorClick(View view){
        Intent intent = new Intent(this, WorkoutGeneratorActivity.class);
        startActivity(intent);
    }
    @Override
    public void applyDelay(int delay) {

    }
}