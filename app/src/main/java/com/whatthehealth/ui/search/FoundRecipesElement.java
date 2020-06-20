package com.whatthehealth.ui.search;

import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.whatthehealth.R;

public class FoundRecipesElement  extends RecyclerView.ViewHolder  {
    public LinearLayout parentLayout;

    public FoundRecipesElement(@NonNull View itemView) {
        super(itemView);
        parentLayout = itemView.findViewById(R.id.recipe_element);

    }

}
