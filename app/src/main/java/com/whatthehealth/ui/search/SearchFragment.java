package com.whatthehealth.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.whatthehealth.R;
import com.whatthehealth.models.FoundRecipe;
import com.whatthehealth.network.ClientInstance;
import com.whatthehealth.network.GetFoundRecipesService;
import com.whatthehealth.ui.shoppinglist.ShoppingListViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {
    private SearchViewModel searchViewModel;
    private String ingredients = "apple";
    private String api_key = "c4bcf0cd65d0487b98e3f168328eaef7";

    private void generateDataList(List<FoundRecipe> recipeList) {
        for(FoundRecipe recipe : recipeList){
            System.out.println(recipe.getTitle());
        }
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                ViewModelProviders.of(this).get(SearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        final TextView textView = root.findViewById(R.id.text_search);
        searchViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
                getActivity().setTitle("Found recipes");
            }
        });
        GetFoundRecipesService service = ClientInstance.getRetrofitInstance().create(GetFoundRecipesService.class);
        Call<List<FoundRecipe>> call = service.getAllRecipes(ingredients,api_key);
        call.enqueue(new Callback<List<FoundRecipe>>() {
            @Override
            public void onResponse(Call<List<FoundRecipe>> call, Response<List<FoundRecipe>> response) {
                if(response.isSuccessful()){
                    generateDataList(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<FoundRecipe>> call, Throwable t) {
                System.out.println(t);
            }
        });
        return root;
    }
}