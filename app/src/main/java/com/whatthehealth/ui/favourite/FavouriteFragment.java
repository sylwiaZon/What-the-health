package com.whatthehealth.ui.favourite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.whatthehealth.R;
import com.whatthehealth.entities.RecipeItem;
import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment {
    private FavouriteViewModel favouriteViewModel;
    private FavouriteAdapter adapter;
    private RecyclerView recyclerView;
    private List<RecipeItem> recipeList = new ArrayList<>();

    private void getData(){
        recipeList.clear();
        favouriteViewModel.getAllItems().observe(getViewLifecycleOwner(), items -> {
            for(RecipeItem item : items){
                recipeList.add(item);
            }
            adapter = new FavouriteAdapter(this.getContext(),recipeList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favouriteViewModel =
                new ViewModelProvider(this).get(FavouriteViewModel.class);
        getActivity().setTitle("Favourite");
        View root = inflater.inflate(R.layout.fragment_favourite, container, false);
        recyclerView = root.findViewById(R.id.recycler_view_2);
        favouriteViewModel.getItemsCount().observe(getViewLifecycleOwner(),integer -> {
            if(integer > 0) {
                getData();
            } else {
                System.out.println("No recipes");
            }
        });
        return root;
    }
}