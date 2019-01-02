
package nanodegree.android.nba.rest.response.boxScore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Home implements Serializable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("alias")
    @Expose
    private String alias;
    @SerializedName("market")
    @Expose
    private String market;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("points")
    @Expose
    private Long points;
    @SerializedName("bonus")
    @Expose
    private Boolean bonus;
    @SerializedName("sr_id")
    @Expose
    private String srId;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("scoring")
    @Expose
    private List<Scoring> scoring = new ArrayList<Scoring>();
    @SerializedName("leaders")
    @Expose
    private Leaders leaders;
    private final static long serialVersionUID = 659670511913634443L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Home() {
    }

    /**
     * 
     * @param id
     * @param market
     * @param alias
     * @param name
     * @param bonus
     * @param points
     * @param leaders
     * @param reference
     * @param scoring
     * @param srId
     */
    public Home(String name, String alias, String market, String id, Long points, Boolean bonus, String srId, String reference, List<Scoring> scoring, Leaders leaders) {
        super();
        this.name = name;
        this.alias = alias;
        this.market = market;
        this.id = id;
        this.points = points;
        this.bonus = bonus;
        this.srId = srId;
        this.reference = reference;
        this.scoring = scoring;
        this.leaders = leaders;
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

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public Boolean getBonus() {
        return bonus;
    }

    public void setBonus(Boolean bonus) {
        this.bonus = bonus;
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

    public List<Scoring> getScoring() {
        return scoring;
    }

    public void setScoring(List<Scoring> scoring) {
        this.scoring = scoring;
    }

    public Leaders getLeaders() {
        return leaders;
    }

    public void setLeaders(Leaders leaders) {
        this.leaders = leaders;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(market).append(alias).append(name).append(bonus).append(points).append(leaders).append(reference).append(scoring).append(srId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Home) == false) {
            return false;
        }
        Home rhs = ((Home) other);
        return new EqualsBuilder().append(id, rhs.id).append(market, rhs.market).append(alias, rhs.alias).append(name, rhs.name).append(bonus, rhs.bonus).append(points, rhs.points).append(leaders, rhs.leaders).append(reference, rhs.reference).append(scoring, rhs.scoring).append(srId, rhs.srId).isEquals();
    }

}
