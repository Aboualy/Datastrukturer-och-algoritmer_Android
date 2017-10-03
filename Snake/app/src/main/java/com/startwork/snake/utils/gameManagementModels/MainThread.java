package com.startwork.snake.utils.gameManagementModels;


public class MainThread extends Thread {

    public static final int sleepTime = 50;
    public static final long seconds = 1000000000;

    private boolean running = false;
    private boolean pause = false;

    private GameRendering gameRendering;

    public MainThread(GameRendering gameRendering) {
        super();
        this.gameRendering = gameRendering;

    }

    public void setRunning(boolean running) {
        this.running = running;
    }


    private boolean checkPause() {
        boolean paused = false;
        while(pause && running) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(1);
            }
            paused = true;
        }

        return paused;
    }

    @Override
    public void run() {

        long lastTime = 0;
        long curTime = System.nanoTime();
        boolean first = true;
        double delta = 1;
        double targetTime = ((double) seconds) / (double) gameRendering.targetLogicRate;
        int correction = 0;
        int diff = 0;

        long lastTime2 = 0;
        long curTime2 = System.nanoTime();


        while(running) {

            lastTime2 = curTime2;
            curTime2 = System.nanoTime();

            if(first) {
                delta = 1;
            } else {
                delta = (double) (curTime2 - lastTime2) / targetTime;
            }

            lastTime = System.nanoTime();

            {
                gameRendering.update(delta* gameRendering.deltaFactor);

                targetTime = ((double) seconds) / (double) gameRendering.targetLogicRate;
            }
            curTime = System.nanoTime();



            diff = (int) (curTime-lastTime);

            correction =  Math.max(0, (int) targetTime - diff)/1000000;

            if(!first) {
                try {
                    Thread.sleep(correction);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }

            first = false;
            first = this.checkPause();

        }
    }

}
