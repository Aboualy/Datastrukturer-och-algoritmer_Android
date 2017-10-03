package com.startwork.snake.utils.gameManagementModels.gamestates;


import android.content.Context;
import android.content.Intent;
import javax.microedition.khronos.opengles.GL10;
import com.startwork.snake.activities.HighScoreActivity;
import com.startwork.snake.dbhelper.PlayerScoreDB;
import com.startwork.snake.playing.SnakeModel;
import com.startwork.snake.utils.gameManagementModels.PlayerEntityModel;
import com.startwork.snake.utils.gameManagementModels.Utils;
import com.startwork.snake.utils.gameManagementModels.gameview.PointPainterModel;
import com.startwork.snake.utils.gameManagementModels.gameview.TouchPointModel;
import com.startwork.snake.playing.Snake;
import com.startwork.snake.utils.gameManagementModels.snakefood.NormalFood;
import com.startwork.snake.utils.gameManagementModels.snakefood.SnakeNormalFood;
import com.startwork.snake.utils.gameManagementModels.snakefood.PoisonFood;
import com.startwork.snake.utils.gameManagementModels.snakefood.PoisonSnakeFood;
import com.startwork.snake.utils.gameManagementModels.ScoreModel;
import java.util.List;


public class GameStartingGameInitializing extends GameInitializing {

    public Snake snake;

    public NormalFood normalFood;
    public PoisonFood[] poisonFood;
    PlayerScoreDB playerScoreDB;
    PlayerEntityModel playerEntityModel;
    List<PlayerEntityModel> playerEntityModelList;
    public int score, size = 50;
    Context context;

    public GameStartingGameInitializing(Context activity) {
        context = activity;
        Utils.score = 0;
        Utils.size=0;
        snake = new Snake(0, 0, 1, 0, .01f);
        snake.addUnit(new SnakeModel(0, 0, 0));
        for (int i = 0; i < 1; i++)
            snake.addUnit(new SnakeModel(i + 1, 0, 45));
        normalFood = new SnakeNormalFood(.5f, .5f);
        poisonFood = new PoisonFood[size];
        for (int i = 0; i < poisonFood.length; i++) {
            poisonFood[i] = new PoisonSnakeFood(.8f, .8f);
        }

    }

    @Override
    public void render(GL10 gl) {
        PointPainterModel.setAlphaBlend(gl);
        snake.render(gl);
        normalFood.render(gl);
        for (int i = 0; i < poisonFood.length; i++) {
            poisonFood[i].render(gl);
        }
        ScoreModel.drawNumber(gl,Utils.newPlayerName+ score, TouchPointModel.transformX(0) + .07f, TouchPointModel.transformY(0) - .07f, .07f);
    }

    @Override
    public GameInitializing update(double delta) {
        synchronized (TouchPointModel.activeListModel) {
            if (TouchPointModel.activeListModel.size() > 0) {
                TouchPointModel first = TouchPointModel.activeListModel.get(0);
                double x = (TouchPointModel.transformX(first.getLastX()));
                double y = (TouchPointModel.transformY(first.getLastY()));
                snake.goToGoal((float) x, (float) y, (float) delta);

            }
        }
        snake.update(delta);
        score = snake.getSize() - 2;
        snake.foodCheck(normalFood,poisonFood);

        for (int i = 0; i < poisonFood.length; i++) {
            if (snake.eatPoison(poisonFood[i])) {
                Utils.score = snake.getSize();
                addToDataBase();
            }
        }

        if (snake.tailTouch()) {
            Utils.score = snake.getSize();
            addToDataBase();

        }

        return this;
    }

    private void addToDataBase() {
        playerScoreDB = new PlayerScoreDB(context);
        playerEntityModel = new PlayerEntityModel(Utils.newPlayerName, String.valueOf(score));
        playerEntityModelList = playerScoreDB.getAllLists();

        for (int i = 0; i <= playerEntityModelList.size(); i++) {
            if (playerEntityModelList.size() == 0) {
                playerScoreDB.addLst(playerEntityModel);
            } else if ((Utils.newPlayerName.compareTo(playerEntityModelList.get(i).getName()) == 0)) {
                if (score >= Integer.parseInt(playerEntityModelList.get(i).getScore())) {
                    playerScoreDB.deleteScore(playerEntityModelList, i);
                    playerScoreDB.addLst(playerEntityModel);
                    break;
                } else {
                    break;
                }
            } else if (Utils.newPlayerName.compareTo(playerEntityModelList.get(i).getName()) != 0 && i == playerEntityModelList.size() - 1) {
                playerScoreDB.addLst(playerEntityModel);
                break;
            }
        }
        Intent i = new Intent(context.getApplicationContext(), HighScoreActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(i);
    }

}

