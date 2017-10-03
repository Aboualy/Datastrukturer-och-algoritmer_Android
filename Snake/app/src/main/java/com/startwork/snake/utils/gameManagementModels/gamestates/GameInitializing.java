package com.startwork.snake.utils.gameManagementModels.gamestates;


import javax.microedition.khronos.opengles.GL10;





    public  class GameInitializing {


        public GameInitializing childGameInitializing;

        public GameInitializing() {
            super();
            childGameInitializing = null;
        }

        public  void render(GL10 gl){


        };


        public GameInitializing update(double delta) {
            return null;
        }

        public GameInitializing updateState(double delta) {
            GameInitializing next = this.update(delta);

            if (childGameInitializing != null) {
                GameInitializing nextChild = childGameInitializing.updateState(delta);
                synchronized (childGameInitializing) {
                    childGameInitializing = nextChild;
                }
            }

            return next;

        }

        public void renderState(GL10 gl) {
            this.render(gl);

            if (childGameInitializing != null) {
                synchronized (childGameInitializing) {
                    if (childGameInitializing != null) {
                        childGameInitializing.renderState(gl);
                    }
                }
            }

        }


    }


