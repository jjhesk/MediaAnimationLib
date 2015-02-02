package com.hkm.media.library.elements.core;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * http://flavienlaurent.com/index.html
 * Created by hesk on 6/13/2014.
 */
public class CanvasThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private boolean isRun;
    private Object mPauseLock;
    private boolean mPaused;
    public static String TAG = "CANVASCLASS";
    private CallBack cb;
    private status mstatus;

    enum status {
        PAUSE, RUNNING
    }

    public interface CallBack {
        void threadRender(Canvas canvas);
    }

    @Override
    public synchronized void start() {
        super.start();
        mstatus = status.RUNNING;
        isRun = true;
    }

    public CanvasThread(SurfaceHolder h, CallBack mcb) {
        surfaceHolder = h;
        mPauseLock = new Object();
        mPaused = false;
        isRun = false;
        cb = mcb;
    }

    public boolean isRun() {
        return isRun;
    }

    /**
     * Call this on pause.
     */
    public void onPause() {
        synchronized (mPauseLock) {
            mPaused = true;
            mstatus = status.PAUSE;
        }
    }

    /**
     * Call this on resume.
     */
    public void onResume() {
        synchronized (mPauseLock) {
            mPaused = false;
            mPauseLock.notifyAll();
            mstatus = status.RUNNING;
        }
    }

    public void setRunning(boolean run) {
        this.isRun = run;
        if (run) mstatus = status.RUNNING;
        else mstatus = status.PAUSE;
    }

    @SuppressLint("WrongCall")
    @Override
    public void run() {
        Canvas c;
        while (isRun) {
            c = null;
            if (!surfaceHolder.getSurface().isValid()) {
                continue;
            } else {
                try {
                    c = this.surfaceHolder.lockCanvas(null);
                    synchronized (this.surfaceHolder) {
                        cb.threadRender(c);
                    }
                } catch (Exception e) {
                    Log.d(TAG, "+1348: surface holder is having error");
                } finally {
                    if (surfaceHolder.getSurface().isValid()) {
                        surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
            //do stuffs
            // structure for cutting the pause
            // http://stackoverflow.com/questions/6776327/how-to-pause-resume-thread-in-android
            synchronized (mPauseLock) {
                while (mPaused) {
                    try {
                        mPauseLock.wait();
                    } catch (InterruptedException e) {
                        Log.d(TAG, "+1364: surface holder is having InterruptedException error " + e.toString());
                    }
                }
            }
        }
    }
}
