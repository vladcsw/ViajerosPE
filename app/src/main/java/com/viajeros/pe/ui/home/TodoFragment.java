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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.viajeros.pe.R;
import com.viajeros.pe.firebase.service.AuthService;

import java.util.ArrayList;

public class TodoFragment extends Fragment implements AdaptadorTodo.ItemClickListener {

    Button buttonTodo;
    FloatingActionButton fbaBack, fbaNext;
    ToDoViewModel todoViewModel;
    private ArrayList<String> todoNames;
    private ArrayList<Boolean> checkTodoNames;
    private AdaptadorTodo adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        todoNames = new ArrayList<>();
        checkTodoNames = new ArrayList<>();
      /*  todoNames.add("asdf");
        todoNames.add("asdasdff");
        todoNames.add("asdaasdsdff");*/
        //fragmentHomeSelect_buttonToDo
        View view = inflater.inflate(R.layout.fragment_todo, container, false);
        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.rv_places);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


        todoViewModel = new ToDoViewModel();

        String uid = AuthService.firebaseGetCurrentUser().getUid();
        /*OBTENER LOS DESTINOS DEL VIAJE SELECCIONADO*/
        //EJEMPLO DE VIAJE o BITACORA (BINNACLE)

        todoNames.clear();
        checkTodoNames.clear();
        todoViewModel.findByBinnacle("fddasassx").observe(this.getViewLifecycleOwner(), data->{
            data.forEach(touristPlace -> {

                for (int i = 0; i<touristPlace.getItemList().size(); i++){
                    Log.e("TAG", "TODO LIST: "+touristPlace.getItemList().get(i).getStatement());
                    todoNames.add(touristPlace.getItemList().get(i).getStatement());
                    checkTodoNames.add(touristPlace.getItemList().get(i).getItem_state());

                }

                //todoNames.add(touristPlace.getItemList());
            });
            adapter = new AdaptadorTodo(view.getContext(), todoNames, checkTodoNames);
            adapter.setClickListener(this::onItemClick);
            recyclerView.setAdapter(adapter);
        });


       // buttonTodo = view.findViewById(R.id.fragmentHomeSelect_buttonToDo);
        fbaBack = view.findViewById(R.id.fragmentHomeSelect_fabBefore);
        fbaNext = view.findViewById(R.id.fragmentHomeSelect_fabNext);

/*        buttonTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Mi lista por hacer", Toast.LENGTH_LONG).show();
            }
        });*/

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
        Toast.makeText(view.getContext(), "You clicked " + adapter.getItem(position)  +  " on row number " + position, Toast.LENGTH_SHORT).show();

    }

}