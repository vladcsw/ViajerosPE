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

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.viajeros.pe.R;
import com.viajeros.pe.firebase.model.ToDoList;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AdaptadorTodo extends RecyclerView.Adapter<AdaptadorTodo.AdaptadorTodoHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private List<Boolean> mCheck;
    private String uid;
    private String bid;
    private int GG = 0;
    //private View view;
    AdaptadorTodo(Context context, List<String> data, List<Boolean> a, String uid, String bid) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mCheck = a;
        this.uid = uid;
        this.bid = bid;
    }

    @NonNull
    @Override
    public AdaptadorTodoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


            View view = mInflater.inflate(R.layout.layout_places,parent,false);
            return new AdaptadorTodoHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorTodoHolder holder, @SuppressLint("RecyclerView") int position) {

        Log.e("H3","H3");

        String place = mData.get(position);
        final boolean placeChecked = mCheck.get(position);
        holder.txt1.setText(place);
        holder.check.setChecked(placeChecked);
        holder.check.setOnCheckedChangeListener(null);
        int i = 0;

        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int i = 0;

                Log.e("MM",i+"");
                i++;

                ToDoViewModel todoViewModel = new ToDoViewModel();
               /* String id=mData.get(position);
                Log.e("positioncheck",id);*/
                AtomicReference<ToDoList> g = new AtomicReference<>();

                //You can call detail fragment here


                final int[] finalI = {i};
                todoViewModel.findByBinnacle(bid).observe((LifecycleOwner) compoundButton.getContext(), data->{
                    for (ToDoList touristPlace : data) {
                        if (finalI[0] <  2){
                            Log.e("MM", finalI[0] + "");
                            finalI[0]++;
                            touristPlace.getItemList().get(position).setItem_state(b);
                            Log.e("IF", touristPlace.getItemList().toString());
                            g.set(new ToDoList(uid, bid, true, touristPlace.getItemList()));
                            todoViewModel.setH(g.get(), touristPlace.getDocumentId());

                            //clear();
                            todoViewModel.update(g.get());
                            break;
                        }


                        //notifyItemChanged(position);
                            /*List<ToDoListItem> itemList = new ArrayList<>();
                            itemList.add(new ToDoListItem(false, "Ir a caminar"));
                            Log.e("HEREHERE",touristPlace.getDocumentId());
                            /*    String uid = touristPlace.getUserId();
                            List<ToDoListItem> itemList = new ArrayList<>();
                            itemList.add(new ToDoListItem(true, "Ir a caminar"));
                            itemList.add(new ToDoListItem(true, "Ir a comer"));
                            itemList.add(new ToDoListItem(true, "Ir a correr"));
                            ToDoList toDoList = new ToDoList(uid, "dX0OtRyfQzEBrebrDY1v", true, itemList); // ToDoList object*/

                        //itemList.add(new ToDoListItem(true, touristPlace.getItemList()));
                           /* Log.e("xd",todoViewModel.getY());
                            //Log.e("xd2",touristPlace.getItemList().get(position).getStatement()+"xd");
                            ToDoList g = new ToDoList("DSH3CzFvIMYFDvMLHGvBEPdbM7d2","fddasassx",true,itemList);
                            todoViewModel.setH(g,touristPlace.getDocumentId());
                            todoViewModel.update(g);*/
                        //touristPlace.getItemList().get(position).setItem_state(false);

                        //ToDoList toDoList = new ToDoList(uid, "dX0OtRyfQzEBrebrDY1v", false, touristPlace.getItemList()); // ToDoList object
                        // todoViewModel.save(toDoList);
                        //todoNames.add(touristPlace.getItemList());
                    }

                });
                holder.check.setChecked(b);
                if (holder.check.isChecked()){
                    Log.e("is","sdfa");
                }else{
                    Log.e("isnt","sdfa");
                    /*holder.check.setChecked(false);
                    todoViewModel.findByBinnacle("kXq9kQitaJBbP73fawwr").observe((LifecycleOwner) compoundButton.getContext(), data->{
                        data.forEach(touristPlace -> {

                            touristPlace.getItemList().get(position).setItem_state(false);
                            Log.e("ELSE",touristPlace.getItemList().toString());
                            g.set(new ToDoList("DSH3CzFvIMYFDvMLHGvBEPdbM7d2","kXq9kQitaJBbP73fawwr",true,touristPlace.getItemList()));
                            todoViewModel.setH(g.get(),touristPlace.getDocumentId());
                            //clear();

                            todoViewModel.update(g.get());*/

                            //notifyItemChanged(position);
                            /*List<ToDoListItem> itemList = new ArrayList<>();
                            itemList.add(new ToDoListItem(true, "Ir a caminar"));
                            Log.e("HEREHERE2",touristPlace.getDocumentId());
                            /*    String uid = touristPlace.getUserId();
                            List<ToDoListItem> itemList = new ArrayList<>();
                            itemList.add(new ToDoListItem(true, "Ir a caminar"));
                            itemList.add(new ToDoListItem(true, "Ir a comer"));
                            itemList.add(new ToDoListItem(true, "Ir a correr"));
                            ToDoList toDoList = new ToDoList(uid, "dX0OtRyfQzEBrebrDY1v", true, itemList); // ToDoList object*/

                            //itemList.add(new ToDoListItem(true, touristPlace.getItemList()));
                            /*Log.e("xd",todoViewModel.getY());
                            Log.e("xd2",touristPlace.getItemList().get(position).getStatement()+"xd");
                            ToDoList g = new ToDoList("DSH3CzFvIMYFDvMLHGvBEPdbM7d2","fddasassx",true,itemList);
                            todoViewModel.setH(g,touristPlace.getDocumentId());
                            todoViewModel.update(g);*/
                       /* });

                    });*/
                }


                //Log.e("BOREAL",mData.get(2));

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
    public void clear() {
        int size = mData.size();

        if (size > 0) {
            for (int i = 0; i < size; i++) {
                mData.remove(i);
                notifyItemRangeRemoved(i, size);
                size-=1;
            }


        }
    }
    public void tryed(){
        notifyDataSetChanged();
    }

}
