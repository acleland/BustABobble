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
            screenSize = new Point(metrics.widthPixels-20, metrics.heightPixels-20);

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

            // Clear the buffer with color
            bufferCanvas.drawColor(Color.WHITE );

            // Draw random circles
            for (int n=0; n<500; n++) {
                // Make random color
                int r = rand.nextInt(256);
                int g = rand.nextInt(256);
                int b = rand.nextInt(256);
                paint.setColor(Color.rgb(r, g, b));

                // Make a random position and radius
                int x = rand.nextInt(bufferCanvas.getWidth());
                int y = rand.nextInt(bufferCanvas.getHeight());
                int radius = rand.nextInt(100) +20;

                // Draw one circle
                bufferCanvas.drawCircle(x, y, radius, paint);

            }
        }
        public void displayMetricsDemo(Canvas canvas, Paint paint, DisplayMetrics dm) {
            // Get canvas resolution
            int width = canvas.getWidth();
            int height = canvas.getHeight();

            // Print out screen details
            int x = 10, y = 30, step = 36;
            canvas.drawText("Density: " + dm.density, x, y, paint);
            y += step;
            canvas.drawText("Scaled density: " + dm.scaledDensity, x, y, paint);
            y += step;
            canvas.drawText("Width " + width, x, y, paint);
            y += step;
            canvas.drawText("Height " + height, x, y, paint);
            y += step;
            canvas.drawText("X dpi: " + dm.xdpi, x, y, paint);
            y += step;
            canvas.drawText("Y dpi: " + dm.ydpi, x, y, paint);

        } // displayMetricsDemo()



        public void drawStuff(Canvas canvas, Paint paint, Random random) {
            // Draw some circles with random colors in random locations
            for (int i = 0; i < 20; i++) {
                paint.setColor(random.nextInt());
                canvas.drawCircle(Math.abs(100+random.nextInt())%400, 20+Math.abs(random.nextInt())%400, 50, paint);
            }

            // Draw a line
            paint.setColor(Color.BLACK);
            canvas.drawLine(0,0,400,400, paint);

            // Draw a box using the method definied here.
            Point p1 = new Point(20,20);
            Point p2 = new Point(100,100);
            this.drawBox(canvas, p1, p2, paint);

            //Draw a box using Canvas method
            Point p3 = new Point(50,50);
            Point p4 = new Point(200,200);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(p3.x, p3.y, p4.x, p4.y, paint);
        } //drawStuff()

        // Method for drawing box (superfluous b/c drawRect is already a method in Canvas)
        public void drawBox(Canvas canvas, Point p1, Point p2, Paint paint) {
            canvas.drawLine(p1.x, p1.y, p1.x, p2.y, paint);
            canvas.drawLine(p1.x, p1.y, p2.x, p1.y, paint);
            canvas.drawLine(p1.x, p2.y, p2.x, p2.y, paint);
            canvas.drawLine(p2.x, p1.y, p2.x, p2.y, paint);
        }

        // Method for bitmap demo
        public void bitmapStuff() {

        }
    } //DrawView
} //MainActivity
