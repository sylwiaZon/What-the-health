package com.whatthehealth.ui.recipe;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.whatthehealth.BuildConfig;
import com.whatthehealth.R;
import com.whatthehealth.models.FoundRecipe;
import com.whatthehealth.network.ClientInstance;
import com.whatthehealth.network.GetFoundRecipesService;
import com.whatthehealth.ui.recipe.similar.SimilarRecipesFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeActivity extends AppCompatActivity {
    public static final String EXTRA_RECIPE = "Extra_Recipe";
    private RecipeFragment recipeFragment;
    private SimilarRecipesFragment similarRecipesFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_activity);

        RecipeData recipeData = getIntent().getParcelableExtra(EXTRA_RECIPE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(recipeData.getTitle());
        recipeFragment = (RecipeFragment)
                getSupportFragmentManager().findFragmentById(R.id.recipe_fragment);
        recipeFragment.setRecipeData(recipeData);
        similarRecipesFragment = (SimilarRecipesFragment)
                getSupportFragmentManager().findFragmentById(R.id.similar_fragment);
        if(similarRecipesFragment != null){
            similarRecipesFragment.setSimilarToId(Integer.parseInt(recipeData.getId()));
            similarRecipesFragment.setListener(this::changeRecipe);
        }
    }

    private void changeRecipe(int id){
        GetFoundRecipesService retrofit = ClientInstance.getRetrofitInstance().create(GetFoundRecipesService.class);
        retrofit.getRecipeInfo(id, BuildConfig.API_KEY).enqueue(new Callback<FoundRecipe>() {
            @Override
            public void onResponse(Call<FoundRecipe> call, Response<FoundRecipe> response) {
                if(response.isSuccessful()){
                    RecipeData recipeData = new RecipeData(
                            response.body().getTitle(),
                            response.body().getId().toString(),
                            response.body().getImage(),
                            recipeFragment.getRecipeData().getIngredients(),
                            false
                    );
                    getSupportActionBar().setTitle(recipeData.getTitle());
                    recipeFragment.setRecipeData(recipeData);
                    if(similarRecipesFragment != null)  similarRecipesFragment.setSimilarToId(response.body().getId());
                }
            }

            @Override
            public void onFailure(Call<FoundRecipe> call, Throwable t) {

            }
        });
    }
}
