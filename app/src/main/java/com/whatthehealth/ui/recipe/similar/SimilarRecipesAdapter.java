package com.whatthehealth.ui.recipe.similar;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.whatthehealth.R;
import com.whatthehealth.models.SimilarRecipe;
import com.whatthehealth.ui.search.FoundRecipesElement;

import java.util.ArrayList;
import java.util.List;

public class SimilarRecipesAdapter extends RecyclerView.Adapter<FoundRecipesElement> {
    private List<SimilarRecipe> similarRecipes = new ArrayList<>();
    private Listener listener;

    public SimilarRecipesAdapter(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public FoundRecipesElement onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_element, parent, false);
        return new FoundRecipesElement(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FoundRecipesElement holder, int position) {
        TextView textView = holder.itemView.findViewById(R.id.recipe_item);
        textView.setText(similarRecipes.get(position).getTitle());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(similarRecipes.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return similarRecipes.size();
    }

    public void setSimilarRecipes(List<SimilarRecipe> similarRecipes) {
        this.similarRecipes = similarRecipes;
        notifyDataSetChanged();
    }

    interface Listener{
        void onItemClick(SimilarRecipe similarRecipe);
    }
}
