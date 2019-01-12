package nanodegree.android.nba.persistence.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import nanodegree.android.nba.persistence.db.NBAContract;


@Entity(tableName = NBAContract.LeaderBoxScoreEntry.TABLE_NAME,
        foreignKeys = @ForeignKey(entity = GameAgg.class,
                parentColumns = NBAContract.GameEntry.COLUMN_ID,
                childColumns = NBAContract.LeaderBoxScoreEntry.COLUMN_GAME_ID))
public class LeaderBoxScore implements Parcelable {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = NBAContract.LeaderBoxScoreEntry.COLUMN_ID)
    private long id;

    @ColumnInfo(index = true, name = NBAContract.LeaderBoxScoreEntry.COLUMN_GAME_ID)
    private String gameId;

    @ColumnInfo(index = true, name = NBAContract.LeaderBoxScoreEntry.COLUMN_LOCATION)
    private String location;

    @ColumnInfo(name = NBAContract.LeaderBoxScoreEntry.COLUMN_NAME)
    private String fullName;

    @ColumnInfo(name = NBAContract.LeaderBoxScoreEntry.COLUMN_MINUTES)
    private String minutes;

    @ColumnInfo(name = NBAContract.LeaderBoxScoreEntry.COLUMN_POINTS)
    private String points;

    @ColumnInfo(name = NBAContract.LeaderBoxScoreEntry.COLUMN_REBOUNDS)
    private String rebounds;

    @ColumnInfo(name = NBAContract.LeaderBoxScoreEntry.COLUMN_ASSISTS)
    private String assists;

    @ColumnInfo(name = NBAContract.LeaderBoxScoreEntry.COLUMN_STAT)
    private String stat;

    public LeaderBoxScore() {}

    @Ignore
    public LeaderBoxScore(String gameId, String location, String stat, String fullName,
                          String minutes, String points, String rebounds, String assists) {
        this.gameId = gameId;
        this.location = location;
        this.stat = stat;
        this.fullName = fullName;
        this.minutes = minutes;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
    }

    public LeaderBoxScore(Parcel in) {
        id = in.readLong();
        gameId = in.readString();
        location = in.readString();
        stat = in.readString();
        fullName = in.readString();
        minutes = in.readString();
        points = in.readString();
        rebounds = in.readString();
        assists = in.readString();
    }

    public static final Creator<LeaderBoxScore> CREATOR = new Creator<LeaderBoxScore>() {

        @Override
        public LeaderBoxScore createFromParcel(Parcel source) {
            return new LeaderBoxScore(source);
        }

        @Override
        public LeaderBoxScore[] newArray(int size) {
            return new LeaderBoxScore[size];
        }
    };

    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getRebounds() {
        return rebounds;
    }

    public void setRebounds(String rebounds) {
        this.rebounds = rebounds;
    }

    public String getAssists() {
        return assists;
    }

    public void setAssists(String assists) {
        this.assists = assists;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(gameId);
        dest.writeString(location);
        dest.writeString(stat);
        dest.writeString(fullName);
        dest.writeString(minutes);
        dest.writeString(points);
        dest.writeString(rebounds);
        dest.writeString(assists);
    }

    @Override
    public String toString() {
        return "LeaderBoxScore{" +
                "id=" + id +
                ", gameId='" + gameId + '\'' +
                ", location='" + location + '\'' +
                ", fullName='" + fullName + '\'' +
                ", minutes='" + minutes + '\'' +
                ", points='" + points + '\'' +
                ", rebounds='" + rebounds + '\'' +
                ", assists='" + assists + '\'' +
                ", stat='" + stat + '\'' +
                '}';
    }
}
