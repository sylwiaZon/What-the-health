package com.whatthehealth.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleRegistryOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.whatthehealth.R;
import com.whatthehealth.entities.FridgeItem;
import com.whatthehealth.models.FoundRecipe;
import com.whatthehealth.network.ClientInstance;
import com.whatthehealth.network.GetFoundRecipesService;
import com.whatthehealth.ui.fridge.FridgeViewModel;
import com.whatthehealth.ui.shoppinglist.ShoppingListAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {
    private FridgeViewModel fridgeViewModel;
    private SearchAdapter adapter;
    private RecyclerView recyclerView;
    private String ingredients = "";
    private String api_key = "c4bcf0cd65d0487b98e3f168328eaef7";

    private void generateDataList(List<FoundRecipe> recipeList) {
        adapter = new SearchAdapter(recipeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        for(FoundRecipe recipe : recipeList){
            System.out.println(recipe.getTitle());
        }
    }
    private void setIngredinetsFromFridge(){
        fridgeViewModel.getAllItems().observe(getViewLifecycleOwner(), new Observer<List<FridgeItem>>() {
            @Override
            public void onChanged(List<FridgeItem> fridgeItems) {
                for(FridgeItem item : fridgeItems){
                    ingredients+=item.getItem()+",";
                }
                 ingredients = ingredients.substring(0, ingredients.length() - 1);
                System.out.println(ingredients);

                GetFoundRecipesService service = ClientInstance.getRetrofitInstance().create(GetFoundRecipesService.class);
                System.out.println("Second "+ingredients);
                Call<List<FoundRecipe>> call = service.getAllRecipes(ingredients,1, 20, api_key);
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
            }
        });

    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fridgeViewModel =
                new ViewModelProvider(this).get(FridgeViewModel.class);
        getActivity().setTitle("Fridge");

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