package nanodegree.android.nba.persistence.db;

import android.provider.BaseColumns;

public class GameContract {

    private GameContract(){}

    public static final class GameEntry implements BaseColumns {
        public static final String TABLE_NAME = "game";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_GAME_STATUS = "status";

        public static final String COLUMN_HOME_IMAGE = "home_team_image";
        public static final String COLUMN_HOME_NAME = "home_team_name";
        public static final String COLUMN_HOME_RECORD = "home_team_record";
        public static final String  COLUMN_HOME_TEAM_SCORE = "home_team_score";


        public static final String COLUMN_AWAY_IMAGE = "away_team_image";
        public static final String COLUMN_AWAY_NAME = "away_team_name";
        public static final String COLUMN_AWAY_RECORD = "away_team_record";
        public static final String  COLUMN_AWAY_TEAM_SCORE = "away_team_score";



    }
}
