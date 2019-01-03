package nanodegree.android.nba.persistence.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import nanodegree.android.nba.persistence.db.NBAContract;

import java.util.ArrayList;

@Entity(tableName = NBAContract.DailyScheduleEntry.TABLE_NAME)
public class DailyScheduleAgg implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = NBAContract.DailyScheduleEntry.COLUMN_ID)
    private long id;

    @ColumnInfo(name = NBAContract.DailyScheduleEntry.COLUMN_DATE)
    private String date;

    @Ignore
    private ArrayList<GameAgg> games = null;

    public DailyScheduleAgg() {}

    public DailyScheduleAgg(Parcel in) {
        id = in.readLong();
        date = in.readString();
        games = in.createTypedArrayList(GameAgg.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(date);
        dest.writeTypedList(games);

    }

    public static final Creator<DailyScheduleAgg> CREATOR = new Creator<DailyScheduleAgg>() {
        @Override
        public DailyScheduleAgg createFromParcel(Parcel source) {
            return new DailyScheduleAgg(source);
        }

        @Override
        public DailyScheduleAgg[] newArray(int size) {
            return new DailyScheduleAgg[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<GameAgg> getGames() {
        return games;
    }

    public void setGames(ArrayList<GameAgg> games) {
        this.games = games;
    }
}
