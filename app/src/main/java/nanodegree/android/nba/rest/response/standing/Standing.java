
package nanodegree.android.nba.rest.response.standing;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Standing {

    @SerializedName("league")
    @Expose
    private League league;
    @SerializedName("season")
    @Expose
    private Season season;
    @SerializedName("conferences")
    @Expose
    private List<Conference> conferences = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Standing() {
    }

    /**
     * 
     * @param season
     * @param conferences
     * @param league
     */
    public Standing(League league, Season season, List<Conference> conferences) {
        super();
        this.league = league;
        this.season = season;
        this.conferences = conferences;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public List<Conference> getConferences() {
        return conferences;
    }

    public void setConferences(List<Conference> conferences) {
        this.conferences = conferences;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("league", league).append("season", season).append("conferences", conferences).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(season).append(conferences).append(league).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Standing) == false) {
            return false;
        }
        Standing rhs = ((Standing) other);
        return new EqualsBuilder().append(season, rhs.season).append(conferences, rhs.conferences).append(league, rhs.league).isEquals();
    }

}
