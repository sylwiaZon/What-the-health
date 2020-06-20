package com.whatthehealth.ui.recipe;

import android.os.Parcel;
import android.os.Parcelable;

public class RecipeData implements Parcelable {
    private String title;
    private String id;
    private String image;
    private boolean favourite;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.id);
        dest.writeString(this.image);
        dest.writeByte(this.favourite ? (byte) 1 : (byte) 0);
    }

    public RecipeData() {
    }

    public RecipeData(String title, String id, String image, boolean favourite) {
        this.title = title;
        this.id = id;
        this.image = image;
        this.favourite = favourite;
    }

    protected RecipeData(Parcel in) {
        this.title = in.readString();
        this.id = in.readString();
        this.image = in.readString();
        this.favourite = in.readByte() != 0;
    }

    public static final Parcelable.Creator<RecipeData> CREATOR = new Parcelable.Creator<RecipeData>() {
        @Override
        public RecipeData createFromParcel(Parcel source) {
            return new RecipeData(source);
        }

        @Override
        public RecipeData[] newArray(int size) {
            return new RecipeData[size];
        }
    };
}