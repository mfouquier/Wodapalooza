package com.fouq.wodapalooza;

/**
 * Author: Matthew Fouquier
 * Date: May 1, 2022
 * Project: Wodapalooza
 */

public class GeneratorModel {
    String body_zone;
    int duration;
    String timeType;
    int[] equipmentAvailable;

    public GeneratorModel(int duration, String body_zone, String timeType, int[] equipmentAvailable) {
        this.duration = duration;
        this.body_zone = body_zone;
        this.timeType = timeType;
        this.equipmentAvailable = equipmentAvailable;
    }

    public String getBody_zone() {
        return body_zone;
    }

    public void setBody_zone(String body_zone) {
        this.body_zone = body_zone;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public int[] getEquipmentAvailable() {
        return equipmentAvailable;
    }

    public void setEquipmentAvailable(int[] equipmentAvailable) {
        this.equipmentAvailable = equipmentAvailable;
    }
}