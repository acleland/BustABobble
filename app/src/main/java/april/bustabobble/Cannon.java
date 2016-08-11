/**
 * Copyright (c) 2016 Andrew Cleland.
 */
package april.bustabobble;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import game.engine.Vec2;


public class Cannon {
    public static int RADIUS;
    public Vec2 center;
    public float angle = 0.0f;
    public Vec2 direction;
    public static int color = Color.BLACK;
    public static final Vec2 vertical = new Vec2(0, -1);
    private Game engine;
    //private Bobble bobble = null;

    public static float cos(float theta) {return (float) Math.cos(theta);}
    public static float sin(float theta) {return (float) Math.sin(theta);}

    public Cannon (Game e, Vec2 center) {
        this.center = center;
        this.engine = e;
        this.direction = new Vec2(0, -1);

    }


    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        paint.setStrokeWidth(8.0f);
        canvas.drawLine(
                center.x - RADIUS*direction.x, center.y - RADIUS*direction.y,
                center.x + RADIUS*direction.x, center.y + RADIUS*direction.y, paint);
    }

    public void drawArrow(float startX, float startY, float endX, float endY, Canvas canvas, Paint paint) {
        // Unfinished. Eventually would like cannon to be cool looking.

        canvas.drawLine(startX, startY, endX, endY, paint);
        paint.setStyle(Paint.Style.FILL);

    }


    public static void setRADIUS(int r) {
        RADIUS = r;
    }


}
