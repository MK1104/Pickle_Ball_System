package Match;

import Team.Team;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import Player.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MatchManagement {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final File file = new File("src/main/resources/Matches.json");
    private static final File teamFile = new File("src/main/resources/Team.json");
    private static final File playerFile = new File("src/main/resources/players.json");
    private static final Random random = new Random();
    // Create Single Match
    public static void createSingleMatch() {
        try {
            // Load players
            List<Player> players = loadPlayers();
            if (players.isEmpty()) {
                System.out.println("No players available. Create players first!");
                return;
            }

            // Collect player IDs already in matches
            List<Match> matches = loadMatches();
            List<Integer> usedPlayerIds = new ArrayList<>();
            for (Match m : matches) {
                if (m instanceof SingleMatch sm) {
                    for (Player p : sm.getPlayers()) {
                        usedPlayerIds.add(p.getPlayerId());
                    }
                }
            }

            // Filter single players
            List<Player> eligiblePlayers = new ArrayList<>();
            for (Player player : players) {
                if (player.getType().equalsIgnoreCase("Single") && !usedPlayerIds.contains(player.getPlayerId())){
                    eligiblePlayers.add(player);
                }
            }
            if (eligiblePlayers.size() < 2) {
                System.out.println("Not enough players to load matches");
            }

            // Randomly select 2 different players
            Player p1 = eligiblePlayers.get(random.nextInt(eligiblePlayers.size()));
            Player p2;
            do {
                p2 = eligiblePlayers.get(random.nextInt(eligiblePlayers.size()));
            } while (p1.getPlayerId() == p2.getPlayerId());
            // Generate match ID
            int nextID = 1;
            for (Match match : matches) {
                if (match.getMatchID() >= nextID){
                    nextID = match.getMatchID() + 1;
                }
            }
            SingleMatch match = new SingleMatch(nextID, Arrays.asList(p1,p2));
            matches.add(match);
            saveMatches(matches);
            System.out.println("Single match created!");
            System.out.println("Match ID: " + match.getMatchID() + "|" + p1.getName() + " vs " + p2.getName());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void createTeamMatch(){
        try {
            List<Team> teams = loadTeams();
            if (teams.size() < 2) {
                System.out.println("Not enough teams to create matches");
                return;
            }
            List<Match> matches = loadMatches();
            // Collect team IDs already used
            List<Integer> usedTeamIds = new ArrayList<>();
            for (Match m : matches) {
                if (m instanceof DoubleMatch dm) {
                    for (Team t : dm.getTeams()) {
                        usedTeamIds.add(t.getTeamID());
                    }
                }
            }

            List<Team> eligibleTeams = new ArrayList<>();
            for (Team t : teams) {
                if (!usedTeamIds.contains(t.getTeamID())) {
                    eligibleTeams.add(t);
                }
            }
            if (eligibleTeams.size() < 2) {
                System.out.println("Not enough teams to load matches");
                return;
            }
            // Randomly select 2 different teams
            Team t1 = eligibleTeams.get(random.nextInt(teams.size()));
            Team t2;
            do{
                t2 = eligibleTeams.get(random.nextInt(teams.size()));
            } while (t1.getTeamID() == t2.getTeamID());
            // Generate ID
            int nextID = 1;
            for (Match match : matches) {
                if (match.getMatchID() >= nextID){
                    nextID = match.getMatchID() + 1;
                }
            }
            DoubleMatch match = new DoubleMatch(nextID, Arrays.asList(t1,t2));
            matches.add(match);
            saveMatches(matches);
            System.out.println("Team match created!");
            System.out.println("Match ID: " + match.getMatchID() + "|" + t1.getTeamName() + " vs " + t2.getTeamName());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    // List all matches
    public static void listMatches(){
        try {
            List<Match> matches = loadMatches();
            if (matches.isEmpty()){
                System.out.println("No matches found");
                return;
            }
            System.out.println("\n--- Match List ---");
            for (Match m : matches) {
                if (m instanceof SingleMatch sm){
                    System.out.println("[Single] ID: " + m.getMatchID()
                            + " | Players: " + sm.getPlayers().get(0).getName()
                            + " vs " + sm.getPlayers().get(1).getName()
                            + " | Score: " + m.getScore1() + "-" + m.getScore2()
                            + (m.getFinished() ? " | Winner: " + m.getWinnerID() : ""));
                } else if (m instanceof DoubleMatch dm){
                    System.out.println("[Double] ID: " + m.getMatchID()
                            + " | Teams: " + dm.getTeams().get(0).getTeamName()
                            + " vs " + dm.getTeams().get(1).getTeamName()
                            + " | Score: " + m.getScore1() + "-" + m.getScore2()
                            + (m.getFinished() ? " | Winner: " + m.getWinnerID() : ""));
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    // Helper method
    public static List<Player> loadPlayers() throws Exception {
        if (!playerFile.exists() || playerFile.length() == 0) {
            return new ArrayList<>();
        }
        return mapper.readValue(playerFile, new TypeReference<List<Player>>(){});
    }

    public static List<Team> loadTeams() throws Exception {
        if (!teamFile.exists() || teamFile.length() == 0) {
            return new ArrayList<>();
        }
        return mapper.readValue(teamFile, new TypeReference<List<Team>>(){});
    }

    public static List<Match> loadMatches() throws Exception {
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }
        return mapper.readValue(file, new TypeReference<List<Match>>(){});
    }
    public static void saveMatches(List<Match> matches) throws Exception {
        mapper.writerWithDefaultPrettyPrinter()
                .forType(new TypeReference<List<Match>>() {})
                .writeValue(file, matches);
    }
}
