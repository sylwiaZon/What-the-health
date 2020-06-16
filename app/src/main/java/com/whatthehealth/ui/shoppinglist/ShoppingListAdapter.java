package com.whatthehealth.ui.shoppinglist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.whatthehealth.R;
import com.whatthehealth.entities.ShopItem;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListElement> {

    private List<ShopItem> shoppingList = new ArrayList<>();
    private Listener listener;

    public void setShoppingList(List<ShopItem> shoppingList) {
        this.shoppingList = shoppingList;
        notifyDataSetChanged();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ShoppingListElement onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element, parent, false);
        return new ShoppingListElement(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListElement holder, int position) {
        TextView textView = holder.itemView.findViewById(R.id.shopping_item);
        textView.setText(shoppingList.get(position).getItem());

        CheckBox checkBox = holder.itemView.findViewById(R.id.checkBox);
        checkBox.setChecked(shoppingList.get(position).isChecked());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ShopItem item = shoppingList.get(position);
                item.setChecked(isChecked);
                listener.itemChange(item);
            }
        });
    }

    interface Listener{
        void itemChange(ShopItem item);
    }

    @Override
    public int getItemCount() {
        return shoppingList.size();
    }
}
