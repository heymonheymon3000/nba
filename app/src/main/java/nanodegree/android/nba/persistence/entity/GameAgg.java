package nanodegree.android.nba.persistence.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Objects;

import nanodegree.android.nba.persistence.db.NBAContract;

@Entity(tableName = NBAContract.GameEntry.TABLE_NAME,
        foreignKeys = @ForeignKey(entity = DailyScheduleAgg.class,
                parentColumns = NBAContract.DailyScheduleEntry.COLUMN_ID,
                childColumns = NBAContract.GameEntry.COLUMN_DAILY_SCHEDULE_ID))
public class GameAgg implements Parcelable {
    @NonNull
    @PrimaryKey
    @ColumnInfo(index = true, name = NBAContract.GameEntry.COLUMN_ID)
    private String id;

    @ColumnInfo(index = true, name = NBAContract.GameEntry.COLUMN_DAILY_SCHEDULE_ID)
    private String dailyScheduleDate;

    @ColumnInfo(name = NBAContract.GameEntry.COLUMN_STATUS)
    private String status;

    @ColumnInfo(name = NBAContract.GameEntry.COLUMN_SCHEDULED)
    private String scheduled;

    @ColumnInfo(name = NBAContract.GameEntry.COLUMN_AWAY_NAME)
    private String awayName;

    @ColumnInfo(name = NBAContract.GameEntry.COLUMN_AWAY_ALIAS)
    private String awayAlias;

    @ColumnInfo(name = NBAContract.GameEntry.COLUMN_AWAY_POINTS)
    private String awayPoints;

    @ColumnInfo(name = NBAContract.GameEntry.COLUMN_HOME_NAME)
    private String homeName;

    @ColumnInfo(name = NBAContract.GameEntry.COLUMN_HOME_ALIAS)
    private String homeAlias;

    @ColumnInfo(name = NBAContract.GameEntry.COLUMN_HOME_POINTS)
    private String homePoints;

    @ColumnInfo(name = NBAContract.GameEntry.COLUMN_TIME_ON_CLOCK)
    private String timeOnClock;

    @ColumnInfo(name = NBAContract.GameEntry.COLUMN_BROADCAST)
    private String broadcast;

    @Ignore
    private LeaderBoxScore homeLeaderAssists = null;

    @Ignore
    private LeaderBoxScore homeLeaderRebounds = null;

    @Ignore
    private LeaderBoxScore homeLeaderPoints = null;

    @Ignore
    private LeaderBoxScore awayLeaderAssists = null;

    @Ignore
    private LeaderBoxScore awayLeaderRebounds = null;

    @Ignore
    private LeaderBoxScore awayLeaderPoints = null;

    public GameAgg() {}

    private GameAgg(Parcel in) {
        homeLeaderAssists = in.readParcelable(LeaderBoxScore.class.getClassLoader());
        homeLeaderRebounds =  in.readParcelable(LeaderBoxScore.class.getClassLoader());
        homeLeaderPoints = in.readParcelable(LeaderBoxScore.class.getClassLoader());
        awayLeaderAssists = in.readParcelable(LeaderBoxScore.class.getClassLoader());
        awayLeaderRebounds = in.readParcelable(LeaderBoxScore.class.getClassLoader());
        awayLeaderPoints = in.readParcelable(LeaderBoxScore.class.getClassLoader());
        id = in.readString();
        dailyScheduleDate = in.readString();
        status = in.readString();
        scheduled = in.readString();
        awayName = in.readString();
        awayAlias = in.readString();
        awayPoints = in.readString();
        homeName = in.readString();
        homeAlias = in.readString();
        homePoints = in.readString();
        timeOnClock = in.readString();
        broadcast = in.readString();
    }

