
package nanodegree.android.nba.persistence.pojo.response.dailySchedule;

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
    @SerializedName("league")
    @Expose
    private League league;
    @SerializedName("games")
    @Expose
    private List<Game> games = new ArrayList<Game>();
    private final static long serialVersionUID = 3313066631548103796L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DailySchedule() {
    }

    /**
     * 
     * @param games
     * @param league
     * @param date
     */
    public DailySchedule(String date, League league, List<Game> games) {
        super();
        this.date = date;
        this.league = league;
        this.games = games;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public List<Game> getGames() {
        return games;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(games).append(league).append(date).toHashCode();
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
        return new EqualsBuilder().append(games, rhs.games).append(league, rhs.league).append(date, rhs.date).isEquals();
    }

}
