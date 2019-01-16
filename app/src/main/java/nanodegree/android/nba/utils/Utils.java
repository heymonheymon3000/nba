package nanodegree.android.nba.utils;

public class Utils {
    public static String getShortName(String name) {
        String[] result = name.split(" ");
        return result[result.length-1];
    }
}
