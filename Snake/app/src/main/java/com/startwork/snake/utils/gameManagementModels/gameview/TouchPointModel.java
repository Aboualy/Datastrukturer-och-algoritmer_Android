package com.startwork.snake.utils.gameManagementModels.gameview;

import java.util.ArrayList;


public class TouchPointModel {

    public static final double distant = 2000.0;

    public static ArrayList<TouchPointModel> activeListModel = new ArrayList<TouchPointModel>();
    public static ArrayList<TouchPointModel> deActivtedList = new ArrayList<TouchPointModel>();

    private static int width;
    private static int height;

    public ArrayList<Point> path;
    private boolean active = true;
    private boolean used = false;
    private int lastX;
    private int lastY;

    public TouchPointModel(int x, int y) {
        path = new ArrayList<Point>();

        path.add(new Point(x, y, System.currentTimeMillis()));
        lastX = x;
        lastY = y;

        synchronized (activeListModel) {
            activeListModel.add(this);
        }
        synchronized (deActivtedList) {
            deActivtedList.add(this);
        }

    }


    public int getLastX() {
        return lastX;
    }

    public int getLastY() {
        return lastY;
    }


    public void addPoint(int x, int y) {
        synchronized (path) {
            path.add(new Point(x, y, System.currentTimeMillis()));
        }
        lastX = x;
        lastY = y;
    }

    public Point getIndexPoint(int index) {
        synchronized (path) {
            if(index > path.size()-1)
                index = path.size()-1;
            return path.get((path.size()-index)-1);
        }
    }

    public static void actionDown(int x, int y) {
        new TouchPointModel(x, y);
    }

    public static void actionMove(int x, int y) {
        double minDist = distant*distant;
        TouchPointModel closest = null;
        synchronized (activeListModel) {
            for(TouchPointModel p : activeListModel) {
                Point pTmp = p.getIndexPoint(0);
                int dx = pTmp.x - x;
                int dy = pTmp.y - y;
                int dist = dx*dx + dy*dy;
                if(dist < minDist) {
                    minDist = dist;
                    closest = p;
                }
            }
        }
        if(closest != null) {
            if(x != closest.getLastX() || y != closest.getLastY())
                closest.addPoint(x, y);
        }
    }

    public static void actionUp(int x, int y) {
        double minDist = distant*distant;
        TouchPointModel closest = null;
        synchronized (activeListModel) {
            for(TouchPointModel p : activeListModel) {
                Point pTmp = p.getIndexPoint(0);
                int dx = pTmp.x - x;
                int dy = pTmp.y - y;
                int dist = dx*dx + dy*dy;
                if(dist < minDist) {
                    minDist = dist;
                    closest = p;
                }
            }
            if(closest != null) {
                activeListModel.remove(closest);
                closest.active = false;
            }
        }
    }



    public static void resetPoints() {
        synchronized (activeListModel) {

            for(TouchPointModel p : activeListModel) {
                p.active = false;
            }

            activeListModel.clear();
        }
    }

    public static void setWidth(int width) {
        TouchPointModel.width = width;
    }

    public static void setHeight(int height) {
        TouchPointModel.height = height;
    }



    public static float transformX(int x) {
        return 2*((float)x)/(float)width - 1.0f;

    }

    public static float transformY(int y) {
        return -2*((float)y)/(float)width + height/(float)width;
    }

}

