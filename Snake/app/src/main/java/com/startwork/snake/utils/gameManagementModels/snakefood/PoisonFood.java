package com.startwork.snake.utils.gameManagementModels.snakefood;



import javax.microedition.khronos.opengles.GL10;


public abstract class PoisonFood {

    public abstract float getX();

    public abstract float getY();

    public abstract void eat();

    public abstract void render(GL10 gl);

}
