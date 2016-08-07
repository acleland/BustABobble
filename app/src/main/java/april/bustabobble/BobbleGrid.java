package april.bustabobble;

import android.graphics.Point;
import android.graphics.Rect;

import java.util.Hashtable;

/**
 * Created by Andronius on 8/4/16.
 */
public class BobbleGrid {
    private Hashtable<Point, Bobble> pointToBobble;
    private Hashtable<Bobble, Point> bobbleToPoint;
    private int ceiling;
    private int GRID_WIDTH = Game.BOBBLES_PER_FRAME_WIDTH;
    private int UNIT_WIDTH;
    public Game game;
    private Rect frame;

    public BobbleGrid(Game game, Rect frame) {
        this.game = game;
        pointToBobble = new Hashtable<Point, Bobble>();
        bobbleToPoint = new Hashtable<Bobble, Point>();
        ceiling = frame.top;
        UNIT_WIDTH = frame.width()/GRID_WIDTH;
    }


    public void add(Bobble bobble) {
        Point screenPos = bobble.getPosition().toPoint();

    }





}
