
package nanodegree.android.nba.persistence.pojo.response.boxScore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Leaders_ implements Serializable
{

    @SerializedName("points")
    @Expose
    private List<Point_> points = new ArrayList<Point_>();
    @SerializedName("rebounds")
    @Expose
    private List<Rebound_> rebounds = new ArrayList<Rebound_>();
    @SerializedName("assists")
    @Expose
    private List<Assist_> assists = new ArrayList<Assist_>();
    private final static long serialVersionUID = -5177416472245415433L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Leaders_() {
    }

    /**
     * 
     * @param assists
     * @param rebounds
     * @param points
     */
    public Leaders_(List<Point_> points, List<Rebound_> rebounds, List<Assist_> assists) {
        super();
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
    }

    public List<Point_> getPoints() {
        return points;
    }

    public void setPoints(List<Point_> points) {
        this.points = points;
    }

    public List<Rebound_> getRebounds() {
        return rebounds;
    }

    public void setRebounds(List<Rebound_> rebounds) {
        this.rebounds = rebounds;
    }

    public List<Assist_> getAssists() {
        return assists;
    }

    public void setAssists(List<Assist_> assists) {
        this.assists = assists;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(assists).append(rebounds).append(points).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Leaders_) == false) {
            return false;
        }
        Leaders_ rhs = ((Leaders_) other);
        return new EqualsBuilder().append(assists, rhs.assists).append(rebounds, rhs.rebounds).append(points, rhs.points).isEquals();
    }

}
