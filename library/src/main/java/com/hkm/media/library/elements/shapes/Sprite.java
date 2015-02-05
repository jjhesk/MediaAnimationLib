package com.hkm.media.library.elements.shapes;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.hkm.media.library.elements.core.Element;


/**
 * This is the BaseBottom sprite engine for a working one
 * Created by Hesk on
 */
public class Sprite extends Element {
    private int x, y;
    private int xspeed, yspeed;
    private int height, width;
    private Bitmap bitmap_asset;
    private int panel_width, panel_height;
    //initial direction once from the starting point
    private int direction = 3;
    private int currentFrame = 0;
    private int sprite_rows = 4;
    private int sprite_columns = 10;
    private int fps = 0;
    private final Paint pbitmap = new Paint();


    public Sprite(Bitmap c) {
        bitmap_asset = c;
        x = y = xspeed = yspeed = 0;
        panel_width = 600;
        panel_height = 400;
        pbitmap.setFilterBitmap(true);
    }

    //https://www.youtube.com/watch?v=J29V0nvmZ2M
    public Sprite defineRowCol(int r, int c) {
        sprite_columns = c;
        sprite_rows = r;
        return this;
    }

    public Sprite setSpeed(int _v_x, int _v_y) {
        xspeed = _v_x;
        yspeed = _v_y;
        return this;
    }

    public Sprite setFPS(final int f) {
        fps = f;
        return this;
    }

    public Sprite setPos(Point pos) {
        x = pos.x;
        y = pos.y;
        return this;
    }

    public Sprite setPos(int _x, int _y) {
        x = _x;
        y = _y;
        return this;
    }

    public void done() {
        height = bitmap_asset.getHeight() / sprite_rows;
        width = bitmap_asset.getWidth() / sprite_columns;
    }


    private void frameplay() {
        currentFrame = ++currentFrame % sprite_columns;
    }

    private void rt_offset_position() {
        x += xspeed;
        y += yspeed;

    }

    public void update() {

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

        rt_offset_position();
        try {
            Thread.sleep(fps);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        frameplay();
    }


    @Override
    protected void rendering() {
        update();
        final int srcY = direction * height;
        final int srcX = currentFrame * width;
        //startin on the size of the rendering object
        // Rect src = new Rect(0, 0, width, height);
        final Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        //starting on the position of the render object
        final Rect dst = new Rect(x, y, x + width, y + height);


        getMainCanvas().drawBitmap(bitmap_asset, src, dst, pbitmap);
    }
}
