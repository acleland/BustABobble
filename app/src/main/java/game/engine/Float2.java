package game.engine;

import android.graphics.Point;

/**
 * Created by Andronius on 7/30/16.
 */
public class Float2 {
    public float x;
    public float y;

    public Float2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Float2(android.renderscript.Float2 v) {
        x = v.x;
        y = v.y;
    }

    public Float2(Point p) {
        x = (float) p.x;
        y = (float) p.y;
    }

    public Float2(int x, int y) {
        this.x = (float) x;
        this.y = (float) y;
    }

    public Float2 plus(Float2 v) {
        return new Float2(x+v.x, y+v.y);
    }

    public Float2 minus(Float2 v) {
        return new Float2(x-v.x, y-v.y);
    }

    public float dot(Float2 v) {
        return (x*v.x + y*v.y);
    }

    public Float2 times(float scalar) {
        return new Float2(this.x * scalar, this.y * scalar);
    }

    public float mag() {
        return (float) Math.sqrt(x*x + y*y);
    }

    public float sqmag() {
        return x*x + y*y;
    }

    public float cosAngle(Float2 v) {
        return this.dot(v)/(this.mag()*v.mag());
    }

    public float getAngle(Float2 v) {
        return (float) Math.acos(this.cosAngle(v));
    }


    public Point toPoint() {
        return new Point(Math.round(x), Math.round(y));
    }

    public android.renderscript.Float2 toFloat2() {
        return new android.renderscript.Float2(x, y);
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

}
