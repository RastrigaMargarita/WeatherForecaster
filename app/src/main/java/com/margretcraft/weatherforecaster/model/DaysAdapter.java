package com.margretcraft.weatherforecaster.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.margretcraft.weatherforecaster.R;
import com.margretcraft.weatherforecaster.model.jsonmodel.Daily;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.HoursHolder> {
    private String[] days;
    private Daily[] temps;
    private String mes;
    private LayoutInflater inflater;

    public DaysAdapter(Context context, String mes, String[] days, Daily[] temps) {
        inflater = LayoutInflater.from(context);
        this.mes = mes;
        this.days = days;
        this.temps = temps;
    }

    public HoursHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HoursHolder(inflater.inflate(R.layout.item_days, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HoursHolder holder, int position) {
        if (temps != null) {
            if (mes.equals(inflater.getContext().getString(R.string.tempmes1))) {
                holder.setData(days[position],
                        String.format("   %d" + mes, Math.round(temps[position].getTemp().getDay() - inflater.getContext().getResources().getInteger(R.integer.transferT))));
            } else {
                holder.setData(days[position],
                        String.format("   %f" + mes, temps[position].getTemp().getDay()));
            }
        }
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

