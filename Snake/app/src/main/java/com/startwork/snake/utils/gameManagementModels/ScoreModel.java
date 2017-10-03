package com.startwork.snake.utils.gameManagementModels;


import javax.microedition.khronos.opengles.GL10;

import com.startwork.snake.R;
import com.startwork.snake.utils.gameManagementModels.gameview.PointPainterModel;

public class ScoreModel {

    public static PointPainterModel[] slices;

    public static void init(GameRendering gameRendering, GL10 gl) {
        slices = new PointPainterModel[36];
        slices[0] = new PointPainterModel(gl, gameRendering.context, R.drawable.n0);
        slices[1] = new PointPainterModel(gl, gameRendering.context, R.drawable.n1);
        slices[2] = new PointPainterModel(gl, gameRendering.context, R.drawable.n2);
        slices[3] = new PointPainterModel(gl, gameRendering.context, R.drawable.n3);
        slices[4] = new PointPainterModel(gl, gameRendering.context, R.drawable.n4);
        slices[5] = new PointPainterModel(gl, gameRendering.context, R.drawable.n5);
        slices[6] = new PointPainterModel(gl, gameRendering.context, R.drawable.n6);
        slices[7] = new PointPainterModel(gl, gameRendering.context, R.drawable.n7);
        slices[8] = new PointPainterModel(gl, gameRendering.context, R.drawable.n8);
        slices[9] = new PointPainterModel(gl, gameRendering.context, R.drawable.n9);
        slices[10] = new PointPainterModel(gl, gameRendering.context, R.drawable.a);
        slices[11] = new PointPainterModel(gl, gameRendering.context, R.drawable.b);
        slices[12] = new PointPainterModel(gl, gameRendering.context, R.drawable.c);
        slices[13] = new PointPainterModel(gl, gameRendering.context, R.drawable.d);
        slices[14] = new PointPainterModel(gl, gameRendering.context, R.drawable.e);
        slices[15] = new PointPainterModel(gl, gameRendering.context, R.drawable.f);
        slices[16] = new PointPainterModel(gl, gameRendering.context, R.drawable.g);
        slices[17] = new PointPainterModel(gl, gameRendering.context, R.drawable.h);
        slices[18] = new PointPainterModel(gl, gameRendering.context, R.drawable.i);
        slices[19] = new PointPainterModel(gl, gameRendering.context, R.drawable.j);
        slices[20] = new PointPainterModel(gl, gameRendering.context, R.drawable.k);
        slices[21] = new PointPainterModel(gl, gameRendering.context, R.drawable.l);
        slices[22] = new PointPainterModel(gl, gameRendering.context, R.drawable.m);
        slices[23] = new PointPainterModel(gl, gameRendering.context, R.drawable.n);
        slices[24] = new PointPainterModel(gl, gameRendering.context, R.drawable.o);
        slices[25] = new PointPainterModel(gl, gameRendering.context, R.drawable.p);
        slices[26] = new PointPainterModel(gl, gameRendering.context, R.drawable.q);
        slices[27] = new PointPainterModel(gl, gameRendering.context, R.drawable.r);
        slices[28] = new PointPainterModel(gl, gameRendering.context, R.drawable.s);
        slices[29] = new PointPainterModel(gl, gameRendering.context, R.drawable.t);
        slices[30] = new PointPainterModel(gl, gameRendering.context, R.drawable.u);
        slices[31] = new PointPainterModel(gl, gameRendering.context, R.drawable.v);
        slices[32] = new PointPainterModel(gl, gameRendering.context, R.drawable.w);
        slices[33] = new PointPainterModel(gl, gameRendering.context, R.drawable.x);
        slices[34] = new PointPainterModel(gl, gameRendering.context, R.drawable.y);
        slices[35] = new PointPainterModel(gl, gameRendering.context, R.drawable.z);
    }

    public static void drawNumber(GL10 gl, String number, float x, float y, float size) {
        String num = number + "";
        PointPainterModel.setScale(size, size);
        for (int i = 0; i < num.length(); i++) {
            if (Character.isDigit(num.charAt(i))) {
                int n = Integer.parseInt(num.charAt(i) + "");
                slices[n].draw(gl,(x + i * size)+1, y);
            }else{
                if (num.charAt(i) == 'a'){
                    slices[10].draw(gl,x + i * size, y);
                }
                if (num.charAt(i) == 'b'){
                    slices[11].draw(gl, x + i * size, y);
                }
                if (num.charAt(i) == 'c'){
                    slices[12].draw(gl, x + i * size, y);
                }
                if (num.charAt(i) == 'd'){
                    slices[13].draw(gl,x + i * size, y);
                }
                if (num.charAt(i) == 'e'){
                    slices[14].draw(gl,x + i * size, y);
                }
                if (num.charAt(i) == 'f'){
                    slices[15].draw(gl,x + i * size, y);
                }
                if (num.charAt(i) == 'g'){
                    slices[16].draw(gl,x + i * size, y);
                }
                if (num.charAt(i) == 'h'){
                    slices[17].draw(gl,x + i * size, y);
                }
                if (num.charAt(i) == 'i'){
                    slices[18].draw(gl,x + i * size, y);
                }

                if (num.charAt(i) == 'j'){
                    slices[19].draw(gl,x + i * size, y);
                }

                if (num.charAt(i) == 'k'){
                    slices[20].draw(gl,x + i * size, y);
                }

                if (num.charAt(i) == 'l'){
                    slices[21].draw(gl,x + i * size, y);
                }

                if (num.charAt(i) == 'm'){
                    slices[22].draw(gl,x + i * size, y);
                }

                if (num.charAt(i) == 'n'){
                    slices[23].draw(gl,x + i * size, y);
                }

                if (num.charAt(i) == 'o'){
                    slices[24].draw(gl,x + i * size, y);
                }

                if (num.charAt(i) == 'p'){
                    slices[25].draw(gl,x + i * size, y);
                }

                if (num.charAt(i) == 'q'){
                    slices[26].draw(gl,x + i * size, y);
                }

                if (num.charAt(i) == 'r'){
                    slices[27].draw(gl,x + i * size, y);
                }

                if (num.charAt(i) == 's'){
                    slices[28].draw(gl,x + i * size, y);
                }

                if (num.charAt(i) == 't'){
                    slices[29].draw(gl,x + i * size, y);
                }

                if (num.charAt(i) == 'u'){
                    slices[30].draw(gl,x + i * size, y);
                }

                if (num.charAt(i) == 'v'){
                    slices[31].draw(gl,x + i * size, y);
                }

                if (num.charAt(i) == 'w'){
                    slices[32].draw(gl,x + i * size, y);
                }

                if (num.charAt(i) == 'x'){
                    slices[33].draw(gl,x + i * size, y);
                }

                if (num.charAt(i) == 'y'){
                    slices[34].draw(gl,x + i * size, y);
                }

                if (num.charAt(i) == 'z'){
                    slices[35].draw(gl,x + i * size, y);
                }



            }
        }
        PointPainterModel.setScale(1, 1);
    }



}
