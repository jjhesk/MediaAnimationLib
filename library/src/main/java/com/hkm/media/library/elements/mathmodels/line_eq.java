package com.hkm.media.library.elements.mathmodels;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;


import java.io.Serializable;

/**
 * Created by Hesk on
 */
public class line_eq implements Serializable {
    transient protected final PointF startp = new PointF(), endp = new PointF();
    transient protected final Path line = new Path();
    transient protected final Point any_point_on_the_line = new Point();
    transient protected final Point awayPoint = new Point();
    /**
     * Simple line equation with this..
     * y = mx + t
     */

    public double t;

    public double m;

    transient private float min_y, max_y, min_x, max_x;

    transient private line_type type;

    static public enum line_type {VERTICAL, HORIZONTAL, NORMAL}

    /**
     * Simple Line equation
     * Applying to the line equation with two different points p0, p1
     *
     * @param p0
     * @param p1
     */
    public line_eq(PointF p0, PointF p1) {
        min_y = max_y = min_x = max_x = 0.0f;
        construct_line(p0, p1);
    }

    public line_eq(Point p0, Point p1) {
        min_y = max_y = min_x = max_x = 0.0f;
        construct_line(new PointF(p0.x, p0.y), new PointF(p1.x, p1.y));
    }

    public line_eq(double m, double t) {
        this.m = m;
        this.t = t;
        if (m == 0) {
            type = line_type.HORIZONTAL;
        } else {
            type = line_type.NORMAL;
        }
    }

    /**
     * Constructing a line with a slope and a crossing point
     *
     * @param slope
     * @param crossPoint
     */
    public line_eq(double slope, PointF crossPoint) {
        min_y = max_y = min_x = max_x = 0.0f;
        construct_line(slope, crossPoint);
    }

    public double getAngle() {
        if (type == line_type.NORMAL) {
            return Math.atan2(m, 1) * 180 / Math.PI;
        } else if (type == line_type.VERTICAL) {
            return 90.0d;
        } else {
            return 0.0d;
        }
    }

    public PointF getStrictedPoint() {
        return startp;
    }

    private void construct_line(PointF point_0, PointF point_1) {
        startp.set(point_0);
        endp.set(point_1);
        try {
            m = Geometry.slope(point_0, point_1);
            type = line_type.NORMAL;
            t = point_0.y - m * point_0.x;
            if (Math.floor(m * 1000) == 0) {
                type = line_type.HORIZONTAL;
            } else if (Math.floor(point_0.x) == Math.floor(point_1.x)) {
                type = line_type.VERTICAL;
                t = point_1.x;
            }
        } catch (Exception e) {
            //y=m only x is dynamic
            type = line_type.VERTICAL;
            t = point_1.x;
        }
    }

    private void construct_line(Point point_0, Point point_1) {
        this.construct_line(
                new PointF(point_0.x, point_0.y),
                new PointF(point_1.x, point_1.y)
        );
    }

    private void construct_line(double slope, PointF crossPoint) {
        m = slope;
        t = crossPoint.y - m * crossPoint.x;
    }

    public line_type get_type() {
        return type;
    }


    public line_eq update_line_by_slope(float slope, PointF crossPoint) {
        construct_line(slope, crossPoint);
        line.reset();
        return this;
    }

    public line_eq update_line_by_points(Point p0, Point p1) {
        construct_line(p0, p1);
        line.reset();
        return this;
    }

    public line_eq update_line_by_points(PointF p0, PointF p1) {
        construct_line(p0, p1);
        line.reset();
        return this;
    }


    public Point getMinDistancePoint(PointF any_pt) {
        final Point g = new Point();
        if (type == line_type.HORIZONTAL) {
            g.set((int) any_pt.x, (int) startp.y);
        } else if (type == line_type.VERTICAL) {
            g.set((int) startp.x, (int) any_pt.y);
        } else if (type == line_type.NORMAL) {
            double m = Geometry.perpendicular_slope(this.m);
            line_eq d = new line_eq(m, any_pt);
            Point f = Geometry.cross_point(this, d, any_pt);
            g.set(f.x, f.y);
        }
        this.any_point_on_the_line.set(g.x, g.y);
        this.awayPoint.set((int) any_pt.x, (int) any_pt.y);
        return any_point_on_the_line;
    }

    public void setBound(float init_min_x, float init_max_x, float init_min_y, float init_max_y) {
        min_x = init_min_x;
        max_x = init_max_x;
        min_y = init_min_y;
        max_y = init_max_y;
    }

    public Path getAwayPointMinDistance() {
        final Path f = new Path();
        f.moveTo(awayPoint.x, awayPoint.y);
        f.lineTo(any_point_on_the_line.x, any_point_on_the_line.y);
        return f;
    }

    public Path getPath() {
        float er = min_x + max_x + min_y + max_y;
        System.out.print("the boundary of the canvas needed to be defined first");
        if (er > 0) {
            if (type == line_type.VERTICAL) {
                line.moveTo(startp.x, min_y);
                line.lineTo(startp.x, max_y);
            } else if (type == line_type.HORIZONTAL) {
                line.moveTo(min_x, startp.y);
                line.lineTo(max_x, startp.y);
            } else {
                final PointF pl = get_point_from_x(min_x); //bound_left
                final PointF pr = get_point_from_x(max_x); //bound_right
                final PointF pt = get_point_from_y(min_y); //bound_top
                final PointF pb = get_point_from_y(max_y); //bound_bottom
                //===============================================
                final boolean bound_left = pl.y > min_y && pl.y < max_y;
                final boolean bound_right = pr.y > min_y && pr.y < max_y;
                final boolean bound_top = pt.x > min_x && pt.x < max_x;
                final boolean bound_bottom = pb.x > min_x && pb.x < max_x;
                //===============================================
                final PointF line_startp = new PointF(), line_endp = new PointF();
                if (bound_left && bound_right) {
                    line_startp.set(pl);
                    line_endp.set(pr);
                } else if (bound_left && bound_top) {
                    line_startp.set(pl);
                    line_endp.set(pt);
                } else if (bound_left && bound_bottom) {
                    line_startp.set(pl);
                    line_endp.set(pb);
                } else if (bound_right && bound_top) {
                    line_startp.set(pr);
                    line_endp.set(pt);
                } else if (bound_right && bound_bottom) {
                    line_startp.set(pr);
                    line_endp.set(pb);
                } else if (bound_top && bound_bottom) {
                    line_startp.set(pt);
                    line_endp.set(pb);
                }
                line.moveTo(line_startp.x, line_startp.y);
                line.lineTo(line_endp.x, line_endp.y);
            }
        }
        return line;
    }

    public double calculate_y(float x) {
        return m * x + t;
    }

    public double calculate_y(double x) {
        return m * x + t;
    }

    public float calculate_yf(float x) {
        return (float) (m * x + t);
    }

    public double calculate_x(float y) {
        return (t - y) / m * -1;
    }

    public double calculate_x(double y) {
        return (t - y) / m * -1;
    }

    private PointF get_point_from_x(float x) {
        float h = (float) calculate_y(x);
        // h = (int) (m * x + t);
        return new PointF(x, h);
    }

    private PointF get_point_from_y(float y) {
        float h = (float) calculate_x(y);
        // h = (int) ((t - y) / m * -1);
        return new PointF(h, y);
    }
}
