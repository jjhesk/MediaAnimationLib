package com.hkm.media.library.elements.shapes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by Hesk on 13/10/15
 */
public class IconBitmap {
    Paint mPaint = new Paint();
    private Context ctx;
    private int r;
    private Drawable bd;
    private Bitmap bm;
    private String text;

    public IconBitmap(Context a) {
        ctx = a;
    }

    public IconBitmap(Context a, int resource_id) {
        ctx = a;
        init_1(resource_id);
        //c.scale(1.5, 1.5);
    }

    public IconBitmap(Context a, int resource_id, String tx) {
        ctx = a;
        init_1(resource_id);
        init_2(tx);
    }

    private void init_1(int resource_id) {
        bd = ctx.getResources().getDrawable(resource_id);
        mPaint.setStyle(Paint.Style.FILL);
        //mPaint.setColor(0xffff0000);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        bm = ((BitmapDrawable) bd).getBitmap();

    }

    private void init_2(String tx) {
        Canvas c = new Canvas(bm);
        if (tx.equalsIgnoreCase("A")) {
            text = "reference point a";
        } else if (tx.equalsIgnoreCase("B")) {
            text = "reference point b";
        } else {
            text = tx;
        }
    }

    public void onDraw(Canvas c, Point pos) {
        if (text != null) {
            c.drawText(text, pos.x, pos.y, mPaint);
        }
        c.drawBitmap(bm, pos.x, pos.y, mPaint);
    }
}
