
package nanodegree.android.nba.rest.response.boxScore;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Point implements Serializable
{

    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("statistics")
    @Expose
    private Statistics statistics;
    private final static long serialVersionUID = 5754670158457543631L;


    public Point() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(fullName).append(statistics).append(id).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Point) == false) {
            return false;
        }
        Point rhs = ((Point) other);
        return new EqualsBuilder().append(fullName, rhs.fullName).append(statistics, rhs.statistics).append(id, rhs.id).isEquals();
    }

}
