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
import androidx.lifecycle.LiveData;
import androidx.navigation.Navigation;

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

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    private CardView cardView02;
    private ToDoList toDoListsExample = new ToDoList(); // Store temporally todolist
    private ArrayList<ToDoListItem> items = new ArrayList<>(); // Array store temporally todolist item array

    @RequiresApi(api = Build.VERSION_CODES.N) // Only work if exist foreach
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Example
        List<TouristPlace> savePlace = new ArrayList<>(); // List of TouristPlaces

        HomeViewModel homeViewModel = new HomeViewModel(); // Calls view model Home
        // Get all tourist places saved id FireStore and fill the list of TouristPlaces
        homeViewModel.getAllLiveData().observe(getViewLifecycleOwner(), savePlace::addAll);

        String uid = AuthService.firebaseGetCurrentUser().getUid(); // Get id of current user logged

        Utils utils = new Utils(); // Own class utils created
        String binnacleIdGenerated = utils.getDocumentIdGenerated("Binnacle"); // Generate a id for Binnacle
        Log.e("Generated Id", binnacleIdGenerated); // Print generated id

        Binnacle binnacle = new Binnacle(uid, savePlace); // Object Binnacle to save
        binnacle.setDocumentId(binnacleIdGenerated); // Set the id generate in object binnacle created

        HomeSelectViewModel homeSelectViewModel = new HomeSelectViewModel(); // Calls view model Binnacle

        ToDoViewModel toDoViewModel = new ToDoViewModel(); // Calls view model TodoList

        // Get a todolist created by a binnacle (for the example we are using an id stored in firebase)
        toDoViewModel.findByBinnacle("Z5im6DGNLKNruSoiQTot").observe(getViewLifecycleOwner(), toDoLists -> {
            toDoListsExample = toDoLists.get(0); // store temporally the object todolist
            items = (ArrayList<ToDoListItem>) toDoLists.get(0).getItemList(); // we get the array itemList
            for (int i = 0; i < items.size(); i++) {
                Log.e("Item "+i,items.get(i).toString()); // Print each object
                items.get(i).setStatement("POSITION: "+i); // Update the value statement of the item
                items.set(i, items.get(i)); // Here we can change the item in position i
            }
        });

        // ArrayList of ToDoListItem
        List<ToDoListItem> itemList = new ArrayList<>();
        itemList.add(new ToDoListItem(true, "Ir a caminar"));
        itemList.add(new ToDoListItem(true, "Ir a comer"));
        itemList.add(new ToDoListItem(true, "Ir a correr"));
        ToDoList toDoList = new ToDoList(uid, binnacleIdGenerated, true, itemList); // ToDoList object

        //Example

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        cardView02 = view.findViewById(R.id.fragment_tripsCard);
        cardView02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Example
                homeSelectViewModel.saveWithIdGenerated(binnacle, binnacleIdGenerated); // Save binnacle in FireStore
                toDoViewModel.save(toDoList); // Save a new ToDoList object in FireStore

                toDoListsExample.setItemList(items); // Update the itemList in the object todolist
                toDoViewModel.update(toDoListsExample); // Update the element in firebase
                // Example

                Toast.makeText(view.getContext(), "Mi nuevo viaje", Toast.LENGTH_LONG).show();
                Navigation.findNavController(view).navigate(R.id.navigation_homeSelect);
            }
        });

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
}