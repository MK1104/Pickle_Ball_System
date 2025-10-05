package Match;

import java.util.List;
import Player.Player;
public class SingleMatch extends Match {
    private List<Player> players;
    public SingleMatch(){
        super();
    }

    public SingleMatch(int matchID, List<Player> players){
        super(matchID);
        if (players.size() != 2){
            throw new IllegalArgumentException("There should be exactly two players");
        }
        this.players = players;
    }

    public List<Player> getPlayers(){
        return players;
    }

}
