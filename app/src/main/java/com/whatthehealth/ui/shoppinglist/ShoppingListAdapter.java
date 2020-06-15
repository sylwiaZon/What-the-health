package com.whatthehealth.ui.shoppinglist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.whatthehealth.R;
import com.whatthehealth.entities.ShopItem;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListElement> {

    private List<ShopItem> shoppingList = new ArrayList<>();

    public void setShoppingList(List<ShopItem> shoppingList) {
        this.shoppingList = shoppingList;
        notifyDataSetChanged();
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
    }

    @Override
    public int getItemCount() {
        return shoppingList.size();
    }
}
