package com.startwork.snake.utils.gameManagementModels;




import javax.microedition.khronos.opengles.GL10;

import com.startwork.snake.utils.gameManagementModels.gameview.SnakeSurfaceView;

import android.content.Context;

public abstract class GameRendering {

    public int targetLogicRate = 120;
    public double deltaFactor = .5;

    public Context context;

    public SnakeSurfaceView surfaceView;

    public MainThread main;

    public GameRendering(Context context) {
        surfaceView = new SnakeSurfaceView(context);
        this.context = context;

        RenderingView renderer = new RenderingView(context, this);
        surfaceView.setRenderer(renderer);
        RandomModel.init();
    }


    public abstract void init();


    public abstract void loadGLAssets(GL10 gl);



    public abstract void render(RenderingView renderer, GL10 gl);


    public abstract void update(double delta);

    public void setContext(Context context) {
        this.context = context;
    }

    public void destroy() {

    }

    public void start() {
        main = new MainThread(this);
    }

    public void resume() {
        main.setRunning(true);
        main.start();

    }

    public void pause() {
        main.setRunning(false);
    }

    public void stop() {
    }


}
