package nanodegree.android.nba.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import nanodegree.android.nba.persistence.db.NBAContract;
import nanodegree.android.nba.persistence.entity.FavoriteTeam;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface FavoriteTeamDao {
    @Insert(onConflict = REPLACE)
    long[] insertAll(FavoriteTeam... favoriteTeam);

    @Update(onConflict = REPLACE)
    int update(FavoriteTeam favoriteTeam);

    @Query("SELECT * FROM " + NBAContract.FavoriteTeamEntry.TABLE_NAME +" ORDER BY " +
            NBAContract.FavoriteTeamEntry.COLUMN_NAME)
    List<FavoriteTeam> getAllFavoriteTeams();

    @Query("SELECT * FROM " + NBAContract.FavoriteTeamEntry.TABLE_NAME +
    " WHERE " + NBAContract.FavoriteTeamEntry.COLUMN_SELECTED + " == :selected")
    List<FavoriteTeam> getAllSelectedFavoriteTeams(Boolean selected);
}
