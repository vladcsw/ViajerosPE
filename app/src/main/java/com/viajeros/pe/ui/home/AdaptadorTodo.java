package com.viajeros.pe.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.viajeros.pe.R;
import com.viajeros.pe.firebase.model.ToDoList;
import com.viajeros.pe.firebase.model.ToDoListItem;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorTodo extends RecyclerView.Adapter<AdaptadorTodo.AdaptadorTodoHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private List<Boolean> mCheck;
    //private View view;
    AdaptadorTodo(Context context, List<String> data, List<Boolean> a) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mCheck = a;
    }

    @NonNull
    @Override
    public AdaptadorTodoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.layout_places,parent,false);
        return new AdaptadorTodoHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorTodoHolder holder, @SuppressLint("RecyclerView") int position) {

        String place = mData.get(position);
        boolean placeChecked = mCheck.get(position);
        holder.txt1.setText(place);
        holder.check.setChecked(placeChecked);
        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToDoViewModel todoViewModel = new ToDoViewModel();
                String id=mData.get(position);
                Log.e("positioncheck",id);

                //You can call detail fragment here
                if (mCheck.get(position) == true){

                    todoViewModel.findByBinnacle("fddasassx").observe((LifecycleOwner) view.getContext(), data->{
                        data.forEach(touristPlace -> {
                            List<ToDoListItem> itemList = new ArrayList<>();
                            itemList.add(new ToDoListItem(false, "Ir a caminar"));
                            Log.e("HEREHERE",touristPlace.getDocumentId());
                            /*    String uid = touristPlace.getUserId();
                            List<ToDoListItem> itemList = new ArrayList<>();
                            itemList.add(new ToDoListItem(true, "Ir a caminar"));
                            itemList.add(new ToDoListItem(true, "Ir a comer"));
                            itemList.add(new ToDoListItem(true, "Ir a correr"));
                            ToDoList toDoList = new ToDoList(uid, "dX0OtRyfQzEBrebrDY1v", true, itemList); // ToDoList object*/

                            //itemList.add(new ToDoListItem(true, touristPlace.getItemList()));
                            Log.e("xd",todoViewModel.getY());
                            //Log.e("xd2",touristPlace.getItemList().get(position).getStatement()+"xd");
                            ToDoList g = new ToDoList("DSH3CzFvIMYFDvMLHGvBEPdbM7d2","fddasassx",true,itemList);
                            todoViewModel.setH(g,touristPlace.getDocumentId());
                            todoViewModel.update(g);
                            //touristPlace.getItemList().get(position).setItem_state(false);

                //ToDoList toDoList = new ToDoList(uid, "dX0OtRyfQzEBrebrDY1v", false, touristPlace.getItemList()); // ToDoList object
                // todoViewModel.save(toDoList);
                //todoNames.add(touristPlace.getItemList());
                        });

                    });
                }else{
                    todoViewModel.findByBinnacle("fddasassx").observe((LifecycleOwner) view.getContext(), data->{
                        data.forEach(touristPlace -> {
                            List<ToDoListItem> itemList = new ArrayList<>();
                            itemList.add(new ToDoListItem(true, "Ir a caminar"));
                            Log.e("HEREHERE2",touristPlace.getDocumentId());
                            /*    String uid = touristPlace.getUserId();
                            List<ToDoListItem> itemList = new ArrayList<>();
                            itemList.add(new ToDoListItem(true, "Ir a caminar"));
                            itemList.add(new ToDoListItem(true, "Ir a comer"));
                            itemList.add(new ToDoListItem(true, "Ir a correr"));
                            ToDoList toDoList = new ToDoList(uid, "dX0OtRyfQzEBrebrDY1v", true, itemList); // ToDoList object*/

                            //itemList.add(new ToDoListItem(true, touristPlace.getItemList()));
                            Log.e("xd",todoViewModel.getY());
                            Log.e("xd2",touristPlace.getItemList().get(position).getStatement()+"xd");
                            ToDoList g = new ToDoList("DSH3CzFvIMYFDvMLHGvBEPdbM7d2","fddasassx",true,itemList);
                            todoViewModel.setH(g,touristPlace.getDocumentId());
                            todoViewModel.update(g);
                        });

                    });
                }


            }
        });
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    class AdaptadorTodoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txt1;
        CheckBox check;
        public AdaptadorTodoHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.textView11);
            check = itemView.findViewById(R.id.checkBox);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }

    }

    String getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;

    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }


}
