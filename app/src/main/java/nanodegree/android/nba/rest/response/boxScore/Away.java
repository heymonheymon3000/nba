
package nanodegree.android.nba.rest.response.boxScore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;

public class Away implements Serializable
{
    @SerializedName("alias")
    @Expose
    private String alias;
    @SerializedName("points")
    @Expose
    private Long points;
    @SerializedName("leaders")
    @Expose
    private Leaders leaders;
    private final static long serialVersionUID = 463820561379358009L;


    public Away() {}

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
        return new HashCodeBuilder().append(alias).append(points).append(leaders).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Away) == false) {
            return false;
        }
        Away rhs = ((Away) other);
        return new EqualsBuilder().append(alias, rhs.alias).append(points, rhs.points).append(leaders, rhs.leaders).isEquals();
    }

}
