
package nanodegree.android.nba.rest.response.boxScore;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Assist_ implements Serializable
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
    @SerializedName("sr_id")
    @Expose
    private String srId;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("statistics")
    @Expose
    private Statistics_____ statistics;
    private final static long serialVersionUID = -5975243082764443925L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Assist_() {
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
     * @param srId
     */
    public Assist_(String fullName, String jerseyNumber, String id, String position, String primaryPosition, String srId, String reference, Statistics_____ statistics) {
        super();
        this.fullName = fullName;
        this.jerseyNumber = jerseyNumber;
        this.id = id;
        this.position = position;
        this.primaryPosition = primaryPosition;
        this.srId = srId;
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

    public Statistics_____ getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics_____ statistics) {
        this.statistics = statistics;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(position).append(id).append(jerseyNumber).append(primaryPosition).append(fullName).append(reference).append(statistics).append(srId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Assist_) == false) {
            return false;
        }
        Assist_ rhs = ((Assist_) other);
        return new EqualsBuilder().append(position, rhs.position).append(id, rhs.id).append(jerseyNumber, rhs.jerseyNumber).append(primaryPosition, rhs.primaryPosition).append(fullName, rhs.fullName).append(reference, rhs.reference).append(statistics, rhs.statistics).append(srId, rhs.srId).isEquals();
    }

}
