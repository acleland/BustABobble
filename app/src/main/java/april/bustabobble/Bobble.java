package april.bustabobble;

import android.graphics.Point;

/**
 * Created by Andronius on 7/20/16.
 */
public class Bobble {
    public static final int RADIUS = 40;
    private int color = Colors.TRANS_PINK;
    private Point loc = new Point(0,0);

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
