package com.whatthehealth.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.whatthehealth.BuildConfig;
import com.whatthehealth.R;
import com.whatthehealth.entities.FridgeItem;
import com.whatthehealth.models.FoundRecipe;
import com.whatthehealth.network.ClientInstance;
import com.whatthehealth.network.GetFoundRecipesService;
import com.whatthehealth.ui.fridge.FridgeViewModel;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {
    private FridgeViewModel fridgeViewModel;
    private SearchAdapter adapter;
    private RecyclerView recyclerView;
    private String ingredients = "";

    private void generateDataList(List<FoundRecipe> recipeList) {
        adapter = new SearchAdapter(this.getContext(),recipeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        for(FoundRecipe recipe : recipeList){
            System.out.println(recipe.getTitle());
        }
    }
    private void setIngredinetsFromFridge(){
        fridgeViewModel.getAllItems().observe(getViewLifecycleOwner(), fridgeItems -> {
            for(FridgeItem item : fridgeItems){
                ingredients+=item.getItem()+",";
            }
            ingredients = ingredients.substring(0, ingredients.length() - 1);

            GetFoundRecipesService service = ClientInstance.getRetrofitInstance().create(GetFoundRecipesService.class);
            Call<List<FoundRecipe>> call = service.getAllRecipes(ingredients,1, 20, BuildConfig.API_KEY);
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
        });

    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fridgeViewModel =
                new ViewModelProvider(this).get(FridgeViewModel.class);
        getActivity().setTitle("Found recipes");

        View root = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = root.findViewById(R.id.recycler_view_2);
        fridgeViewModel.getItemsCount().observe(getViewLifecycleOwner(),integer -> {
            if(integer > 0) {
                setIngredinetsFromFridge();
            } else {
                System.out.println("No ingredients");
            }
        });
        return root;
    }
}