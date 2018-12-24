
package nanodegree.android.nba.persistence.pojo.response.boxScore;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Statistics__ implements Serializable
{

    @SerializedName("minutes")
    @Expose
    private String minutes;
    @SerializedName("field_goals_made")
    @Expose
    private Long fieldGoalsMade;
    @SerializedName("field_goals_att")
    @Expose
    private Long fieldGoalsAtt;
    @SerializedName("field_goals_pct")
    @Expose
    private Double fieldGoalsPct;
    @SerializedName("three_points_made")
    @Expose
    private Long threePointsMade;
    @SerializedName("three_points_att")
    @Expose
    private Long threePointsAtt;
    @SerializedName("three_points_pct")
    @Expose
    private Double threePointsPct;
    @SerializedName("two_points_made")
    @Expose
    private Long twoPointsMade;
    @SerializedName("two_points_att")
    @Expose
    private Long twoPointsAtt;
    @SerializedName("two_points_pct")
    @Expose
    private Double twoPointsPct;
    @SerializedName("blocked_att")
    @Expose
    private Long blockedAtt;
    @SerializedName("free_throws_made")
    @Expose
    private Long freeThrowsMade;
    @SerializedName("free_throws_att")
    @Expose
    private Long freeThrowsAtt;
    @SerializedName("free_throws_pct")
    @Expose
    private Double freeThrowsPct;
    @SerializedName("offensive_rebounds")
    @Expose
    private Long offensiveRebounds;
    @SerializedName("defensive_rebounds")
    @Expose
    private Long defensiveRebounds;
    @SerializedName("rebounds")
    @Expose
    private Long rebounds;
    @SerializedName("assists")
    @Expose
    private Long assists;
    @SerializedName("turnovers")
    @Expose
    private Long turnovers;
    @SerializedName("steals")
    @Expose
    private Long steals;
    @SerializedName("blocks")
    @Expose
    private Long blocks;
    @SerializedName("assists_turnover_ratio")
    @Expose
    private Double assistsTurnoverRatio;
    @SerializedName("personal_fouls")
    @Expose
    private Long personalFouls;
    @SerializedName("tech_fouls")
    @Expose
    private Long techFouls;
    @SerializedName("flagrant_fouls")
    @Expose
    private Long flagrantFouls;
    @SerializedName("pls_min")
    @Expose
    private Long plsMin;
    @SerializedName("points")
    @Expose
    private Long points;
    @SerializedName("double_double")
    @Expose
    private Boolean doubleDouble;
    @SerializedName("triple_double")
    @Expose
    private Boolean tripleDouble;
    @SerializedName("effective_fg_pct")
    @Expose
    private Double effectiveFgPct;
    @SerializedName("efficiency")
    @Expose
    private Long efficiency;
    @SerializedName("efficiency_game_score")
    @Expose
    private Double efficiencyGameScore;
    @SerializedName("points_in_paint")
    @Expose
    private Long pointsInPaint;
    @SerializedName("points_in_paint_att")
    @Expose
    private Long pointsInPaintAtt;
    @SerializedName("points_in_paint_made")
    @Expose
    private Long pointsInPaintMade;
    @SerializedName("points_in_paint_pct")
    @Expose
    private Double pointsInPaintPct;
    @SerializedName("true_shooting_att")
    @Expose
    private Double trueShootingAtt;
    @SerializedName("true_shooting_pct")
    @Expose
    private Double trueShootingPct;
    @SerializedName("fouls_drawn")
    @Expose
    private Long foulsDrawn;
    @SerializedName("offensive_fouls")
    @Expose
    private Long offensiveFouls;
    @SerializedName("points_off_turnovers")
    @Expose
    private Long pointsOffTurnovers;
    @SerializedName("second_chance_pts")
    @Expose
    private Long secondChancePts;
    private final static long serialVersionUID = 5528438291231979872L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Statistics__() {
    }

    /**
     * 
     * @param blockedAtt
     * @param pointsInPaintAtt
     * @param pointsInPaintMade
     * @param fieldGoalsAtt
     * @param personalFouls
     * @param pointsOffTurnovers
     * @param offensiveFouls
     * @param freeThrowsMade
     * @param trueShootingPct
     * @param techFouls
     * @param plsMin
     * @param points
     * @param pointsInPaintPct
     * @param tripleDouble
     * @param assists
     * @param minutes
     * @param foulsDrawn
     * @param efficiencyGameScore
     * @param threePointsMade
     * @param pointsInPaint
     * @param blocks
     * @param doubleDouble
     * @param fieldGoalsPct
     * @param fieldGoalsMade
     * @param flagrantFouls
     * @param assistsTurnoverRatio
     * @param twoPointsMade
     * @param rebounds
     * @param secondChancePts
     * @param defensiveRebounds
     * @param twoPointsAtt
     * @param effectiveFgPct
     * @param freeThrowsPct
     * @param threePointsPct
     * @param turnovers
     * @param offensiveRebounds
     * @param trueShootingAtt
     * @param steals
     * @param efficiency
     * @param freeThrowsAtt
     * @param twoPointsPct
     * @param threePointsAtt
     */
    public Statistics__(String minutes, Long fieldGoalsMade, Long fieldGoalsAtt, Double fieldGoalsPct, Long threePointsMade, Long threePointsAtt, Double threePointsPct, Long twoPointsMade, Long twoPointsAtt, Double twoPointsPct, Long blockedAtt, Long freeThrowsMade, Long freeThrowsAtt, Double freeThrowsPct, Long offensiveRebounds, Long defensiveRebounds, Long rebounds, Long assists, Long turnovers, Long steals, Long blocks, Double assistsTurnoverRatio, Long personalFouls, Long techFouls, Long flagrantFouls, Long plsMin, Long points, Boolean doubleDouble, Boolean tripleDouble, Double effectiveFgPct, Long efficiency, Double efficiencyGameScore, Long pointsInPaint, Long pointsInPaintAtt, Long pointsInPaintMade, Double pointsInPaintPct, Double trueShootingAtt, Double trueShootingPct, Long foulsDrawn, Long offensiveFouls, Long pointsOffTurnovers, Long secondChancePts) {
        super();
        this.minutes = minutes;
        this.fieldGoalsMade = fieldGoalsMade;
        this.fieldGoalsAtt = fieldGoalsAtt;
        this.fieldGoalsPct = fieldGoalsPct;
        this.threePointsMade = threePointsMade;
        this.threePointsAtt = threePointsAtt;
        this.threePointsPct = threePointsPct;
        this.twoPointsMade = twoPointsMade;
        this.twoPointsAtt = twoPointsAtt;
        this.twoPointsPct = twoPointsPct;
        this.blockedAtt = blockedAtt;
        this.freeThrowsMade = freeThrowsMade;
        this.freeThrowsAtt = freeThrowsAtt;
        this.freeThrowsPct = freeThrowsPct;
        this.offensiveRebounds = offensiveRebounds;
        this.defensiveRebounds = defensiveRebounds;
        this.rebounds = rebounds;
        this.assists = assists;
        this.turnovers = turnovers;
        this.steals = steals;
        this.blocks = blocks;
        this.assistsTurnoverRatio = assistsTurnoverRatio;
        this.personalFouls = personalFouls;
        this.techFouls = techFouls;
        this.flagrantFouls = flagrantFouls;
        this.plsMin = plsMin;
        this.points = points;
        this.doubleDouble = doubleDouble;
        this.tripleDouble = tripleDouble;
        this.effectiveFgPct = effectiveFgPct;
        this.efficiency = efficiency;
        this.efficiencyGameScore = efficiencyGameScore;
        this.pointsInPaint = pointsInPaint;
        this.pointsInPaintAtt = pointsInPaintAtt;
        this.pointsInPaintMade = pointsInPaintMade;
        this.pointsInPaintPct = pointsInPaintPct;
        this.trueShootingAtt = trueShootingAtt;
        this.trueShootingPct = trueShootingPct;
        this.foulsDrawn = foulsDrawn;
        this.offensiveFouls = offensiveFouls;
        this.pointsOffTurnovers = pointsOffTurnovers;
        this.secondChancePts = secondChancePts;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public Long getFieldGoalsMade() {
        return fieldGoalsMade;
    }

    public void setFieldGoalsMade(Long fieldGoalsMade) {
        this.fieldGoalsMade = fieldGoalsMade;
    }

    public Long getFieldGoalsAtt() {
        return fieldGoalsAtt;
    }

    public void setFieldGoalsAtt(Long fieldGoalsAtt) {
        this.fieldGoalsAtt = fieldGoalsAtt;
    }

    public Double getFieldGoalsPct() {
        return fieldGoalsPct;
    }

    public void setFieldGoalsPct(Double fieldGoalsPct) {
        this.fieldGoalsPct = fieldGoalsPct;
    }

    public Long getThreePointsMade() {
        return threePointsMade;
    }

    public void setThreePointsMade(Long threePointsMade) {
        this.threePointsMade = threePointsMade;
    }

    public Long getThreePointsAtt() {
        return threePointsAtt;
    }

    public void setThreePointsAtt(Long threePointsAtt) {
        this.threePointsAtt = threePointsAtt;
    }

    public Double getThreePointsPct() {
        return threePointsPct;
    }

    public void setThreePointsPct(Double threePointsPct) {
        this.threePointsPct = threePointsPct;
    }

    public Long getTwoPointsMade() {
        return twoPointsMade;
    }

    public void setTwoPointsMade(Long twoPointsMade) {
        this.twoPointsMade = twoPointsMade;
    }

    public Long getTwoPointsAtt() {
        return twoPointsAtt;
    }

    public void setTwoPointsAtt(Long twoPointsAtt) {
        this.twoPointsAtt = twoPointsAtt;
    }

    public Double getTwoPointsPct() {
        return twoPointsPct;
    }

    public void setTwoPointsPct(Double twoPointsPct) {
        this.twoPointsPct = twoPointsPct;
    }

    public Long getBlockedAtt() {
        return blockedAtt;
    }

    public void setBlockedAtt(Long blockedAtt) {
        this.blockedAtt = blockedAtt;
    }

    public Long getFreeThrowsMade() {
        return freeThrowsMade;
    }

    public void setFreeThrowsMade(Long freeThrowsMade) {
        this.freeThrowsMade = freeThrowsMade;
    }

    public Long getFreeThrowsAtt() {
        return freeThrowsAtt;
    }

    public void setFreeThrowsAtt(Long freeThrowsAtt) {
        this.freeThrowsAtt = freeThrowsAtt;
    }

    public Double getFreeThrowsPct() {
        return freeThrowsPct;
    }

    public void setFreeThrowsPct(Double freeThrowsPct) {
        this.freeThrowsPct = freeThrowsPct;
    }

    public Long getOffensiveRebounds() {
        return offensiveRebounds;
    }

    public void setOffensiveRebounds(Long offensiveRebounds) {
        this.offensiveRebounds = offensiveRebounds;
    }

    public Long getDefensiveRebounds() {
        return defensiveRebounds;
    }

    public void setDefensiveRebounds(Long defensiveRebounds) {
        this.defensiveRebounds = defensiveRebounds;
    }

    public Long getRebounds() {
        return rebounds;
    }

    public void setRebounds(Long rebounds) {
        this.rebounds = rebounds;
    }

    public Long getAssists() {
        return assists;
    }

    public void setAssists(Long assists) {
        this.assists = assists;
    }

    public Long getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(Long turnovers) {
        this.turnovers = turnovers;
    }

    public Long getSteals() {
        return steals;
    }

    public void setSteals(Long steals) {
        this.steals = steals;
    }

    public Long getBlocks() {
        return blocks;
    }

    public void setBlocks(Long blocks) {
        this.blocks = blocks;
    }

    public Double getAssistsTurnoverRatio() {
        return assistsTurnoverRatio;
    }

    public void setAssistsTurnoverRatio(Double assistsTurnoverRatio) {
        this.assistsTurnoverRatio = assistsTurnoverRatio;
    }

    public Long getPersonalFouls() {
        return personalFouls;
    }

    public void setPersonalFouls(Long personalFouls) {
        this.personalFouls = personalFouls;
    }

    public Long getTechFouls() {
        return techFouls;
    }

    public void setTechFouls(Long techFouls) {
        this.techFouls = techFouls;
    }

    public Long getFlagrantFouls() {
        return flagrantFouls;
    }

    public void setFlagrantFouls(Long flagrantFouls) {
        this.flagrantFouls = flagrantFouls;
    }

    public Long getPlsMin() {
        return plsMin;
    }

    public void setPlsMin(Long plsMin) {
        this.plsMin = plsMin;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public Boolean getDoubleDouble() {
        return doubleDouble;
    }

    public void setDoubleDouble(Boolean doubleDouble) {
        this.doubleDouble = doubleDouble;
    }

    public Boolean getTripleDouble() {
        return tripleDouble;
    }

    public void setTripleDouble(Boolean tripleDouble) {
        this.tripleDouble = tripleDouble;
    }

    public Double getEffectiveFgPct() {
        return effectiveFgPct;
    }

    public void setEffectiveFgPct(Double effectiveFgPct) {
        this.effectiveFgPct = effectiveFgPct;
    }

    public Long getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Long efficiency) {
        this.efficiency = efficiency;
    }

    public Double getEfficiencyGameScore() {
        return efficiencyGameScore;
    }

    public void setEfficiencyGameScore(Double efficiencyGameScore) {
        this.efficiencyGameScore = efficiencyGameScore;
    }

    public Long getPointsInPaint() {
        return pointsInPaint;
    }

    public void setPointsInPaint(Long pointsInPaint) {
        this.pointsInPaint = pointsInPaint;
    }

    public Long getPointsInPaintAtt() {
        return pointsInPaintAtt;
    }

    public void setPointsInPaintAtt(Long pointsInPaintAtt) {
        this.pointsInPaintAtt = pointsInPaintAtt;
    }

    public Long getPointsInPaintMade() {
        return pointsInPaintMade;
    }

    public void setPointsInPaintMade(Long pointsInPaintMade) {
        this.pointsInPaintMade = pointsInPaintMade;
    }

    public Double getPointsInPaintPct() {
        return pointsInPaintPct;
    }

    public void setPointsInPaintPct(Double pointsInPaintPct) {
        this.pointsInPaintPct = pointsInPaintPct;
    }

    public Double getTrueShootingAtt() {
        return trueShootingAtt;
    }

    public void setTrueShootingAtt(Double trueShootingAtt) {
        this.trueShootingAtt = trueShootingAtt;
    }

    public Double getTrueShootingPct() {
        return trueShootingPct;
    }

    public void setTrueShootingPct(Double trueShootingPct) {
        this.trueShootingPct = trueShootingPct;
    }

    public Long getFoulsDrawn() {
        return foulsDrawn;
    }

    public void setFoulsDrawn(Long foulsDrawn) {
        this.foulsDrawn = foulsDrawn;
    }

    public Long getOffensiveFouls() {
        return offensiveFouls;
    }

    public void setOffensiveFouls(Long offensiveFouls) {
        this.offensiveFouls = offensiveFouls;
    }

    public Long getPointsOffTurnovers() {
        return pointsOffTurnovers;
    }

    public void setPointsOffTurnovers(Long pointsOffTurnovers) {
        this.pointsOffTurnovers = pointsOffTurnovers;
    }

    public Long getSecondChancePts() {
        return secondChancePts;
    }

    public void setSecondChancePts(Long secondChancePts) {
        this.secondChancePts = secondChancePts;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(blockedAtt).append(pointsInPaintAtt).append(pointsInPaintMade).append(fieldGoalsAtt).append(personalFouls).append(pointsOffTurnovers).append(offensiveFouls).append(freeThrowsMade).append(trueShootingPct).append(techFouls).append(plsMin).append(points).append(pointsInPaintPct).append(tripleDouble).append(assists).append(minutes).append(foulsDrawn).append(efficiencyGameScore).append(threePointsMade).append(pointsInPaint).append(blocks).append(doubleDouble).append(fieldGoalsPct).append(fieldGoalsMade).append(flagrantFouls).append(assistsTurnoverRatio).append(rebounds).append(twoPointsMade).append(secondChancePts).append(defensiveRebounds).append(twoPointsAtt).append(effectiveFgPct).append(freeThrowsPct).append(turnovers).append(threePointsPct).append(offensiveRebounds).append(trueShootingAtt).append(steals).append(efficiency).append(freeThrowsAtt).append(twoPointsPct).append(threePointsAtt).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Statistics__) == false) {
            return false;
        }
        Statistics__ rhs = ((Statistics__) other);
        return new EqualsBuilder().append(blockedAtt, rhs.blockedAtt).append(pointsInPaintAtt, rhs.pointsInPaintAtt).append(pointsInPaintMade, rhs.pointsInPaintMade).append(fieldGoalsAtt, rhs.fieldGoalsAtt).append(personalFouls, rhs.personalFouls).append(pointsOffTurnovers, rhs.pointsOffTurnovers).append(offensiveFouls, rhs.offensiveFouls).append(freeThrowsMade, rhs.freeThrowsMade).append(trueShootingPct, rhs.trueShootingPct).append(techFouls, rhs.techFouls).append(plsMin, rhs.plsMin).append(points, rhs.points).append(pointsInPaintPct, rhs.pointsInPaintPct).append(tripleDouble, rhs.tripleDouble).append(assists, rhs.assists).append(minutes, rhs.minutes).append(foulsDrawn, rhs.foulsDrawn).append(efficiencyGameScore, rhs.efficiencyGameScore).append(threePointsMade, rhs.threePointsMade).append(pointsInPaint, rhs.pointsInPaint).append(blocks, rhs.blocks).append(doubleDouble, rhs.doubleDouble).append(fieldGoalsPct, rhs.fieldGoalsPct).append(fieldGoalsMade, rhs.fieldGoalsMade).append(flagrantFouls, rhs.flagrantFouls).append(assistsTurnoverRatio, rhs.assistsTurnoverRatio).append(rebounds, rhs.rebounds).append(twoPointsMade, rhs.twoPointsMade).append(secondChancePts, rhs.secondChancePts).append(defensiveRebounds, rhs.defensiveRebounds).append(twoPointsAtt, rhs.twoPointsAtt).append(effectiveFgPct, rhs.effectiveFgPct).append(freeThrowsPct, rhs.freeThrowsPct).append(turnovers, rhs.turnovers).append(threePointsPct, rhs.threePointsPct).append(offensiveRebounds, rhs.offensiveRebounds).append(trueShootingAtt, rhs.trueShootingAtt).append(steals, rhs.steals).append(efficiency, rhs.efficiency).append(freeThrowsAtt, rhs.freeThrowsAtt).append(twoPointsPct, rhs.twoPointsPct).append(threePointsAtt, rhs.threePointsAtt).isEquals();
    }

}
