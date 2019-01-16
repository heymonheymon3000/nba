package nanodegree.android.nba.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import nanodegree.android.nba.BuildConfig;
import nanodegree.android.nba.NBAApplication;
import nanodegree.android.nba.R;
import nanodegree.android.nba.rest.ApiUtils;
import nanodegree.android.nba.rest.response.dailySchedule.DailySchedule;
import nanodegree.android.nba.rest.response.dailySchedule.Game;
import nanodegree.android.nba.utils.DisplayDateUtils;
import nanodegree.android.nba.utils.Utils;

public class GridWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GridRemoteViewsFactory(this.getApplicationContext());
    }
}

class GridRemoteViewsFactory
        implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private static ArrayList<Game> games = new ArrayList<Game>();

    public GridRemoteViewsFactory(Context applicationContext) {
        this.context = applicationContext;
    }

    @Override
    public void onCreate() {}

    @Override
    public void onDataSetChanged() {
        games = getGames();
    }

    @Override
    public void onDestroy() {}

    @Override
    public int getCount() {
        if(games == null) return 0;
        return games.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if(games == null || games.size() == 0)  return null;
        Game game = games.get(position);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.nbaschedule_widget);

        views.setTextViewText(R.id.game_date, DisplayDateUtils.getTodayDate());

        views.setImageViewResource(R.id.home_team_logo_image_view,
                NBAApplication.teamInfoHashMap.get(games.get(position).getHome().getAlias()).getLogo());
        views.setTextViewText(R.id.home_team_logo_text_view,
                Utils.getShortName(games.get(position).getHome().getName()));
        views.setTextViewText(R.id.home_team_score_text_view,
                games.get(position).getHomePoints());

        views.setTextViewText(R.id.game_status_text_view,
                getGameStatus(games.get(position).getStatus(), game));

        views.setTextViewText(R.id.away_team_score_text_view,
                games.get(position).getAwayPoints());
        views.setImageViewResource(R.id.away_team_logo_image_view,
                NBAApplication.teamInfoHashMap.get(games.get(position).getAway().getAlias()).getLogo());
        views.setTextViewText(R.id.away_team_logo_text_view,
                Utils.getShortName(games.get(position).getAway().getName()));


        return views;
    }

    private ArrayList<Game> getGames() {
        Calendar todayCal = Calendar.getInstance();
        Integer year = todayCal.get(Calendar.YEAR);
        Integer month = todayCal.get(Calendar.MONTH)+1;
        Integer day = todayCal.get(Calendar.DATE);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DailySchedule dailySchedule = ApiUtils.getGameService()
                .getGameScheduleByDate("en",
                        year, month, day, ".json",
                        BuildConfig.NBA_DB_API_KEY).blockingGet();

        List<Game> games = dailySchedule.getGames();
        ArrayList<Game> returnGames = new ArrayList<>();
        for(Game game : games) {
            returnGames.add(game);
        }

        return returnGames;
    }

    private String getGameStatus(String status, Game game) {
        if(context.getString(R.string.closed).equals(status)) {
            return context.getString(R.string.finish);
        } else if(context.getString(R.string.scheduled).equals(status)) {
            return getGameStartTime(game);
        } else if(context.getString(R.string.inprogress).equals(status)) {
            return null;
        }

        return null;
    }

    private String getGameStartTime(Game game) {
        DateFormat dateFormat =
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX",
                        Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone(context.getString(R.string.gmt)));
        Date date = null;
        try {
            date = dateFormat.parse(game.getScheduled());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat d =
                new SimpleDateFormat(context.getString(
                        R.string.display_date_pattern), Locale.ENGLISH);
        d.setTimeZone(TimeZone.getTimeZone(context.getString(R.string.est)));
        return d.format(date) + " " + context.getString(R.string.et);
    }

    @Override
    public RemoteViews getLoadingView() { return null; }

    @Override
    public int getViewTypeCount() { return 1; }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public boolean hasStableIds() { return false; }
}
