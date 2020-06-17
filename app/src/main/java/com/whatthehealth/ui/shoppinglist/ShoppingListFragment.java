package com.whatthehealth.ui.shoppinglist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.whatthehealth.R;
import com.whatthehealth.entities.ShopItem;
import com.whatthehealth.repositories.ShoppingListRepository;

import java.util.List;

public class ShoppingListFragment extends Fragment implements AddShoppingItemFragment.AddItemDialogListener {

    private ShoppingListViewModel shoppingListViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private ShoppingListRepository shoppingListRepository;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list, container, false);
        getActivity().setTitle("Shopping List");
        shoppingListRepository = new ShoppingListRepository(this.getContext());

        final RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        final ShoppingListAdapter adapter = new ShoppingListAdapter();
        adapter.setListener(new ShoppingListAdapter.Listener() {
            @Override
            public void itemChange(ShopItem item) {
                shoppingListRepository.changeItemState(item);
            }
        });

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
        AddShoppingItemFragment.AddItemDialogListener listener = this;
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddShoppingItemFragment newFragment = new AddShoppingItemFragment();
                newFragment.setDialogListener(listener);
                newFragment.show(getChildFragmentManager(), "AddShoppingItem");
            }
        });
        Button minusButton = root.findViewById(R.id.minus_button);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingListRepository.deleteCheckedItems();
            }
        });

        return root;
    }

    @Override
    public void onDialogPositiveClick(String text) {
        ShopItem item = new ShopItem(text);
        shoppingListRepository.insert(item);
    }

}