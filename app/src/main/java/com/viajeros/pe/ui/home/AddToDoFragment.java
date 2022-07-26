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
import com.viajeros.pe.firebase.model.ToDoList;
import com.viajeros.pe.firebase.model.ToDoListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddToDoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddToDoFragment extends Fragment {

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

    public AddToDoFragment() {
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        uid = getArguments().get("uid").toString();
        bid = getArguments().get("bid").toString();
        /*ToDoViewModel x = new ToDoViewModel();
        Log.e("test",x.test("ulbpvJdjE05LxyuW798o")+"");*/
        View view = inflater.inflate(R.layout.fragment_add_to_do, container, false);
        TextView someText = (TextView) view.findViewById(R.id.fragment_add_todo);
        view.findViewById(R.id.fragment_btn_todo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("XD", someText.getText().toString());
                ToDoViewModel todoViewModel = new ToDoViewModel();
                AtomicReference<ToDoList> g = new AtomicReference<>();
                int i = 0;
                i++;
                final int[] finalI = {i};
                todoViewModel.findByBinnacle(bid).observe( getViewLifecycleOwner(), data-> {

                    for (ToDoList touristPlace : data) {
                        if (finalI[0] < 2) {
                            finalI[0]++;
                            touristPlace.getItemList().add(new ToDoListItem(true,someText.getText().toString()));
                            g.set(new ToDoList(uid, bid, true, touristPlace.getItemList()));
                            todoViewModel.setH(g.get(), touristPlace.getDocumentId());
                            todoViewModel.update(g.get());
                            break;
                        }
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("uid", uid);
                    bundle.putString("bid", bid);
                    Navigation.findNavController(getView()).navigate(R.id.navigation_todo, bundle);
                });

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
                    Bundle bundle = new Bundle();
                    bundle.putString("uid", uid);
                    bundle.putString("bid", bid);
                    Navigation.findNavController(getView()).navigate(R.id.navigation_todo, bundle);
                }

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}