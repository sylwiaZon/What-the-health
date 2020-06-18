package com.whatthehealth.ui.search;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.whatthehealth.R;
import com.whatthehealth.models.FoundRecipe;
import com.whatthehealth.ui.recipe.RecipeActivity;

import java.util.List;


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
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RecipeActivity.class);
                System.out.println(recipeList.get(position).getId().toString());
                intent.putExtra("title", recipeList.get(position).getTitle());
                intent.putExtra("id", recipeList.get(position).getId().toString());
                intent.putExtra("image", recipeList.get(position).getImage());
                intent.putExtra("fav", false);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return recipeList.size();
    }
}
