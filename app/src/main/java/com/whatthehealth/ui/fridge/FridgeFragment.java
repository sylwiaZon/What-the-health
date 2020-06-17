package com.whatthehealth.ui.fridge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.whatthehealth.R;
import com.whatthehealth.entities.FridgeItem;
import com.whatthehealth.repositories.FridgeRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class FridgeFragment extends Fragment implements AddFridgeItemFragment.AddItemDialogListener {

    private FridgeViewModel fridgeViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private FridgeRepository fridgeRepository;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fridgeRepository = new FridgeRepository(this.getContext());
        fridgeViewModel = new ViewModelProvider(this).get(FridgeViewModel.class);
        getActivity().setTitle("Fridge");

        View view = inflater.inflate(R.layout.fragment_fridge, container, false);
        ConstraintLayout emptyFridge = view.findViewById(R.id.empty_fridge);
        ConstraintLayout notEmptyFridge = view.findViewById(R.id.fridge);
        emptyFridge.setVisibility(View.VISIBLE);
        notEmptyFridge.setVisibility(View.GONE);
        fridgeViewModel.getItemsCount().observe(getViewLifecycleOwner(),integer -> {
            if(integer > 0) {
                notEmptyFridge.setVisibility(View.VISIBLE);
                emptyFridge.setVisibility(View.GONE);
            } else {
                emptyFridge.setVisibility(View.VISIBLE);
                notEmptyFridge.setVisibility(View.GONE);
            }
        });
        setUpEmptyFridgeView(view);
        setUpNotEmptyFridgeView(view);
        return view;
    }

    private void setUpNotEmptyFridgeView(View root){
        final RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        final FridgeAdapter adapter = new FridgeAdapter();
        adapter.setListener(new FridgeAdapter.Listener() {
            @Override
            public void itemChange(FridgeItem item) {
                fridgeRepository.changeItemState(item);
            }
        });

        fridgeViewModel.getAllItems().observe(getViewLifecycleOwner(), new Observer<List<FridgeItem>>() {
            @Override
            public void onChanged(List<FridgeItem> fridgeItems) {
                adapter.setShoppingList(fridgeItems);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Button plusButton = root.findViewById(R.id.plus_button);
        AddFridgeItemFragment.AddItemDialogListener listener = this;
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFridgeItemFragment newFragment = new AddFridgeItemFragment();
                newFragment.setDialogListener(listener);
                newFragment.show(getChildFragmentManager(), "AddFridgeItem");
            }
        });
        Button minusButton = root.findViewById(R.id.minus_button);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fridgeRepository.deleteCheckedItems();
            }
        });

    }

    private void setUpEmptyFridgeView(View root){
        Button plusButton = root.findViewById(R.id.plus_empty_button);
        AddFridgeItemFragment.AddItemDialogListener listener = this;
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = root.findViewById(R.id.new_item);
                FridgeItem fridgeItem = new FridgeItem(textView.getText().toString());
                fridgeRepository.insert(fridgeItem);
            }
        });
    }

    @Override
    public void onDialogPositiveClick(String text) {
        FridgeItem item = new FridgeItem(text);
        fridgeRepository.insert(item);
    }

}