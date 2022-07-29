package com.viajeros.pe.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.viajeros.pe.R;
import com.viajeros.pe.firebase.service.AuthService;

import java.util.ArrayList;

public class HomeSelectFragment extends Fragment implements AdaptadorPlaces.ItemClickListener {

    Button buttonTodo;
    FloatingActionButton fbaBack, fbaNext;
    private HomeViewModel homeViewModel;
    private ArrayList<String> placesNames;
    private AdaptadorPlaces adapter;
    private String uid;
    private String bid;
    private ArrayList<Boolean> checkTodoNames = new ArrayList<>();
    View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Log.e("bundle",getArguments().getString("amount")) ;
        placesNames = new ArrayList<>();
      /*  placesNames.add("asdf");
        placesNames.add("asdasdff");
        placesNames.add("asdaasdsdff");*/
        //fragmentHomeSelect_buttonToDo
        view = inflater.inflate(R.layout.fragment_home_select, container, false);
        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.rv_places);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


        homeViewModel = new HomeViewModel();

        //String uid = AuthService.firebaseGetCurrentUser().getUid();
        /*OBTENER LOS DESTINOS DEL VIAJE SELECCIONADO*/
        //EJEMPLO DE VIAJE o BITACORA (BINNACLE)

        uid = getArguments().get("uid").toString();
        bid = getArguments().get("bid").toString();

        homeViewModel.getLiveDataBinnacle(bid).observe(this.getViewLifecycleOwner(), data->{

            checkTodoNames.clear();
            placesNames.clear();

            data.getPlaces().forEach(touristPlace -> {
                Log.e("TAG", "LUGARES DE UNA BITACORA: "+touristPlace.getName());
                placesNames.add(touristPlace.getName());
                checkTodoNames.add(touristPlace.getState());
            });
            adapter = new AdaptadorPlaces(view.getContext(), placesNames, checkTodoNames, uid, bid);
            adapter.setClickListener(this::onItemClick);
            recyclerView.setAdapter(adapter);
        });





        buttonTodo = view.findViewById(R.id.fragmentHomeSelect_buttonToDo);
        fbaBack = view.findViewById(R.id.fragmentHomeSelect_fabBefore);
        fbaNext = view.findViewById(R.id.fragmentHomeSelect_fabNext);

        buttonTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), "Mi lista por hacer", Toast.LENGTH_LONG).show();}

                Bundle bundle = new Bundle();
                bundle.putString("uid", uid);
                bundle.putString("bid", bid);
                Navigation.findNavController(view).navigate(R.id.navigation_todo,bundle);
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

    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(view.getContext(), "You clicked " + adapter.getItem(position)  +  " on row number " + position, Toast.LENGTH_SHORT).show();

    }



}