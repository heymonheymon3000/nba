
package nanodegree.android.nba.persistence.pojo.response.dailySchedule;

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
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("sr_id")
    @Expose
    private String srId;
    @SerializedName("reference")
    @Expose
    private String reference;
    private final static long serialVersionUID = -7244452822517430984L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Away() {
    }

    /**
     * 
     * @param id
     * @param alias
     * @param name
     * @param reference
     * @param srId
     */
    public Away(String name, String alias, String id, String srId, String reference) {
        super();
        this.name = name;
        this.alias = alias;
        this.id = id;
        this.srId = srId;
        this.reference = reference;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSrId() {
        return srId;
    }

    public void setSrId(String srId) {
        this.srId = srId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(alias).append(name).append(reference).append(srId).toHashCode();
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
        return new EqualsBuilder().append(id, rhs.id).append(alias, rhs.alias).append(name, rhs.name).append(reference, rhs.reference).append(srId, rhs.srId).isEquals();
    }

}
