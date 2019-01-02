package nanodegree.android.nba.persistence.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import nanodegree.android.nba.persistence.db.NBAContract;

import java.util.ArrayList;

@Entity(tableName = NBAContract.DailyScheduleEntry.TABLE_NAME)
public class DailySchedule implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = NBAContract.DailyScheduleEntry.COLUMN_ID)
    private long id;

    @ColumnInfo(name = NBAContract.DailyScheduleEntry.COLUMN_DATE)
    private String date;

    public DailySchedule() {}

    public DailySchedule(Parcel in) {
        id = in.readLong();
        date = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(date);
    }

    public static final Creator<DailySchedule> CREATOR = new Creator<DailySchedule>() {
        @Override
        public DailySchedule createFromParcel(Parcel source) {
            return new DailySchedule(source);
        }

        @Override
        public DailySchedule[] newArray(int size) {
            return new DailySchedule[size];
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
}
