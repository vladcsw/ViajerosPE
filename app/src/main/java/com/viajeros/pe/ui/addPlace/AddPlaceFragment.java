package com.viajeros.pe.ui.addPlace;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.viajeros.pe.databinding.FragmentNotificationsBinding;
import com.viajeros.pe.firebase.model.TouristPlace;

public class AddPlaceFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AddPlaceViewModel addPlaceViewModel =
                new ViewModelProvider(this).get(AddPlaceViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        Button btn = binding.fragmentAddPlaceButtonAddPlace;
        btn.setOnClickListener(view -> {
            TouristPlace touristPlace = new TouristPlace(binding.fragmentAddPlaceEditTextName.getText().toString(),
                    binding.fragmentAddPlaceEditTextGps.getText().toString(),
                    binding.fragmentAddPlaceEditTextDescription.getText().toString(),
                    false);
            addPlaceViewModel.save(touristPlace);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}