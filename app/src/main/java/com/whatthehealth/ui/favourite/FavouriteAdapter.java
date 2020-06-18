package com.whatthehealth.ui.favourite;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.whatthehealth.R;
import com.whatthehealth.entities.RecipeItem;
import com.whatthehealth.models.FoundRecipe;
import com.whatthehealth.ui.recipe.RecipeActivity;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteElement>   {
    private List<RecipeItem> recipeList;
    private Context context;

    public FavouriteAdapter(Context context,List<RecipeItem> dataList){
        this.recipeList = dataList;
        this.context = context;
    }
    @NonNull
    @Override
    public FavouriteElement onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_element, parent, false);
        return new FavouriteElement(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteElement holder, int position) {
        TextView textView = holder.itemView.findViewById(R.id.recipe_item);
        textView.setText(recipeList.get(position).getTitle());
        holder.parentLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, RecipeActivity.class);
            intent.putExtra("title", recipeList.get(position).getTitle());
            intent.putExtra("id", recipeList.get(position).getId());
            intent.putExtra("image", recipeList.get(position).getImage());
            intent.putExtra("fav", true);
            context.startActivity(intent);
        });
    }
    @Override
    public int getItemCount() {
        return recipeList.size();
    }
}

