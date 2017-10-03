package com.startwork.snake.playing;


import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import com.startwork.snake.utils.gameManagementModels.Utils;
import com.startwork.snake.utils.gameManagementModels.gameview.PointPainterModel;

public class SnakeModel {

    public static final float bodySize = .06f;
    public static final float linkDistant = .12f;

    private float x, y;
    private float angle;
    private SnakeModel ahead;

    public SnakeModel(float x, float y, float angle) {
        super();
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public void render(GL10 gl) {
        PointPainterModel.setScale(bodySize, bodySize);
        Utils.bodyPointPainterModel.draw(gl, x, y);
    }

    public int update(ArrayList<PointsVariables> history, int pos) {
        float targetDist = pos*linkDistant;
        int cnt = 0;
        for (PointsVariables point : history) {
            if (point.dist < targetDist && point != history.get(history.size() - 1)) {
                targetDist -= point.dist;
            } else {
                x = point.x + point.nx*targetDist;
                y = point.y + point.ny*targetDist;
                angle = (float) Math.toDegrees(Math.atan2(point.ny, point.nx));
                return cnt;
            }
            cnt++;
        }
        return history.size();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }


    public void setAhead(SnakeModel ahead) {
        this.ahead = ahead;
    }
}

