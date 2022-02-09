package com.fouq.wodapalooza;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WorkoutGeneratorActivity extends AppCompatActivity {

    //Radio Buttons
    RadioGroup radioGroupHITTTime;
    RadioGroup radioGroupDuration;
    RadioGroup radioGroupBodyZone;

    RadioButton rb_lessThanFifteen;
    RadioButton rb_fifteen2twenty;
    RadioButton rb_overTwenty;

    RadioButton rb_upper;
    RadioButton rb_lower;
    RadioButton rb_core;
    RadioButton rb_hiit;

    RadioButton rb_amrap;
    RadioButton rb_forTime;

    //Check Boxes
    CheckBox cb_barbell;
    CheckBox cb_dumbbell;
    CheckBox cb_kettlebell;
    CheckBox cb_bench;
    CheckBox cb_pullupBar;
    CheckBox cb_squatRack;
    CheckBox cb_dipBar;
    CheckBox cb_trxRings;

    //Button
    Button btn_generate;

    //TextViews
    TextView tv_hiit;
    ListView lv_workoutView;

    //Exercise Generator variables
    int duration;
    String bodyZone;
    String timeType;
    int[] exercisesArray;

    //Array adapter
    ArrayAdapter workoutListArrayAdapter;

    //Database
    DatabaseHelper databaseHelper;
    private GeneratorModel generatorModel;

    //Fragment
    //WorkoutListFragment workoutListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(WorkoutGeneratorActivity.this);
        databaseHelper.populateExercisesTable();
        databaseHelper.populateHIITTable();

        //Assign RadioButtons, Checkboxes, Buttons
        radioGroupBodyZone = findViewById(R.id.radioGroupWorkout);
        radioGroupDuration = findViewById(R.id.radioGroupTimeDuration);
        radioGroupHITTTime = findViewById(R.id.radioGroupHIITtime);

        tv_hiit = findViewById(R.id.tv_hiitType);

        rb_lessThanFifteen = findViewById(R.id.rb_lessThan15);
        rb_fifteen2twenty = findViewById(R.id.rb_fifteen2twenty);
        rb_overTwenty = findViewById(R.id.rb_twentyplus);
        rb_upper = findViewById(R.id.rb_upperBody);
        rb_lower = findViewById(R.id.rb_lowerBody);
        rb_core = findViewById(R.id.rb_core);
        rb_hiit = findViewById(R.id.rb_hiit);
        rb_amrap = findViewById(R.id.rb_amrap);
        rb_forTime = findViewById(R.id.rb_forTime);
        cb_barbell = findViewById(R.id.cb_barbell);
        cb_dumbbell = findViewById(R.id.cb_dumbbell);
        cb_kettlebell = findViewById(R.id.cb_kettlebell);
        cb_bench = findViewById(R.id.cb_bench);
        cb_pullupBar = findViewById(R.id.cb_pullupBar);
        cb_squatRack = findViewById(R.id.cb_squatRack);
        cb_dipBar = findViewById(R.id.cb_dipBar);
        cb_trxRings = findViewById(R.id.cb_trxRings);
        btn_generate = findViewById(R.id.btn_generate);
        lv_workoutView = findViewById(R.id.lv_workoutView);

        exercisesArray = new int[8];
        //workoutListFragment = new WorkoutListFragment();



        //////////////////Generate Button On Click Listener////////////////////
        btn_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                generatorModel = new GeneratorModel(duration, bodyZone, timeType, exercisesArray);
                showWorkoutView(databaseHelper);
                Toast.makeText(WorkoutGeneratorActivity.this, generatorModel.toString(), Toast.LENGTH_LONG).show();
            }
        });

        ////////////////////Radio Button Listeners/////////////////////////////
        ////////////////////RADIO GROUP WORKOUT DURATION//////////////////////
        radioGroupDuration.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case(R.id.rb_lessThan15):
                        duration = 1;
                        break;
                    case(R.id.rb_fifteen2twenty):
                        duration = 3;
                        break;
                    case(R.id.rb_twentyplus):
                        duration = 4;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + i);
                }
            }
        });
        rb_lessThanFifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        rb_fifteen2twenty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        rb_overTwenty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        /////////////////RADIO GROUP WORKOUT ZONE//////////////////////
        radioGroupBodyZone.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case(R.id.rb_upperBody):
                        bodyZone = "Upper";
                        break;
                    case(R.id.rb_lowerBody):
                        bodyZone = "Lower";
                        break;
                    case(R.id.rb_hiit):
                        bodyZone = "HIIT";
                        break;
                    case(R.id.rb_core):
                        bodyZone = "Core";
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + i);
                }
            }
        });
        rb_upper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //Hide HIIT Time Type when checked - should be invisible unless HIIT is checked
                if(compoundButton.isChecked()){
                    tv_hiit.setVisibility(View.INVISIBLE);
                    radioGroupHITTTime.setVisibility(View.INVISIBLE);
                }
            }
        });
        rb_lower.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //Hide HIIT Time Type when checked - should be invisible unless HIIT is checked
                if(compoundButton.isChecked()){
                    tv_hiit.setVisibility(View.INVISIBLE);
                    radioGroupHITTTime.setVisibility(View.INVISIBLE);
                }
            }
        });
        rb_core.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //Hide HIIT Time Type when checked - should be invisible unless HIIT is checked
                if(compoundButton.isChecked()){
                    tv_hiit.setVisibility(View.INVISIBLE);
                    radioGroupHITTTime.setVisibility(View.INVISIBLE);
                }
            }
        });
        rb_hiit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            //Make HIIT Time Type Visible when checked
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    tv_hiit.setVisibility(View.VISIBLE);
                    radioGroupHITTTime.setVisibility(View.VISIBLE);
                }
            }
        });
        /////////////////RADIO GROUP HIIT FOR TIME OR AMRAP//////////////////////
        radioGroupHITTTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case(R.id.rb_forTime):
                        timeType = "For Time";
                        break;
                    case(R.id.rb_amrap):
                        timeType = "AMRAP";
                        break;
                    default:
                        timeType = null;
                        throw new IllegalStateException("Unexpected value: " + i);
                }
            }
        });
        rb_forTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        rb_amrap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        ///////////////Checkbox Listeners/////////////////////////////////
        cb_barbell.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    exercisesArray[0] = 1;
                }else{
                    exercisesArray[0] = 0;
                }
            }
        });
        cb_dumbbell.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    exercisesArray[1] = 1;
                }else{
                    exercisesArray[1] = 0;
                }
            }
        });
        cb_kettlebell.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    exercisesArray[2] = 1;
                }else{
                    exercisesArray[2] = 0;
                }
            }
        });
        cb_bench.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    exercisesArray[3] = 1;
                }else{
                    exercisesArray[3] = 0;
                }
            }
        });
        cb_pullupBar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    exercisesArray[4] = 1;
                }else{
                    exercisesArray[4] = 0;
                }
            }
        });
        cb_squatRack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    exercisesArray[5] = 1;
                }else{
                    exercisesArray[5] = 0;
                }
            }
        });
        cb_dipBar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    exercisesArray[6] = 1;
                }else{
                    exercisesArray[6] = 0;
                }
            }
        });
        cb_trxRings.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    exercisesArray[7] = 1;
                }else{
                    exercisesArray[7] = 0;
                }
            }
        });
    }

    /*
    /Creates an ArrayAdapter that calls DatabaseHelper method workoutPicker which queries
    /the SQL database with the generatorModel. The ArrayAdapter is returned to a ListView
    */
    public void showWorkoutView(DatabaseHelper databaseHelper) {
        workoutListArrayAdapter = new ArrayAdapter<GeneratedWorkouts>(WorkoutGeneratorActivity.this,
                android.R.layout.simple_list_item_1, databaseHelper.workoutPicker(generatorModel));
        lv_workoutView.setAdapter(workoutListArrayAdapter);
    }
}