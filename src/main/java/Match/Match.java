package Match;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SingleMatch.class, name = "single"),
        @JsonSubTypes.Type(value = DoubleMatch.class, name = "double")
})
public abstract class Match {
    protected int matchID;
    protected int score1;
    protected int score2;
    protected boolean finished;
    protected String winnerID;

    public Match(){}
    public Match(int matchID) {
        this.matchID = matchID;
        this.finished = false;
    }

    public int getMatchID() { return matchID; }
    public int getScore1() { return score1; }
    public int getScore2() { return score2; }
    public boolean getFinished() { return finished; }
    public String getWinnerID() { return winnerID; }

    public void setScores(int score1, int score2) {
        this.score1 = score1;
        this.score2 = score2;
    }

    public void finishMatch(String winnerID) {
        this.finished = true;
        this.winnerID = winnerID;
    }
}
