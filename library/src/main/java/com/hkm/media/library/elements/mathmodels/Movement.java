package com.hkm.media.library.elements.mathmodels;

import android.graphics.Rect;

/**
 * Created by hesk on 2/6/15.
 */
public class Movement {
    private double degree = 0d;

    public Movement(movemode m) {
        _movemode = m;
    }

    public void setMoveMode(movemode m) {
        _movemode = m;
    }

    public enum movemode {
        HORIZONTAL_X, BONCE_MOVE
    }

    public enum SimpleDirection {
        HORIZONTAL, VERTICAL, SLOPE_ONE, SLOP_NEG_ONE
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


    private void sample_move(SimpleDirection direction, Rect bounce) {
        switch (direction) {
            case HORIZONTAL:
                //sample_move_in_bonuce();
                break;
            case VERTICAL:
                //sample_move_horizontal();
                break;
            case SLOPE_ONE:
                //sample_move_horizontal();
                break;
            case SLOP_NEG_ONE:
                //sample_move_horizontal();
                break;
        }
    }

    public void update(int x, int y, int level) {

    }

    public void update(int xspeed, int yspeed) {
        degree += 2d;
        xspeed = (int) (100 * Math.sin(degree));
    }

    public void update() {
        switch (_movemode) {
            case BONCE_MOVE:
                //sample_move_in_bonuce();
                break;
            case HORIZONTAL_X:
                //sample_move_horizontal();
                break;
        }
    }
}
