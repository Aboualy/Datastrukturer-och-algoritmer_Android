package com.startwork.snake.utils.gameManagementModels;


import java.util.Random;

public class RandomModel {
    public static Random random;

    public static void init() {
        random = new Random();
    }

    public static double getRange(double low, double high) {
        double diff = high-low;
        return random.nextDouble()*diff+low;
    }
}

