package april.bustabobble;

import android.app.Activity;
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
            screenSize = new Point(metrics.widthPixels, metrics.heightPixels);

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
            drawCheckerBoard(Colors.DARK_TILE, Colors.LIGHT_TILE, paint);
        }

        // Draw a b
        public void drawCheckerBoard(int color1, int color2, Paint paint) {
            //Paint paint = new Paint();

            // Clear the buffer with color
            bufferCanvas.drawColor(Color.WHITE);

            // Determine the number of squares on the board
            int NUM_SQUARES_X = 6;
            int NUM_SQUARES_Y = (int) ((float)bufferCanvas.getHeight()/bufferCanvas.getWidth()* NUM_SQUARES_X);
            bufferCanvas.drawText("NUM_SQUARES: " + NUM_SQUARES_X + ", " + NUM_SQUARES_Y, 10, 30, paint);

            // Determine square size
            int sideLength = Math.round((float) screenSize.x/NUM_SQUARES_X);

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
