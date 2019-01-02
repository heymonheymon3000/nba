
package nanodegree.android.nba.rest.response.dailySchedule;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Broadcast implements Serializable
{

    @SerializedName("network")
    @Expose
    private String network;

    private final static long serialVersionUID = 9023869840772507577L;

    public Broadcast() {}

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(network).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Broadcast) == false) {
            return false;
        }
        Broadcast rhs = ((Broadcast) other);
        return new EqualsBuilder().append(network, rhs.network).isEquals();
    }

}