    public static final Creator<GameAgg> CREATOR = new Creator<GameAgg>() {

        @Override
        public GameAgg createFromParcel(Parcel source) {
            return new GameAgg(source);
        }

        @Override
        public GameAgg[] newArray(int size) {
            return new GameAgg[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(homeLeaderAssists, flags);
        dest.writeParcelable(homeLeaderRebounds, flags);
        dest.writeParcelable(homeLeaderPoints, flags);
        dest.writeParcelable(awayLeaderAssists, flags);
        dest.writeParcelable(awayLeaderRebounds, flags);
        dest.writeParcelable(awayLeaderPoints, flags);
        dest.writeString(id);
        dest.writeString(dailyScheduleDate);
        dest.writeString(status);
        dest.writeString(scheduled);
        dest.writeString(awayName);
        dest.writeString(awayAlias);
        dest.writeString(awayPoints);
        dest.writeString(homeName);
        dest.writeString(homeAlias);
        dest.writeString(homePoints);
        dest.writeString(timeOnClock);
        dest.writeString(broadcast);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDailyScheduleDate() {
        return dailyScheduleDate;
    }

    public void setDailyScheduleDate(String dailyScheduleDate) {
        this.dailyScheduleDate = dailyScheduleDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getScheduled() {
        return scheduled;
    }

    public void setScheduled(String scheduled) {
        this.scheduled = scheduled;
    }

    public String getAwayName() {
        return awayName;
    }

    public void setAwayName(String awayName) {
        this.awayName = awayName;
    }

    public String getAwayAlias() {
        return awayAlias;
    }

    public void setAwayAlias(String awayAlias) {
        this.awayAlias = awayAlias;
    }

    public String getAwayPoints() {
        return awayPoints;
    }

    public void setAwayPoints(String awayPoints) {
        this.awayPoints = awayPoints;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getHomeAlias() {
        return homeAlias;
    }

    public void setHomeAlias(String homeAlias) {
        this.homeAlias = homeAlias;
    }

    public String getHomePoints() {
        return homePoints;
    }

    public void setHomePoints(String homePoints) {
        this.homePoints = homePoints;
    }

    public String getTimeOnClock() {
        return timeOnClock;
    }

    public void setTimeOnClock(String timeOnClock) {
        this.timeOnClock = timeOnClock;
    }

    public String getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(String broadcast) {
        this.broadcast = broadcast;
    }

    public LeaderBoxScore getHomeLeaderAssists() {
        return homeLeaderAssists;
    }

    public void setHomeLeaderAssists(LeaderBoxScore homeLeaderAssists) {
        this.homeLeaderAssists = homeLeaderAssists;
    }

    public LeaderBoxScore getHomeLeaderRebounds() {
        return homeLeaderRebounds;
    }

    public void setHomeLeaderRebounds(LeaderBoxScore homeLeaderRebounds) {
        this.homeLeaderRebounds = homeLeaderRebounds;
    }

    public LeaderBoxScore getHomeLeaderPoints() {
        return homeLeaderPoints;
    }

    public void setHomeLeaderPoints(LeaderBoxScore homeLeaderPoints) {
        this.homeLeaderPoints = homeLeaderPoints;
    }

    public LeaderBoxScore getAwayLeaderAssists() {
        return awayLeaderAssists;
    }

    public void setAwayLeaderAssists(LeaderBoxScore awayLeaderAssists) {
        this.awayLeaderAssists = awayLeaderAssists;
    }

    public LeaderBoxScore getAwayLeaderRebounds() {
        return awayLeaderRebounds;
    }

    public void setAwayLeaderRebounds(LeaderBoxScore awayLeaderRebounds) {
        this.awayLeaderRebounds = awayLeaderRebounds;
    }

    public LeaderBoxScore getAwayLeaderPoints() {
        return awayLeaderPoints;
    }

    public void setAwayLeaderPoints(LeaderBoxScore awayLeaderPoints) {
        this.awayLeaderPoints = awayLeaderPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameAgg gameAgg = (GameAgg) o;
        return Objects.equals(id, gameAgg.id) &&
                Objects.equals(dailyScheduleDate, gameAgg.dailyScheduleDate) &&
                Objects.equals(status, gameAgg.status) &&
                Objects.equals(scheduled, gameAgg.scheduled) &&
                Objects.equals(awayName, gameAgg.awayName) &&
                Objects.equals(awayAlias, gameAgg.awayAlias) &&
                Objects.equals(awayPoints, gameAgg.awayPoints) &&
                Objects.equals(homeName, gameAgg.homeName) &&
                Objects.equals(homeAlias, gameAgg.homeAlias) &&
                Objects.equals(homePoints, gameAgg.homePoints) &&
                Objects.equals(timeOnClock, gameAgg.timeOnClock) &&
                Objects.equals(broadcast, gameAgg.broadcast);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dailyScheduleDate, status, scheduled, awayName, awayAlias, awayPoints, homeName, homeAlias, homePoints, timeOnClock, broadcast);
    }
}
