
package nanodegree.android.nba.rest.response.boxScore;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Rebound_ implements Serializable
{

    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("jersey_number")
    @Expose
    private String jerseyNumber;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("primary_position")
    @Expose
    private String primaryPosition;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("statistics")
    @Expose
    private Statistics____ statistics;
    private final static long serialVersionUID = 2186169139970118881L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Rebound_() {
    }

    /**
     * 
     * @param position
     * @param id
     * @param jerseyNumber
     * @param primaryPosition
     * @param fullName
     * @param reference
     * @param statistics
     */
    public Rebound_(String fullName, String jerseyNumber, String id, String position, String primaryPosition, String reference, Statistics____ statistics) {
        super();
        this.fullName = fullName;
        this.jerseyNumber = jerseyNumber;
        this.id = id;
        this.position = position;
        this.primaryPosition = primaryPosition;
        this.reference = reference;
        this.statistics = statistics;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(String jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPrimaryPosition() {
        return primaryPosition;
    }

    public void setPrimaryPosition(String primaryPosition) {
        this.primaryPosition = primaryPosition;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Statistics____ getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics____ statistics) {
        this.statistics = statistics;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(position).append(id).append(jerseyNumber).append(primaryPosition).append(fullName).append(reference).append(statistics).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Rebound_) == false) {
            return false;
        }
        Rebound_ rhs = ((Rebound_) other);
        return new EqualsBuilder().append(position, rhs.position).append(id, rhs.id).append(jerseyNumber, rhs.jerseyNumber).append(primaryPosition, rhs.primaryPosition).append(fullName, rhs.fullName).append(reference, rhs.reference).append(statistics, rhs.statistics).isEquals();
    }

}
