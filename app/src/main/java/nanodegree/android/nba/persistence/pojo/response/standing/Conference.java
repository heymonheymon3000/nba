
package nanodegree.android.nba.persistence.pojo.response.standing;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Conference {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("alias")
    @Expose
    private String alias;
    @SerializedName("divisions")
    @Expose
    private List<Division> divisions = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Conference() {
    }

    /**
     * 
     * @param id
     * @param alias
     * @param name
     * @param divisions
     */
    public Conference(String id, String name, String alias, List<Division> divisions) {
        super();
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.divisions = divisions;
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

    public List<Division> getDivisions() {
        return divisions;
    }

    public void setDivisions(List<Division> divisions) {
        this.divisions = divisions;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("alias", alias).append("divisions", divisions).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(alias).append(name).append(divisions).toHashCode();
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
        return new EqualsBuilder().append(id, rhs.id).append(alias, rhs.alias).append(name, rhs.name).append(divisions, rhs.divisions).isEquals();
    }

}
