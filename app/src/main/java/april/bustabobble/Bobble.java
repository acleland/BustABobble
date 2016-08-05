package april.bustabobble;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import game.engine.Engine;
import game.engine.Float2;
import game.engine.Texture;

/**
 * Created by Andronius on 7/20/16.
 */
public class Bobble extends game.engine.Sprite {
    private static int RADIUS;
    public static int color;

    public Bobble(Engine engine, int color) {
        super(engine, 2*RADIUS, 2*RADIUS, 1);
        this.setTexture(new Texture(engine, makeBitmap(color)));
        this.setCollidable(true);
    }

    public Bobble(Engine engine, int color, Float2 pos) {
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

}
