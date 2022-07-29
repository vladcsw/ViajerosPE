package com.viajeros.pe.ui.home;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.viajeros.pe.R;
import com.viajeros.pe.firebase.model.ToDoList;
import com.viajeros.pe.firebase.model.ToDoListItem;
import com.viajeros.pe.firebase.model.TouristPlace;
import com.viajeros.pe.firebase.service.AuthService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class TodoFragment extends Fragment implements AdaptadorTodo.ItemClickListener {

    Button buttonTodo;
    FloatingActionButton fbaBack, fbaNext;
    ToDoViewModel todoViewModel;
    private ArrayList<String> todoNames;
    private ArrayList<Boolean> checkTodoNames;
    private AdaptadorTodo adapter;
    String uid;
    String bid;
    String tid;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        todoNames = new ArrayList<>();
        checkTodoNames = new ArrayList<>();
        uid = getArguments().get("uid").toString();
        bid = getArguments().get("bid").toString();
      /*  todoNames.add("asdf");
        todoNames.add("asdasdff");
        todoNames.add("asdaasdsdff");*/
        //fragmentHomeSelect_buttonToDo
        view = inflater.inflate(R.layout.fragment_todo, container, false);
        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.rv_places);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


        todoViewModel = new ToDoViewModel();

        String uid = AuthService.firebaseGetCurrentUser().getUid();
        /*OBTENER LOS DESTINOS DEL VIAJE SELECCIONADO*/
        //EJEMPLO DE VIAJE o BITACORA (BINNACLE)

        todoNames.clear();
        checkTodoNames.clear();

        /*todoNames.add("Ir a caminar");
        todoNames.add("Ir a comer");
        todoNames.add("Ir a correr");
        checkTodoNames.add(true);
        checkTodoNames.add(true);
        checkTodoNames.add(true);
        adapter = new AdaptadorTodo(view.getContext(), todoNames, checkTodoNames);
        adapter.setClickListener(this::onItemClick);
        recyclerView.setAdapter(adapter);
        Log.e("H1","H1");*/
        todoViewModel.findByBinnacle(bid).observe(this.getViewLifecycleOwner(), data->{
            data.forEach(touristPlace -> {
                todoNames.clear();
                checkTodoNames.clear();
                for (int i = 0; i<touristPlace.getItemList().size(); i++){
                    Log.e("TAG", "TODO LIST: "+touristPlace.getItemList().get(i).getStatement());
                    todoNames.add(touristPlace.getItemList().get(i).getStatement());
                    checkTodoNames.add(touristPlace.getItemList().get(i).getItem_state());
                    tid = touristPlace.getDocumentId();

                }

                //todoNames.add(touristPlace.getItemList());
            });

            adapter = new AdaptadorTodo(view.getContext(), todoNames, checkTodoNames, uid, bid);
            adapter.setClickListener(this::onItemClick);
            new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
            recyclerView.setAdapter(adapter);
            Log.e("H1","H1");


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
       // Toast.makeText(view.getContext(), "You clicked " + adapter.getItem(position)  +  " on row number " + position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu02, menu);
        super.onCreateOptionsMenu(menu,inflater);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.app_bar_add:
                if (item.getItemId() == R.id.app_bar_add){
                    Bundle bundle = new Bundle();
                    bundle.putString("uid", uid);
                    bundle.putString("bid", bid);
                    Navigation.findNavController(getView()).navigate(R.id.navigation_addtodo, bundle);
                }

                //.e("memu","men");
               /* List<ToDoListItem> itemList = new ArrayList<>();
                itemList.add(new ToDoListItem(false, "Ir a caminar"));
                List<ToDoList> savePlace = new ArrayList<>(); // List of TouristPlaces
                ToDoList f = new ToDoList("DSH3CzFvIMYFDvMLHGvBEPdbM7d2","cSksnzFszcriepLWGuUU",true, itemList);

                todoViewModel.setH(f,"cSksnzFszcriepLWGuUU");
                savePlace.add(f);
                todoViewModel.getAllLiveData().observe(getViewLifecycleOwner(), savePlace::addAll);*/
                //AtomicReference<ToDoList> g = new AtomicReference<>();

                //You can call detail fragment here

                /*int i = 0;

                Log.e("MM",i+"");
                i++;
                final int[] finalI = {i};
                todoViewModel.findByBinnacle("kXq9kQitaJBbP73fawwr").observe( getViewLifecycleOwner(), data-> {
                    for (ToDoList touristPlace : data) {
                        if (finalI[0] < 2) {

                            finalI[0]++;
                            touristPlace.getItemList().add(new ToDoListItem(true,"Ir de "));
                            Log.e("IF", touristPlace.getItemList().toString());
                            g.set(new ToDoList("DSH3CzFvIMYFDvMLHGvBEPdbM7d2", "kXq9kQitaJBbP73fawwr", true, touristPlace.getItemList()));
                            todoViewModel.setH(g.get(), touristPlace.getDocumentId());

                            //clear();
                            todoViewModel.update(g.get());
                            break;
                        }
                    }
                });*/
            case android.R.id.home:
                if (item.getItemId() == android.R.id.home){
                    Bundle bundle = new Bundle();
                    bundle.putString("uid", uid);
                    bundle.putString("bid", bid);
                    Navigation.findNavController(getView()).navigate(R.id.navigation_homeSelect, bundle);
                }

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public int getDragDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return super.getDragDirs(recyclerView, viewHolder);
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            ToDoViewModel todoViewModel2 = new ToDoViewModel();

            final int[] i = {1};
            todoViewModel2.findByBinnacle(bid).observe((LifecycleOwner) view.getContext(), data -> {

                if (i[0] == 1){
                    i[0]++;
                    data.get(0).getItemList().remove(viewHolder.getAdapterPosition());
                    todoViewModel2.saveWithId(data.get(0),tid);

                }

            });
        }
    };
}