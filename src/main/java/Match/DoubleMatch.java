package Match;

import java.util.List;
import Team.Team;
public class DoubleMatch extends Match {
    private List<Team> teams;

    public DoubleMatch() {
        super();
    }

    public DoubleMatch(int matchID, List<Team> teams) {
        super(matchID);
        if (teams.size() != 2) {
            throw new IllegalArgumentException("Teams must have exactly two teams");
        }
        this.teams = teams;
    }

    public List<Team> getTeams() {
        return teams;
    }
}
