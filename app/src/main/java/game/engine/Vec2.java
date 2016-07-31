package game.engine;

import android.renderscript.Float2;
import android.graphics.Point;

/**
 * Created by Andronius on 7/30/16.
 */
public class Vec2 {
    public float x;
    public float y;

    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2(Float2 v) {
        x = v.x;
        y = v.y;
    }

    public Vec2(Point p) {
        x = (float) p.x;
        y = (float) p.y;
    }

    public Vec2(int x, int y) {
        this.x = (float) x;
        this.y = (float) y;
    }

    public Vec2 plus(Vec2 v) {
        return new Vec2(x+v.x, y+v.y);
    }

    public Vec2 minus(Vec2 v) {
        return new Vec2(x-v.x, y-v.y);
    }

    public float dot(Vec2 v) {
        return (x*v.x + y*v.y);
    }

    public float mag() {
        return (float) Math.sqrt(x*x + y*y);
    }

    public float sqmag() {
        return x*x + y*y;
    }

    public float cosAngle(Vec2 v) {
        return this.dot(v)/(this.mag()*v.mag());
    }

    public float getAngle(Vec2 v) {
        return (float) Math.acos(this.cosAngle(v));
    }

    public Point toPoint() {
        return new Point(Math.round(x), Math.round(y));
    }

    public Float2 toFloat2() {
        return new Float2(x, y);
    }

}
