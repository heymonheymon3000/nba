
package nanodegree.android.nba.rest.response.boxScore;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Assist implements Serializable
{

    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("statistics")
    @Expose
    private Statistics__ statistics;
    private final static long serialVersionUID = -3974690002958022380L;


    public Assist() {}

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

    public Statistics__ getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics__ statistics) {
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
        if ((other instanceof Assist) == false) {
            return false;
        }
        Assist rhs = ((Assist) other);
        return new EqualsBuilder().append(fullName, rhs.fullName).append(statistics, rhs.statistics).append(id, rhs.id).isEquals();
    }

}
