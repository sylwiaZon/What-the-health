package com.whatthehealth.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FoundRecipe {
    @SerializedName("id")
    private Integer id;
    @SerializedName("image")
    private String image;
    @SerializedName("imageType")
    private String imageType;
    @SerializedName("likes")
    private Integer likes;
    @SerializedName("missedIngredientCount")
    private Integer missedIngredientCount;
    @SerializedName("missedIngredients")
    ArrayList < Object > missedIngredients = new ArrayList < Object > ();
    @SerializedName("title")
    private String title;
    @SerializedName("unusedIngredients")
    ArrayList < Object > unusedIngredients = new ArrayList < Object > ();
    @SerializedName("usedIngredientCount")
    private Integer usedIngredientCount;
    @SerializedName("usedIngredients")
    ArrayList < Object > usedIngredients = new ArrayList< Object >();

        public Integer getId() {
            return id;
        }
        public String getImage() {
            return image;
        }
        public String getImageType() {
            return imageType;
        }
        public Integer getLikes() {
            return likes;
        }
        public Integer getMissedIngredientCount() {
            return missedIngredientCount;
        }
        public String getTitle() {
            return title;
        }
        public float getUsedIngredientCount() {
            return usedIngredientCount;
        }

        public void setId(Integer id) {
            this.id = id;
        }
        public void setImage(String image) {
            this.image = image;
        }
        public void setImageType(String imageType) {
            this.imageType = imageType;
        }
        public void setLikes(Integer likes) {
            this.likes = likes;
        }
        public void setMissedIngredientCount(Integer missedIngredientCount) {
            this.missedIngredientCount = missedIngredientCount;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public void setUsedIngredientCount(Integer usedIngredientCount) {
            this.usedIngredientCount = usedIngredientCount;
        }
}
