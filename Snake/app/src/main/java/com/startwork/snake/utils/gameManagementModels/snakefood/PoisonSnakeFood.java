package com.startwork.snake.utils.gameManagementModels.snakefood;



import javax.microedition.khronos.opengles.GL10;

import com.startwork.snake.utils.gameManagementModels.RandomModel;
import com.startwork.snake.utils.gameManagementModels.Utils;
import com.startwork.snake.utils.gameManagementModels.gameview.PointPainterModel;

public class PoisonSnakeFood extends PoisonFood {

    public static final float RADIUS = .04f;

    private float x, y;

    public PoisonSnakeFood(float x, float y) {
        super();
        this.x = x;
        this.y = y;
    }


    public void render(GL10 gl){
        PointPainterModel.setScale(RADIUS, RADIUS);
        Utils.poisonFoodPointPainterModel.draw(gl, x, y);
    }


    public float getX() {
        return x;
    }

    public float getY(){
        return y;
    }


    public void eat() {
        x = (float) RandomModel.getRange(-1, 1);
        y = (float) RandomModel.getRange(-1, 1);
    }

}
