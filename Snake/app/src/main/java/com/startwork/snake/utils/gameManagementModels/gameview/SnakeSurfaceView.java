package com.startwork.snake.utils.gameManagementModels.gameview;



import android.content.Context;
import android.opengl.GLSurfaceView;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class SnakeSurfaceView extends GLSurfaceView {

    public SnakeSurfaceView(Context context) {
        super(context);
    }

    public SnakeSurfaceView(Context context, AttributeSet a) {
        super(context, a);
    }

    public boolean onTouchEvent(MotionEvent event) {

        int index = MotionEventCompat.getActionIndex(event);

        switch (MotionEventCompat.getActionMasked(event)) {
            case (MotionEvent.ACTION_DOWN) : {
                TouchPointModel.actionDown((int) MotionEventCompat.getX(event, index), (int) MotionEventCompat.getY(event, index));

                break;
            }
            case (MotionEvent.ACTION_CANCEL) : {
                TouchPointModel.resetPoints();
                break;
            }
            case (MotionEvent.ACTION_UP) : {

                TouchPointModel.actionUp((int) MotionEventCompat.getX(event, index), (int) MotionEventCompat.getY(event, index));
                break;
            }
            case (MotionEvent.ACTION_MOVE) : {
                int num = event.getPointerCount();
                for(int i = 0; i < num; i++) {
                    TouchPointModel.actionMove((int) MotionEventCompat.getX(event, i), (int) MotionEventCompat.getY(event, i));
                }
                break;
            }
            case (MotionEventCompat.ACTION_POINTER_DOWN) : {
                TouchPointModel.actionDown((int) MotionEventCompat.getX(event, index), (int) MotionEventCompat.getY(event, index));

                break;
            }
            case (MotionEventCompat.ACTION_POINTER_UP): {
                TouchPointModel.actionUp((int) MotionEventCompat.getX(event, index), (int) MotionEventCompat.getY(event, index));

                break;
            }

        }

        return true;
    }


}

