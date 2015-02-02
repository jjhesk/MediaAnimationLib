package com.hkm.media.library.elements;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.hkm.media.library.R;
import com.hkm.media.library.elements.core.CanvasThread;
import com.hkm.media.library.elements.core.InteractSurface;

/**
 * Created by hesk on 2/2/2015.
 */
public class ReactSurface extends InteractSurface {
    private int mRippleColor;
    private boolean mIsAnimating = false;
    private boolean mHover = true;

    private float mDownX;
    private float mDownY;
    private float mAlphaFactor;
    private float mDensity;
    private float mRadius;
    private float mMaxRadius;

    public ReactSurface(Context ctx, AttributeSet attrs, int defStyle) {
        super(ctx, attrs, defStyle);
        TypedArray a = ctx.obtainStyledAttributes(attrs,
                R.styleable.ReactSurface);
        mRippleColor = a.getColor(R.styleable.ReactSurface_rippleColor,
                mRippleColor);
        mAlphaFactor = a.getFloat(R.styleable.ReactSurface_alphaFactor,
                mAlphaFactor);
        mHover = a.getBoolean(R.styleable.ReactSurface_hover, mHover);
        a.recycle();

    }

    public ReactSurface(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
    }

    public ReactSurface(Context ctx) {
        super(ctx);
    }
    private int dp(int dp) {
        return (int) (dp * mDensity + 0.5f);
    }

    @Override
    protected void init() {

        mDensity = getContext().getResources().getDisplayMetrics().density;

    /*    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAlpha(100);
        setRippleColor(Color.BLACK, 0.2f);
        */
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
