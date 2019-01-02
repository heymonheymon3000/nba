
package nanodegree.android.nba.rest.response.standing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class GamesBehind {

    @SerializedName("league")
    @Expose
    private Double league;
    @SerializedName("conference")
    @Expose
    private Double conference;
    @SerializedName("division")
    @Expose
    private Double division;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GamesBehind() {
    }

    /**
     * 
     * @param division
     * @param conference
     * @param league
     */
    public GamesBehind(Double league, Double conference, Double division) {
        super();
        this.league = league;
        this.conference = conference;
        this.division = division;
    }

    public Double getLeague() {
        return league;
    }

    public void setLeague(Double league) {
        this.league = league;
    }

    public Double getConference() {
        return conference;
    }

    public void setConference(Double conference) {
        this.conference = conference;
    }

    public Double getDivision() {
        return division;
    }

    public void setDivision(Double division) {
        this.division = division;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("league", league).append("conference", conference).append("division", division).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(division).append(conference).append(league).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GamesBehind) == false) {
            return false;
        }
        GamesBehind rhs = ((GamesBehind) other);
        return new EqualsBuilder().append(division, rhs.division).append(conference, rhs.conference).append(league, rhs.league).isEquals();
    }

}
