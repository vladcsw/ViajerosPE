package com.viajeros.pe.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.viajeros.pe.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeSelect2Fragment\#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeSelect2Fragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_home_select2, container, false);

        buttonTodo = view.findViewById(R.id.fragmentHomeSelect2_buttonToDo);
        fbaBack = view.findViewById(R.id.fragmentHomeSelect2_fabBefore);
        fbaNext = view.findViewById(R.id.fragmentHomeSelect2_fabNext);

        buttonTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Mi lista por hacer", Toast.LENGTH_LONG).show();
            }
        });

        fbaBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigation_homeSelect);
            }
        });

        fbaNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "RA", Toast.LENGTH_LONG).show();
            }
        });

        return view;

    }
}