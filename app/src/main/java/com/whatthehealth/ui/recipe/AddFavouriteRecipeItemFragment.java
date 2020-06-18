package com.whatthehealth.ui.recipe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.whatthehealth.R;

public class AddFavouriteRecipeItemFragment extends DialogFragment {
    private AddFavouriteRecipeItemFragment.AddItemDialogListener dialogListener;

    public void setDialogListener(AddItemDialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_fav_recipe_dialog, null);
        builder.setView(view)
                .setPositiveButton("Add", (dialog, id) -> {
                    AddFavouriteRecipeItemFragment.this.getDialog().cancel();
                    dialogListener.onDialogPositiveClick();
                })
                .setNegativeButton("Cancel", (dialog, id) -> AddFavouriteRecipeItemFragment.this.getDialog().cancel());
        return builder.create();
    }

    public interface AddItemDialogListener {
        void onDialogPositiveClick();
    }

}
