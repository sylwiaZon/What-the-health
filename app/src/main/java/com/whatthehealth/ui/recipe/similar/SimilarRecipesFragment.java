package com.whatthehealth.ui.recipe.similar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.whatthehealth.BuildConfig;
import com.whatthehealth.R;
import com.whatthehealth.models.SimilarRecipe;
import com.whatthehealth.network.ClientInstance;
import com.whatthehealth.network.GetFoundRecipesService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SimilarRecipesFragment extends Fragment {
    private View root;
    private int similarToId;
    private Listener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_search, container, false);
        return root;
    }

    public void setSimilarToId(int similarToId) {
        this.similarToId = similarToId;
        getSimilarData();
    }

    private void getSimilarData(){
        GetFoundRecipesService retrofit = ClientInstance.getRetrofitInstance().create(GetFoundRecipesService.class);
        RecyclerView recyclerView = root.findViewById(R.id.search_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        SimilarRecipesAdapter adapter = new SimilarRecipesAdapter(similarRecipe -> {
            listener.onRecipeChange(similarRecipe.getId());
        });
        recyclerView.setAdapter(adapter);
        retrofit.getSimilarRecipes(similarToId, BuildConfig.API_KEY).enqueue(new Callback<List<SimilarRecipe>>() {
            @Override
            public void onResponse(Call<List<SimilarRecipe>> call, Response<List<SimilarRecipe>> response) {
                adapter.setSimilarRecipes(response.body());
            }

            @Override
            public void onFailure(Call<List<SimilarRecipe>> call, Throwable t) {
                System.out.println();
            }
        });
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener{
        void onRecipeChange(int id);
    }
}
