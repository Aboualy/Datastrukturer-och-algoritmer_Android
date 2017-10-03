package com.startwork.snake.utils.gameManagementModels.gameview;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

public class PointPainterModel {

    private static float angle = 0;
    private static float scaleX = 1;
    private static float scaleY = 1;

    private static FloatBuffer vertexBuffer;

    private static float vertices[] = {
            -1.0f, -1.0f,  0.0f,
            -1.0f,  1.0f,  0.0f,
            1.0f, -1.0f,  0.0f,
            1.0f,  1.0f,  0.0f
    };

    private FloatBuffer textureBuffer;
    private float texture[] = {
            0.0f, 1.0f,
            0.0f, 0.0f,
            1.0f, 1.0f,
            1.0f, 0.0f
    };

    private int[] textures = new int[1];

    private float handleX, handleY;


    public static void init() {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        PointPainterModel.vertexBuffer = byteBuffer.asFloatBuffer();
        PointPainterModel.vertexBuffer.put(vertices);
        PointPainterModel.vertexBuffer.position(0);
    }

    public PointPainterModel() {

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(texture.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        textureBuffer = byteBuffer.asFloatBuffer();
        textureBuffer.put(texture);
        textureBuffer.position(0);

        this.setHandle(0, 0);
    }

    public PointPainterModel(GL10 gl, Context context, int resource) {
        this();

        this.loadGLTexture(gl, context, resource);
    }


    public void draw(GL10 gl, float x, float y) {
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        gl.glFrontFace(GL10.GL_CW);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, PointPainterModel.vertexBuffer);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);

        gl.glPushMatrix();

        gl.glTranslatef(x, y, 0f);
        gl.glRotatef(PointPainterModel.angle, 0, 0, 1);
        gl.glScalef(PointPainterModel.scaleX, PointPainterModel.scaleY, 1f);

        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);

        gl.glPopMatrix();

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    }


    public void loadGLTexture(GL10 gl, Context context, int resource) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
                resource);


        gl.glGenTextures(1, textures, 0);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

        bitmap.recycle();
    }




    public void setHandle(float handleX, float handleY) {
        this.handleY = handleY;
        this.handleX = handleX;
    }







    public static void setScale(float scaleX, float scaleY) {
        PointPainterModel.scaleX = scaleX;
        PointPainterModel.scaleY = scaleY;
    }

    public static void setAlphaBlend(GL10 gl) {
        gl.glBlendFunc(GL10.GL_SRC_ALPHA,GL10.GL_ONE_MINUS_SRC_ALPHA);
    }


}
