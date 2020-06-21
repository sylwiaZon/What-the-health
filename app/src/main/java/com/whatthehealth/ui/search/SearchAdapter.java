package com.whatthehealth.ui.search;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.whatthehealth.R;
import com.whatthehealth.models.FoundRecipe;
import com.whatthehealth.models.Ingredients;
import com.whatthehealth.ui.recipe.RecipeActivity;
import com.whatthehealth.ui.recipe.RecipeData;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class SearchAdapter extends RecyclerView.Adapter<FoundRecipesElement>  {

    private List<FoundRecipe> recipeList;
    private Context context;

    public SearchAdapter(Context context,List<FoundRecipe> dataList){
        this.recipeList = dataList;
        this.context = context;
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
        textView.setText(recipeList.get(position).getTitle());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RecipeActivity.class);
                FoundRecipe recipe = recipeList.get(position);
                String ingredients = prepareIngreientsData(recipe);
                intent.putExtra(RecipeActivity.EXTRA_RECIPE, new RecipeData(
                        recipe.getTitle(),
                        recipe.getId().toString(),
                        recipe.getImage(),
                        ingredients,
                        false
                ));
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String prepareIngreientsData(FoundRecipe recipe){
        List <String> missedIngredients = recipe.getMissedIngredients().stream().map(Ingredients::getOriginal).collect(Collectors.toList());
        List <String> usedIngredients = recipe.getUsedIngredients().stream().map(Ingredients::getOriginal).collect(Collectors.toList());
        List <String> unusedIngredients = recipe.getUnusedIngredients().stream().map(Ingredients::getOriginal).collect(Collectors.toList());
        List<String> ingredients = Stream.of(missedIngredients, usedIngredients, unusedIngredients)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        String ingredientsString = ingredients.stream().collect(Collectors.joining("\n"));
        return ingredientsString;
    }
}
