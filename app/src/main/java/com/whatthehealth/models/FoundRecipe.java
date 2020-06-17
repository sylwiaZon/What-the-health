package com.whatthehealth.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FoundRecipe {
    @SerializedName("id")
    private float id;
    @SerializedName("image")
    private String image;
    @SerializedName("imageType")
    private String imageType;
    @SerializedName("likes")
    private float likes;
    @SerializedName("missedIngredientCount")
    private float missedIngredientCount;
    @SerializedName("missedIngredients")
    ArrayList < Object > missedIngredients = new ArrayList < Object > ();
    @SerializedName("title")
    private String title;
    @SerializedName("unusedIngredients")
    ArrayList < Object > unusedIngredients = new ArrayList < Object > ();
    @SerializedName("usedIngredientCount")
    private float usedIngredientCount;
    @SerializedName("usedIngredients")
    ArrayList < Object > usedIngredients = new ArrayList< Object >();

        public float getId() {
            return id;
        }

        public String getImage() {
            return image;
        }

        public String getImageType() {
            return imageType;
        }

        public float getLikes() {
            return likes;
        }

        public float getMissedIngredientCount() {
            return missedIngredientCount;
        }

        public String getTitle() {
            return title;
        }

        public float getUsedIngredientCount() {
            return usedIngredientCount;
        }

        // Setter Methods

        public void setId(float id) {
            this.id = id;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setImageType(String imageType) {
            this.imageType = imageType;
        }

        public void setLikes(float likes) {
            this.likes = likes;
        }

        public void setMissedIngredientCount(float missedIngredientCount) {
            this.missedIngredientCount = missedIngredientCount;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setUsedIngredientCount(float usedIngredientCount) {
            this.usedIngredientCount = usedIngredientCount;
        }
}
