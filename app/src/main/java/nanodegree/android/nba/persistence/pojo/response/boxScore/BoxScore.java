
package nanodegree.android.nba.persistence.pojo.response.boxScore;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class BoxScore implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("coverage")
    @Expose
    private String coverage;
    @SerializedName("neutral_site")
    @Expose
    private Boolean neutralSite;
    @SerializedName("scheduled")
    @Expose
    private String scheduled;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("attendance")
    @Expose
    private Long attendance;
    @SerializedName("lead_changes")
    @Expose
    private Long leadChanges;
    @SerializedName("times_tied")
    @Expose
    private Long timesTied;
    @SerializedName("clock")
    @Expose
    private String clock;
    @SerializedName("quarter")
    @Expose
    private Long quarter;
    @SerializedName("track_on_court")
    @Expose
    private Boolean trackOnCourt;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("entry_mode")
    @Expose
    private String entryMode;
    @SerializedName("sr_id")
    @Expose
    private String srId;
    @SerializedName("time_zones")
    @Expose
    private TimeZones timeZones;
    @SerializedName("home")
    @Expose
    private Home home;
    @SerializedName("away")
    @Expose
    private Away away;
    private final static long serialVersionUID = 9060797406718362652L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BoxScore() {
    }

    /**
     * 
     * @param home
     * @param status
     * @param neutralSite
     * @param clock
     * @param attendance
     * @param timeZones
     * @param timesTied
     * @param reference
     * @param entryMode
     * @param id
     * @param away
     * @param leadChanges
     * @param scheduled
     * @param duration
     * @param coverage
     * @param trackOnCourt
     * @param srId
     * @param quarter
     */
    public BoxScore(String id, String status, String coverage, Boolean neutralSite, String scheduled, String duration, Long attendance, Long leadChanges, Long timesTied, String clock, Long quarter, Boolean trackOnCourt, String reference, String entryMode, String srId, TimeZones timeZones, Home home, Away away) {
        super();
        this.id = id;
        this.status = status;
        this.coverage = coverage;
        this.neutralSite = neutralSite;
        this.scheduled = scheduled;
        this.duration = duration;
        this.attendance = attendance;
        this.leadChanges = leadChanges;
        this.timesTied = timesTied;
        this.clock = clock;
        this.quarter = quarter;
        this.trackOnCourt = trackOnCourt;
        this.reference = reference;
        this.entryMode = entryMode;
        this.srId = srId;
        this.timeZones = timeZones;
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

    public Boolean getNeutralSite() {
        return neutralSite;
    }

    public void setNeutralSite(Boolean neutralSite) {
        this.neutralSite = neutralSite;
    }

    public String getScheduled() {
        return scheduled;
    }

    public void setScheduled(String scheduled) {
        this.scheduled = scheduled;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Long getAttendance() {
        return attendance;
    }

    public void setAttendance(Long attendance) {
        this.attendance = attendance;
    }

    public Long getLeadChanges() {
        return leadChanges;
    }

    public void setLeadChanges(Long leadChanges) {
        this.leadChanges = leadChanges;
    }

    public Long getTimesTied() {
        return timesTied;
    }

    public void setTimesTied(Long timesTied) {
        this.timesTied = timesTied;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public Long getQuarter() {
        return quarter;
    }

    public void setQuarter(Long quarter) {
        this.quarter = quarter;
    }

    public Boolean getTrackOnCourt() {
        return trackOnCourt;
    }

    public void setTrackOnCourt(Boolean trackOnCourt) {
        this.trackOnCourt = trackOnCourt;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getEntryMode() {
        return entryMode;
    }

    public void setEntryMode(String entryMode) {
        this.entryMode = entryMode;
    }

    public String getSrId() {
        return srId;
    }

    public void setSrId(String srId) {
        this.srId = srId;
    }

    public TimeZones getTimeZones() {
        return timeZones;
    }

    public void setTimeZones(TimeZones timeZones) {
        this.timeZones = timeZones;
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

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(home).append(clock).append(neutralSite).append(status).append(attendance).append(timeZones).append(timesTied).append(reference).append(entryMode).append(id).append(away).append(leadChanges).append(duration).append(scheduled).append(coverage).append(trackOnCourt).append(srId).append(quarter).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BoxScore) == false) {
            return false;
        }
        BoxScore rhs = ((BoxScore) other);
        return new EqualsBuilder().append(home, rhs.home).append(clock, rhs.clock).append(neutralSite, rhs.neutralSite).append(status, rhs.status).append(attendance, rhs.attendance).append(timeZones, rhs.timeZones).append(timesTied, rhs.timesTied).append(reference, rhs.reference).append(entryMode, rhs.entryMode).append(id, rhs.id).append(away, rhs.away).append(leadChanges, rhs.leadChanges).append(duration, rhs.duration).append(scheduled, rhs.scheduled).append(coverage, rhs.coverage).append(trackOnCourt, rhs.trackOnCourt).append(srId, rhs.srId).append(quarter, rhs.quarter).isEquals();
    }

}
