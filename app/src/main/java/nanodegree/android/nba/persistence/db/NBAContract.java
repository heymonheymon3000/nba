package nanodegree.android.nba.persistence.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class NBAContract {
    public static final String AUTHORITY = "nanodegree.android.nba";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String HOME = "home";
    public static final String AWAY = "away";
    public static final String REBOUNDS = "rebounds";
    public static final String ASSISTS = "assists";
    public static final String POINTS = "points";

    private NBAContract() {}

    public static final class DailyScheduleEntry implements BaseColumns {
        public static final String TABLE_NAME = "daily_schedule_agg";
        public static final String COLUMN_ID = "id";
    }

    public static final class GameEntry implements BaseColumns {
        public static final String TABLE_NAME = "game_agg";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_DAILY_SCHEDULE_ID = "daily_schedule_id";
        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_SCHEDULED = "scheduled";
        public static final String COLUMN_AWAY_NAME = "away_name";
        public static final String COLUMN_AWAY_ALIAS = "away_alias";
        public static final String COLUMN_AWAY_POINTS = "away_points";
        public static final String COLUMN_HOME_NAME = "home_name";
        public static final String COLUMN_HOME_ALIAS = "home_alias";
        public static final String COLUMN_HOME_POINTS = "home_points";
        public static final String COLUMN_TIME_ON_CLOCK = "time_on_clock";
        public static final String COLUMN_BROADCAST = "broadcast";
    }

    public static final class FavoriteTeamEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorite_team";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ALIAS = "alias";
        public static final String COLUMN_SELECTED = "selected";
    }

    public static final class LeaderBoxScoreEntry implements BaseColumns {
        public static final String TABLE_NAME = "leader_box_score";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_GAME_ID = "game_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_LOCATION = "location";
        public static final String COLUMN_MINUTES = "minutes";
        public static final String COLUMN_ASSISTS = "assists";
        public static final String COLUMN_REBOUNDS = "rebounds";
        public static final String COLUMN_POINTS = "points";
        public static final String COLUMN_STAT = "stat";
    }
}

