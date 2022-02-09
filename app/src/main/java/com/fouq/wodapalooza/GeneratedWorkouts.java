package com.fouq.wodapalooza;

public class GeneratedWorkouts {
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
        return "GeneratedWorkouts{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
