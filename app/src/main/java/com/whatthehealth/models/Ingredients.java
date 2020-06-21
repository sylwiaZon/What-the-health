package com.whatthehealth.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Ingredients {
    @SerializedName("aisle")
        private String aisle;
    @SerializedName("amount")
        private float amount;
    @SerializedName("id")
        private float id;
    @SerializedName("image")
        private String image;
    @SerializedName("meta")
        ArrayList< Object > meta = new ArrayList < Object > ();
    @SerializedName("name")
        private String name;
    @SerializedName("original")
        private String original;
    @SerializedName("originalName")
        private String originalName;
    @SerializedName("unit")
        private String unit;
    @SerializedName("unitLong")
        private String unitLong;
    @SerializedName("unitShort")
        private String unitShort;

        public String getAisle() {
            return aisle;
        }

        public float getAmount() {
            return amount;
        }

        public float getId() {
            return id;
        }

        public String getImage() {
            return image;
        }

        public String getName() {
            return name;
        }

        public String getOriginal() {
            return original;
        }

        public String getOriginalName() {
            return originalName;
        }

        public String getUnit() {
            return unit;
        }

        public String getUnitLong() {
            return unitLong;
        }

        public String getUnitShort() {
            return unitShort;
        }

        public void setAisle(String aisle) {
            this.aisle = aisle;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }

        public void setId(float id) {
            this.id = id;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setOriginal(String original) {
            this.original = original;
        }

        public void setOriginalName(String originalName) {
            this.originalName = originalName;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public void setUnitLong(String unitLong) {
            this.unitLong = unitLong;
        }

        public void setUnitShort(String unitShort) {
            this.unitShort = unitShort;
        }
    }

