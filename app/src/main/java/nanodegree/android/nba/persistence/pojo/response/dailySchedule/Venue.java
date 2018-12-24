
package nanodegree.android.nba.persistence.pojo.response.dailySchedule;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Venue implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("capacity")
    @Expose
    private Long capacity;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("zip")
    @Expose
    private String zip;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("sr_id")
    @Expose
    private String srId;
    private final static long serialVersionUID = 4428664426284771123L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Venue() {
    }

    /**
     * 
     * @param id
     * @param zip
     * @param address
     * @param name
     * @param state
     * @param capacity
     * @param srId
     * @param country
     * @param city
     */
    public Venue(String id, String name, Long capacity, String address, String city, String state, String zip, String country, String srId) {
        super();
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.srId = srId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSrId() {
        return srId;
    }

    public void setSrId(String srId) {
        this.srId = srId;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(zip).append(address).append(name).append(state).append(capacity).append(srId).append(country).append(city).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Venue) == false) {
            return false;
        }
        Venue rhs = ((Venue) other);
        return new EqualsBuilder().append(id, rhs.id).append(zip, rhs.zip).append(address, rhs.address).append(name, rhs.name).append(state, rhs.state).append(capacity, rhs.capacity).append(srId, rhs.srId).append(country, rhs.country).append(city, rhs.city).isEquals();
    }

}
