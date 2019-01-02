
package nanodegree.android.nba.rest.response.standing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Streak {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("length")
    @Expose
    private Integer length;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Streak() {
    }

    /**
     * 
     * @param length
     * @param kind
     */
    public Streak(String kind, Integer length) {
        super();
        this.kind = kind;
        this.length = length;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("kind", kind).append("length", length).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(length).append(kind).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Streak) == false) {
            return false;
        }
        Streak rhs = ((Streak) other);
        return new EqualsBuilder().append(length, rhs.length).append(kind, rhs.kind).isEquals();
    }

}
