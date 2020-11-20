package com.margretcraft.weatherforecaster.hours;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.margretcraft.weatherforecaster.MainActivity;
import com.margretcraft.weatherforecaster.R;

public class DaysFragment extends Fragment {

    private String mes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_days, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bd = getArguments();
        String[] days = new String[5];
        if (bd!=null){
            days = bd.getStringArray("days");
        }
        mes = ((MainActivity) getActivity()).isTempmes() ? getString(R.string.tempmes1) : getString(R.string.tempmes2);
        RecyclerView recyclerViewHours = getActivity().findViewById(R.id.recyclerViewHours);
        recyclerViewHours.setHasFixedSize(true);
        recyclerViewHours.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewHours.setAdapter(new DaysAdapter(getContext(), mes, days));
    }

}