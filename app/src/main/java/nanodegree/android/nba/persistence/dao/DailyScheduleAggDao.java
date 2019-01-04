package nanodegree.android.nba.persistence.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import nanodegree.android.nba.persistence.db.NBAContract;
import nanodegree.android.nba.persistence.entity.DailyScheduleAgg;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface DailyScheduleAggDao {
    @Query("SELECT * FROM " + NBAContract.DailyScheduleEntry.TABLE_NAME + " WHERE "
            + NBAContract.DailyScheduleEntry.COLUMN_DATE + " = :date")
    DailyScheduleAgg getDailyScheduleAggWithDate(String date);

    @Insert
    public void insert(DailyScheduleAgg... dailyScheduleAggs);
}








    // CREATE
//    @Insert(onConflict = REPLACE)
//    long insert(DailyScheduleAgg dailySchedule);
//
//    @Insert(onConflict = REPLACE)
//    long[] insertAll(DailyScheduleAgg[] dailySchedules);
//
//    // READ
//    @Query("SELECT COUNT(*) FROM " + NBAContract.DailyScheduleEntry.TABLE_NAME)
//    int count();





//    // UPDATE
//    @Update(onConflict = REPLACE)
//    int update(DailyScheduleAgg dailySchedule);
//
//    // DELETE
//    @Query("DELETE FROM " + NBAContract.DailyScheduleEntry.TABLE_NAME + " WHERE "
//            + NBAContract.DailyScheduleEntry.COLUMN_ID + " = :id")
//    int deleteById(long id);
//}
