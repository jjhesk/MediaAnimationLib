package com.hkm.media.panels;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.hkm.media.library.elements.core.Element;

import java.util.Arrays;

/**
 * Created by hesk on 2/5/15.
 */
public class PanAnimation extends ReactSurface {
    public PanAnimation(Context ctx, AttributeSet attrs, int defStyle) {
        super(ctx, attrs, defStyle);
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

    @Override
    protected void onDraw(Canvas mcanvas) {
        for (int i = 0; i < elements.length; i++) {
            final Element element = elements[i];
            element.renderPath();
        }
    }

    @Override
    protected void init() {
        if (elements == null)
            elements = new Element[0];

        super.init();
    }
}
