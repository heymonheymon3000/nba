package nanodegree.android.nba.persistence.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import nanodegree.android.nba.persistence.db.NBAContract;
import nanodegree.android.nba.persistence.entity.DailyScheduleAgg;

@Dao
public interface DailyScheduleAggDao {
    @Insert
    long insert(DailyScheduleAgg dailyScheduleAgg);

    @Query("SELECT * FROM " + NBAContract.DailyScheduleEntry.TABLE_NAME + " WHERE "
            + NBAContract.DailyScheduleEntry.COLUMN_ID + " = :id")
    LiveData<DailyScheduleAgg> getDailyScheduleAggById(String id);
}
