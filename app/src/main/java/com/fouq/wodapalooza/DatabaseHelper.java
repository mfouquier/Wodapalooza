package com.fouq.wodapalooza;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQL QUERY RESULT: ";
    private static String DB_PATH = "/data/data/com.fouq.wodapalooza/databases/";
    private static String DB_NAME = "workouts.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    // Table name in the database.
    public static final String EXERCISES_TABLE = "exercises";
    public static final String HIIT_TABLE = "hiit";

    public DatabaseHelper(@Nullable Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();

        if (!dbExist) {
            this.getWritableDatabase();
            copyDataBase();
        }
    }
    // Check if the database already exist to avoid re-copying the file each time you open the application
    // return true if it exists false if it doesn't.
    private boolean checkDataBase()
    {
        SQLiteDatabase db = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
            db.setLocale(Locale.getDefault());
            db.setVersion(1);
        }
        catch (SQLiteException e) {
            // database doesn't exist yet.
            Log.e("SQLHelper", "Database not found");
        }
        if (db != null) {
            db.close();
        }
        return db != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.This is done by transferring bytestream.
     * */
    private void copyDataBase() throws IOException {
        // Open your local db as the input stream
        InputStream myInput = null;
        OutputStream myOutput = null;

        String dbFilePath = DB_PATH + DB_NAME;


        try {
            myInput = myContext.getAssets().open(DB_NAME);
            myOutput = new FileOutputStream(dbFilePath);

            // transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase()
            throws SQLException
    {
        // Open the database
        String myPath = DB_PATH;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close()
    {
        // close the database.
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<GeneratedWorkouts> workoutPicker(GeneratorModel generatorModel) {
        List<GeneratedWorkouts> returnList = new ArrayList<>();
        String sqlQuery;

        if (generatorModel.getBody_zone().equals("HIIT")) {
//            sqlQuery = "SELECT name, description, timeType FROM " + HIIT_TABLE + " WHERE (duration = '" + generatorModel.getDuration() + "' ) AND (timeType =  '" + generatorModel.getTimeType() + "' ) AND (barbell =  "
//                    + generatorModel.equipmentAvailable[0] + " OR dumbbell = " + generatorModel.equipmentAvailable[1] + " OR kettlebell = " + generatorModel.equipmentAvailable[2] +
//                    " OR bench = " + generatorModel.equipmentAvailable[3] + " OR pullupBar = " + generatorModel.equipmentAvailable[4] + " OR squatRack = " + generatorModel.equipmentAvailable[5] +
//                    " OR dipBar = " + generatorModel.equipmentAvailable[6] + " OR trxRing = " + generatorModel.equipmentAvailable[7] + ");";

            sqlQuery = "SELECT name, duration, timeType, barbell, dumbbell, kettlebell, bench, pullupBar, squatRack, dipBar, trxRing, description from hiit where timeType = '" + generatorModel.getTimeType() +
                    "' AND duration = '" + generatorModel.getDuration() + "';";

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(sqlQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(0);
                    int duration = cursor.getInt(1);
                    String timeType = cursor.getString(2);
                    int barbell = cursor.getInt(3);
                    int dumbbell = cursor.getInt(4);
                    int kettlebell = cursor.getInt(5);
                    int bench = cursor.getInt(6);
                    int pullupBar = cursor.getInt(7);
                    int squatRack = cursor.getInt(8);
                    int dipBar = cursor.getInt(9);
                    int trxRing = cursor.getInt(10);
                    String description = cursor.getString(11);
                    GeneratedWorkouts generatedWorkouts = new GeneratedWorkouts(name, duration, timeType, barbell, dumbbell, kettlebell, bench, pullupBar, squatRack, dipBar, trxRing, description);

                    if (generatedWorkouts.getBarbell() == 1 && generatorModel.equipmentAvailable[0] == 1) {
                        returnList.add(generatedWorkouts);

                    }else if (generatedWorkouts.getDumbbell() == 1 && generatorModel.equipmentAvailable[1] == 1) {
                        returnList.add(generatedWorkouts);

                    }else if (generatedWorkouts.getKettlebell() == 1 && generatorModel.equipmentAvailable[2] == 1) {
                        returnList.add(generatedWorkouts);

                    }else if (generatedWorkouts.getBench() == 1 && generatorModel.equipmentAvailable[3] == 1) {
                        returnList.add(generatedWorkouts);

                    }else if (generatedWorkouts.getPullupBar() == 1 && generatorModel.equipmentAvailable[4] == 1) {
                        returnList.add(generatedWorkouts);

                    }else if (generatedWorkouts.getSquatRack() == 1 && generatorModel.equipmentAvailable[5] == 1) {
                        returnList.add(generatedWorkouts);

                    }else if (generatedWorkouts.getDipBar() == 1 && generatorModel.equipmentAvailable[6] == 1) {
                        returnList.add(generatedWorkouts);

                    }else if (generatedWorkouts.getTrxRing() == 1 && generatorModel.equipmentAvailable[7] == 1) {
                        returnList.add(generatedWorkouts);

                    }else if (generatedWorkouts.getBarbell() == 0 && generatedWorkouts.getDumbbell() == 0 && generatedWorkouts.getKettlebell() == 0 &&
                            generatedWorkouts.getBench() == 0 && generatedWorkouts.getPullupBar() == 0 && generatedWorkouts.getSquatRack() == 0 &&
                            generatedWorkouts.getDipBar() == 0 && generatedWorkouts.getTrxRing() == 0) {
                        returnList.add(generatedWorkouts);
                    }

                } while (cursor.moveToNext());
            }

            return returnList;
        } else {
//            sqlQuery = "SELECT name, description FROM " + EXERCISES_TABLE + " WHERE (body_zone = '" + generatorModel.getBody_zone() + "' ) AND (barbell =  "
//                    + generatorModel.equipmentAvailable[0] + " OR dumbbell = " + generatorModel.equipmentAvailable[1] + " OR kettlebell = " + generatorModel.equipmentAvailable[2] +
//                    " OR bench = " + generatorModel.equipmentAvailable[3] + " OR pullupBar = " + generatorModel.equipmentAvailable[4] + " OR squatRack = " + generatorModel.equipmentAvailable[5] +
//                    " OR dipBar = " + generatorModel.equipmentAvailable[6] + " OR trxRing = " + generatorModel.equipmentAvailable[7] + ");";

            sqlQuery = "SELECT name, body_zone, barbell, dumbbell, kettlebell, bench, pullupBar, squatRack, dipBar, trxRing, description from exercises where body_zone = '" + generatorModel.getBody_zone() + "';";

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(sqlQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(0);
                    String body_zone = cursor.getString(1);
                    int barbell = cursor.getInt(2);
                    int dumbbell = cursor.getInt(3);
                    int kettlebell = cursor.getInt(4);
                    int bench = cursor.getInt(5);
                    int pullupBar = cursor.getInt(6);
                    int squatRack = cursor.getInt(7);
                    int dipBar = cursor.getInt(8);
                    int trxRing = cursor.getInt(9);
                    String description = cursor.getString(10);
                    GeneratedWorkouts generatedWorkouts = new GeneratedWorkouts(name, body_zone, barbell, dumbbell, kettlebell, bench, pullupBar, squatRack, dipBar, trxRing, description);

                            if (generatedWorkouts.getBarbell() == 1 && generatorModel.equipmentAvailable[0] == 1) {
                                returnList.add(generatedWorkouts);

                            }else if (generatedWorkouts.getDumbbell() == 1 && generatorModel.equipmentAvailable[1] == 1) {
                                returnList.add(generatedWorkouts);

                            }else if (generatedWorkouts.getKettlebell() == 1 && generatorModel.equipmentAvailable[2] == 1) {
                                returnList.add(generatedWorkouts);

                            }else if (generatedWorkouts.getBench() == 1 && generatorModel.equipmentAvailable[3] == 1) {
                                returnList.add(generatedWorkouts);

                            }else if (generatedWorkouts.getPullupBar() == 1 && generatorModel.equipmentAvailable[4] == 1) {
                                returnList.add(generatedWorkouts);

                            }else if (generatedWorkouts.getSquatRack() == 1 && generatorModel.equipmentAvailable[5] == 1) {
                                returnList.add(generatedWorkouts);

                            }else if (generatedWorkouts.getDipBar() == 1 && generatorModel.equipmentAvailable[6] == 1) {
                                returnList.add(generatedWorkouts);

                            }else if (generatedWorkouts.getTrxRing() == 1 && generatorModel.equipmentAvailable[7] == 1) {
                                returnList.add(generatedWorkouts);

                            }else if (generatedWorkouts.getBarbell() == 0 && generatedWorkouts.getDumbbell() == 0 && generatedWorkouts.getKettlebell() == 0 &&
                                    generatedWorkouts.getBench() == 0 && generatedWorkouts.getPullupBar() == 0 && generatedWorkouts.getSquatRack() == 0 &&
                                    generatedWorkouts.getDipBar() == 0 && generatedWorkouts.getTrxRing() == 0) {
                                returnList.add(generatedWorkouts);
                            }
                } while (cursor.moveToNext());
            }

           }
//            if(generatorModel.getDuration() == 1){
//                Random random = new Random();
//                int randInt = random.nextInt(returnList.size());
//
//                for(int i = 0; i <= returnList.size() / 5; i++){
//                    returnList.remove(randInt);
//                }
//            }

            return returnList;
        }

    }