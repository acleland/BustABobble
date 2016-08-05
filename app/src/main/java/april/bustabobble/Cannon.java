package april.bustabobble;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import game.engine.Vec2;

/**
 * Created by Andronius on 7/30/16.
 */
public class Cannon {
    public static int RADIUS;
    public Vec2 center;
    public float angle = 0.0f;
    public Vec2 direction;
    public static int color = Color.BLACK;
    public static final Vec2 vertical = new Vec2(0, -1);
    private Game engine;
    private Bobble bobble = null;

    public static float cos(float theta) {return (float) Math.cos(theta);}
    public static float sin(float theta) {return (float) Math.sin(theta);}

    public Cannon (Game e, Vec2 center) {
        this.center = center;
        this.engine = e;
        this.direction = new Vec2(0, -1);

    }

    public void load(Bobble b) {
        bobble = b;
        Vec2 offset = new Vec2(b.getSize()).times(-.5f);
        bobble.setPosition(this.center.plus(offset));
        bobble.setVelocity(new Vec2(0,0));
        engine.initNextBobble();
    }

    public void fire() {
        if (bobble != null) {
            bobble.setVelocity(this.direction.times(Game.LAUNCH_SPEED));
            bobble.addAnimation(new ReboundBehavior(new RectF(engine.frame), bobble.getSize(), bobble.getVelocity()));
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        paint.setStrokeWidth(8.0f);
        canvas.drawLine(
                center.x - RADIUS*direction.x, center.y - RADIUS*direction.y,
                center.x + RADIUS*direction.x, center.y + RADIUS*direction.y, paint);
    }

    public void drawArrow(float startX, float startY, float endX, float endY, Canvas canvas, Paint paint) {
        canvas.drawLine(startX, startY, endX, endY, paint);
        paint.setStyle(Paint.Style.FILL);

    }


    public static void setRADIUS(int r) {
        RADIUS = r;
    }


}
