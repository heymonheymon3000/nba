package nanodegree.android.nba.persistence.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import android.support.annotation.NonNull;

import android.os.Parcel;
import android.os.Parcelable;

import nanodegree.android.nba.persistence.db.NBAContract;

@Entity(tableName = NBAContract.GameEntry.TABLE_NAME,
        foreignKeys = @ForeignKey(entity = DailyScheduleAgg.class,
                parentColumns = NBAContract.DailyScheduleEntry.COLUMN_ID,
                childColumns = NBAContract.GameEntry.COLUMN_DAILY_SCHEDULE_ID))
public class GameAgg implements Parcelable {
    @PrimaryKey
    @ColumnInfo(index = true, name = NBAContract.GameEntry.COLUMN_ID)
    @NonNull
    private String id;

    @ColumnInfo(index = true, name = NBAContract.GameEntry.COLUMN_DAILY_SCHEDULE_ID)
    private String dailyScheduleId;

    @ColumnInfo(name = NBAContract.GameEntry.COLUMN_STATUS)
    private String status;

    @ColumnInfo(name = NBAContract.GameEntry.COLUMN_SCHEDULED)
    private String scheduled;

    @ColumnInfo(name = NBAContract.GameEntry.COLUMN_AWAY_NAME)
    private String awayName;

    @ColumnInfo(name = NBAContract.GameEntry.COLUMN_AWAY_ALIAS)
    private String awayAlias;

    @ColumnInfo(name = NBAContract.GameEntry.COLUMN_AWAY_POINTS)
    private Long awayPoints;

    @ColumnInfo(name = NBAContract.GameEntry.COLUMN_AWAY_RECORD)
    private String awayRecord;

    @ColumnInfo(name = NBAContract.GameEntry.COLUMN_HOME_NAME)
    private String homeName;

    @ColumnInfo(name = NBAContract.GameEntry.COLUMN_HOME_ALIAS)
    private String homeAlias;

    @ColumnInfo(name = NBAContract.GameEntry.COLUMN_HOME_POINTS)
    private Long homePoints;

    @ColumnInfo(name = NBAContract.GameEntry.COLUMN_HOME_RECORD)
    private String homeRecord;

    @ColumnInfo(name = NBAContract.GameEntry.COLUMN_TIME_ON_CLOCK)
    private String timeOnClock;

    @ColumnInfo(name = NBAContract.GameEntry.COLUMN_BROADCAST)
    private String broadcast;

    public GameAgg() {}

    private GameAgg(Parcel in) {
        id = in.readString();
        dailyScheduleId = in.readString();
        status = in.readString();
        scheduled = in.readString();
        awayName = in.readString();
        awayAlias = in.readString();
        awayPoints = in.readLong();
        awayRecord = in.readString();
        homeName = in.readString();
        homeAlias = in.readString();
        homePoints = in.readLong();
        homeRecord = in.readString();
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
        dest.writeString(id);
        dest.writeString(dailyScheduleId);
        dest.writeString(status);
        dest.writeString(scheduled);
        dest.writeString(awayName);
        dest.writeString(awayAlias);
        dest.writeLong(awayPoints);
        dest.writeString(awayRecord);
        dest.writeString(homeName);
        dest.writeString(homeAlias);
        dest.writeLong(homePoints);
        dest.writeString(homeRecord);
        dest.writeString(timeOnClock);
        dest.writeString(broadcast);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDailyScheduleId() {
        return dailyScheduleId;
    }

    public void setDailyScheduleId(String dailyScheduleId) {
        this.dailyScheduleId = dailyScheduleId;
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

    public Long getAwayPoints() {
        return awayPoints;
    }

    public void setAwayPoints(Long awayPoints) {
        this.awayPoints = awayPoints;
    }

    public String getAwayRecord() {
        return awayRecord;
    }

    public void setAwayRecord(String awayRecord) {
        this.awayRecord = awayRecord;
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

    public Long getHomePoints() {
        return homePoints;
    }

    public void setHomePoints(Long homePoints) {
        this.homePoints = homePoints;
    }

    public String getHomeRecord() {
        return homeRecord;
    }

    public void setHomeRecord(String homeRecord) {
        this.homeRecord = homeRecord;
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
}
