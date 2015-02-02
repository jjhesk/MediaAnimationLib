package com.hkm.media.library.elements.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by hesk on 11/1/13.
 */
public abstract class Element {
    protected Canvas main;
    protected Matrix global_matrix;
    protected Context ctx;
    protected Paint normal_paint;

    public Element() {
        this.normal_paint = new Paint();
    }

    public Element(Canvas setCanvas) {
        main = setCanvas;
        this.normal_paint = new Paint();
    }

    public Element(Canvas mcanvas, Context ctx) {
        main = mcanvas;
        this.ctx = ctx;
        this.normal_paint = new Paint();
    }

    public Element updateCanvas(Canvas cv, Matrix mx) {
        main = cv;
        global_matrix = mx;
        return this;
    }

    /**
     * this is the path for the elements of drawing to be filled
     */

    protected abstract void rendering();


    public void renderPath() {
        this.rendering();
    }

    public void renderPath(Canvas pointer_reference) {
        final Canvas cache_pointer = main;
        main = pointer_reference;
        this.rendering();
        main = cache_pointer;
    }
}
