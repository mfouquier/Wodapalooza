package com.fouq.wodapalooza;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String EXERCISES_TABLE = "exercises";
    private static final String HIIT_TABLE = "hiit";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_BODY_ZONE = "bodyZone";
    private static final String COLUMN_DURATION = "duration";
    private static final String COLUMN_TIME_TYPE = "timeType";
    private static final String COLUMN_BARBELL = "barbell";
    private static final String COLUMN_DUMBBELL = "dumbbell";
    private static final String COLUMN_KETTLEBELL = "kettlebell";
    private static final String COLUMN_BENCH = "bench";
    private static final String COLUMN_PULLUP_BAR = "pullupBar";
    private static final String COLUMN_SQUAT_RACK = "squatRack";
    private static final String COLUMN_DIPBAR = "dipBar";
    private static final String COLUMN_TRX_RINGS = "trxRings";
    private static final String COLUMN_DESCRIPTION = "description";


    public DatabaseHelper(@Nullable Context context) {
        super(context, "workouts.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //CREATE the Exercises Table
        String createTableExercises = "CREATE TABLE " + EXERCISES_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " VARCHAR (25), " + COLUMN_BODY_ZONE + " VARCHAR (10), " + COLUMN_BARBELL + " BIT, " + COLUMN_DUMBBELL + " BIT, "
                + COLUMN_KETTLEBELL + " BIT, " + COLUMN_BENCH + " BIT, " + COLUMN_PULLUP_BAR + " BIT, " + COLUMN_SQUAT_RACK + " BIT, "
                + COLUMN_DIPBAR + " BIT, " + COLUMN_TRX_RINGS + " BIT, " + COLUMN_DESCRIPTION + " TEXT)";
        //CREATE the HIIT Table
        String createTableHiit = "CREATE TABLE " + HIIT_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " VARCHAR (25), " + COLUMN_DURATION + " INT, " + COLUMN_TIME_TYPE + " VARCHAR (10), " + COLUMN_BARBELL + " BIT, "
                + COLUMN_DUMBBELL + " BIT, " + COLUMN_KETTLEBELL + " BIT, " + COLUMN_BENCH + " BIT, " + COLUMN_PULLUP_BAR + " BIT, "
                + COLUMN_SQUAT_RACK + " BIT, " + COLUMN_DIPBAR + " BIT, " + COLUMN_TRX_RINGS + " BIT, " + COLUMN_DESCRIPTION + " TEXT)";

        sqLiteDatabase.execSQL(createTableExercises);
        sqLiteDatabase.execSQL(createTableHiit);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EXERCISES_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HIIT_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void populateExercisesTable() {
        String sqlInsert = "INSERT into exercises (name, bodyZone, barbell, dumbbell, kettlebell, bench, pullupBar, squatRack, dipBar, trxRings, description) VALUES" +
                " (?,?,?,?,?,?,?,?,?,?,?);";

        SQLiteDatabase db = this.getWritableDatabase();

        db.beginTransaction();
        SQLiteStatement stmt = db.compileStatement(sqlInsert);

        String[] names = {"Bench Press", "Push Ups", "Dips", "Squat", "Lunges", "Plank", "V-Up"};
        String[] zone = {"Upper", "Upper", "Upper", "Lower", "Lower", "Core", "Core"};
        int[] barbell = {1, 0, 0, 1, 1, 0, 0};
        int[] dumbbell = {1, 0, 0, 1, 1, 0, 0};
        int[] kettlebell = {1, 0, 0, 1, 1, 0, 0};
        int[] bench = {1, 0, 0, 0, 0, 0, 0};
        int[] pullupBar = {0, 0, 0, 0, 0, 0, 0};
        int[] squatRack = {0, 0, 0, 1, 0, 0, 0};
        int[] dipBar = {0, 0, 1, 0, 0, 0, 0};
        int[] trxRings = {0, 0, 1, 1, 1, 0, 0};
        String[] description = {"https://www.youtube.com/watch?v=gRVjAtPip0Y", "https://www.youtube.com/watch?v=IODxDxX7oi4", "https://www.youtube.com/watch?v=2z8JmcrW-As",
                "https://www.youtube.com/watch?v=Dy28eq2PjcM", "https://www.youtube.com/watch?v=3XDriUn0udo", "https://www.youtube.com/watch?v=pSHjTRCQxIw", "https://www.youtube.com/watch?v=iP2fjvG0g3w"};

        for (int i = 0; i < names.length; i++) {
            stmt.bindString(1, names[i]);
            stmt.bindString(2, zone[i]);
            stmt.bindDouble(3, barbell[i]);
            stmt.bindDouble(4, dumbbell[i]);
            stmt.bindDouble(5, kettlebell[i]);
            stmt.bindDouble(6, bench[i]);
            stmt.bindDouble(7, pullupBar[i]);
            stmt.bindDouble(8, squatRack[i]);
            stmt.bindDouble(9, dipBar[i]);
            stmt.bindDouble(10, trxRings[i]);
            stmt.bindString(11, description[i]);

            long entryID = stmt.executeInsert();
            stmt.clearBindings();
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void populateHIITTable() {
        String sqlInsert = "INSERT into hiit (name, duration, timeType, barbell, dumbbell, kettlebell, bench, pullupBar, squatRack, dipBar, trxRings, description) VALUES" +
                " (?,?,?,?,?,?,?,?,?,?,?,?);";

        SQLiteDatabase db = this.getWritableDatabase();

        db.beginTransaction();
        SQLiteStatement stmt = db.compileStatement(sqlInsert);

        String[] names = {"Cindy", "Fran"};
        int[] duration = {3, 1};
        String[] timeType = {"AMRAP", "For Time"};
        int[] barbell = {0, 1};
        int[] dumbbell = {0, 1};
        int[] kettlebell = {0, 1};
        int[] bench = {0, 0};
        int[] pullupBar = {1, 1};
        int[] squatRack = {0, 0};
        int[] dipBar = {0, 0};
        int[] trxRings = {0, 0};
        String[] description = {"20 MIN AMRAP 5 pull-ups, 10 push-ups, 15 air squats", "For Time 21-15-9 Thruster (95/65lbs), Pull-ups 8 min cap"};

        for (int i = 0; i < names.length; i++) {
            stmt.bindString(1, names[i]);
            stmt.bindDouble(2, duration[i]);
            stmt.bindString(3, timeType[i]);
            stmt.bindDouble(4, barbell[i]);
            stmt.bindDouble(5, dumbbell[i]);
            stmt.bindDouble(6, kettlebell[i]);
            stmt.bindDouble(7, bench[i]);
            stmt.bindDouble(8, pullupBar[i]);
            stmt.bindDouble(9, squatRack[i]);
            stmt.bindDouble(10, dipBar[i]);
            stmt.bindDouble(11, trxRings[i]);
            stmt.bindString(12, description[i]);

            long entryID = stmt.executeInsert();
            stmt.clearBindings();
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public List<GeneratedWorkouts> workoutPicker(GeneratorModel generatorModel) {
        List<GeneratedWorkouts> returnList = new ArrayList<>();

        String sqlQuery = "SELECT name, description FROM " + EXERCISES_TABLE + " WHERE (bodyZone = '" + generatorModel.getBody_zone() + "' ) AND (barbell =  "
                + generatorModel.equipmentAvailable[0] + " OR dumbbell = " + generatorModel.equipmentAvailable[1] + " OR kettlebell = " + generatorModel.equipmentAvailable[2] +
                " OR bench = " + generatorModel.equipmentAvailable[3] + " OR pullupBar = " + generatorModel.equipmentAvailable[4] + " OR squatRack = " + generatorModel.equipmentAvailable[5] +
                " OR dipBar = " + generatorModel.equipmentAvailable[6] + " OR trxRings = " + generatorModel.equipmentAvailable[7] + ");";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(0);
                String description = cursor.getString(1);
                GeneratedWorkouts generatedWorkouts = new GeneratedWorkouts(name, description);

                returnList.add(generatedWorkouts);

            }while(cursor.moveToNext());
        }

        return returnList;
    }
}