package com.hkm.media.library.elements.shapes;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.hkm.media.library.elements.core.Element;
import com.hkm.media.library.elements.mathmodels.Movement;


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
    //initial cX once from the starting point
    private int cX = 0, cY = 0;
    private int currentFrame = 0;
    private int sprite_rows = 4;
    private int sprite_columns = 10;
    private int fps = 0;
    private final Paint pbitmap = new Paint();
    private Movement _movement;
    private int total_frames = 0;

    public static enum frameType {
        CHARACTER, ANIMATION
    }

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
        total_frames = r * c;
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

    private frameType type = frameType.CHARACTER;

    public Sprite setFrameType(frameType t) {
        type = t;
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

    public Sprite setMovement(Movement mmove) {
        _movement = mmove;
        return this;
    }

    public void done() {
        height = bitmap_asset.getHeight() / sprite_rows;
        width = bitmap_asset.getWidth() / sprite_columns;
    }


    private void frameplay() {
        switch (type) {
            case CHARACTER:
                currentFrame = ++currentFrame % sprite_columns;
                cX = currentFrame;
                cY = 0;
                frameLocation();
                break;
            case ANIMATION:
                currentFrame = ++currentFrame % total_frames;
                if (currentFrame % sprite_columns == 0) {
                    cY = ++cY % sprite_rows;
                }
                cX = currentFrame % sprite_columns;
                frameLocation();
                break;
        }
    }

    private void frameLocation() {
        srcY = cY * height;
        srcX = cX * width;
    }

    private void rt_offset_position() {
        x += xspeed;
        y += yspeed;

    }

    public void update() {
        _movement.update();
        rt_offset_position();
        try {
            Thread.sleep(fps);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        frameplay();
    }

    private int srcY, srcX;

    @Override
    protected void rendering() {
        update();
        //startin on the size of the rendering object
        // Rect src = new Rect(0, 0, width, height);
        final Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        //starting on the position of the render object
        final Rect dst = new Rect(x, y, x + width, y + height);
        getMainCanvas().drawBitmap(bitmap_asset, src, dst, pbitmap);
    }
}
