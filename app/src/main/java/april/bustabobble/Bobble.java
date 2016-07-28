package april.bustabobble;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by Andronius on 7/20/16.
 */
public class Bobble {
    public static final int RADIUS = 40;
    private int color = Colors.TRANS_PINK;
    private Point loc = new Point(0,0);

    /**
     * Make bobble bitmaps
     */
    public static Bitmap createBitmap(int color) {
        Bitmap b = Bitmap.createBitmap(2*RADIUS, 2*RADIUS, Bitmap.Config.ARGB_8888);
        Canvas buffer = new Canvas(b);
        Paint paint = new Paint();
        paint.setColor(color);
        buffer.drawCircle(RADIUS, RADIUS, RADIUS, paint);
        return b;
    }



    public Bobble(int x, int y, int color) {
        loc.x = x;
        loc.y = y;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public Point getLoc() {
        return loc;
    }

    public void setLoc(int x, int y) {
        loc.x = x;
        loc.y = y;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void move(int dx, int dy) {
        loc.x += dx;
        loc.y += dy;
    }
}
