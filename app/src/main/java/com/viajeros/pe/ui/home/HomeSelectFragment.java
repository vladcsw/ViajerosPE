package com.viajeros.pe.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.viajeros.pe.R;

public class HomeSelectFragment extends Fragment {

    Button buttonTodo;
    FloatingActionButton fbaBack, fbaNext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        //fragmentHomeSelect_buttonToDo
        View view = inflater.inflate(R.layout.fragment_home_select, container, false);

        buttonTodo = view.findViewById(R.id.fragmentHomeSelect_buttonToDo);
        fbaBack = view.findViewById(R.id.fragmentHomeSelect_fabBefore);
        fbaNext = view.findViewById(R.id.fragmentHomeSelect_fabNext);

        buttonTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Mi lista por hacer", Toast.LENGTH_LONG).show();
            }
        });

        fbaBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigation_home);
            }
        });

        fbaNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigation_homeSelect2);
            }
        });

        return view;

    }
}