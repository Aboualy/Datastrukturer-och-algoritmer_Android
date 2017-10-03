package com.startwork.snake.utils.gameManagementModels;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.startwork.snake.R;

import java.util.List;


public class HighScoreAdapter extends RecyclerView.Adapter<HighScoreAdapter.ScoreHolder> {
    List<PlayerEntityModel>playerEntities;
    public  HighScoreAdapter(List<PlayerEntityModel>playerEntities){
        this.playerEntities=playerEntities;
    }

    @Override
    public ScoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.score_view,parent,false);
        ScoreHolder scoreHolder=new ScoreHolder(view);
        return scoreHolder;
    }

    @Override
    public void onBindViewHolder(ScoreHolder holder, int position) {
        PlayerEntityModel playerEntityModel =playerEntities.get(position);
        holder.playerName.setText(playerEntityModel.getName());
        holder.highScore.setText(playerEntityModel.getScore());

    }

    @Override
    public int getItemCount() {
        return playerEntities.size();
    }

    public class ScoreHolder extends RecyclerView.ViewHolder{
        TextView playerName,highScore;

        public ScoreHolder(View itemView) {
            super(itemView);
            playerName=(TextView)itemView.findViewById(R.id.highscore_name);
            highScore=(TextView)itemView.findViewById(R.id.highscore_value);
        }
    }
}

