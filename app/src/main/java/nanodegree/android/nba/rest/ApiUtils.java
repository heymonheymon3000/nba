package nanodegree.android.nba.rest;

public class ApiUtils {
    private static final String BASE_URL = "http://api.sportradar.us/";

    public static GameService getGameService() {
        return RetrofitClient.getClient(BASE_URL).create(GameService.class);
    }
}

