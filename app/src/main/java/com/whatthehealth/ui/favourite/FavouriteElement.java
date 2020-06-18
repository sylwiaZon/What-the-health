package com.whatthehealth.ui.favourite;

import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.whatthehealth.R;

public class FavouriteElement extends RecyclerView.ViewHolder  {
    LinearLayout parentLayout;
    public FavouriteElement(@NonNull View itemView) {
        super(itemView);
        parentLayout = itemView.findViewById(R.id.recipe_element);

    }
}
