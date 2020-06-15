package com.whatthehealth.ui.shoppinglist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.whatthehealth.R;
import com.whatthehealth.entities.ShopItem;
import com.whatthehealth.repositories.ShoppingListRepository;

import java.util.List;

public class ShoppingListFragment extends Fragment {

    private ShoppingListViewModel shoppingListViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private ShoppingListRepository shoppingListRepository;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list, container, false);
        getActivity().setTitle("Shopping List");

        final RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        final ShoppingListAdapter adapter = new ShoppingListAdapter();

        shoppingListRepository = new ShoppingListRepository(this.getContext());

        shoppingListViewModel = new ViewModelProvider(this).get(ShoppingListViewModel.class);
        shoppingListViewModel.getAllItems().observe(getViewLifecycleOwner(), new Observer<List<ShopItem>>() {
            @Override
            public void onChanged(List<ShopItem> shopItems) {
                adapter.setShoppingList(shopItems);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Button plusButton = root.findViewById(R.id.plus_button);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingListRepository.insert(new ShopItem("Jestem jedzeniem"));
            }
        });
        return root;
    }

    /*public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            //ShopItem item = new ShopItem(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
            //shoppingListViewModel.insert(item);
        } else {
            //Toast.makeText(
                    //getApplicationContext(),
                    //R.string.empty_not_saved,
                    //Toast.LENGTH_LONG).show();
        }
    }*/
}