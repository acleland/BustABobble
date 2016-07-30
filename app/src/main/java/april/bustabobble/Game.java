package april.bustabobble;
import java.util.Random;
import android.graphics.*;
import android.renderscript.*;
import game.engine.*;


public class Game extends game.engine.Engine {
    Canvas canvas = null;
    Paint paint = null;
    Random rand = null;
    Point touch = null;
    int score = 0;
    Bitmap bg_pattern = null;
    Bobble testBobble = null;
    Bobble testBobble2 = null;

    public Game() {
        paint = new Paint();
        paint.setColor(Color.RED);
        rand = new Random();
    }

    /*
    Define Engine Abstract methods
     */
    public void init() {
        setScreenOrientation(ScreenModes.PORTRAIT);
        setFrameRate(60);
    }

    public void load() {
        float w = (float) getScreenWidth();
        float h = (float) getScreenHeight();
        createBackground();

        // Init testBobble1
        testBobble = new Bobble(this, Colors.TRANS_GREEN, new Float2(w/2, h/2));
        addToGroup(testBobble);

        // Init testBobble2
        testBobble2 = new Bobble(this, Colors.TRANS_PINK, new Float2(w/3, h/4));
        testBobble2.setVelocity(new Float2(4.0f,-6.0f));
        Point size = testBobble2.getSize();
        testBobble2.addAnimation(new ReboundBehavior(
                new RectF(0,0, w, h),
                size, testBobble2.getVelocity()) );
        addToGroup(testBobble2);

    }

    public void draw() {
        canvas = getCanvas();
        canvas.drawBitmap(bg_pattern, 0, 0, paint);

        drawText("SCORE" + toString(score), 0, 20);
    }

    public void update() {
        int inputs = getTouchInputs();
        if (inputs > 0) {
            touch = getTouchPoint(0);
            {
                testBobble.position.x = touch.x - testBobble.getWidth()/2;
                testBobble.position.y = touch.y - 50;
            }
        }
    }

    public void collision(Sprite sprite) {

    }

    /*
    Code for drawing the background
     */
    public void createBackground() {
        int w = getScreenWidth();
        int h = getScreenHeight();
        bg_pattern = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888); // Config.ARGB_888  means each pixel is stored on 4 bytes
        canvas = new Canvas(bg_pattern);
        paint.setAntiAlias(true);
        drawCheckerBoard(Colors.DARK_TILE, Colors.LIGHT_TILE, canvas, paint);
    }

    // Draw a b
    public void drawCheckerBoard(int color1, int color2, Canvas canvas, Paint paint) {
        //Paint paint = new Paint();

        // Clear the buffer with color
        canvas.drawColor(Color.WHITE);

        // Determine the number of squares on the board
        int NUM_SQUARES_X = 10;
        int NUM_SQUARES_Y = (int) ((float)canvas.getHeight()/canvas.getWidth()* NUM_SQUARES_X);
        canvas.drawText("NUM_SQUARES: " + NUM_SQUARES_X + ", " + NUM_SQUARES_Y, 10, 30, paint);

        // Determine square size
        int sideLength = Math.round((float) canvas.getWidth()/NUM_SQUARES_X);

        paint.setStyle(Paint.Style.FILL);


        // Draw the checker board
        int temp = color1;
        for (int row = 0; row < NUM_SQUARES_Y + 1; row++) {
            for (int col = 0; col < NUM_SQUARES_X; col++) {
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

    public static enum BobbleColor {
        RED(Colors.TRANS_RED),
        ORANGE(Colors.TRANS_ORANGE),
        YELLOW(Colors.TRANS_YELLOW),
        GREEN(Colors.TRANS_GREEN),
        BLUE(Colors.TRANS_BLUE),
        PURPLE(Colors.TRANS_PURPLE),
        PINK(Colors.TRANS_PINK);

        private int color;
        BobbleColor(int c) {
            this.color = c;
        }
    }

}
