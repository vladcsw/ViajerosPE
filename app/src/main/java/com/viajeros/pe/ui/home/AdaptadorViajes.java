package com.viajeros.pe.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.viajeros.pe.R;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorViajes extends RecyclerView.Adapter<AdaptadorViajes.AdaptadorViajesHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private ArrayList<String> mString;

    AdaptadorViajes(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }



    @NonNull
    @Override
    public AdaptadorViajesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == 0) {
            View view = mInflater.inflate(R.layout.layout_add_viaje,parent,false);
            return new AdaptadorViajesHolder(view);
        } else {
            View view = mInflater.inflate(R.layout.layout_viaje,parent,false);
            return new AdaptadorViajesHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorViajesHolder holder, int position) {

        if (position > 0){
            String animal = mData.get(position);
            holder.txt1.setText(animal);
            holder.txt2.setText(animal);
        }

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addArray(Context context, ArrayList<String> binnacleNames) {
        this.mInflater = LayoutInflater.from(context);
        this.mData.addAll(binnacleNames);
    }

    class AdaptadorViajesHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txt1;
        TextView txt2;
        public AdaptadorViajesHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.fragment_tripsCard2_title);
            txt2 = itemView.findViewById(R.id.fragment_tripsCard2_place);
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
    void setClickListener(ItemClickListener itemClickListener, ArrayList<String> trs) {
        this.mClickListener = itemClickListener;
        this.mString = trs;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 0;
        return 1;
    }

    public ArrayList<String> getCode() {
        return mString;
    }

}
