package april.bustabobble;
import java.util.Random;
import android.graphics.*;
import android.util.Log;

import game.engine.*;
import game.engine.Vec2;


public class Game extends game.engine.Engine {
    Canvas canvas = null;
    Paint paint = null;
    Random random = null;
    Point touch = null;
    Bitmap bg_pattern = null;
    Bitmap frame_pattern = null;
    Rect frame = null;
    private int score = 0;
    Cannon cannon = null;
    Sprite compressor = null;

    Bobble nextBobble = null;
    Bobble loadedBobble = null;
    public static final int BOBBLES_PER_FRAME_WIDTH = 10;
    public static final float FRAME_HEIGHT_TO_WIDTH = 1.7f;
    public static final float Y_FRAME_RATIO = .98f;
    public static final float LAUNCH_SPEED = 10.0f;
    public boolean ready = false;
    public boolean launching = false;
    public static final int LOOSE = 100;
    public static final int IN_GRID = 200;


    public Game() {
        paint = new Paint();
        paint.setColor(Color.RED);
        random = new Random();
    }

    /*
    Define Engine Abstract methods
     */
    public void init() {
        setScreenOrientation(ScreenModes.PORTRAIT);
        setFrameRate(60);
    }

    public void load() {

        // Set sizes of game objects according to screen information
        float w = (float) getScreenWidth();
        float h = (float) getScreenHeight();
        int BOBBLE_RADIUS = Math.round(w/ BOBBLES_PER_FRAME_WIDTH / 2);
        Bobble.setRADIUS(BOBBLE_RADIUS);
        Cannon.setRADIUS(2*BOBBLE_RADIUS);

        bg_pattern = createBackground();
        frame = createFrame(FRAME_HEIGHT_TO_WIDTH, Y_FRAME_RATIO);
        frame_pattern = createFrameBackground(frame);
        if (debugging()) {
            System.out.println("frame values:");
            System.out.println("left: " + frame.left);
            System.out.println("right: " + frame.right);
            System.out.println("top: " + frame.top);
            System.out.println("bottom: " + frame.bottom);
            System.out.println("width: " + frame.width());
            System.out.println("height: " + frame.height());
        }

        // Init Compressor
        initCompressor(frame);

        // Init Next Bobble
        initNextBobble();

        // Init Cannon
        cannon = new Cannon(this, new Vec2(frame.centerX(), frame.bottom - Cannon.RADIUS));
        loadCannon();
    }

    public void draw() {
        canvas = getCanvas();
        canvas.drawBitmap(bg_pattern, 0, 0, paint);
        canvas.drawBitmap(frame_pattern, null, frame, paint);
        cannon.draw(canvas, paint);
        setTextColor(Color.RED);
        drawText("SCORE: " + toString(score), 0, 20);

        if (touch != null) {
            drawText("Touch " + touch.x + ", " + touch.y, 0, 80);
        }

    }

    public void update() {
        int inputs = getTouchInputs();
        if (isPressed()) {
            touch = getTouchPoint(0);
            rotateCannon(touch);
            if (!launching)
                ready = true;
        }
        else if (ready) {
            fireCannon();
            loadCannon();
            //launching = true;
            ready = false;
        }



    }

    public void collision(Sprite sprite) {
        Bobble bobble = (Bobble) sprite;
        Bobble other = (Bobble) sprite.getOffender();
        bobble.setColor(Color.BLACK);
        other.setColor(Color.DKGRAY);

    }

    public Bitmap createBackground() {
        int w = getScreenWidth();
        int h = getScreenHeight();
        Bitmap bg_pattern = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888); // Config.ARGB_888  means each pixel is stored on 4 bytes
        Canvas canvas = new Canvas(bg_pattern);
        canvas.drawColor(Color.BLACK);
        return bg_pattern;
    }

    public Rect createFrame(float frameRatio, float yratio) {
        int w = getScreenWidth();
        int h = getScreenHeight();
        int height = Math.round(yratio*h);
        int width = Math.round(height/frameRatio);
        int ymargin = Math.round((1-yratio)*h/2);
        int xmargin = Math.round((w - width)/2);
        return new Rect(xmargin, ymargin, xmargin + width, ymargin + height);

    }

    public Bitmap createFrameBackground(Rect frame) {
        Bitmap b = Bitmap.createBitmap(frame.width(), frame.height(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(b);
        drawCheckerBoard(BOBBLES_PER_FRAME_WIDTH, Colors.DARK_TILE, Colors.LIGHT_TILE, canvas);
        return b;
    }

    public void drawCheckerBoard(int numSquaresX, int color1, int color2, Canvas canvas) {
        // Clear the buffer with color
        Paint paint = new Paint();
        canvas.drawColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);

        int sideLength = Math.round((float)canvas.getWidth()/numSquaresX);
        int numSquaresY = canvas.getHeight()/sideLength + 1;

        // Draw the checker board
        int temp = color1;
        for (int row = 0; row < numSquaresY; row++) {
            for (int col = 0; col < numSquaresX; col++) {
                if (col % 2 == 0) {
                    paint.setColor(color1);
                }
                else {
                    paint.setColor(color2);
                }
                canvas.drawRect(col*sideLength, row*sideLength, col*sideLength + sideLength, row*sideLength + sideLength, paint);
            }
            // Swap starting color on new row.
            temp = color1;
            color1 = color2;
            color2 = temp;
        }
    } // drawCheckerBoard()


    public void initNextBobble() {
        nextBobble = getNextBobble();
        nextBobble.setAlive(true);
        nextBobble.setCollidable(false);
        nextBobble.setIdentifier(LOOSE);
        Point size = nextBobble.getSize();
        nextBobble.setPosition(new Vec2((frame.left + frame.centerX())/2, frame.bottom - 2*Bobble.getRADIUS()));
        addToGroup(nextBobble);

    }

    public void rotateCannon(Point touch) {
        Vec2 v_touch = (new Vec2(touch.x, touch.y)).minus(cannon.center);
        cannon.direction = v_touch.times(1/v_touch.mag());
    }


    public void loadCannon() {
        loadedBobble = nextBobble;
        Vec2 offset = new Vec2(loadedBobble.getSize()).times(-.5f);
        loadedBobble.setPosition(cannon.center.plus(offset));
        loadedBobble.setVelocity(new Vec2(0,0));
        initNextBobble();

    }

    public void fireCannon() {
        if (loadedBobble != null) {
            loadedBobble.setVelocity(cannon.direction.times(Game.LAUNCH_SPEED));
            loadedBobble.addAnimation(new ReboundBehavior(new RectF(frame), loadedBobble.getSize(), loadedBobble.getVelocity()));
            loadedBobble.setCollidable(true);
        }
    }

    public Bobble getNextBobble() {
        int color = BobbleColor.getRandom();
        return new Bobble(this, color);
    }

    public void moveBobbleOnTouch(Bobble testBobble) {
        int inputs = getTouchInputs();
        if (inputs > 0) {
            touch = getTouchPoint(0);
            {
                testBobble.position.x = touch.x - testBobble.getWidth()/2;
                testBobble.position.y = touch.y - 50;
            }
        }
    }

    public void initCompressor(Rect frame) {

    }




}
