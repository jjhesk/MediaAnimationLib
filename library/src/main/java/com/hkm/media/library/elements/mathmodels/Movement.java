package com.hkm.media.library.elements.mathmodels;

/**
 * Created by hesk on 2/6/15.
 */
public class Movement {
    public Movement(movemode m) {
        _movemode = m;
    }

    public void setMoveMode(movemode m) {
        _movemode = m;
    }

    public enum movemode {
        HORIZONTAL_X, BONCE_MOVE
    }

    private movemode _movemode;
    private int width, height, panel_width, panel_height, direction;

    private void sample_move_in_bonuce(int x, int y, int xspeed, int yspeed) {
        /*  moving the sprite in the canvas */
        //0 = up
        //1 = down
        //2 = left
        //3 = right
        //facing down
        if (x > panel_width - width - xspeed) {
            xspeed = 0;
            yspeed = 5;
            direction = 1;
        }
        //going left
        if (y > panel_height - height - yspeed) {
            xspeed = -5;
            yspeed = 0;
            direction = 2;
        }
        //facing up
        if (x + xspeed < 0) {
            x = 0;
            xspeed = 0;
            yspeed = -5;
            direction = 0;
        }
        //facing right
        if (y + yspeed < 0) {
            y = 0;
            xspeed = 5;
            yspeed = 0;
            direction = 3;
        }
    }


    private void sample_move_horizontal() {

    }

    public void update() {
        switch (_movemode) {
            case BONCE_MOVE:
                //sample_move_in_bonuce();
                break;
            case HORIZONTAL_X:
                sample_move_horizontal();
                break;
        }
    }
}
