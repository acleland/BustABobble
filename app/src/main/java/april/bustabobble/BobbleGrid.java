/**
 * Copyright (c) 2016 Andrew Cleland.
 */

package april.bustabobble;

import android.graphics.Point;
import android.graphics.Rect;

import java.util.Hashtable;

import game.engine.Vec2;


public class BobbleGrid {
    private Hashtable<Point, Bobble> gridPosToBobble;
    private Hashtable<Bobble, Point> bobbleToGridPos;
    private int ceiling;
    private int GRID_WIDTH = Game.BOBBLES_PER_FRAME_WIDTH;
    private int UNIT_WIDTH;
    private float SPECIAL_OFFSET;
    private static final float sin60 = 0.866f;
    public Game game;
    private Rect frame;

    public BobbleGrid(Game game, Rect frame) {
        this.game = game;
        gridPosToBobble = new Hashtable<Point, Bobble>();
        bobbleToGridPos = new Hashtable<Bobble, Point>();
        ceiling = frame.top;
        UNIT_WIDTH = Math.round((float)frame.width()/GRID_WIDTH);
        SPECIAL_OFFSET = (float)UNIT_WIDTH/2;
        this.frame = frame;
    }



    public void placeInGrid(Bobble bobble, Point gridPos) {
        bobbleToGridPos.put(bobble, gridPos);
        gridPosToBobble.put(gridPos, bobble);
        bobble.setPosition(getScreenPos(gridPos));
    }

    public void placeInTopRow(Bobble bobble) {
        Point gridPos = new Point(getGridX(bobble.getPosition().x), 0);
        if (gridPosToBobble.containsKey(gridPos)){
            // If spot is taken keep moving to left or right till a spot is open
            if (bobble.getPosition().x >= getScreenX(gridPos.x)){
                bobble.setPosition((getScreenPos(gridPos.x + 1, gridPos.y)));
                placeInTopRow(bobble);
            }
            else {
                bobble.setPosition((getScreenPos(gridPos.x - 1, gridPos.y)));
                placeInTopRow(bobble);
            }
        }
        placeInGrid(bobble, gridPos);
    }

    public int getGridX(float screenX) {
        return Math.round(screenX/UNIT_WIDTH);
    }

    public int getSpecialGridX(float screenX) {
        return 0;
    }

    public float getScreenX(int gridX){
        return frame.left + UNIT_WIDTH*gridX;
    }

    public float getSpecialScreenX(int gridX) {
        return getScreenX(gridX) + SPECIAL_OFFSET;
    }

    public float getScreenY(int gridY) {
        return ceiling + UNIT_WIDTH*gridY;
    }

    public float getSpecialScreenY(int gridY) {
        return 0;
    }

    public Vec2 getScreenPos(int gridX, int gridY) {
        return new Vec2(getScreenX(gridX), getScreenY(gridY));
    }

    public Vec2 getScreenPos(Point gridPos){
        return getScreenPos(gridPos.x, gridPos.y);
    }


}
