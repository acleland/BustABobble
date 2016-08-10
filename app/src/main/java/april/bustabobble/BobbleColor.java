package april.bustabobble;
import java.util.Random;
/**
 * Copyright (c) 2016 Andrew Cleland.
 */
public enum BobbleColor {
    RED(Colors.TRANS_RED),
    ORANGE(Colors.TRANS_ORANGE),
    YELLOW(Colors.TRANS_YELLOW),
    GREEN(Colors.TRANS_GREEN),
    BLUE(Colors.TRANS_BLUE);

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

