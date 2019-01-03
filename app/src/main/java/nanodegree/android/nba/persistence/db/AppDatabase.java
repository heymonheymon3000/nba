package nanodegree.android.nba.persistence.db;

import android.arch.persistence.room.Database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import nanodegree.android.nba.persistence.dao.DailyScheduleDao;
import nanodegree.android.nba.persistence.dao.GameDao;
import nanodegree.android.nba.persistence.entity.DailyScheduleAgg;
import nanodegree.android.nba.persistence.entity.GameAgg;

@Database(entities = {DailyScheduleAgg.class, GameAgg.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private volatile static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if(INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "nba_schedule.db").build();
                }
            }
        }

        return INSTANCE;
    }

    public abstract DailyScheduleDao dailyScheduleDao();

    public abstract GameDao gameDao();
}
