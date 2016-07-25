package april.bustabobble.game;

/**
 * Created by Andronius on 7/21/16.
 */

import java.math.BigDecimal;
import android.app.Activity;
import android.os.Bundle;
import android.renderscript.Float2;
import android.renderscript.Float3;
import android.content.pm.ActivityInfo;
import android.graphics.*;
import android.util.Log;
import android.view.*;
import android.view.View.OnTouchListener;

public abstract class Unfinished_Engine extends Activity implements Runnable, OnTouchListener {
    private SurfaceView view;
    private Canvas canvas;
    private Thread thread;
    private boolean running, paused;
    private int pauseCount;
    private Paint paintDraw, paintFont;
    private Typeface typeface;
    private Point[] touchPoints;
    private int numPoints;
    private long preferredFrameRate, sleepTime;

    /*
    Unfinished_Engine Constructor
     */

    public Unfinished_Engine() {
        Log.d("Unfinished_Engine", "Unfinished_Engine constructor");
        view = null;
        canvas = null;
        thread = null;
        running = false;
        paused = false;
        paintDraw = null;
        paintFont = null;
        numPoints = 0;
        typeface = null;
        preferredFrameRate = 40;
        sleepTime = 1000 / preferredFrameRate;
        pauseCount = 0;
    }

    /*
    Abstract methods to be implemented by subclasses
     */
    public abstract void init();
    public abstract void load();
    public abstract void draw();
    public abstract void update();

    /*
    Activity.onCreate event method
     */
    @Override void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Unfinished_Engine", "Unfinished_Engine.onCreate start");

        // Disable the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Set default screen orientation
        setScreenOrientation(ScreenModes.PORTRAIT);

        /*
        Call abstract init
         */
        init();

        // Create the view object
        view = new SurfaceView(this);
        setContentView(view);

        // Turn on touch listening
        view.setOnTouchListener(this);

        // Create the points array
        touchPoints = new Point[5];
        for (int i = 0; i < 5; i++) {
            touchPoints[i] = new Point(0,0);
        }

        // Create Paint objects for drawing and text
        paintDraw = new Paint();
        paintDraw.setColor(Color.WHITE);
        paintFont = new Paint();
        paintFont.setColor(Color.WHITE);
        paintFont.setTextSize(24);

        /*
        Call abstract load method in sub-class
         */
        load();

        // Launch the thread
        running = true;
        thread = new Thread(this);
        thread.start();

        Log.d("Unfinished_Engine", "Unfinished_Engine.onCreate end");
    } // end of onCreate()


    /*
    Runnable.run thread method (main loop)
     */
    @Override
    public void run() {
        Log.d("Unfinished_Engine", "Unfinished_Engine.run start");

        Timer frameTimer = new Timer();
        int frameCount = 0;
        int frameRate = 0;
        long startTime = 0;
        long timeDiff = 0;

        while (running) {
            if (paused) continue;

            // Compute frame rate
            frameCount++;
            startTime = frameTimer.getElapsed();
            if (frameTimer.stopwatch(1000)) {
                frameRate = frameCount;
                frameCount = 0;

                // Reset touch input count   (Why?)
                numPoints = 0;
            }

            /*
            Call update() defined in sub-class
             */
            update();

            /*
            Rendering. Lock the canvas. Only proceed if the surface view
            is valid.
             */
            if (beginDrawing()) {
                canvas.drawColor(Color.BLUE);

                // Call the abstract draw method defined in sub-class
                draw();

                // Complete rendering process by unlocking the canvas
                endDrawing();
            }
            timeDiff = frameTimer.getElapsed() - startTime;
            long updatePeriod = sleepTime - timeDiff;
            if (updatePeriod > 0) {
                try {
                    Thread.sleep(updatePeriod);
                } catch (InterruptedException e) {
                }
            }
        }
        Log.d("Unfinished_Engine", "Unfinished_Engine.run end");
        System.exit(RESULT_OK);
    }

    /*
    * BEGIN RENDERING.
    * Verify that the surface is valid and then lock the canvas.
     */
    private boolean beginDrawing() {

    }

    /*
    END RENDERING
    unlock canvas to free for future use
     */
    private void endDrawing() {

    }

    /*
    Activity.onResume event method
     */
    @Override
    public void onResume() {

    }

    /*
    Activity.onPause
     */
    @Override
    public void onPause() {

    }

    /*
    OnTouchListener.onTouch event method
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {

    }

    /*
    Shortcut methods to duplicate existing android methods
     */
    public void fatalError(String msg) {
        Log.e("FATAL ERROR", msg);
        System.exit(0);
    }

    /*
    Drawing helpers
     */
    public void drawText(String text, int x, int y) {
        canvas.drawText(text, x, y, paintFont);
    }

    /**
     * Unfinished_Engine helper get/set methods for private properties.
     */
    public SurfaceView getView() {
        return view;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setFrameRate(int rate) {
        preferredFrameRate = rate;
        sleepTime = 1000 / preferredFrameRate;
    }

    public int getTouchInputs() {
        return numPoints;
    }

    public Point getTouchPoint(int index) {
        if (index > numPoints)
            index = numPoints;
        return touchPoints[index];
    }

    public void setDrawColor(int color) {
        paintDraw.setColor(color);
    }

    public void setTextColor(int color) {
        paintFont.setColor(color);
    }

    public void setTextSize(int size) {
        paintFont.setTextSize((float)size);
    }

    public void setTextSize(float size) {
        paintFont.setTextSize(size);
    }

    /**
     * Font style helper
     */
    public enum FontStyles {
        NORMAL (Typeface.NORMAL),
        BOLD (Typeface.BOLD),
        ITALIC (Typeface.ITALIC),
        BOLD_ITALIC (Typeface.BOLD_ITALIC);
        int value;
        FontStyles(int type) {
            this.value = type;
        }
    }

    public void setTextStyle(FontStyles style) {

        typeface = Typeface.create(Typeface.DEFAULT, style.value);
        paintFont.setTypeface(typeface);
    }

    /**
     * Screen mode helper
     */
    public enum ScreenModes {
        LANDSCAPE (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE),
        PORTRAIT (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        int value;
        ScreenModes(int mode) {
            this.value = mode;
        }
    }
    public void setScreenOrientation(ScreenModes mode) {
        setRequestedOrientation(mode.value);
    }

    /**
     * Round to a default 2 decimal places
     */
    public double round(double value) {
        return round(value,2);
    }

    /**
     * Round to any number of decimal places
     */
    public double round(double value, int precision) {
        try {
            BigDecimal bd = new BigDecimal(value);
            BigDecimal rounded = bd.setScale(precision, BigDecimal.
                    ROUND_HALF_UP);
            return rounded.doubleValue();
        }
        catch (Exception e) {
            Log.e("Unfinished_Engine","round: error rounding number");
        }
        return 0;
    }

    /**
     * String conversion helpers
     */
    public String toString(int value) {
        return Integer.toString(value);
    }

    public String toString(float value) {
        return Float.toString(value);
    }

    public String toString(double value) {
        return Double.toString(value);
    }

    public String toString(Float2 value) {
        String s = "X:" + round(value.x) + "," +
                "Y:" + round(value.y);
        return s;
    }

    public String toString(Float3 value) {
        String s = "X:" + round(value.x) + "," +
                "Y:" + round(value.y) + "," +
                "Z:" + round(value.z);
        return s;
    }

}
