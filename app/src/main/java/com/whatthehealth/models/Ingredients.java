package com.whatthehealth.models;

import com.google.gson.annotations.SerializedName;

public class Ingredients {
    @SerializedName("id")
    private int id;
    @SerializedName("image")
    private String image;
    @SerializedName("name")
    private String name;

    public void setId(int id){ this.id = id; }
    public void setImage(String image){ this.image = image; }
    public void setName(String name){ this.name = name; }

    public int getId(){ return this.id; }
    public String getName(){ return this.name; }
    public String getImage(){ return this.image; }
}
