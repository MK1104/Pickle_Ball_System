package Team;
import Player.Player;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonPropertyOrder({"teamID", "teamName", "players", "win", "lose"})
public class Team {
    private int teamID;
    private String teamName;
    private List<Player> players;
    private int win;
    private int lose;

    public Team() {}
    public Team(int teamID, String teamName, List<Player> players) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.players = players;
        this.win = 0;
        this.lose = 0;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamID +
                ", teamName='" + teamName + '\'' +
                ", players=" + players +
                ", wins=" + win +
                ", losses=" + lose +
                '}';
    }
}
