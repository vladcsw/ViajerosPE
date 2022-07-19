package com.viajeros.pe.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.viajeros.pe.R;
import com.viajeros.pe.firebase.service.AuthService;

public class HomeSelectFragment extends Fragment {

    Button buttonTodo;
    FloatingActionButton fbaBack, fbaNext;
    HomeViewModel homeViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        homeViewModel = new HomeViewModel();
        //fragmentHomeSelect_buttonToDo
        /*OBTENER EL ID DEL USUARIO*/
        String uid = AuthService.firebaseGetCurrentUser().getUid();
        /*OBTENER LOS DESTINOS DEL VIAJE SELECCIONADO*/
        //EJEMPLO DE VIAJE o BITACORA (BINNACLE)
        homeViewModel.getLiveDataBinnacle("1Z9FrVsQNM5EV3Bo1I9r").observe(this.getViewLifecycleOwner(), data->{
            data.getPlaces().forEach(touristPlace -> {
                Log.e("TAG", "LUGARES DE UNA BITACORA: "+touristPlace.getName());
            });
        });

        //EJEMPLO PARA OBTENER ACTIVIDADES DE UN VIAJE PARA EL ToDoList
        homeViewModel.getLiveDataToDo("KBc7yuoFw0RLlHi22MEQ").observe(this.getViewLifecycleOwner(), data->{
            data.getItemList().forEach(items ->{
                Log.e("TAG", "Items del ToDoList de una bitacora: "+items.getStatement());
            });
        });


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
                Navigation.findNavController(view).navigate(R.id.navigation_map);
            }
        });

        return view;

    }
}
//    Log.e("TAG", list.toString());
//1Z9FrVsQNM5EV3Bo1I9r - primera bitacora