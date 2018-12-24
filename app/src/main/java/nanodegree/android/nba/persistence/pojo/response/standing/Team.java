
package nanodegree.android.nba.persistence.pojo.response.standing;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Team {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("market")
    @Expose
    private String market;
    @SerializedName("wins")
    @Expose
    private Integer wins;
    @SerializedName("losses")
    @Expose
    private Integer losses;
    @SerializedName("win_pct")
    @Expose
    private Double winPct;
    @SerializedName("points_for")
    @Expose
    private Integer pointsFor;
    @SerializedName("points_against")
    @Expose
    private Double pointsAgainst;
    @SerializedName("point_diff")
    @Expose
    private Double pointDiff;
    @SerializedName("sr_id")
    @Expose
    private String srId;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("games_behind")
    @Expose
    private GamesBehind gamesBehind;
    @SerializedName("streak")
    @Expose
    private Streak streak;
    @SerializedName("records")
    @Expose
    private List<Record> records = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Team() {
    }

    /**
     * 
     * @param id
     * @param streak
     * @param market
     * @param winPct
     * @param pointsAgainst
     * @param pointDiff
     * @param name
     * @param losses
     * @param pointsFor
     * @param records
     * @param gamesBehind
     * @param reference
     * @param srId
     * @param wins
     */
    public Team(String id, String name, String market, Integer wins, Integer losses, Double winPct, Integer pointsFor, Double pointsAgainst, Double pointDiff, String srId, String reference, GamesBehind gamesBehind, Streak streak, List<Record> records) {
        super();
        this.id = id;
        this.name = name;
        this.market = market;
        this.wins = wins;
        this.losses = losses;
        this.winPct = winPct;
        this.pointsFor = pointsFor;
        this.pointsAgainst = pointsAgainst;
        this.pointDiff = pointDiff;
        this.srId = srId;
        this.reference = reference;
        this.gamesBehind = gamesBehind;
        this.streak = streak;
        this.records = records;
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

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
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

    public Integer getPointsFor() {
        return pointsFor;
    }

    public void setPointsFor(Integer pointsFor) {
        this.pointsFor = pointsFor;
    }

    public Double getPointsAgainst() {
        return pointsAgainst;
    }

    public void setPointsAgainst(Double pointsAgainst) {
        this.pointsAgainst = pointsAgainst;
    }

    public Double getPointDiff() {
        return pointDiff;
    }

    public void setPointDiff(Double pointDiff) {
        this.pointDiff = pointDiff;
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

    public GamesBehind getGamesBehind() {
        return gamesBehind;
    }

    public void setGamesBehind(GamesBehind gamesBehind) {
        this.gamesBehind = gamesBehind;
    }

    public Streak getStreak() {
        return streak;
    }

    public void setStreak(Streak streak) {
        this.streak = streak;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("market", market).append("wins", wins).append("losses", losses).append("winPct", winPct).append("pointsFor", pointsFor).append("pointsAgainst", pointsAgainst).append("pointDiff", pointDiff).append("srId", srId).append("reference", reference).append("gamesBehind", gamesBehind).append("streak", streak).append("records", records).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(winPct).append(gamesBehind).append(reference).append(id).append(streak).append(market).append(pointsAgainst).append(pointDiff).append(name).append(losses).append(pointsFor).append(records).append(srId).append(wins).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Team) == false) {
            return false;
        }
        Team rhs = ((Team) other);
        return new EqualsBuilder().append(winPct, rhs.winPct).append(gamesBehind, rhs.gamesBehind).append(reference, rhs.reference).append(id, rhs.id).append(streak, rhs.streak).append(market, rhs.market).append(pointsAgainst, rhs.pointsAgainst).append(pointDiff, rhs.pointDiff).append(name, rhs.name).append(losses, rhs.losses).append(pointsFor, rhs.pointsFor).append(records, rhs.records).append(srId, rhs.srId).append(wins, rhs.wins).isEquals();
    }

}
