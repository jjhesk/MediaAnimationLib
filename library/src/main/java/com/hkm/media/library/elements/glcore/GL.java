package com.hkm.media.library.elements.glcore;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.hkm.media.library.elements.shapes.GLsprite;

/**
 * Created by hesk on 2/7/2015.
 */
public abstract class GL extends GLSurfaceView {

    GLsprite mRenderer;

    public GL(Context context) {
        super(context);
        init();
    }

    public GL(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    protected void init() {
        setRenderMode(RENDERMODE_WHEN_DIRTY);
        setDebugFlags(DEBUG_CHECK_GL_ERROR | DEBUG_LOG_GL_CALLS);
        mRenderer = new GLsprite();
        setRenderer(mRenderer);
    }


    public boolean onTouchEvent(final MotionEvent event) {
        queueEvent(new Runnable() {
            public void run() {
                mRenderer.setColor(event.getX() / getWidth(),
                        event.getY() / getHeight(), 1.0f);
            }
        });
        return true;
    }


}
