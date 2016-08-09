package april.bustabobble;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import game.engine.Engine;
import game.engine.Vec2;
import game.engine.Texture;

/**
 * Created by Andronius on 7/20/16.
 */
public class Bobble extends game.engine.Sprite {
    private static int RADIUS;
    private int color;
    private Game game;

    public Bobble(Game engine, int color) {
        super(engine, 2*RADIUS, 2*RADIUS, 1);
        setName("bobble");
        this.game = engine;
        this.setTexture(new Texture(engine, makeBitmap(color)));
        this.setCollidable(true);
        this.color = color;
    }

    public Bobble(Game engine, int color, Vec2 pos) {
        this(engine, color);
        this.setPosition(pos);

    }

    public static Bitmap makeBitmap(int color) {
        Bitmap b = Bitmap.createBitmap(2*RADIUS, 2*RADIUS, Bitmap.Config.ARGB_8888);
        Canvas buffer = new Canvas(b);
        Paint paint = new Paint();
        paint.setColor(color);
        buffer.drawCircle(RADIUS, RADIUS, RADIUS, paint);
        return b;
    }

    public static void setRADIUS(int R) {
        RADIUS = R;
    }

    public static int getRADIUS() {
        return RADIUS;
    }

    public void setColor(int color) {
        this.color = color;
        this.setTexture(new Texture(this.game, makeBitmap(color)));
    }

    public int getColor() {
        return this.color;
    }

    public Vec2 getCenter() {
        return getPosition().plus(new Vec2((float)this.getWidth()/2, (float)this.getHeight()/2));
    }

    public void setCenter(Vec2 center) {
        setPosition(center.minus(new Vec2((float)this.getWidth()/2, (float)this.getHeight()/2)));
    }
}
