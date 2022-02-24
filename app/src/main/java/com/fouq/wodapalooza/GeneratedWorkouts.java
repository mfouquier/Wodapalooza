package com.fouq.wodapalooza;

import android.graphics.fonts.Font;
import android.graphics.fonts.FontStyle;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class GeneratedWorkouts implements Serializable {
    String name;
    String description;

    public GeneratedWorkouts(String name, String description){
        this.name = name;
        this.description = description;
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

        return "4 SETS OF 10 " +
                    name + '\n' +
                    description + '\n';
    }
}
