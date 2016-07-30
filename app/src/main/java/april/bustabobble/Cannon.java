package april.bustabobble;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.renderscript.Float2;

import game.engine.Engine;
import game.engine.Texture;

/**
 * Created by Andronius on 7/30/16.
 */
public class Cannon extends game.engine.Sprite {
    private static int LENGTH = 200;
    private static int color = Color.BLACK;

    public Cannon(Engine engine) {
        super(engine, LENGTH/2, LENGTH, 1);
        this.setTexture(new Texture(engine, makeBitmap(color)));
        this.setCollidable(false);
    }


    public static Bitmap makeBitmap(int color) {
        Bitmap b = Bitmap.createBitmap(LENGTH/2, LENGTH, Bitmap.Config.ARGB_8888);
        Canvas buffer = new Canvas(b);
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(10);
        float w = (float) buffer.getWidth();
        float h = (float) buffer.getHeight();
        buffer.drawLine(w/2, 0, w/2, h, paint);
        return b;
    }

    public static void setLENGTH(int L) {
        LENGTH = L;
    }
}
