
package nanodegree.android.nba.rest.response.standing;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Division {

    @SerializedName("teams")
    @Expose
    private List<Team> teams = null;

    public Division() {}

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("teams", teams).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(teams).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Division) == false) {
            return false;
        }
        Division rhs = ((Division) other);
        return new EqualsBuilder().append(teams, rhs.teams).isEquals();
    }

}
