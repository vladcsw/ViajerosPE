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
import androidx.recyclerview.widget.RecyclerView;

import com.viajeros.pe.R;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPlaces extends RecyclerView.Adapter<AdaptadorPlaces.AdaptadorViajesHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    AdaptadorPlaces(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
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
            holder.check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id=mData.get(position);
                    Log.e("positioncheck",id);
                    //You can call detail fragment here
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
