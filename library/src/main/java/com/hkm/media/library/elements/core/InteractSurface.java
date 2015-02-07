package com.hkm.media.library.elements.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by hesk on 2/2/2015.
 */
public abstract class InteractSurface extends SurfaceView implements SurfaceHolder.Callback2, SurfaceHolder.Callback, View.OnTouchListener {
    protected CanvasThread canvasThread;
    protected Canvas mainCanvas;
    protected SurfaceHolder thePanelHolder;
    protected boolean isFinal = false;

    protected PointF CURSOR = new PointF();

    protected drawMapSpecialGestureDetector mGestureDetector = null;
    protected ScaleGestureDetector mScaleGestureDetector = null;


    public InteractSurface(Context ctx, AttributeSet attrs, int defStyle) {
        super(ctx, attrs, defStyle);
        init();
    }

    public InteractSurface(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
        init();
    }

    public InteractSurface(Context ctx) {
        super(ctx);
        init();
    }

    protected abstract void init();

    protected int canvas_w, canvas_h;

    protected void onMeasure(int x, int y) {
        canvas_w = x;
        canvas_h = y;
        super.onMeasure(x, y);
    }

    public int getCWidth() {
        return canvas_w;
    }

    public int getCHeight() {
        return canvas_h;
    }

    @Override
    public void surfaceRedrawNeeded(SurfaceHolder holder) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    abstract protected void onDrawRender(final Canvas c);

    protected class drawMapSpecialGestureDetector extends GestureDetector {
        //    private int time_count = 0;
        //    private boolean additional_long_press = false;
        public drawMapSpecialGestureDetector(Context context, OnGestureListener listener) {
            super(context, listener);
            //         time_count = 0;
            //         additional_long_press = false;
        }

        public boolean onEnterEvent(MotionEvent e) {
            final boolean move = e.getAction() == MotionEvent.ACTION_MOVE;
            final boolean down = e.getAction() == MotionEvent.ACTION_DOWN;
            final boolean up = e.getAction() == MotionEvent.ACTION_UP;

            final int countpointer = e.getPointerCount();
            if (countpointer > 1) {
                /**
                 * running on the additional gestures
                 */

                return true;
            } else {

               /*

                additional_long_press = false;

                if (move) {
                    time_count++;
                    if (time_count > 1000) {
                        additional_long_press = true;
                    }
                } else if (up || down) {
                    time_count = 0;
                    additional_long_press = false;
                }

                */

                this.onTouchEvent(e);
                return false;
            }
        }
    }
}
