package com.viajeros.pe.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viajeros.pe.R;
import com.viajeros.pe.firebase.model.Binnacle;
import com.viajeros.pe.firebase.model.ToDoList;
import com.viajeros.pe.firebase.model.ToDoListItem;
import com.viajeros.pe.firebase.model.TouristPlace;
import com.viajeros.pe.firebase.service.AuthService;
import com.viajeros.pe.firebase.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddToDoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddBinnacleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int j = 0;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String uid;
    String bid;
    Binnacle binnacle;
    String binnacleIdGenerated;
    List<TouristPlace> savePlace = new ArrayList<>(); // List of TouristPlaces
    private HomeViewModel homeViewModel;
    private HomeSelectViewModel homeSelectViewModel = new HomeSelectViewModel(); // Calls view model Binnacle

    public AddBinnacleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddToDoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddToDoFragment newInstance(String param1, String param2) {
        AddToDoFragment fragment = new AddToDoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
        Utils utils = new Utils(); // Own class utils created
        binnacleIdGenerated = utils.getDocumentIdGenerated("Binnacle");
        Log.e("Generated Id", binnacleIdGenerated); // Print generated id
        uid = AuthService.firebaseGetCurrentUser().getUid(); // Get id of current user logged

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        homeViewModel = new HomeViewModel(); // Calls view model Home
        // Get all tourist places saved id FireStore and fill the list of TouristPlaces
        homeViewModel.getAllLiveData().observe(getViewLifecycleOwner(), savePlace::addAll);

        View view = inflater.inflate(R.layout.fragment_add_binnacle, container, false);
        TextView someText = (TextView) view.findViewById(R.id.fragment_add_binnacle);
        view.findViewById(R.id.fragment_btn_binnacle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("XD", someText.getText().toString());

                binnacle = new Binnacle(uid, savePlace, someText.getText().toString()); // Object Binnacle to save
                binnacle.setDocumentId(binnacleIdGenerated);

                //Log.e("testx",savePlace.toString());
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
        });


        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu03, menu);
        super.onCreateOptionsMenu(menu,inflater);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                if (item.getItemId() == android.R.id.home){
                    Navigation.findNavController(getView()).navigate(R.id.navigation_home);
                }

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}