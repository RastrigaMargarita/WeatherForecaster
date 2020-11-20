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

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.HoursHolder> {
    private String[] days;
    private int[] temps;
    private String mes;
    private LayoutInflater inflater;

    public DaysAdapter(Context context, String mes, String[] days) {
        inflater = LayoutInflater.from(context);
        this.mes = mes;
        this.days = days;

        //Hardcode до изучения api
        this.temps = new int[5];
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            this.temps[i] = random.nextInt(10)+10;
        }

    }

    public HoursHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HoursHolder(inflater.inflate(R.layout.item_days, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HoursHolder holder, int position) {
        holder.setData(days[position],
        String.format("   %d"+mes, temps[position]));
    }

    @Override
    public int getItemCount() {
        return days.length;
    }

    class HoursHolder extends RecyclerView.ViewHolder {
        TextView textViewhour;
        TextView textViewTemp;

        public HoursHolder(@NonNull View itemView) {
            super(itemView);
            textViewhour = itemView.findViewById(R.id.textViewhour);
            textViewTemp = itemView.findViewById(R.id.textViewTemp);
        }

        public void setData(String hour, String format) {
            textViewhour.setText(hour);
            textViewTemp.setText(format);
        }
    }
}

