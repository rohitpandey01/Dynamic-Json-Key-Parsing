package com.json_dynamic_key_parsing.json_feed.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.si_test.R;
import com.json_dynamic_key_parsing.json_feed.model.TeamList;
import java.util.ArrayList;

public class TeamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<TeamList> mTeamLists;
    private Context context;

    public static final String TAG = TeamAdapter.class.getSimpleName();

    public TeamAdapter(Context context, ArrayList<TeamList> teamLists) {
        mTeamLists = teamLists;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team_view, parent, false);
        return new BodyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((BodyHolder) holder).bindView(context, mTeamLists.get(position));
    }

    @Override
    public int getItemCount() {
        return mTeamLists.size();
    }

    public static class BodyHolder extends RecyclerView.ViewHolder {
        TextView textPlayerName, textPlayerIsCaptainOrKeeper;

        BodyHolder(View itemView) {
            super(itemView);
            textPlayerName = itemView.findViewById(R.id.text_player_name);
            textPlayerIsCaptainOrKeeper = itemView.findViewById(R.id.text_player_isCaptainOrKeeper);
        }

        @SuppressLint("SetTextI18n")
        public void bindView(Context context, TeamList teamList) {
            textPlayerName.setText(teamList.getmPlayerName());

            if (teamList.ismIsCaptain()){
                textPlayerIsCaptainOrKeeper.setVisibility(View.VISIBLE);
                textPlayerIsCaptainOrKeeper.setText("( c )");
            }else if (teamList.ismIsKeeper()){
                textPlayerIsCaptainOrKeeper.setVisibility(View.VISIBLE);
                textPlayerIsCaptainOrKeeper.setText("( wk )");
            }else{
                textPlayerIsCaptainOrKeeper.setVisibility(View.GONE);
            }
        }
    }
}