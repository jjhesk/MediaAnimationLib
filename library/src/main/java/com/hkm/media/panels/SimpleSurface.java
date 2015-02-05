package com.hkm.media.panels;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.hkm.media.library.elements.core.CanvasThread;
import com.hkm.media.library.elements.core.InteractSurface;

/**
 * Created by hesk on 2/2/2015.
 */
public class SimpleSurface extends InteractSurface {
    public SimpleSurface(Context ctx, AttributeSet attrs, int defStyle) {
        super(ctx, attrs, defStyle);
    }

    public SimpleSurface(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
    }

    public SimpleSurface(Context ctx) {
        super(ctx);
    }

    @Override
    protected void init() {
        thePanelHolder = getHolder();
        thePanelHolder.addCallback(this);
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        this.setOnTouchListener(this);
      //  mScaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleGestureListener());
      //  mGestureDetector = new drawMapSpecialGestureDetector(getContext(), new commonGestureListenr());
        thePanelHolder.setFixedSize(getWidth(), getHeight());
        // this.setOnTouchListener(getApplicationContext());
        canvasThread = new CanvasThread(thePanelHolder, new CanvasThread.CallBack() {
            @SuppressLint("WrongCall")
            @Override
            public void threadRender(Canvas c) {
                mainCanvas = c;
                onDraw(c);
            }
        });
        this.setFocusable(false);

    }



}
