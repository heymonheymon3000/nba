
package nanodegree.android.nba.persistence.pojo.response.standing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Season {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("type")
    @Expose
    private String type;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Season() {
    }

    /**
     * 
     * @param id
     * @param year
     * @param type
     */
    public Season(String id, Integer year, String type) {
        super();
        this.id = id;
        this.year = year;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("year", year).append("type", type).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(year).append(type).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Season) == false) {
            return false;
        }
        Season rhs = ((Season) other);
        return new EqualsBuilder().append(id, rhs.id).append(year, rhs.year).append(type, rhs.type).isEquals();
    }

}
