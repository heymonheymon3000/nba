
package nanodegree.android.nba.rest.response.dailySchedule;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Away implements Serializable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("alias")
    @Expose
    private String alias;
    private final static long serialVersionUID = -7244452822517430984L;

    public Away() {}

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

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(alias).append(name).toHashCode();
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
        return new EqualsBuilder().append(alias, rhs.alias).append(name, rhs.name).isEquals();
    }

}
