
package nanodegree.android.nba.persistence.pojo.response.dailySchedule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Game implements Serializable
{

    private String gameStatus;
    private Long awayPoints;
    private Long homePoints;


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("coverage")
    @Expose
    private String coverage;
    @SerializedName("scheduled")
    @Expose
    private String scheduled;
    @SerializedName("track_on_court")
    @Expose
    private Boolean trackOnCourt;
    @SerializedName("sr_id")
    @Expose
    private String srId;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("time_zones")
    @Expose
    private TimeZones timeZones;
    @SerializedName("venue")
    @Expose
    private Venue venue;
    @SerializedName("broadcasts")
    @Expose
    private List<Broadcast> broadcasts = new ArrayList<Broadcast>();
    @SerializedName("home")
    @Expose
    private Home home;
    @SerializedName("away")
    @Expose
    private Away away;
    private final static long serialVersionUID = -216109084112392307L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Game() {
    }

    /**
     * 
     * @param id
     * @param away
     * @param home
     * @param scheduled
     * @param coverage
     * @param status
     * @param timeZones
     * @param venue
     * @param trackOnCourt
     * @param reference
     * @param broadcasts
     * @param srId
     */
    public Game(String id, String status, String coverage, String scheduled, Boolean trackOnCourt, String srId, String reference, TimeZones timeZones, Venue venue, List<Broadcast> broadcasts, Home home, Away away) {
        super();
        this.id = id;
        this.status = status;
        this.coverage = coverage;
        this.scheduled = scheduled;
        this.trackOnCourt = trackOnCourt;
        this.srId = srId;
        this.reference = reference;
        this.timeZones = timeZones;
        this.venue = venue;
        this.broadcasts = broadcasts;
        this.home = home;
        this.away = away;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }

    public String getScheduled() {
        return scheduled;
    }

    public void setScheduled(String scheduled) {
        this.scheduled = scheduled;
    }

    public Boolean getTrackOnCourt() {
        return trackOnCourt;
    }

    public void setTrackOnCourt(Boolean trackOnCourt) {
        this.trackOnCourt = trackOnCourt;
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

    public TimeZones getTimeZones() {
        return timeZones;
    }

    public void setTimeZones(TimeZones timeZones) {
        this.timeZones = timeZones;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public List<Broadcast> getBroadcasts() {
        return broadcasts;
    }

    public void setBroadcasts(List<Broadcast> broadcasts) {
        this.broadcasts = broadcasts;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public Away getAway() {
        return away;
    }

    public void setAway(Away away) {
        this.away = away;
    }

    public Long getAwayPoints() {
        return awayPoints;
    }

    public void setAwayPoints(Long awayPoints) {
        this.awayPoints = awayPoints;
    }

    public Long getHomePoints() {
        return homePoints;
    }

    public void setHomePoints(Long homePoints) {
        this.homePoints = homePoints;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(home).append(status).append(timeZones).append(venue).append(reference).append(id).append(away).append(scheduled).append(coverage).append(trackOnCourt).append(broadcasts).append(srId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Game) == false) {
            return false;
        }
        Game rhs = ((Game) other);
        return new EqualsBuilder().append(home, rhs.home).append(status, rhs.status).append(timeZones, rhs.timeZones).append(venue, rhs.venue).append(reference, rhs.reference).append(id, rhs.id).append(away, rhs.away).append(scheduled, rhs.scheduled).append(coverage, rhs.coverage).append(trackOnCourt, rhs.trackOnCourt).append(broadcasts, rhs.broadcasts).append(srId, rhs.srId).isEquals();
    }

}
