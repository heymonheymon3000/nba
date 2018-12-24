package nanodegree.android.nba.persistence.pojo.response.dailySchedule;

import java.util.Objects;

public class TeamInfo {
    private String name;
    private Integer logo;

    public TeamInfo (String name, Integer logo) {
        this.name = name;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLogo() {
        return logo;
    }

    public void setLogo(Integer logo) {
        this.logo = logo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamInfo teamInfo = (TeamInfo) o;
        return Objects.equals(name, teamInfo.name) &&
                Objects.equals(logo, teamInfo.logo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, logo);
    }
}
