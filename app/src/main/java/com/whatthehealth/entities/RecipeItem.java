package com.whatthehealth.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes_table")
public class RecipeItem {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "ingredients")
    private String ingredients;


    public RecipeItem(String id, String title, String image,String ingredients) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.ingredients = ingredients;
    }

    public String getId() {
        return this.id;
    }
    public String getTitle() {
        return this.title;
    }
    public String getImage() {
        return this.image;
    }
    public String getIngredients(){return this.ingredients;}

    public void setId(String id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setIngredients(String ingredients){this.ingredients = ingredients;}

}