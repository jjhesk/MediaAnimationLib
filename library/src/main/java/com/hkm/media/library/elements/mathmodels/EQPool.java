package com.hkm.media.library.elements.mathmodels;

import android.graphics.ColorMatrix;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.MotionEvent;


import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;


/**
 * Geometry ToolBox Developed by Heskeyo Kam
 * Created by Hesk ons
 */
public class EQPool {
    // internal calculations
    private MotionEvent me;

    public EQPool() {

    }

    public static boolean onPressedCicle(PointF ref_point, PointF tp, float r) {
        double a = tp.x - ref_point.x;
        double b = tp.y - ref_point.y;
        return a * a + b * b < r * r;
    }

    public static boolean onPressedCicle(PointF ref_point, PointF tp, int r) {
        double a = tp.x - ref_point.x;
        double b = tp.y - ref_point.y;
        return a * a + b * b < r * r;
    }

    public static boolean onPressedCicle(Point ref_point, Point tp, int r) {
        double a = tp.x - ref_point.x;
        double b = tp.y - ref_point.y;
        return a * a + b * b < r * r;
    }

    public static boolean onPressedCicle(Point ref_point, PointF tp, float r) {
        double a = tp.x - ref_point.x;
        double b = tp.y - ref_point.y;
        return a * a + b * b < r * r;
    }

    public static boolean onPressedCicle(Point ref_point, PointF tp, int r) {
        double a = tp.x - ref_point.x;
        double b = tp.y - ref_point.y;
        return a * a + b * b < r * r;
    }

    private static boolean isCollision_from_circles(PointF p1, double r1, PointF p2, double r2) {
        final double a = r1 + r2;
        final double dx = p1.x - p2.x;
        final double dy = p1.y - p2.y;
        return a * a > (dx * dx + dy * dy);
    }

    public static PointF[] getLayerIntersectPairES(PointF p1, PointF p2, int r1, int r2) {
        float dx = p2.x - p1.x;
        float dy = p2.y - p1.y;
        // PointF point_delta = new PointF(dx, dy);
        double d = Math.sqrt(dx * dx + dy * dy);
        double d2 = d * d;
        float case1 = Math.abs(r1 + r2);
        float case2 = Math.abs(r1 - r2);
        float R1 = r1 * r1;
        float R2 = r2 * r2;
        if (p1.equals(p2) && Math.abs(d) == 0 || Math.abs(d) > case1 || Math.abs(d) < case2) {
            //alert("false estimation");
            //alert("d:" + Math.abs(d) + "case1 :" + case1 + "case2 :" + case2);
            //this.canvas_circles.activate();
            //pH.alert("false estimation\n " + "d:" + Math.abs(d) + "\n case1 :" + case1 + "\n case2 :" + case2);
            return null;
        } else {
            double a = (R1 - R2 + d2) / (2 * d);
            double h = Math.sqrt(R1 - Math.pow(a, 2));
            double x = p1.x + a * (p2.x - p1.x) / d;
            double y = p1.y + a * (p2.y - p1.y) / d;
            double x1 = x + h * (p2.y - p1.y) / d;
            double y1 = y - h * (p2.x - p1.x) / d;
            double x2 = x - h * (p2.y - p1.y) / d;
            double y2 = y + h * (p2.x - p1.x) / d;
            //  HashMap<PointF, PointF> interect_pair = new HashMap<PointF, PointF>();
            //  interect_pair.put(new PointF((float) x1, (float) y1), new PointF((float) x2, (float) y2));
            return new PointF[]{new PointF((float) x1, (float) y1), new PointF((float) x2, (float) y2)};
        }
    }

    public static HashMap<PointF, PointF> get_layer_intersect_pair(PointF p1, PointF p2, int r1, int r2) {
        float dx = p2.x - p1.x;
        float dy = p2.y - p1.y;
        // PointF point_delta = new PointF(dx, dy);
        double d = Math.sqrt(dx * dx + dy * dy);
        double d2 = d * d;
        float case1 = Math.abs(r1 + r2);
        float case2 = Math.abs(r1 - r2);
        float R1 = r1 * r1;
        float R2 = r2 * r2;
        if (p1.equals(p2) && Math.abs(d) == 0 || Math.abs(d) > case1 || Math.abs(d) < case2) {
            //alert("false estimation");
            //alert("d:" + Math.abs(d) + "case1 :" + case1 + "case2 :" + case2);
            //this.canvas_circles.activate();
            //pH.alert("false estimation\n " + "d:" + Math.abs(d) + "\n case1 :" + case1 + "\n case2 :" + case2);
            return null;
        } else {
            double a = (R1 - R2 + d2) / (2 * d);
            double h = Math.sqrt(R1 - Math.pow(a, 2));
            double x = p1.x + a * (p2.x - p1.x) / d;
            double y = p1.y + a * (p2.y - p1.y) / d;
            double x1 = x + h * (p2.y - p1.y) / d;
            double y1 = y - h * (p2.x - p1.x) / d;
            double x2 = x - h * (p2.y - p1.y) / d;
            double y2 = y + h * (p2.x - p1.x) / d;
            HashMap<PointF, PointF> interect_pair = new HashMap<PointF, PointF>();
            interect_pair.put(new PointF((float) x1, (float) y1), new PointF((float) x2, (float) y2));
            return interect_pair;
        }
    }

    // Color management
    private static void setTranslate(ColorMatrix cm, float dr, float dg,
                                     float db, float da) {
        cm.set(new float[]{
                2, 0, 0, 0, dr,
                0, 2, 0, 0, dg,
                0, 0, 2, 0, db,
                0, 0, 0, 1, da});
    }

