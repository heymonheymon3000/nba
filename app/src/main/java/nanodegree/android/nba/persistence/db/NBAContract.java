package nanodegree.android.nba.persistence.db;

import android.arch.persistence.room.ColumnInfo;
import android.net.Uri;
import android.provider.BaseColumns;

public class NBAContract {
    public static final String AUTHORITY = "nanodegree.android.nba";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    private NBAContract() {}

    public static final class DailyScheduleEntry implements BaseColumns {
        public static final String TABLE_NAME  = "daily_schedule_agg";
        public static final String COLUMN_ID   = "id";
        public static final String COLUMN_DATE = "date";
    }

    public static final class GameEntry implements BaseColumns {
        public static final String TABLE_NAME               = "game_agg";
        public static final String COLUMN_ID                = "id";
        public static final String COLUMN_DAILY_SCHEDULE_ID = "daily_schedule_id";
        public static final String COLUMN_STATUS            = "status";
        public static final String COLUMN_SCHEDULED         = "scheduled";
        public static final String COLUMN_AWAY_NAME         = "away_name";
        public static final String COLUMN_AWAY_ALIAS        = "away_alias";
        public static final String COLUMN_AWAY_POINTS       = "away_points";
        public static final String COLUMN_AWAY_RECORD       = "away_record";
        public static final String COLUMN_HOME_NAME         = "home_name";
        public static final String COLUMN_HOME_ALIAS        = "home_alias";
        public static final String COLUMN_HOME_POINTS       = "home_points";
        public static final String COLUMN_HOME_RECORD       = "home_record";
        public static final String COLUMN_TIME_ON_CLOCK     = "time_on_clock";
        public static final String COLUMN_BROADCAST         = "broadcast";

    }
}

