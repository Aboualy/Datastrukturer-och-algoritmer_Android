package com.startwork.snake.playing;


import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import com.startwork.snake.utils.gameManagementModels.Utils;
import com.startwork.snake.utils.gameManagementModels.gameview.PointPainterModel;
import com.startwork.snake.utils.gameManagementModels.snakefood.NormalFood;
import com.startwork.snake.utils.gameManagementModels.snakefood.SnakeNormalFood;
import com.startwork.snake.utils.gameManagementModels.snakefood.PoisonFood;

public class Snake {

    public static final float eye = .04f;
    public static final float distant = .04f;
    public static final float size = .025f;

    private ArrayList<SnakeModel> snakeBody;
    private ArrayList<PointsVariables> snakePast;

    private float x, y;
    private float ox, oy;
    private float angle;
    private float speed;
    private float turningSpeed;

    private boolean angleChanged;

    public Snake(float x, float y, float vx, float vy, float speed) {
        super();
        snakeBody = new ArrayList<>();
        snakePast = new ArrayList<>();

        snakePast.add(new PointsVariables(x, y, -vx * 100, -vy * 100));
        this.x = x;
        this.y = y;
        this.ox = x - .01f;
        this.oy = y - .01f;
        this.angle = (float) Math.atan2(vy, vx);
        this.speed = speed;
        this.angleChanged = false;
        this.turningSpeed = .07f;
    }




    public int getSize() {
        return snakeBody.size();
    }


    public void addAngle(float angle) {

        if (angle != 0)
            angleChanged = true;

        this.angle += angle;
    }

    public void goToGoal(float goalX, float goalY, float delta) {
        float sinAng = (float) Math.sin(angle);
        float cosAng = (float) Math.cos(angle);

        if (Math.abs(cosAng) <= .7) {
            float slope = cosAng/sinAng;
            float xint = x - slope * y;
            float lx = slope*goalY + xint;
            if (sinAng < 0) {
                lx = -lx;
                goalX = -goalX;
            }
            if (lx < goalX) {
                addAngle(-turningSpeed*delta);
            } else {
                addAngle(turningSpeed*delta);
            }
        } else {
            float slope = sinAng/cosAng;
            float yint = y - slope * x;
            float ly = slope*goalX + yint;
            if (cosAng < 0) {
                ly = -ly;
                goalY = -goalY;
            }
            if (ly < goalY) {
                addAngle(turningSpeed);
            } else {
                addAngle(-turningSpeed);
            }
        }
    }

    public void render(GL10 gl) {
        renderBody(gl);
        renderEyes(gl);
    }

    private void renderBody(GL10 gl) {
        synchronized (snakeBody) {
            for (SnakeModel unit : snakeBody) {
                unit.render(gl);
            }
        }
    }

    private void renderEyes(GL10 gl) {
        float dx = (float) Math.cos(angle);
        float dy = (float) Math.sin(angle);
        float ex = dx*distant;
        float ey = dy*distant;
        drawEye(gl, x + ey + ex, y - ex + ey, dx * size, dy * size);
        drawEye(gl, x - ey + ex, y + ex + ey, dx * size, dy * size);
    }

    private void drawEye(GL10 gl, float x, float y, float px, float py) {
        PointPainterModel.setScale(eye, eye);
        Utils.circlePointPainterModel.draw(gl, x, y);
        gl.glColor4f(0, 0, 0, 1);
        PointPainterModel.setScale(size, size);
        Utils.circlePointPainterModel.draw(gl, x + px, y + py);
        gl.glColor4f(1, 1, 1, 1);
    }

    public void update(double delta) {

        if (angle > 2*Math.PI) {
            angle = (float) (angle - 2*Math.PI);
        } else if (angle < 0) {
            angle = (float) (angle + 2*Math.PI);
        }

        ox = x;
        oy = y;

        x = x + (float) (Math.cos(angle)*speed*delta);
        y = y + (float) (Math.sin(angle)*speed*delta);

        if (x > 1) {
            x -= 2;
            ox -= 2;
            angleChanged = true;
        }
        if (x < -1) {
            x += 2;
            ox += 2;
            angleChanged = true;
        }

        newPointsVariables();

        synchronized (snakeBody) {
            int pos = 0;
            int lastHistory = 0;
            for (SnakeModel unit : snakeBody) {
                int tmp = unit.update(snakePast, pos);
                if (tmp > lastHistory)
                    lastHistory = tmp;
                pos++;
            }
            if (lastHistory + 20 < snakePast.size() - 1) {
                snakePast.remove(snakePast.size() -1);
            }
        }

    }

    public void foodCheck(NormalFood normalFood,PoisonFood[] poisonFood){
        if(Math.sqrt((x - normalFood.getX())*(x- normalFood.getX()) + (y - normalFood.getY())*(y - normalFood.getY())) < SnakeNormalFood.RADIUS + SnakeModel.bodySize ){
            normalFood.eat();
                    poisonFood[Utils.size].eat();
                    Utils.size++;
            this.addUnit(new SnakeModel(-100, -100, 0));
        }
    }


    public void newPointsVariables() {

        PointsVariables last = snakePast.get(0);

        if (angleChanged) {

            angleChanged = false;
            snakePast.add(0, new PointsVariables(x, y, ox - x, oy - y));

        } else {

            last.setup(x, y, last.vx + (ox - x), last.vy + (oy - y));

        }
    }

    public void addUnit(SnakeModel unit) {
        synchronized (snakeBody) {
            if (snakeBody.size() > 0)
                unit.setAhead(snakeBody.get(snakeBody.size()-1));

            snakeBody.add(unit);
        }
    }


    public boolean tailTouch(){
        synchronized (snakeBody) {
            for(int i=2; i < snakeBody.size(); i++){
                if(Math.sqrt((x - snakeBody.get(i).getX())*(x-snakeBody.get(i).getX()) + (y - snakeBody.get(i).getY())*(y - snakeBody.get(i).getY())) < SnakeModel.bodySize * 2){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean eatPoison(PoisonFood poisonFood){
                    if(Math.sqrt((x - poisonFood.getX())*(x-poisonFood.getX()) + (y - poisonFood.getY())*(y - poisonFood.getY())) < SnakeNormalFood.RADIUS + SnakeModel.bodySize ){
                        return true;
                    }
        return false;
    }


}



