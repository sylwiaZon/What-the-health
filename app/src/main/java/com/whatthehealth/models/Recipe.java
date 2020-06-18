package com.whatthehealth.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
public class Recipe {
    @SerializedName("name")
    private String name;
    @SerializedName("steps")
    private List <Step> steps = new ArrayList<Step>();

    public List <Step> getSteps() {
        return steps;
    }
    public void setSteps(List <Step> steps) {
        this.steps = steps;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
