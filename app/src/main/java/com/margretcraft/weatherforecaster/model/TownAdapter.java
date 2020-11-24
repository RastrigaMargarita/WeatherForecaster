package com.margretcraft.weatherforecaster.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.margretcraft.weatherforecaster.R;

import java.util.ArrayList;

public class TownAdapter extends RecyclerView.Adapter<TownAdapter.RecordHolder> {
    private ArrayList<TownClass> towns;
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    public void SetOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public TownAdapter(ArrayList<TownClass> towns) {
        this.towns = towns;
    }

    @NonNull
    @Override
    public RecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.town_card, parent, false);
        return new RecordHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordHolder holder, int position) {
        ((RecordHolder) holder).setData(towns.get(position));
    }

    @Override
    public int getItemCount() {
        return towns.size();
    }

    public class RecordHolder extends RecyclerView.ViewHolder {
        private TextView textViewCardTown;
        private TextView textViewCardZone;

        public RecordHolder(@NonNull View itemView) {
            super(itemView);
            textViewCardTown = itemView.findViewById(R.id.textViewCardTown);
            textViewCardZone = itemView.findViewById(R.id.textViewCardZone);

            textViewCardTown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }

        public void setData(TownClass town) {
            textViewCardTown.setText(town.getName());
            textViewCardZone.setText(town.getTimeZone());
        }
    }
}
