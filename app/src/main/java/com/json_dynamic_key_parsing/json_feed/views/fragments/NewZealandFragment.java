package com.json_dynamic_key_parsing.json_feed.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.json_dynamic_key_parsing.json_feed.adapter.TeamAdapter;
import com.json_dynamic_key_parsing.json_feed.model.TeamList;
import com.si_test.R;

import java.util.ArrayList;

public class NewZealandFragment extends Fragment {
    private ArrayList<TeamList> mNzTeamList;
    public NewZealandFragment(ArrayList<TeamList> nzTeamList) {
        mNzTeamList = nzTeamList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newzealand, container, false);

        RecyclerView recyclerTeamNz = view.findViewById(R.id.recycler_team_nz);

        TeamAdapter teamAdapter = new TeamAdapter(getContext(), mNzTeamList);
        recyclerTeamNz.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerTeamNz.setAdapter(teamAdapter);

        return view;
    }
}
