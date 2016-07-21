package april.bustabobble.game;

/**
 * Created by Andronius on 7/21/16.
 */
public class Timer {
    private long p_start;
    private long p_stopwatchStart;

    public Timer() {
        p_start = System.currentTimeMillis();
        p_stopwatchStart = 0;
    }

    public long getElapsed() {
        return System.currentTimeMillis() - p_start;
    }

    public void rest(int ms) {
        long start = getElapsed();
        while (start + ms > getElapsed()) {
            try {
                Thread.sleep(1); // sleep 1 ms
            } catch (InterruptedException e) {}
        }
    }

    public void resetStopwatch() {
        p_stopwatchStart = getElapsed();
    }

    public boolean stopwatch(long ms) {
        if (getElapsed() > p_stopwatchStart + ms) {
            resetStopwatch();
            return true;
        }
        else
            return false;
    }
}
