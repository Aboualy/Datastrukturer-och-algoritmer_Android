package com.startwork.snake.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.startwork.snake.utils.gameManagementModels.HighScoreAdapter;
import com.startwork.snake.utils.gameManagementModels.PlayerEntityModel;
import com.startwork.snake.dbhelper.PlayerScoreDB;
import com.startwork.snake.R;
import com.startwork.snake.utils.gameManagementModels.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HighScoreActivity extends AppCompatActivity implements View.OnClickListener{
    private PlayerScoreDB playerScoreDB;
    private List<PlayerEntityModel> playerEntityModelList;
    private RecyclerView recyclerView;
    private HighScoreAdapter highScoreAdapter;
    private EditText playerName;
    private Button startGame,exitGame;
    String namePattern;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        playerScoreDB=new PlayerScoreDB(this);
        playerEntityModelList =playerScoreDB.getAllLists();

        List<PlayerEntityModel> player=new ArrayList<>();
        for (int i=0;i< playerEntityModelList.size();i++){
            PlayerEntityModel playerEntityModel =new PlayerEntityModel(playerEntityModelList.get(i).getName(), playerEntityModelList.get(i).getScore());
            player.add(playerEntityModel);

        }
        Collections.sort(player, new Comparator<PlayerEntityModel>() {
            @Override
            public int compare(PlayerEntityModel lhs, PlayerEntityModel rhs) {
                int x=Integer.parseInt(lhs.getScore());
                int y=Integer.parseInt(rhs.getScore());
                return Double.compare(y,x);
            }
        });

         recyclerView=(RecyclerView)findViewById(R.id.scorerecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        highScoreAdapter=new HighScoreAdapter(player);
        recyclerView.setAdapter(highScoreAdapter);
        init();

    }

    private void init() {
        startGame = (Button) findViewById(R.id.start);
        exitGame = (Button) findViewById(R.id.finish);
        playerName=(EditText)findViewById(R.id.player_name);
        startGame.setOnClickListener(this);
        exitGame.setOnClickListener(this);
        namePattern="([a-zA-Z]{1,14})";


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:
                startNewGame();
                break;
            case R.id.finish:
                System.exit(0);
                break;

    }}
    private void startNewGame() {


         if(playerName.getText().toString().matches("")){
            Toast.makeText(this,"Please Enter Your Name",Toast.LENGTH_LONG).show();
        }
       else if (playerName.getText().toString().matches(namePattern)){
             Utils.newPlayerName=playerName.getText().toString().toLowerCase();
             Intent i = new Intent(HighScoreActivity.this, MainActivity.class);
             startActivity(i);

         }
             else {
             Toast.makeText(this,"Invalid name, Name can't contain numbers,spaces,signs !",Toast.LENGTH_LONG).show();


         }




    }
}


