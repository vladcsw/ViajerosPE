package com.viajeros.pe.ui.home;

import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.viajeros.pe.R;
import com.viajeros.pe.databinding.FragmentHomeBinding;
import com.viajeros.pe.firebase.model.Binnacle;
import com.viajeros.pe.firebase.model.ToDoList;
import com.viajeros.pe.firebase.model.ToDoListItem;
import com.viajeros.pe.firebase.model.TouristPlace;
import com.viajeros.pe.firebase.service.AuthService;
import com.viajeros.pe.firebase.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class HomeFragment extends Fragment implements AdaptadorViajes.ItemClickListener {

    private FragmentHomeBinding binding;
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    private CardView cardView02;
    private ArrayList<String> binnacleNames;
    private ArrayList<String> binnacleDocuments = new ArrayList<>();

    private HomeViewModel homeViewModel;
    private HomeSelectViewModel homeSelectViewModel;
    List<TouristPlace> savePlace = new ArrayList<>(); // List of TouristPlaces
    Binnacle binnacle;
    String binnacleIdGenerated;
    AdaptadorViajes adapter;
    String uid;

    @RequiresApi(api = Build.VERSION_CODES.N) // Only work if exist foreach
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //
        // data to populate the RecyclerView with
        binnacleNames = new ArrayList<>();
        binnacleNames.add("");

        /*animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");*/

        //
        // Example


        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.rv_viajes);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        homeViewModel = new HomeViewModel(); // Calls view model Home
        // Get all tourist places saved id FireStore and fill the list of TouristPlaces
        homeViewModel.getAllLiveData().observe(getViewLifecycleOwner(), savePlace::addAll);

        uid = AuthService.firebaseGetCurrentUser().getUid(); // Get id of current user logged
        Log.e("Usuario", "ID: " + uid);
        homeViewModel.getAllLiveData().observe(this.getViewLifecycleOwner(), data -> {
            data.forEach(touristPlace -> {
                Log.e("TAG", "LUGARES DE UNA BITACORA: " + touristPlace.getName());
            });
        });

        Utils utils = new Utils(); // Own class utils created
        binnacleIdGenerated = utils.getDocumentIdGenerated("Binnacle");
        Log.e("TAG", binnacleIdGenerated); // Print generated id

        Log.e("TAG", savePlace.toString());
        binnacle = new Binnacle(uid, savePlace); // Object Binnacle to save
        binnacle.setDocumentId(binnacleIdGenerated);
        homeSelectViewModel = new HomeSelectViewModel(); // Calls view model Binnacle
        AtomicInteger h = new AtomicInteger();
        ArrayList<String> a = new ArrayList<>();
        a.add("");
        HomeSelectViewModel homeSelectViewModel2 = new HomeSelectViewModel();
        Log.e("UID",uid);// Calls view model Binnacle
        homeSelectViewModel2.getAllByUser(uid).observe(this.getViewLifecycleOwner(), data -> {
            binnacleNames.clear();
            binnacleDocuments.clear();
            binnacleNames.add("");
            binnacleDocuments.add("");
            h.set(0);
            data.forEach(binaclet ->{
                Log.e("TAG", "test");
                h.getAndIncrement();
                Log.e("TAG",binaclet.getUserId());
                binnacleNames.add("Viaje"+h);
                binnacleDocuments.add(binaclet.getDocumentId());
                a.add(binaclet.getDocumentId());

            });
            adapter = new AdaptadorViajes(view.getContext(), binnacleNames);
            adapter.setClickListener(this::onItemClick, a);
            recyclerView.setAdapter(adapter);

        });
        //animalNames.add("test");


        //ToDoViewModel toDoViewModel = new ToDoViewModel(); // Calls view model TodoList
        // ArrayList of ToDoListItem
       /* List<ToDoListItem> itemList = new ArrayList<>();
        itemList.add(new ToDoListItem(true, "Ir a caminar"));
        itemList.add(new ToDoListItem(true, "Ir a comer"));
        itemList.add(new ToDoListItem(true, "Ir a correr"));
        ToDoList toDoList = new ToDoList(uid, binnacleIdGenerated, true, itemList); */// ToDoList object

        //Example



       /*cardView02 = view.findViewById(R.id.fragment_tripsCard);
        cardView02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Example
                homeSelectViewModel.saveWithIdGenerated(binnacle, binnacleIdGenerated); // Save binnacle in FireStore
                toDoViewModel.save(toDoList); // Save ToDoList in FireStore
                // Example
                Toast.makeText(view.getContext(), "Mi nuevo viaje", Toast.LENGTH_LONG).show();
                Navigation.findNavController(view).navigate(R.id.navigation_homeSelect);
            }
        });*/


        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu01, menu);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);

                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);

                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.e("usuarios2", "test");
        if(position > 0){
            //Toast.makeText(view.getContext(), "You clicked " + adapter.getItem(position) +adapter.getCode().get(position) +  " on row number " + position, Toast.LENGTH_SHORT).show();
            Toast.makeText(view.getContext(), "Mi nuevo viaje", Toast.LENGTH_LONG).show();
            Bundle bundle = new Bundle();
            bundle.putString("uid", uid);
            bundle.putString("bid", binnacleDocuments.get(position));
            Navigation.findNavController(view).navigate(R.id.navigation_homeSelect,bundle);
            Log.e("doc",binnacleDocuments.get(position));
        }else{
            /*String uid = AuthService.firebaseGetCurrentUser().getUid(); // Get id of current user logged
            ToDoViewModel toDoViewModel = new ToDoViewModel(); // Calls view model TodoList
            List<ToDoListItem> itemList = new ArrayList<>();
            itemList.add(new ToDoListItem(true, "Ir a caminar"));
            itemList.add(new ToDoListItem(true, "Ir a comer"));
            itemList.add(new ToDoListItem(true, "Ir a correr"));
            ToDoList toDoList = new ToDoList(uid, "fddasass", true, itemList); /// ToDoList object
            toDoViewModel.save(toDoList); // Save ToDoList in FireStore*/
            /*Toast.makeText(view.getContext(), "Mi nuevo viaje", Toast.LENGTH_LONG).show();
            Navigation.findNavController(view).navigate(R.id.navigation_homeSelect);*/
            Log.e("testx",savePlace.toString());
            homeSelectViewModel.saveWithIdGenerated(binnacle, binnacleIdGenerated); // Save binnacle in FireStore

            Bundle bundle = new Bundle();
            bundle.putString("uid", uid);
            bundle.putString("bid", binnacleIdGenerated);

            List<ToDoListItem> itemList = new ArrayList<>();

            ToDoList toDoList = new ToDoList(uid, binnacleIdGenerated, true, itemList);
            ToDoViewModel todo = new ToDoViewModel();
            todo.save(toDoList);
            Navigation.findNavController(view).navigate(R.id.navigation_homeSelect,bundle);

        }
    }

}