package com.hkm.media.library.elements.shapes;

import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class SwipeDetector implements View.OnTouchListener {

    public static enum Action {
        LR, // Left to Right
        RL, // Right to Left
        TB, // Top to bottom
        BT, // Bottom to Top
        Start,
        Stop,
        None // when no action was detected
    }

    private static final String logTag = "SwipeDetector";
    private static final int MIN_DISTANCE = 100;
    private static final float HORIZONTAL_MIN_DISTANCE = 5;
    private static final float VERTICAL_MIN_DISTANCE = 100;
    private float downX, downY, upX, upY, stopX, stopY;
    private Action mSwipeDetected = Action.None;

    private static SwipeListener mswipelisten;

    public void setListener(SwipeListener m) {
        mswipelisten = m;
    }

    public SwipeDetector() {
        mswipelisten = new SwipeListener() {
            @Override
            public void onTouchSelect(PointF p) {

            }

            @Override
            public void onUpdateTouchDown(float delta_x, float delta_y, PointF downPoint) {

            }

            @Override
            public void onUpdateTouchRelease(float stop_delta_x) {

            }
        };
    }

    public boolean swipeDetected() {
        return mSwipeDetected != Action.None;
    }

    public Action getAction() {
        return mSwipeDetected;
    }

    public interface SwipeListener {
        public void onTouchSelect(PointF p);
        public void onUpdateTouchDown(float delta_x, float delta_y, PointF downPoint);
        public void onUpdateTouchRelease(float stop_delta_x);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                downX = event.getX();
                downY = event.getY();
                mSwipeDetected = Action.Start;
                if (mswipelisten != null) {
                    mswipelisten.onTouchSelect(new PointF(downX, downY));
                }
                return false; // allow other events like Click to be processed
            }
            case MotionEvent.ACTION_MOVE: {
                upX = event.getX();
                upY = event.getY();

                float deltaX = downX - upX;
                float deltaY = downY - upY;

                if (mswipelisten != null) {
                    mswipelisten.onUpdateTouchDown(deltaX, deltaY, new PointF(upX, upY));
                }
                // horizontal swipe detection
                if (Math.abs(deltaX) > HORIZONTAL_MIN_DISTANCE) {
                    // left or right
                    if (deltaX <= 0) {
                        Log.i(logTag, "Swipe Left to Right");
                        mSwipeDetected = Action.LR;
                        return true;
                    }
                    if (deltaX > 0) {
                        Log.i(logTag, "Swipe Right to Left");
                        mSwipeDetected = Action.RL;
                        return true;
                    }
                } else
                    // vertical swipe detection
                    if (Math.abs(deltaY) > VERTICAL_MIN_DISTANCE) {
                        // top or down
                        if (deltaY < 0) {
                            Log.i(logTag, "Swipe Top to Bottom");
                            mSwipeDetected = Action.TB;
                            return false;
                        }
                        if (deltaY > 0) {
                            Log.i(logTag, "Swipe Bottom to Top");
                            mSwipeDetected = Action.BT;
                            return false;
                        }
                    }
                Log.i("delta X", Float.toString(deltaX));
                return true;
            }
            case MotionEvent.ACTION_UP: {

                stopX = event.getX();
                stopY = event.getY();
                float stopValue = upX - stopX;
                float totalDeltaX = downX - stopX;
                Log.i("StopX value", Float.toString(stopValue));
                Log.i("StopX value", Float.toString(stopX));
                Log.i("DownX value", Float.toString(downX));
                Log.i("UpX value", Float.toString(upX));
                //
                mSwipeDetected = Action.None;
                if (mswipelisten != null) {
                    mswipelisten.onUpdateTouchRelease(totalDeltaX);
                }
                return false;
            }


        }
        return false;
    }
}