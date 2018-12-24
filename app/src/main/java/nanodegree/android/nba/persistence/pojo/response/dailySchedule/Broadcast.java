
package nanodegree.android.nba.persistence.pojo.response.dailySchedule;

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
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("locale")
    @Expose
    private String locale;
    @SerializedName("channel")
    @Expose
    private String channel;
    private final static long serialVersionUID = 9023869840772507577L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Broadcast() {
    }

    /**
     * 
     * @param locale
     * @param type
     * @param channel
     * @param network
     */
    public Broadcast(String network, String type, String locale, String channel) {
        super();
        this.network = network;
        this.type = type;
        this.locale = locale;
        this.channel = channel;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(locale).append(type).append(channel).append(network).toHashCode();
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
        return new EqualsBuilder().append(locale, rhs.locale).append(type, rhs.type).append(channel, rhs.channel).append(network, rhs.network).isEquals();
    }

}
