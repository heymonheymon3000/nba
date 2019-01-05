
package nanodegree.android.nba.rest.response.standing;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Standing {

    @SerializedName("conferences")
    @Expose
    private List<Conference> conferences = null;

    public Standing() {}

    public List<Conference> getConferences() {
        return conferences;
    }

    public void setConferences(List<Conference> conferences) {
        this.conferences = conferences;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("conferences", conferences).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(conferences).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Standing) == false) {
            return false;
        }
        Standing rhs = ((Standing) other);
        return new EqualsBuilder().append(conferences, rhs.conferences).isEquals();
    }

}
