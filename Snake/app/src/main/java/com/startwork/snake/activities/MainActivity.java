package com.startwork.snake.activities;

import android.os.Bundle;

import com.startwork.snake.utils.gameManagementModels.ConstructingSnake;
import com.startwork.snake.utils.gameManagementModels.SensorManager;
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;


public class MainActivity extends Activity {


    private static ConstructingSnake constructingSnake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        constructingSnake = new ConstructingSnake(this);

        constructingSnake.init();
            setContentView(constructingSnake.surfaceView);



        SensorManager.getInstance(this);

    }

    protected void onStart() {
        super.onStart();

        constructingSnake.start();

    }

    protected void onPause() {
        super.onPause();
        SensorManager.getInstance().onPause();
        constructingSnake.pause();

    }

    protected void onResume() {
        super.onResume();
        SensorManager.getInstance().onResume();
        constructingSnake.resume();
    }

    protected void onStop() {
        super.onStop();

        constructingSnake.stop();

    }

    @Override
    public void onBackPressed() {
        constructingSnake.destroy();
        super.onBackPressed();
    }
}

