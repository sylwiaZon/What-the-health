package com.whatthehealth.ui.shoppinglist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.whatthehealth.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoppingListFragment extends Fragment {

    private ShoppingListViewModel listViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listViewModel =
                ViewModelProviders.of(this).get(ShoppingListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_list, container, false);
        getActivity().setTitle("Shopping List");

        final RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        final ShoppingListAdapter adapter = new ShoppingListAdapter();
        final List shoppingList = new ArrayList();
        shoppingList.add("Pasta");
        shoppingList.add("Tomato");
        shoppingList.add("Apple");
        adapter.setShoppingList(shoppingList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Button plusButton = root.findViewById(R.id.plus_button);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingList.add("Jestem jedzeniem");
                adapter.setShoppingList(shoppingList);
            }
        });
        return root;
    }
}