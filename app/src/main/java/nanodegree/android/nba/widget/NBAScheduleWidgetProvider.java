package nanodegree.android.nba.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.List;

import nanodegree.android.nba.NBAApplication;
import nanodegree.android.nba.R;
import nanodegree.android.nba.rest.response.dailySchedule.DailySchedule;
import nanodegree.android.nba.rest.response.dailySchedule.Game;
import nanodegree.android.nba.ui.game.GameActivity;
import nanodegree.android.nba.utils.DisplayDateUtils;

/**
 * Implementation of App Widget functionality.
 */
public class NBAScheduleWidgetProvider extends AppWidgetProvider {

    public static void updateNBAScheduleWidgets(Context context, AppWidgetManager appWidgetManager,
                                                DailySchedule dailySchedule, int[] appWidgetIds) {
        for(int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, dailySchedule, appWidgetId);
        }
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                DailySchedule dailySchedule, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_grid_view);
        Intent intent = new Intent(context, GridWidgetService.class);
        views.setTextViewText(R.id.game_date, DisplayDateUtils.getTodayDate());
        views.setRemoteAdapter(R.id.widget_grid_view, intent);

        Intent appIntent = new Intent(context, GameActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.nba_schedule_widget, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //Start the intent service update widget action, the service takes care of updating the widgets UI
        NBAScheduleService.startActionGetNBASchedule(context);
    }

    private static String getShortName(String name) {
        String[] result = name.split(" ");
        return result[result.length-1];
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // Perform any action when the last AppWidget instance for this provider is deleted
    }
}

