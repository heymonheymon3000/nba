package nanodegree.android.nba.rest;

import io.reactivex.Single;
import nanodegree.android.nba.rest.response.boxScore.BoxScore;
import nanodegree.android.nba.rest.response.dailySchedule.DailySchedule;
import nanodegree.android.nba.rest.response.standing.Standing;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GameService {
    @GET("nba/trial/v5/{language_code}/games/{year}/{month}/{day}/schedule{format}")
    Single<DailySchedule> getGameScheduleByDate(
            @Path("language_code") String language_code,
            @Path("year") Integer year,
            @Path("month") Integer month,
            @Path("day") Integer day,
            @Path("format") String format,
            @Query("api_key") String api_key);;

    @GET("nba/trial/v5/{language_code}/games/{game_id}/boxscore{format}")
    Single<BoxScore> getBoxScore(
            @Path("language_code") String language_code,
            @Path("game_id") String game_id,
            @Path("format") String format,
            @Query("api_key") String api_key);

    @GET("nba/trial/v5/{language_code}/seasons/{year}/{season}/standings{format}")
    Single<Standing> getStanding(
            @Path("language_code") String language_code,
            @Path("year") Integer year,
            @Path("season") String season,
            @Path("format") String format,
            @Query("api_key") String api_key);
}
