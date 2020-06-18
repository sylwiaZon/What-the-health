package com.whatthehealth.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
public class Step
{
    @SerializedName("equipment")
    private List<Object> equipment;
    @SerializedName("ingredients")
    private List<Ingredients> ingredients;
    @SerializedName("number")
    private int number;
    @SerializedName("step")
    private String step;

    public void setEquipment(List<Object> equipment){ this.equipment = equipment; }
    public void setIngredients(List<Ingredients> ingredients){
        this.ingredients = ingredients;
    }
    public void setNumber(int number){
        this.number = number;
    }
    public void setStep(String step){
        this.step = step;
    }
    public List<Object> getEquipment(){
        return this.equipment;
    }
    public List<Ingredients> getIngredients(){
        return this.ingredients;
    }
    public int getNumber(){
        return this.number;
    }
    public String getStep(){
        return this.step;
    }

}
