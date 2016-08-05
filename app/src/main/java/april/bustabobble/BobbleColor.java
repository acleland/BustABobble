package april.bustabobble;
import java.util.Random;
/**
 * Created by Andronius on 8/4/16.
 */
public enum BobbleColor {
    RED(Colors.TRANS_RED),
    ORANGE(Colors.TRANS_ORANGE),
    YELLOW(Colors.TRANS_YELLOW),
    GREEN(Colors.TRANS_GREEN),
    BLUE(Colors.TRANS_BLUE),
    PURPLE(Colors.TRANS_PURPLE),
    PINK(Colors.TRANS_PINK);

    private int colorValue;
    private static final BobbleColor[] VALUES = values();
    public static final int SIZE = VALUES.length;
    private static Random random = new Random();
    BobbleColor(int c) {
        this.colorValue = c;
    }

    public int getValue() {
        return this.colorValue;
    }

    public static int getRandom() {
        return BobbleColor.VALUES[random.nextInt(BobbleColor.SIZE)].colorValue;
    }

}

