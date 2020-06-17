package com.whatthehealth.ui.fridge;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.whatthehealth.R;

public class AddFridgeItemFragment extends DialogFragment {
    private AddItemDialogListener dialogListener;

    public void setDialogListener(AddItemDialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_item_dialog, null);
        TextView textView = view.findViewById(R.id.new_item);
        builder.setView(view)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialogListener.onDialogPositiveClick(textView.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddFridgeItemFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }


    public interface AddItemDialogListener {
        void onDialogPositiveClick(String text);
    }

}
