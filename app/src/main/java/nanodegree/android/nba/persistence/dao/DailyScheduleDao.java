package nanodegree.android.nba.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;
import android.arch.persistence.room.Query;


import nanodegree.android.nba.persistence.db.NBAContract;
import nanodegree.android.nba.persistence.entity.DailyScheduleAgg;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface DailyScheduleDao {
    // CREATE
    @Insert(onConflict = REPLACE)
    long insert(DailyScheduleAgg dailySchedule);

    @Insert(onConflict = REPLACE)
    long[] insertAll(DailyScheduleAgg[] dailySchedules);

    // READ
    @Query("SELECT COUNT(*) FROM " + NBAContract.DailyScheduleEntry.TABLE_NAME)
    int count();

    // UPDATE
    @Update(onConflict = REPLACE)
    int update(DailyScheduleAgg dailySchedule);

    // DELETE
    @Query("DELETE FROM " + NBAContract.DailyScheduleEntry.TABLE_NAME + " WHERE "
            + NBAContract.DailyScheduleEntry.COLUMN_ID + " = :id")
    int deleteById(long id);
}
