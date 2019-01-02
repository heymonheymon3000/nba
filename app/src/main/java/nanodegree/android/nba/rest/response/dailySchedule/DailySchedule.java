package nanodegree.android.nba.rest.response.dailySchedule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class DailySchedule implements Serializable
{
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("games")
    @Expose
    private List<Game> games = new ArrayList<Game>();
    private final static long serialVersionUID = 3313066631548103796L;

    public DailySchedule() {}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Game> getGames() {
        return games;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(games).append(date).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DailySchedule) == false) {
            return false;
        }
        DailySchedule rhs = ((DailySchedule) other);
        return new EqualsBuilder().append(games, rhs.games).append(date, rhs.date).isEquals();
    }
}
