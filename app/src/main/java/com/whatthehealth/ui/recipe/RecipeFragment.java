package com.whatthehealth.ui.recipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;
import com.whatthehealth.BuildConfig;
import com.whatthehealth.R;
import com.whatthehealth.entities.RecipeItem;
import com.whatthehealth.models.Ingredients;
import com.whatthehealth.models.Recipe;
import com.whatthehealth.models.Step;
import com.whatthehealth.network.ClientInstance;
import com.whatthehealth.network.GetFoundRecipesService;
import com.whatthehealth.repositories.RecipeRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeFragment extends Fragment implements  AddFavouriteRecipeItemFragment.AddItemDialogListener{
    private RecipeRepository recipeRepository;
    private RecipeData recipeData;
    private View root;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.recipe_fragment, container, false);
        recipeRepository = new RecipeRepository(this.getContext());
        if(recipeData != null) setUpView();

        return root;
    }

    private void setUpView(){
        GetFoundRecipesService service = ClientInstance.getRetrofitInstance().create(GetFoundRecipesService.class);
        Call<List<Recipe>> call = service.getRecipeAnalyzedInstructions(recipeData.getId(), BuildConfig.API_KEY);
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if(response.isSuccessful()){
                    generateData(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    private void generateData(List<Recipe> recipes) {
        ConstraintLayout recipeFound = root.findViewById(R.id.recipe_found);
        ConstraintLayout recipeNotFound = root.findViewById(R.id.recipe_not_found);
        if(recipes.size() == 0){
            recipeNotFound.setVisibility(View.VISIBLE);
            recipeFound.setVisibility(View.GONE);
        }
        else{
            recipeNotFound.setVisibility(View.GONE);
            recipeFound.setVisibility(View.VISIBLE);
            setImage();
            setRecipeText(recipes);
            setIngredientsText();
            addButtonAction();
        }
    }

    private void addButtonAction(){
        Button button = root.findViewById(R.id.add_fav);
        AddFavouriteRecipeItemFragment.AddItemDialogListener listener = this;
        button.setOnClickListener(v -> {
            AddFavouriteRecipeItemFragment newFragment = new AddFavouriteRecipeItemFragment();
            newFragment.setDialogListener(listener);
            newFragment.show(getChildFragmentManager(), "AddFavouriteItem");
        });
        if(!recipeData.isFavourite()){
            button.setVisibility(View.VISIBLE);
        }
        else{
            button.setVisibility(View.INVISIBLE);
        }
    }

    private void setImage(){
        ImageView image = root.findViewById(R.id.recipe_image);
        Picasso.get().load(recipeData.getImage()).into(image);
    }

    private void setRecipeText(List<Recipe> recipes){
        Recipe recipe = recipes.get(0);
        String instructions = "";
        for(Step step : recipe.getSteps()){
            instructions += step.getStep().concat("\n\n");
        }
        TextView recipe_instructions = root.findViewById(R.id.recipe);
        recipe_instructions.setText(instructions);
    }

    private void setIngredientsText(){

        TextView recipe_instructions = root.findViewById(R.id.ingredients);
        recipe_instructions.setText(recipeData.getIngredients());
    }

    public void setRecipeData(RecipeData recipeData) {
        this.recipeData = recipeData;
        setUpView();
    }

    public RecipeData getRecipeData(){
        return recipeData;
    }

    @Override
    public void onDialogPositiveClick() {
        RecipeItem item = new RecipeItem(recipeData.getId(),
                recipeData.getTitle(),
                recipeData.getImage(),
                recipeData.getIngredients());
        recipeRepository.insert(item);
    }
}
