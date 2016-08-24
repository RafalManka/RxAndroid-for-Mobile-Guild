package rm.pl.rxandroid.utils;

import android.os.SystemClock;

/**
 * Created by rafal on 8/23/16.
 */
public class ClickUtils {

    private static final int TIME_BETWEEN_CLICKS = 500;
    private static long mLastClickTime;

    public static boolean isDoubleClick() {
        boolean isDoubleClick = SystemClock.elapsedRealtime() - mLastClickTime < TIME_BETWEEN_CLICKS;
        if (!isDoubleClick) {
            mLastClickTime = SystemClock.elapsedRealtime();
        }
        return isDoubleClick;
    }
}
