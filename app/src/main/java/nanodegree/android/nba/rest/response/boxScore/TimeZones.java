
package nanodegree.android.nba.rest.response.boxScore;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class TimeZones implements Serializable
{

    @SerializedName("venue")
    @Expose
    private String venue;
    @SerializedName("home")
    @Expose
    private String home;
    @SerializedName("away")
    @Expose
    private String away;
    private final static long serialVersionUID = 4127724134738896312L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TimeZones() {
    }

    /**
     * 
     * @param away
     * @param home
     * @param venue
     */
    public TimeZones(String venue, String home, String away) {
        super();
        this.venue = venue;
        this.home = home;
        this.away = away;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        this.away = away;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(away).append(home).append(venue).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TimeZones) == false) {
            return false;
        }
        TimeZones rhs = ((TimeZones) other);
        return new EqualsBuilder().append(away, rhs.away).append(home, rhs.home).append(venue, rhs.venue).isEquals();
    }

}
