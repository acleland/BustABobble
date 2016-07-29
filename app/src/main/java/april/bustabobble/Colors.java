package april.bustabobble;
import android.graphics.Color;

public class Colors {
	// Transparency setting
    public static final int ALPHA = 200;

    // Solid colors for background
    public static final int CREAMY = Color.argb(255, 255, 241, 224);
	public static final int LIGHT_GRAY = Color.argb(255, 200, 200, 200);
	public static final int PINK = Color.argb(255, 255, 0, 150);
	public static final int DARK_TILE = LIGHT_GRAY;
    public static final int LIGHT_TILE = Color.WHITE;

    // Bobble Colors (transparent)
    public static final int TRANS_YELLOW = Color.argb(ALPHA, 255, 255, 0);
    public static final int TRANS_PINK = Color.argb(ALPHA, 255, 0, 150);
    public static final int TRANS_BLUE =  Color.argb(ALPHA, 0, 0, 255);
    public static final int TRANS_GREEN = Color.argb(ALPHA, 0, 255, 0);
    public static final int TRANS_PURPLE = Color.argb(ALPHA, 255, 0, 255);
    public static final int TRANS_ORANGE = Color.argb(ALPHA, 255, 150, 0);
    public static final int TRANS_RED = Color.argb(ALPHA, 255, 0 , 0);
}