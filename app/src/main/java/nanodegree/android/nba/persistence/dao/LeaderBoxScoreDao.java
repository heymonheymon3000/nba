package nanodegree.android.nba.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import nanodegree.android.nba.persistence.db.NBAContract;
import nanodegree.android.nba.persistence.entity.LeaderBoxScore;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface LeaderBoxScoreDao {
    @Insert(onConflict = REPLACE)
    long insert(LeaderBoxScore leaderBoxScore);

    @Query("SELECT * FROM " + NBAContract.LeaderBoxScoreEntry.TABLE_NAME + " WHERE "
            + NBAContract.LeaderBoxScoreEntry.COLUMN_GAME_ID + " = :gameId" +
            " AND " + NBAContract.LeaderBoxScoreEntry.COLUMN_LOCATION + " = :location" +
            " AND " + NBAContract.LeaderBoxScoreEntry.COLUMN_STAT + " = :stat")
    LeaderBoxScore getLeaderBoxScore(String gameId, String location, String stat);
}
