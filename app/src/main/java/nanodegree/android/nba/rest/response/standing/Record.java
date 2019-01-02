
package nanodegree.android.nba.rest.response.standing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Record {

    @SerializedName("record_type")
    @Expose
    private String recordType;
    @SerializedName("wins")
    @Expose
    private Integer wins;
    @SerializedName("losses")
    @Expose
    private Integer losses;
    @SerializedName("win_pct")
    @Expose
    private Double winPct;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Record() {
    }

    /**
     * 
     * @param winPct
     * @param losses
     * @param recordType
     * @param wins
     */
    public Record(String recordType, Integer wins, Integer losses, Double winPct) {
        super();
        this.recordType = recordType;
        this.wins = wins;
        this.losses = losses;
        this.winPct = winPct;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public Double getWinPct() {
        return winPct;
    }

    public void setWinPct(Double winPct) {
        this.winPct = winPct;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("recordType", recordType).append("wins", wins).append("losses", losses).append("winPct", winPct).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(winPct).append(losses).append(recordType).append(wins).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Record) == false) {
            return false;
        }
        Record rhs = ((Record) other);
        return new EqualsBuilder().append(winPct, rhs.winPct).append(losses, rhs.losses).append(recordType, rhs.recordType).append(wins, rhs.wins).isEquals();
    }

}