    private static void setContrast(ColorMatrix cm, float contrast) {
        float scale = contrast + 1.f;
        float translate = (-.5f * scale + .5f) * 255.f;
        cm.set(new float[]{
                scale, 0, 0, 0, translate,
                0, scale, 0, 0, translate,
                0, 0, scale, 0, translate,
                0, 0, 0, 1, 0});
    }

    private static void setContrastTranslateOnly(ColorMatrix cm, float contrast) {
        float scale = contrast + 1.f;
        float translate = (-.5f * scale + .5f) * 255.f;
        cm.set(new float[]{
                1, 0, 0, 0, translate,
                0, 1, 0, 0, translate,
                0, 0, 1, 0, translate,
                0, 0, 0, 1, 0});
    }

    private static void setContrastScaleOnly(ColorMatrix cm, float contrast) {
        float scale = contrast + 1.f;
        float translate = (-.5f * scale + .5f) * 255.f;
        cm.set(new float[]{
                scale, 0, 0, 0, 0,
                0, scale, 0, 0, 0,
                0, 0, scale, 0, 0,
                0, 0, 0, 1, 0});
    }

    public static Path equilateralTriangle(PointF p1, int width, Direction direction) {
        PointF p2 = null, p3 = null;

        if (direction == Direction.NORTH) {
            p2 = new PointF(p1.x + width, p1.y);
            p3 = new PointF(p1.x + (width / 2), p1.y - width);
        } else if (direction == Direction.SOUTH) {
            p2 = new PointF(p1.x + width, p1.y);
            p3 = new PointF(p1.x + (width / 2), p1.y + width);
        } else if (direction == Direction.EAST) {
            p2 = new PointF(p1.x, p1.y + width);
            p3 = new PointF(p1.x - width, p1.y + (width / 2));
        } else if (direction == Direction.WEST) {
            p2 = new PointF(p1.x, p1.y + width);
            p3 = new PointF(p1.x + width, p1.y + (width / 2));
        }

        Path path = new Path();
        path.moveTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);
//http://tech.chitgoks.com/2012/07/08/android-draw-equilateral-triangle-shapes-in-canvas/
        return path;
    }

    public static Path ruler(PointF from, PointF to) {
        final Path p = new Path();
        float cross_line = 10.0f;
        p.moveTo(from.x, from.y);
        p.lineTo(to.x, to.y);
        return p;
    }

    // rendering part
    public static Path shape_cross_t(PointF pos) {
        final Path p = new Path();
        float cross_line = 10.0f;
        p.moveTo(pos.x, pos.y - cross_line);
        p.lineTo(pos.x, pos.y + cross_line);
        p.moveTo(pos.x + cross_line, pos.y);
        p.lineTo(pos.x - cross_line, pos.y);
        return p;
    }

    public static Path shape_cross_x(PointF pos) {
        Path p = new Path();
        float cross_line = 10.0f;
        p.moveTo(pos.x - cross_line, pos.y - cross_line);
        p.lineTo(pos.x + cross_line, pos.y + cross_line);
        p.moveTo(pos.x + cross_line, pos.y - cross_line);
        p.lineTo(pos.x - cross_line, pos.y + cross_line);
        return p;
    }

    public static float angleFromPoint(PointF a, PointF b) {
        final PointF vec = EQPool.vector(a, b);
        double angle = Math.atan2((double) vec.x, (double) vec.y);
        return (float) angle;
    }

    public static PointF vector(PointF a, PointF b) {
        float d1 = a.x - b.x;
        float d2 = a.y - b.y;
        return new PointF(d1, d2);
    }

    public static float dist(PointF a, PointF b) {
        float d1 = a.x - b.x;
        float d2 = a.y - b.y;
        float d = (float) Math.sqrt(d1 * d1 + d2 * d2);
        return d;
    }

    public static Point centerPoint(Point newPoint1, Point newPoint2) {
        final float gCenterX = (newPoint1.x + newPoint2.x) / 2;
        final float gCenterY = (newPoint1.y + newPoint2.y) / 2;
        return new Point((int) gCenterX, (int) gCenterY);
    }

    public static PointF centerPoint(PointF newPoint1, PointF newPoint2) {
        final float gCenterX = (newPoint1.x + newPoint2.x) / 2;
        final float gCenterY = (newPoint1.y + newPoint2.y) / 2;
        return new PointF(gCenterX, gCenterY);
    }

    public static Float precision(int decimalPlace, Float d) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public static float measure_distance_ratio(PointF pA, PointF pB, float real_distance) {
        float pd = Geometry.distance(pA, pB);
        float ratio = pd / real_distance;
        return ratio;
    }


    private Path randomTriangle(PointF A, PointF B, PointF C) {
        Path Pencil = new Path();
        Pencil.moveTo(A.x, A.y);
        Pencil.lineTo(B.x, B.y);
        Pencil.lineTo(C.x, C.y);
        return Pencil;
    }

    public enum Direction {
        NORTH, SOUTH, EAST, WEST;
    }

    public static int generate_random_id() {
        final int max = 9000;
        final int min = 1000;
        final Random rn = new Random();
        return rn.nextInt(max - min + 1) + min;
    }
    public static int generate_random_id(int length) {
        final int max = 9000;
        final int min = 1000;
        final Random rn = new Random();
        return rn.nextInt(max - min + 1) + min;
    }
    public static String shortUUID() {
        UUID uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        return Long.toString(l, Character.MAX_RADIX);
    }
}
