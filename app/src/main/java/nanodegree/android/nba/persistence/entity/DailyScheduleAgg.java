package nanodegree.android.nba.persistence.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import nanodegree.android.nba.persistence.db.NBAContract;

@Entity(tableName = NBAContract.DailyScheduleEntry.TABLE_NAME)
public class DailyScheduleAgg implements Parcelable {
    @NonNull
    @PrimaryKey
    @ColumnInfo(index = true, name = NBAContract.DailyScheduleEntry.COLUMN_ID)
    private String id;

    @Ignore
    private ArrayList<GameAgg> games = null;

    public DailyScheduleAgg() {}

    public DailyScheduleAgg(Parcel in) {
        id = in.readString();
        games = in.createTypedArrayList(GameAgg.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<GameAgg> getGames() {
        return games;
    }

    public void setGames(ArrayList<GameAgg> games) {
        this.games = games;
    }
}
