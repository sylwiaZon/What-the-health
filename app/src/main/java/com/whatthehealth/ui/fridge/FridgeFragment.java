package com.whatthehealth.ui.fridge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.whatthehealth.R;

public class FridgeFragment extends Fragment {

    private FridgeViewModel fridgeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fridgeViewModel =
                ViewModelProviders.of(this).get(FridgeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_fridge, container, false);
        final TextView textView = root.findViewById(R.id.text_fridge);
        fridgeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
                getActivity().setTitle("Fridge");
            }
        });
        return root;
    }
}