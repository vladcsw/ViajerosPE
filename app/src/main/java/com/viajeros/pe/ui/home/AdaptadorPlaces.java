package com.viajeros.pe.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
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

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AdaptadorPlaces extends RecyclerView.Adapter<AdaptadorPlaces.AdaptadorViajesHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private List<Boolean> mCheck;
    private String uid;
    private String bid;


    AdaptadorPlaces(Context context, List<String> data, List<Boolean> check, String uid, String bid) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mCheck = check;
        this.uid = uid;
        this.bid = bid;

    }

    @NonNull
    @Override
    public AdaptadorViajesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = mInflater.inflate(R.layout.layout_places,parent,false);
            return new AdaptadorViajesHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorViajesHolder holder, @SuppressLint("RecyclerView") int position) {

        String place = mData.get(position);
        holder.txt1.setText(place);

        final boolean placeChecked = mCheck.get(position);
        holder.txt1.setText(place);
        holder.check.setChecked(placeChecked);
        holder.check.setOnCheckedChangeListener(null);

        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                HomeViewModel homeViewModel = new HomeViewModel();
                final int[] i = {1};
                homeViewModel.getLiveDataBinnacle(bid).observe((LifecycleOwner) compoundButton.getContext(), data -> {

                    if (i[0] == 1){
                        i[0]++;
                        data.getPlaces().get(position).setState(b);
                        homeViewModel.saveWithIdGenerated(data,bid);

                    }

                });

                holder.check.setChecked(b);
            }
        });

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    class AdaptadorViajesHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txt1;
        CheckBox check;
        public AdaptadorViajesHolder(@NonNull View itemView) {
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
