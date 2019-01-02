package nanodegree.android.nba.persistence.source.remote;


import nanodegree.android.nba.rest.response.dailySchedule.DailySchedule;

public interface RemoteSource {
    void getGames(RemoteResponseListener<DailySchedule> responseListener);
}
