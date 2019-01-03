
package nanodegree.android.nba.rest.response.dailySchedule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Game implements Serializable
{
//    private String gameStatus;
//    private Long awayPoints;
//    private String awayRecord;
//    private Long homePoints;
//    private String homeRecord;
//    private String timeOnClock;

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("scheduled")
    @Expose
    private String scheduled;
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

    public Game() {}

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

    public String getScheduled() {
        return scheduled;
    }

    public void setScheduled(String scheduled) {
        this.scheduled = scheduled;
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

//    public Long getAwayPoints() {
//        return awayPoints;
//    }
//
//    public void setAwayPoints(Long awayPoints) {
//        this.awayPoints = awayPoints;
//    }
//
//    public Long getHomePoints() {
//        return homePoints;
//    }
//
//    public void setHomePoints(Long homePoints) {
//        this.homePoints = homePoints;
//    }
//
//    public String getGameStatus() {
//        return gameStatus;
//    }
//
//    public void setGameStatus(String gameStatus) {
//        this.gameStatus = gameStatus;
//    }
//
//    public String getAwayRecord() {
//        return awayRecord;
//    }
//
//    public void setAwayRecord(String awayRecord) {
//        this.awayRecord = awayRecord;
//    }
//
//    public String getHomeRecord() {
//        return homeRecord;
//    }
//
//    public void setHomeRecord(String homeRecord) {
//        this.homeRecord = homeRecord;
//    }
//
//    public String getTimeOnClock() {
//        return timeOnClock;
//    }
//
//    public void setTimeOnClock(String timeOnClock) {
//        this.timeOnClock = timeOnClock;
//    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(home).append(status).append(id).append(away).append(scheduled).append(broadcasts).toHashCode();
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
        return new EqualsBuilder().append(home, rhs.home).append(status, rhs.status).append(id, rhs.id).append(away, rhs.away).append(scheduled, rhs.scheduled).append(broadcasts, rhs.broadcasts).isEquals();
    }
}
