package com.startwork.snake.utils.gameManagementModels;



import javax.microedition.khronos.opengles.GL10;

import android.content.Context;

import com.startwork.snake.R;
import com.startwork.snake.utils.gameManagementModels.gamestates.GameStartingGameInitializing;
import com.startwork.snake.utils.gameManagementModels.gameview.PointPainterModel;
import com.startwork.snake.utils.gameManagementModels.gamestates.GameInitializing;

public class ConstructingSnake extends GameRendering {





    Context context;

    public GameInitializing mainGameInitializing;

    public ConstructingSnake(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void render(RenderingView renderer, GL10 gl) {

        gl.glClearColor(0, 0.5f, 0, 1);
        PointPainterModel.setAlphaBlend(gl);
        mainGameInitializing.renderState(gl);

    }

    @Override
    public void update(double delta) {
        mainGameInitializing.updateState(delta);
    }


    @Override
    public void loadGLAssets(GL10 gl) {
        Utils.bodyPointPainterModel = new PointPainterModel(gl, this.context, R.drawable.snakehead);
        Utils.foodPointPainterModel = new PointPainterModel(gl, this.context, R.drawable.redapple);
        Utils.poisonFoodPointPainterModel = new PointPainterModel(gl, this.context, R.drawable.mashroom);
        Utils.circlePointPainterModel = new PointPainterModel(gl, this.context, R.drawable.white_circle);


        ScoreModel.init(this, gl);
    }

    @Override
    public void init() {
        RandomModel.init();
        mainGameInitializing = new GameStartingGameInitializing(context);
    }
}

