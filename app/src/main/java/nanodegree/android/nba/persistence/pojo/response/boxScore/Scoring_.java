
package nanodegree.android.nba.persistence.pojo.response.boxScore;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Scoring_ implements Serializable
{

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("number")
    @Expose
    private Long number;
    @SerializedName("sequence")
    @Expose
    private Long sequence;
    @SerializedName("points")
    @Expose
    private Long points;
    private final static long serialVersionUID = -1608002430692215940L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Scoring_() {
    }

    /**
     * 
     * @param sequence
     * @param number
     * @param points
     * @param type
     */
    public Scoring_(String type, Long number, Long sequence, Long points) {
        super();
        this.type = type;
        this.number = number;
        this.sequence = sequence;
        this.points = points;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(sequence).append(number).append(points).append(type).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Scoring_) == false) {
            return false;
        }
        Scoring_ rhs = ((Scoring_) other);
        return new EqualsBuilder().append(sequence, rhs.sequence).append(number, rhs.number).append(points, rhs.points).append(type, rhs.type).isEquals();
    }

}
