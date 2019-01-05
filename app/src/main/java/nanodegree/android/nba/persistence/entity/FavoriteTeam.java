package nanodegree.android.nba.persistence.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import nanodegree.android.nba.NBAApplication;
import nanodegree.android.nba.persistence.db.NBAContract;
import nanodegree.android.nba.utils.TeamInfo;

@Entity(tableName = NBAContract.FavoriteTeamEntry.TABLE_NAME)
public class FavoriteTeam implements Parcelable {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = NBAContract.FavoriteTeamEntry.COLUMN_ID)
    private long id;

    @ColumnInfo(index = true, name = NBAContract.FavoriteTeamEntry.COLUMN_NAME)
    private String name;

    @ColumnInfo(index = true, name = NBAContract.FavoriteTeamEntry.COLUMN_ALIAS)
    private String alias;

    @ColumnInfo(name = NBAContract.FavoriteTeamEntry.COLUMN_SELECTED)
    private Boolean selected;

    public FavoriteTeam() {}

    @Ignore
    public FavoriteTeam(String name, String alias, Boolean selected) {
        this.name = name;
        this.alias = alias;
        this.selected = selected;
    }

    public FavoriteTeam(Parcel in) {
        id = in.readLong();
        name = in.readString();
        alias = in.readString();
        selected = in.readByte() != 0;
    }

    public static final Creator<FavoriteTeam> CREATOR = new Creator<FavoriteTeam>() {
        @Override
        public FavoriteTeam createFromParcel(Parcel in) {
            return new FavoriteTeam(in);
        }

        @Override
        public FavoriteTeam[] newArray(int size) {
            return new FavoriteTeam[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(alias);
        dest.writeByte((byte) (selected ? 1 : 0));
    }

    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteTeam that = (FavoriteTeam) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(alias, that.alias) &&
                Objects.equals(selected, that.selected);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, alias, selected);
    }

    public static FavoriteTeam[] populateData() {
        HashMap<String, TeamInfo> teamInfoHashMap = NBAApplication.teamInfoHashMap;
        FavoriteTeam[] favoriteTeams = new FavoriteTeam[teamInfoHashMap.size()];
        int index = 0;
        for (String key : teamInfoHashMap.keySet()) {
            String alias = key;
            TeamInfo teamInfo = teamInfoHashMap.get(key);
            favoriteTeams[index] = new FavoriteTeam(teamInfo.getName(), alias, false);
            index++;
        }

        return favoriteTeams;
    }
}
