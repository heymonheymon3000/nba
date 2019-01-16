package nanodegree.android.nba.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

import nanodegree.android.nba.BuildConfig;
import nanodegree.android.nba.R;
import nanodegree.android.nba.rest.ApiUtils;
import nanodegree.android.nba.rest.response.dailySchedule.DailySchedule;

public class NBAScheduleService extends IntentService {
    public static final String ACTION_UPDATE_NBA_SCHEDULE_WIDGETS =
            "nanodegree.android.nba.widget.action.update.nba.schedule.widgets";

    public NBAScheduleService() {
        super("NBAScheduleService");
    }

    public static void startActionGetNBASchedule(Context context) {
        Intent intent = new Intent(context, NBAScheduleService.class);
        intent.setAction(ACTION_UPDATE_NBA_SCHEDULE_WIDGETS);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(intent != null) {
            final String action = intent.getAction();
            if(ACTION_UPDATE_NBA_SCHEDULE_WIDGETS.equals(action)) {
                handleActionUpdateNBAScheduleWidgets();
            }
        }
    }

    private void handleActionUpdateNBAScheduleWidgets() {
        Calendar todayCal = Calendar.getInstance();
        Integer year = todayCal.get(Calendar.YEAR);
        Integer month = todayCal.get(Calendar.MONTH)+1;
        Integer day = todayCal.get(Calendar.DATE);
        DailySchedule dailySchedule = null;

        try {
            Thread.sleep(1000);
            dailySchedule = ApiUtils.getGameService()
                    .getGameScheduleByDate("en",
                            year, month, day, ".json",
                            BuildConfig.NBA_DB_API_KEY).blockingGet();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    getApplicationContext().getString(R.string.no_connection),
                    Toast.LENGTH_LONG).show();
        }

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(
                new ComponentName(this, NBAScheduleWidgetProvider.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_grid_view);

        NBAScheduleWidgetProvider.updateNBAScheduleWidgets(this, appWidgetManager,
                dailySchedule, appWidgetIds);
    }
}
