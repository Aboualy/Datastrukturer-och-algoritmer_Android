package com.startwork.snake.utils.gameManagementModels;


import javax.microedition.khronos.egl.EGLConfig;
        import javax.microedition.khronos.opengles.GL10;

import com.startwork.snake.utils.gameManagementModels.gameview.PointPainterModel;
import com.startwork.snake.utils.gameManagementModels.gameview.TouchPointModel;

import android.content.Context;
        import android.opengl.GLU;
        import android.opengl.GLSurfaceView.Renderer;

class RenderingView implements Renderer {

    private GameRendering gameRendering;

    private int width, height;

    public RenderingView(Context context, GameRendering gameRendering) {
        this.gameRendering = gameRendering;
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glLoadIdentity();

        gameRendering.render(this, gl);


    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if(height == 0) {
            height = 1;
        }


        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();

        this.width = width;
        this.height = height;


        GLU.gluOrtho2D(gl,-1.0f, 1.0f, -height/((float)width), height/((float)width));

        TouchPointModel.setWidth(width);
        TouchPointModel.setHeight(height);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        PointPainterModel.init();

        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1f);
        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_ALWAYS);
        gl.glEnable(GL10.GL_BLEND);

        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);

        gameRendering.loadGLAssets(gl);
    }


}