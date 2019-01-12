
package nanodegree.android.nba.rest.response.boxScore;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Statistics__ implements Serializable
{
    @SerializedName("minutes")
    @Expose
    private String minutes;
    @SerializedName("rebounds")
    @Expose
    private Long rebounds;
    @SerializedName("assists")
    @Expose
    private Long assists;
    @SerializedName("points")
    @Expose
    private Long points;
    private final static long serialVersionUID = -5528438291231979872L;

    public Statistics__() {}

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public Long getRebounds() {
        return rebounds;
    }

    public void setRebounds(Long rebounds) {
        this.rebounds = rebounds;
    }

    public Long getAssists() {
        return assists;
    }

    public void setAssists(Long assists) {
        this.assists = assists;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(minutes).append(rebounds).append(assists).append(points).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Statistics__) == false) {
            return false;
        }
        Statistics__ rhs = ((Statistics__) other);
        return new EqualsBuilder().append(minutes, rhs.minutes).append(rebounds, rhs.rebounds).append(assists, rhs.assists).append(points, rhs.points).isEquals();
    }
}

