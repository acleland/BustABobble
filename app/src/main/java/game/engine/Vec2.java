package game.engine;

import android.renderscript.Float2;

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




}
