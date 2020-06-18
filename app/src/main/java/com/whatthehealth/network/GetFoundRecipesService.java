package com.whatthehealth.network;

import com.whatthehealth.models.FoundRecipe;
import com.whatthehealth.models.Recipe;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetFoundRecipesService {
    @GET("recipes/findByIngredients")
    Call<List<FoundRecipe>> getAllRecipes(@Query("ingredients") String ingredients,@Query("ranking") Integer ranking,@Query("number") Integer number, @Query("apiKey") String apiKey);

    @GET("recipes/{id}/analyzedInstructions")
    Call<List<Recipe>> getRecipe(@Path("id") String id , @Query("apiKey") String apiKey);
}
