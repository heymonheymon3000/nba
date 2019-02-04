package nanodegree.android.nba.persistence.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.concurrent.Executors;

import nanodegree.android.nba.persistence.dao.DailyScheduleAggDao;
import nanodegree.android.nba.persistence.dao.FavoriteTeamDao;
import nanodegree.android.nba.persistence.dao.GameAggDao;
import nanodegree.android.nba.persistence.dao.LeaderBoxScoreDao;
import nanodegree.android.nba.persistence.entity.DailyScheduleAgg;
import nanodegree.android.nba.persistence.entity.FavoriteTeam;
import nanodegree.android.nba.persistence.entity.GameAgg;
import nanodegree.android.nba.persistence.entity.LeaderBoxScore;

@Database(entities = {DailyScheduleAgg.class, GameAgg.class,
        FavoriteTeam.class, LeaderBoxScore.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private final static String DB_NAME = "nba_game_schedule9.db";
    private volatile static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if(INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, DB_NAME)
                        .addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        getInstance(context).gameFavoriteTeamDao()
                                            .insertAll(FavoriteTeam.populateData());
                                    }
                                });
                            }
                        }
                    ).build();
                }
            }
        }

        return INSTANCE;
    }

    public abstract DailyScheduleAggDao dailyScheduleAggDao();
    public abstract GameAggDao gameAggDao();
    public abstract FavoriteTeamDao gameFavoriteTeamDao();
    public abstract LeaderBoxScoreDao leaderBoxScoreDao();
}
