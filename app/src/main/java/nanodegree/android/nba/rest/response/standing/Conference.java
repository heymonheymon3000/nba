
package nanodegree.android.nba.rest.response.standing;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Conference {

    @SerializedName("divisions")
    @Expose
    private List<Division> divisions = null;

    public Conference() {}

    public List<Division> getDivisions() {
        return divisions;
    }

    public void setDivisions(List<Division> divisions) {
        this.divisions = divisions;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("divisions", divisions).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(divisions).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Conference) == false) {
            return false;
        }
        Conference rhs = ((Conference) other);
        return new EqualsBuilder().append(divisions, rhs.divisions).isEquals();
    }

}
