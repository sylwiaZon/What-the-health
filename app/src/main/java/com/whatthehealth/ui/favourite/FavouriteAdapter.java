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
import com.whatthehealth.ui.recipe.RecipeData;

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
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RecipeActivity.class);
                RecipeItem recipe = recipeList.get(position);
                intent.putExtra(RecipeActivity.EXTRA_RECIPE, new RecipeData(
                        recipe.getTitle(),
                        recipe.getId().toString(),
                        recipe.getImage(),
                        recipe.getIngredients(),
                        true
                ));
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return recipeList.size();
    }
}

