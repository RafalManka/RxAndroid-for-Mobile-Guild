package rm.pl.rxandroid.utils;

import java.util.Random;

/**
 * Created by rafal on 8/23/16.
 */
public class RandomUtils {
    private static final int MAX = 5000;
    private static final int MIN = 1000;

    public static long makeRandom() {
        return new Random().nextInt(MAX) + MIN;
    }
}
