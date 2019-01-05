
package nanodegree.android.nba.rest.response.boxScore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Away implements Serializable
{
    @SerializedName("alias")
    @Expose
    private String alias;
    @SerializedName("points")
    @Expose
    private Long points;
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

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(alias).append(points).toHashCode();
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
        return new EqualsBuilder().append(alias, rhs.alias).append(points, rhs.points).isEquals();
    }

}
