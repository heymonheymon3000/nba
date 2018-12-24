package nanodegree.android.nba.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public final class DisplayDateUtils {
    private DisplayDateUtils() {}

    private static DateFormat dateFormat = new SimpleDateFormat("EEEE, MMM d", Locale.ENGLISH);
    public static String GAME = "GAME";
    public static String MY_GAME = "MY_GAME";
    private static Calendar gameCal = Calendar.getInstance();
    private static Calendar myGameCal = Calendar.getInstance();

    public static Calendar getCurrentDate(String gameType) {
        if(gameType.equals(GAME)) {
            return gameCal;
        } else {
            return myGameCal;
        }
    }

    public static String getTodayDate(String gameType) {
        if(gameType.equals(GAME)) {
            return dateFormat.format(gameCal.getTime());
        } else {
            return dateFormat.format(myGameCal.getTime());
        }
    }

    public static String getTomorrowDate(String gameType) {
        if(gameType.equals(GAME)) {
            gameCal.add(Calendar.DATE, 1);
            return dateFormat.format(gameCal.getTime());
        } else {
            myGameCal.add(Calendar.DATE, 1);
            return dateFormat.format(myGameCal.getTime());
        }
    }

    public static String getYesterdayDate(String gameType) {
        if(gameType.equals(GAME)) {
            gameCal.add(Calendar.DATE, -1);
            return dateFormat.format(gameCal.getTime());
        } else {
            myGameCal.add(Calendar.DATE, -1);
            return dateFormat.format(myGameCal.getTime());
        }
    }

    /**
     * Converts the given <code>date</code> from the <code>fromTimeZone</code> to the
     * <code>toTimeZone</code>.  Since java.util.Date has does not really store time zome
     * information, this actually converts the date to the date that it would be in the
     * other time zone.
     * @param date
     * @param fromTimeZone
     * @param toTimeZone
     * @return
     */
    public static Date convertTimeZone(Date date, TimeZone fromTimeZone, TimeZone toTimeZone)
    {
        long fromTimeZoneOffset = getTimeZoneUTCAndDSTOffset(date, fromTimeZone);
        long toTimeZoneOffset = getTimeZoneUTCAndDSTOffset(date, toTimeZone);

        return new Date(date.getTime() + (toTimeZoneOffset - fromTimeZoneOffset));
    }

    /**
     * Calculates the offset of the <code>timeZone</code> from UTC, factoring in any
     * additional offset due to the time zone being in daylight savings time as of
     * the given <code>date</code>.
     * @param date
     * @param timeZone
     * @return
     */
    private static long getTimeZoneUTCAndDSTOffset(Date date, TimeZone timeZone)
    {
        long timeZoneDSTOffset = 0;
        if(timeZone.inDaylightTime(date))
        {
            timeZoneDSTOffset = timeZone.getDSTSavings();
        }

        return timeZone.getRawOffset() + timeZoneDSTOffset;
    }
}
