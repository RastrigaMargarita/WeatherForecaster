package com.margretcraft.weatherforecaster.hours;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.margretcraft.weatherforecaster.R;

import java.util.Random;

public class HoursAdapter extends RecyclerView.Adapter<HoursAdapter.HoursHolder> {
    private String[] hours;
    private int[] temps;
    private String mes;
    private LayoutInflater inflater;

    public HoursAdapter(Context context, String mes) {
        inflater = LayoutInflater.from(context);
        this.mes = mes;

        //Hardcode до изучения api
        this.hours = new String[24];
        this.temps = new int[24];
        Random random = new Random();
        for (int i = 0; i < 24; i++) {
            this.hours[i] = String.format("%d.00",i);
            this.temps[i] = random.nextInt(10)+10;
        }

    }

    public HoursHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HoursHolder(inflater.inflate(R.layout.item_hours, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HoursHolder holder, int position) {
        holder.textViewhour.setText(hours[position]);
        holder.textViewTemp.setText(String.format("   %d"+mes, temps[position]));
    }

    @Override
    public int getItemCount() {
        return hours.length;
    }

    class HoursHolder extends RecyclerView.ViewHolder {
        TextView textViewhour;
        TextView textViewTemp;

        public HoursHolder(@NonNull View itemView) {
            super(itemView);
            textViewhour = itemView.findViewById(R.id.textViewhour);
            textViewTemp = itemView.findViewById(R.id.textViewTemp);
        }
    }
}

