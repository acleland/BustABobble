/**
 * A 2D vector class
 * Copyright (c) 2016 Andrew Cleland.
 */

package game.engine;
import android.graphics.Point;


public class Vec2 {
    public float x;
    public float y;

    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2(android.renderscript.Float2 v) {
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

    public Vec2 times(float scalar) {
        return new Vec2(this.x * scalar, this.y * scalar);
    }

    public float mag() {
        return (float) Math.sqrt(x*x + y*y);
    }

    public float sqmag() {
        return x*x + y*y;
    }

    public Vec2 getUnit() {
        return this.times(1.0f/this.mag());
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

    public android.renderscript.Float2 toFloat2() {
        return new android.renderscript.Float2(x, y);
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

}
