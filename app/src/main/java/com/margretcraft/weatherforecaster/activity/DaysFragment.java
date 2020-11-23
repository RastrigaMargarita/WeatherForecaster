package com.margretcraft.weatherforecaster.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.margretcraft.weatherforecaster.R;
import com.margretcraft.weatherforecaster.model.DaysAdapter;

public class DaysFragment extends Fragment {

    private String mes;
    private RecyclerView recyclerViewHours;
    private String[] days;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_days, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bd = getArguments();
        days = bd.getStringArray("days");

        mes = ((MainActivity) getActivity()).isTempmes() ? getString(R.string.tempmes1) : getString(R.string.tempmes2);
        recyclerViewHours = getActivity().findViewById(R.id.recyclerViewHours);
        recyclerViewHours.setHasFixedSize(true);
        recyclerViewHours.setLayoutManager(new LinearLayoutManager(getContext()));
        updateAdapter();
    }

    public void updateAdapter() {
        if (((MainActivity) getActivity()).getListRequest() != null) {
            recyclerViewHours.setAdapter(new DaysAdapter(getContext(), mes, days, ((MainActivity) getActivity()).getListRequest().getDaily()));
        }
    }

}