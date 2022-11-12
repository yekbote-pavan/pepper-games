package com.example.peppergames;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GO_PlayerListAdapter extends RecyclerView.Adapter<GO_PlayerListAdapter.PlayerListHolder> {

    private final List<GO_Player> players;
    private final Context context;

    public GO_PlayerListAdapter(List<GO_Player> players, Context context) {
        this.players = new ArrayList<>();
        this.players.addAll(players);
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return this.players.size();
    }

    @NonNull
    @Override
    public PlayerListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutView = li.inflate(R.layout.game_overview_li, null);
        return new PlayerListHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerListHolder holder, int position) {
        holder.name.setText(this.players.get(position).getName());
        holder.skills.setText(String.valueOf(this.players.get(position).getSkill()));
        holder.conduct.setText(String.valueOf(this.players.get(position).getConduct()));
    }

    public void updateData(List<GO_Player> items) {
        this.players.clear();
        this.players.addAll(items);
        notifyDataSetChanged();
    }

    public class PlayerListHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView skills;
        TextView conduct;

        public PlayerListHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.player_name_0);
            this.conduct = itemView.findViewById(R.id.player_conduct_0);
            this.skills = itemView.findViewById(R.id.player_skills_0);
        }

    }
}
