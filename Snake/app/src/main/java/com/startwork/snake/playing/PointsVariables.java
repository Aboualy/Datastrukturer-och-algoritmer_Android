package com.startwork.snake.playing;


public class PointsVariables {

    public float x;
    public float y;
    public float vx;
    public float vy;
    public float dist;
    public float nx;
    public float ny;
    public PointsVariables(float x, float y, float vx, float vy) {
        super();
        setup(x, y, vx, vy);
    }

    public void setup(float x, float y, float vx, float vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.dist = (float) Math.sqrt(vx * vx + vy * vy);
        this.nx = this.vx / this.dist;
        this.ny = this.vy / this.dist;
    }
}
