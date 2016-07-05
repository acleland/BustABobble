package april.bustabobble;

import android.app.Activity;
import android.app.ActionBar;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.*;
import android.os.Bundle;
import android.util.*;
import android.view.*;
import java.util.Random;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new DrawView(this));
    }

    public class DrawView extends View {
        Bitmap bufferBitmap;
        Canvas bufferCanvas;
        Point screenSize;
        Random rand = new Random();


        public DrawView(Context context) {
            super(context);


            // Get the screen size before the main canvas is ready
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            screenSize = new Point(metrics.widthPixels, metrics.heightPixels-20);

            // Create the back buffer
            bufferBitmap = Bitmap.createBitmap(screenSize.x, screenSize.y, Bitmap.Config.ARGB_8888);
            bufferCanvas = new Canvas(bufferBitmap);
        }


        @Override
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // Fill the back buffer with graphics
            drawOnBuffer();

            // copy the back buffer to the screen
            canvas.drawBitmap(bufferBitmap, 0, 0, new Paint());

        } //onDraw()

        public void drawOnBuffer() {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            drawCheckerBoard(paint);
        }

        public void drawStuff(Canvas canvas, Paint paint, Random random) {
            // Draw some circles with random colors in random locations
            for (int i = 0; i < 20; i++) {
                paint.setColor(random.nextInt());
                canvas.drawCircle(Math.abs(100+random.nextInt())%400, 20+Math.abs(random.nextInt())%400, 50, paint);
            }

            //Draw a box using Canvas method
            Point p3 = new Point(50,50);
            Point p4 = new Point(200,200);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(p3.x, p3.y, p4.x, p4.y, paint);
        } //drawStuff()


        // Draw a b
        public void drawCheckerBoard(Paint paint) {
            //Paint paint = new Paint();

            // Clear the buffer with color
            bufferCanvas.drawColor(Color.WHITE);

            // Determine the number of squares on the board
            int NUM_SQUARES_X = 6;
            int NUM_SQUARES_Y = (int) ((float)screenSize.y/screenSize.x * NUM_SQUARES_X);
            bufferCanvas.drawText("NUM_SQUARES: " + NUM_SQUARES_X + ", " + NUM_SQUARES_Y, 10, 30, paint);

            // Determine square size
            int sideLength = Math.round((float) screenSize.x/NUM_SQUARES_X);

            // Set square colors and set to FILL
            int color1 = (Color.BLACK);
            int color2 = (Color.WHITE);
            paint.setStyle(Paint.Style.FILL);


            // Draw the checker board
            int temp = color1;
            for (int row = 0; row < NUM_SQUARES_Y; row++) {
                for (int col = 0; col < NUM_SQUARES_X; col++) {
                    if (col % 2 == 0) {
                        paint.setColor(color1);
                    }
                    else {
                        paint.setColor(color2);
                    }
                    bufferCanvas.drawRect(col*sideLength, row*sideLength, col*sideLength + sideLength, row*sideLength + sideLength, paint);
                }
                // Swap starting color on new row.
                temp = color1;
                color1 = color2;
                color2 = temp;
            }
        } // drawCheckerBoard()

    } //DrawView
} //MainActivity
