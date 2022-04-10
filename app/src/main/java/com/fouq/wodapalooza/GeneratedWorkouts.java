package com.fouq.wodapalooza;

import android.graphics.fonts.Font;
import android.graphics.fonts.FontStyle;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class GeneratedWorkouts implements Serializable {
    String name;
    String description;
    String timeType = null;
    String body_zone;
    int duration, barbell, dumbbell, kettlebell, bench, pullupBar, squatRack, dipBar, trxRing;

    public GeneratedWorkouts(String name, String body_zone, int barbell, int dumbbell, int kettlebell, int bench, int pullupBar, int squatRack, int dipBar, int trxRing, String description) {
        this.name = name;
        this.description = description;
        this.body_zone = body_zone;
        this.barbell = barbell;
        this.dumbbell = dumbbell;
        this.kettlebell = kettlebell;
        this.bench = bench;
        this.pullupBar = pullupBar;
        this.squatRack = squatRack;
        this.dipBar = dipBar;
        this.trxRing = trxRing;
    }

    public GeneratedWorkouts(String name, int duration, String timeType, int barbell, int dumbbell, int kettlebell, int bench, int pullupBar, int squatRack, int dipBar, int trxRing, String description) {
        this.name = name;
        this.description = description;
        this.timeType = timeType;
        this.duration = duration;
        this.barbell = barbell;
        this.dumbbell = dumbbell;
        this.kettlebell = kettlebell;
        this.bench = bench;
        this.pullupBar = pullupBar;
        this.squatRack = squatRack;
        this.dipBar = dipBar;
        this.trxRing = trxRing;
    }

    public GeneratedWorkouts(String name, String description, String timeType) {
        this.name = name;
        this.description = description;
        this.timeType = timeType;
    }

    public GeneratedWorkouts(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getBody_zone() {
        return body_zone;
    }

    public void setBody_zone(String body_zone) {
        this.body_zone = body_zone;
    }

    public int getBarbell() {
        return barbell;
    }

    public void setBarbell(int barbell) {
        this.barbell = barbell;
    }

    public int getDumbbell() {
        return dumbbell;
    }

    public void setDumbbell(int dumbbell) {
        this.dumbbell = dumbbell;
    }

    public int getKettlebell() {
        return kettlebell;
    }

    public void setKettlebell(int kettlebell) {
        this.kettlebell = kettlebell;
    }

    public int getBench() {
        return bench;
    }

    public void setBench(int bench) {
        this.bench = bench;
    }

    public int getPullupBar() {
        return pullupBar;
    }

    public void setPullupBar(int pullupBar) {
        this.pullupBar = pullupBar;
    }

    public int getSquatRack() {
        return squatRack;
    }

    public void setSquatRack(int squatRack) {
        this.squatRack = squatRack;
    }

    public int getDipBar() {
        return dipBar;
    }

    public void setDipBar(int dipBar) {
        this.dipBar = dipBar;
    }

    public int getTrxRing() {
        return trxRing;
    }

    public void setTrxRing(int trxRing) {
        this.trxRing = trxRing;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        if (timeType != null) {
            return name + '\n' + description + '\n';
        } else {
            final String s = "4 SETS OF 10 " +
                    name + '\n' +
                    description + '\n' ;
                    //timeType + '\n';
            return s;

        }
    }
}
