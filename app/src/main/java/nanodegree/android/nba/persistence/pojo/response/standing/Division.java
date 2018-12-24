
package nanodegree.android.nba.persistence.pojo.response.standing;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Division {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("alias")
    @Expose
    private String alias;
    @SerializedName("teams")
    @Expose
    private List<Team> teams = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Division() {
    }

    /**
     * 
     * @param id
     * @param teams
     * @param alias
     * @param name
     */
    public Division(String id, String name, String alias, List<Team> teams) {
        super();
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.teams = teams;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("alias", alias).append("teams", teams).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(teams).append(alias).append(name).toHashCode();
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
        return new EqualsBuilder().append(id, rhs.id).append(teams, rhs.teams).append(alias, rhs.alias).append(name, rhs.name).isEquals();
    }

}
