package com.whatthehealth.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.whatthehealth.R;
import com.whatthehealth.models.FoundRecipe;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<FoundRecipesElement>  {

    private List<FoundRecipe> recipeList;

    public SearchAdapter(List<FoundRecipe> dataList){
        this.recipeList = dataList;
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
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }
}
