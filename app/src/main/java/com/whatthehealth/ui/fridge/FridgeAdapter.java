package com.whatthehealth.ui.fridge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.whatthehealth.R;
import com.whatthehealth.entities.FridgeItem;
import java.util.ArrayList;
import java.util.List;

public class FridgeAdapter extends RecyclerView.Adapter<FridgeElement> {

    private List<FridgeItem> fridgeItems = new ArrayList<>();
    private Listener listener;

    public void setShoppingList(List<FridgeItem> fridgeItems) {
        this.fridgeItems = fridgeItems;
        notifyDataSetChanged();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public FridgeElement onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element, parent, false);
        return new FridgeElement(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FridgeElement holder, int position) {
        TextView textView = holder.itemView.findViewById(R.id.shopping_item);
        textView.setText(fridgeItems.get(position).getItem());

        CheckBox checkBox = holder.itemView.findViewById(R.id.checkBox);
        checkBox.setChecked(fridgeItems.get(position).isChecked());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FridgeItem item = fridgeItems.get(position);
                item.setChecked(isChecked);
                listener.itemChange(item);
            }
        });
    }

    interface Listener{
        void itemChange(FridgeItem item);
    }

    @Override
    public int getItemCount() {
        return fridgeItems.size();
    }
}
