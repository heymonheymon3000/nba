package nanodegree.android.nba.persistence.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import nanodegree.android.nba.persistence.db.NBAContract;
import nanodegree.android.nba.persistence.entity.GameAgg;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface GameAggDao {
    @Insert(onConflict = REPLACE)
    long insert(GameAgg game);

    @Query("SELECT * FROM " + NBAContract.GameEntry.TABLE_NAME + " WHERE "
            + NBAContract.GameEntry.COLUMN_DAILY_SCHEDULE_ID + " = :id")
    List<GameAgg> getGameAggByDailyScheduleId(String id);

    @Query("SELECT * FROM " + NBAContract.GameEntry.TABLE_NAME + " WHERE "
            + NBAContract.GameEntry.COLUMN_DAILY_SCHEDULE_ID + " = :id")
    LiveData<List<GameAgg>> getGameAggByDailyScheduleId1(String id);
}