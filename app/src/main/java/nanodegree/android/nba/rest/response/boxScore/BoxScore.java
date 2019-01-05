
package nanodegree.android.nba.rest.response.boxScore;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class BoxScore implements Serializable
{
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("scheduled")
    @Expose
    private String scheduled;
    @SerializedName("clock")
    @Expose
    private String clock;
    @SerializedName("quarter")
    @Expose
    private Long quarter;
    @Expose
    private Home home;
    @SerializedName("away")
    @Expose
    private Away away;
    private final static long serialVersionUID = 9060797406718362652L;


    public BoxScore() {}

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

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public Long getQuarter() {
        return quarter;
    }

    public void setQuarter(Long quarter) {
        this.quarter = quarter;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public Away getAway() {
        return away;
    }

    public void setAway(Away away) {
        this.away = away;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(home).append(clock).append(status).append(away).append(scheduled).append(quarter).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BoxScore) == false) {
            return false;
        }
        BoxScore rhs = ((BoxScore) other);
        return new EqualsBuilder().append(home, rhs.home).append(clock, rhs.clock).append(status, rhs.status).append(away, rhs.away).append(scheduled, rhs.scheduled).append(quarter, rhs.quarter).isEquals();
    }

}
