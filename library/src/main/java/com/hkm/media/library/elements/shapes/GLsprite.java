package com.hkm.media.library.elements.shapes;

import android.opengl.GLSurfaceView;

import com.hkm.media.library.elements.glcore.GL;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by hesk on 2/7/2015.
 */
public class GLsprite implements GLSurfaceView.Renderer {

    private float mRed;
    private float mGreen;
    private float mBlue;

    public GLsprite() {

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {

    }

    public void setColor(float r, float g, float b) {
        mRed = r;
        mGreen = g;
        mBlue = b;
    }

}
