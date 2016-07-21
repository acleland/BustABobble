package april.bustabobble.game;

/**
 * Created by Andronius on 7/21/16.
 */

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class TextPrinter {
    private Canvas p_canvas;
    private Paint p_paint;
    private float p_x, p_y;
    private float p_spacing;

    public TextPrinter() {
        this(null);
    }

    public TextPrinter(Canvas canvas) {
        p_canvas = canvas;
        p_paint = new Paint();
        p_x = p_y = 0;
        p_spacing = 22;
        setTextSize(18);
        setColor(Color.WHITE);
    }

    public void setCanvas(Canvas canvas) {
        p_canvas = canvas;
    }

    public void setLineSpacing(float spacing) {
        p_spacing = spacing;
    }

    public void setTextSize(float size) {
        p_paint.setTextSize(size);
    }

    public void setColor(int color) {
        p_paint.setColor(color);
    }

    public void draw(String text, float x, float y) {
        p_x = x;
        p_y = y;
        draw(text);
    }

    public void draw(String text) {
        p_canvas.drawText(text, p_x, p_y,  p_paint);
        p_y += p_spacing;
    }
}
