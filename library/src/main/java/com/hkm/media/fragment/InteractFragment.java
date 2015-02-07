package com.hkm.media.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hkm.media.library.R;
import com.hkm.media.library.elements.core.Element;
import com.hkm.media.library.elements.shapes.Sprite;
import com.hkm.media.panels.PanAnimation;

/**
 * Created by hesk on 2/5/15.
 */
public class InteractFragment extends Fragment {

    private Element[] new_elem;
    private PanAnimation panel;


    public InteractFragment() {
        super();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment, container, false);
    }

    public PanAnimation getPanel() {
        return panel;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        panel = (PanAnimation) view.findViewById(R.id.panel);
        panel.addElements(new_elem);
        panel.start();
    }

    /**
     * adding artwork before initiation
     *
     * @param e
     */
    public void addChilds(Element[] e) {
        new_elem = e;
    }

    public static Sprite newCharacter(final int resId, Context context) {
        final Bitmap bm = BitmapFactory.decodeResource(context.getResources(), resId);
        final Sprite sp = new Sprite(bm);
        sp.setFPS(50);
        sp.setFrameType(Sprite.frameType.CHARACTER);
        return sp;
    }

    public static Sprite newAnimation(final int resId, Context context) {
        final Bitmap bm = BitmapFactory.decodeResource(context.getResources(), resId);
        final Sprite sp = new Sprite(bm);
        sp.setFPS(50);
        sp.setFrameType(Sprite.frameType.ANIMATION);
        return sp;
    }
}
