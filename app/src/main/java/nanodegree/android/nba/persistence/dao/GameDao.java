package nanodegree.android.nba.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import nanodegree.android.nba.persistence.db.NBAContract;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import nanodegree.android.nba.persistence.entity.Game;

@Dao
public interface GameDao {
    // CREATE
    @Insert(onConflict = REPLACE)
    long insert(Game game);

    @Insert(onConflict = REPLACE)
    long[] insertAll(Game[] games);

    // READ
    @Query("SELECT COUNT(*) FROM " + NBAContract.GameEntry.TABLE_NAME)
    int count();

    // UPDATE
    @Update(onConflict = REPLACE)
    int update(Game game);

    // DELETE
    @Query("DELETE FROM " + NBAContract.GameEntry.TABLE_NAME + " WHERE "
            + NBAContract.GameEntry.COLUMN_ID + " = :id")
    int deleteById(long id);
}
