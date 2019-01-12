
package nanodegree.android.nba.rest.response.boxScore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Home implements Serializable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("alias")
    @Expose
    private String alias;
    @SerializedName("points")
    @Expose
    private Long points;
    @SerializedName("leaders")
    @Expose
    private Leaders leaders;
    private final static long serialVersionUID = 659670511913634443L;

    public Home() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public Leaders getLeaders() {
        return leaders;
    }

    public void setLeaders(Leaders leaders) {
        this.leaders = leaders;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(alias).append(name).append(points).append(leaders).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Home) == false) {
            return false;
        }
        Home rhs = ((Home) other);
        return new EqualsBuilder().append(alias, rhs.alias).append(name, rhs.name).append(points, rhs.points).append(leaders, rhs.leaders).isEquals();
    }

}
