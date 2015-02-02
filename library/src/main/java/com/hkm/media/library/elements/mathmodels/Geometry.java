package com.hkm.media.library.elements.mathmodels;

import android.graphics.Point;
import android.graphics.PointF;
import android.util.FloatMath;
import android.view.MotionEvent;

/**
 * Created by hesk on 8/28/13.
 */
public class Geometry {

    private static boolean is_collision_between_2_circles(PointF p1, double r1, PointF p2, double r2) {
        final double a = r1 + r2;
        final double dx = p1.x - p2.x;
        final double dy = p1.y - p2.y;
        return a * a > (dx * dx + dy * dy);
    }

    public static boolean is_point_on_circle(PointF circle_center, PointF testing_point, float r) {
        double a = testing_point.x - circle_center.x;
        double b = testing_point.y - circle_center.y;
        return a * a + b * b < r * r;
    }

    public static boolean is_point_on_circle(PointF circle_center, PointF testing_point, int r) {
        double a = testing_point.x - circle_center.x;
        double b = testing_point.y - circle_center.y;
        return a * a + b * b < r * r;
    }

    public static boolean is_point_on_circle(Point circle_center, Point testing_point, int r) {
        double a = testing_point.x - circle_center.x;
        double b = testing_point.y - circle_center.y;
        return a * a + b * b < r * r;
    }

    public static boolean is_point_on_circle(Point circle_center, PointF testing_point, float r) {
        double a = testing_point.x - circle_center.x;
        double b = testing_point.y - circle_center.y;
        return a * a + b * b < r * r;
    }

    public static boolean is_point_on_circle(Point circle_center, PointF testing_point, int r) {
        double a = testing_point.x - circle_center.x;
        double b = testing_point.y - circle_center.y;
        return a * a + b * b < r * r;
    }

    /**
     * Recall the result given in Chapter 3: When an object is in uniform circular motion, moving
     * in a circle of radius r with speed v, the acceleration is directed toward the center of the circle
     * and has magnitude
     * http://iweb.tntech.edu/murdock/books/v1chap5.pdf
     */
    public static float motion_towards_centric(float v, float r) {
        return (v * v) / r;
    }

    /**
     * Therefore, by Newtons Second Law of Motion, the net force on this object must also be
     * directed toward the center of the circle and have magnitude
     * Such a force is called a centripetal force, as indicated in this equation.
     * http://hyperphysics.phy-astr.gsu.edu/hbase/cf.html
     * Kepler's Third Law: Centripetal force on planet is equal to Gravitational force on planet.
     */
    public static float centripetal_force(float v, float r, float m) {
        return (v * v) * m / r;
    }

    /**
     * Returns 1 if the lines intersect, otherwise 0. In addition, if the
     * lines intersect the intersection point may be stored in the floats i_x and i_y.
     *
     * @param p0_x
     * @param p0_y
     * @param p1_x
     * @param p1_y
     * @param p2_x
     * @param p2_y
     * @param p3_x
     * @param p3_y
     * @return
     * @throws Exception
     */
    public static PointF get_line_intersection(float p0_x, float p0_y, float p1_x, float p1_y,
                                               float p2_x, float p2_y, float p3_x, float p3_y
    ) throws Exception {
        float s1_x, s1_y, s2_x, s2_y;
        s1_x = p1_x - p0_x;
        s1_y = p1_y - p0_y;
        s2_x = p3_x - p2_x;
        s2_y = p3_y - p2_y;

        float s, t;
        s = (-s1_y * (p0_x - p2_x) + s1_x * (p0_y - p2_y)) / (-s2_x * s1_y + s1_x * s2_y);
        t = (s2_x * (p0_y - p2_y) - s2_y * (p0_x - p2_x)) / (-s2_x * s1_y + s1_x * s2_y);

        if (s >= 0 && s <= 1 && t >= 0 && t <= 1) {
            // Collision detected
            return new PointF(p0_x + (t * s1_x), p0_y + (t * s1_y));
        } else {
            throw new Exception("no intersection"); // No collision
        }
    }

    public static float slope(Point p, Point p_new) {
        float delta = p_new.x - p.x;
        //infinite.. this is a straight line to the top for delta  == 0
        if (delta == 0) {
            System.out.print("error delta");
        }
        float slope_normal = (p_new.y - p.y) / delta;
        return slope_normal;
//        return perpendicular_slope(slope_normal);
    }

