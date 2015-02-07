package com.hkm.media.panels;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;

import com.hkm.media.library.R;
import com.hkm.media.library.elements.core.Element;

import java.util.Arrays;

/**
 * Created by hesk on 2/5/15.
 */
public class PanAnimation extends ReactSurface {
    public PanAnimation(Context ctx, AttributeSet attrs, int defStyle) {
        super(ctx, attrs, defStyle);
    }

    public PanAnimation(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
    }

    public PanAnimation(Context ctx) {
        super(ctx);
    }

    private Element[] elements;

    private Element[] addElement(Element added) {
        final Element[] result = Arrays.copyOf(elements, elements.length + 1);
        result[elements.length] = added;
        return result;
    }

    public PanAnimation addArtWork(final Element ele) {
        elements = addElement(ele);
        return this;
    }

    public Element[] getDrawQueue() {
        return elements;
    }

    @Override
    protected void onDrawRender(Canvas mcanvas) {
        super.onDrawRender(mainCanvas);
        for (int i = 0; i < elements.length; i++) {
            final Element element = elements[i];
            if (element != null) {
                element.updateCanvas(mcanvas).renderPath();
            }
        }

    }

    public void start() {
        init();
    }

    @Override
    protected void init() {
        if (elements == null)
            elements = new Element[0];
        super.init();
        threadStart();
    }
}
