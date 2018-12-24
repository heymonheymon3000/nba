
package nanodegree.android.nba.persistence.pojo.response.boxScore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Leaders implements Serializable
{

    @SerializedName("points")
    @Expose
    private List<Point> points = new ArrayList<Point>();
    @SerializedName("rebounds")
    @Expose
    private List<Rebound> rebounds = new ArrayList<Rebound>();
    @SerializedName("assists")
    @Expose
    private List<Assist> assists = new ArrayList<Assist>();
    private final static long serialVersionUID = -303271062209457860L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Leaders() {
    }

    /**
     * 
     * @param assists
     * @param rebounds
     * @param points
     */
    public Leaders(List<Point> points, List<Rebound> rebounds, List<Assist> assists) {
        super();
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public List<Rebound> getRebounds() {
        return rebounds;
    }

    public void setRebounds(List<Rebound> rebounds) {
        this.rebounds = rebounds;
    }

    public List<Assist> getAssists() {
        return assists;
    }

    public void setAssists(List<Assist> assists) {
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
        if ((other instanceof Leaders) == false) {
            return false;
        }
        Leaders rhs = ((Leaders) other);
        return new EqualsBuilder().append(assists, rhs.assists).append(rebounds, rhs.rebounds).append(points, rhs.points).isEquals();
    }

}