    /**
     * @param p
     * @param p_new
     * @return
     * @throws Exception
     * @source http://cstl.syr.edu/fipse/grapha/unit4/unit4a.html
     */
    public static double slope(PointF p, PointF p_new) throws Exception {
        float delta = p_new.x - p.x;
        //infinite.. this is a straight line to the top for delta  == 0
        if (delta == 0) throw new Exception("divided by zero exception");
        double slope_normal = (p_new.y - p.y) / delta;
        return slope_normal;
        //        return perpendicular_slope(slope_normal);
    }

    public static double perpendicular_slope(double m1) {
        return -1 / m1;
    }

    public static float distance(PointF p1, PointF p2) {
        final float d1 = (p2.x - p1.x) * (p2.x - p1.x);
        final float d2 = (p2.y - p1.y) * (p2.y - p1.y);
        final float f = d1 + d2;
        return (float) Math.sqrt((double) f);
    }

    public static double QuadPlus(double a, double b, double c) {
        return ((-b) + Math.sqrt((b * b) - (4 * a * c))) / (2 * a);
    }

    public static double QuadMinus(double a, double b, double c) {
        return ((-b) - Math.sqrt((b * b) - (4 * a * c))) / (2 * a);
    }

    public static int powered(int k) {
        return k * k;
    }

    public static double powered(double k) {
        return k * k;
    }

    public static float powered(float k) {
        return k * k;
    }

    //                                                            (a,b)
    public static float[] circle_and_linear_line(line_eq ln, PointF center_point, double R2) {
        float X1, X2, Y1, Y2;
        if (ln.get_type() == line_eq.line_type.HORIZONTAL) {
            X1 = center_point.x + (float) Math.sqrt(R2);
            X2 = center_point.x - (float) Math.sqrt(R2);
            Y1 = Y2 = center_point.y;
        } else if (  ln.get_type() == line_eq.line_type.VERTICAL) {
            X1 = X2 = center_point.x;
            Y1 = center_point.y + (float) Math.sqrt(R2);
            Y2 = center_point.y - (float) Math.sqrt(R2);
        } else {
            //the line 1
            double solver_a = 1 + powered(ln.m);
            double solver_b = 2 * ((ln.t - center_point.y) * ln.m - center_point.x);
            double solver_c = powered(center_point.x) + powered(ln.t - center_point.y) - R2;
            X1 = (float) QuadPlus(solver_a, solver_b, solver_c);
            X2 = (float) QuadMinus(solver_a, solver_b, solver_c);
            Y1 = ln.calculate_yf(X1);
            Y2 = ln.calculate_yf(X2);
        }

        float[] answers = {X1, Y1, X2, Y2};
        return answers;
    }

    public static float distance(Point p1, Point p2) {
        final float d1 = (p2.x - p1.x) * (p2.x - p1.x);
        final float d2 = (p2.y - p1.y) * (p2.y - p1.y);
        final float f = d1 + d2;
        return (float) Math.sqrt((double) f);
    }

    public static float getRadiusBy_touch_point_cir() {
        return 0.0f;
    }

    /* public PointF touch_point_circle_to_radius(PointF centrical_point, PointF moveFrom, PointF moveTo) {
         //the starting line from zero
         final line_eq l1 = new line_eq(moveFrom, moveTo);
         //the line for perpendicular
         final line_eq l2 = new line_eq(perpendicular_slope(l1.m), centrical_point);
         final float x = (l2.t - l1.t) / (l1.m - l2.m);
         final float y = l2.m * x + l2.t;
         return new PointF(x, y);
     }*/

    public static Point cross_point(line_eq l1, line_eq l2, PointF away_point) {
        if (l1.get_type() == line_eq.line_type.VERTICAL) {
            return new Point((int) l1.getStrictedPoint().x, (int) away_point.y);
        } else {
            final int x = (int) ((l2.t - l1.t) / (l1.m - l2.m));
            final int y = (int) (l2.m * x + l2.t);
            return new Point(x, y);
        }

    }

    public PointF midPoint(PointF point, MotionEvent me) {
        float x = me.getX(0) + me.getX(1);
        float y = me.getY(0) + me.getY(1);
        point.set(x / 2, y / 2);
        return point;
    }

    public float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return FloatMath.sqrt(x * x + y * y);
    }
}
