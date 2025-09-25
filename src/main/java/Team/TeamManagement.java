package Team;

import Player.Player;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TeamManagement {
    private static ObjectMapper mapper = new ObjectMapper();
    private static File teamFile = new File("src/main/resources/Team.json");
    private static File playerFile = new File("src/main/resources/players.json");

    // Create a team
    public static void createTeam() {
        try {
            // Load player
            List<Player> players = new ArrayList<>();
            if (playerFile.exists()) {
                players = mapper.readValue(playerFile, new TypeReference<List<Player>>(){});
            }
            if (players.isEmpty()) {
                System.out.println("No players available. Create players first!");
                return;
            }

            // Filter eligible players (Double or Both)
            List<Player> eligible = new ArrayList<>();
            for (Player p : players) {
                if (p.getType().equalsIgnoreCase("Double") || p.getType().equalsIgnoreCase("Both")) {
                    eligible.add(p);
                }
            }
            if (eligible.size() < 2) {
                System.out.println("Not enough eligible players. Create players first!");
            }

            // Show eligible players
            System.out.println("\n Eligible players:");
            for (Player p : eligible) {
                System.out.println(p.getPlayerId() + " - " + p.getName() + "(" + p.getType() + ")");
            }

            // Ask user to pick 2 players
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter first player ID: ");
            int id1 = scanner.nextInt();
            System.out.print("Enter second player ID: ");
            int id2 = scanner.nextInt();
            scanner.nextLine();

            Player player1 = null, player2 = null;
            for (Player p : eligible) {
                if (id1 == p.getPlayerId()) {
                    player1 = p;
                }
                if (id2 == p.getPlayerId()) {
                    player2 = p;
                }
            }
            if (player1 == null || player2 == null || id1 ==id2) {
                System.out.println("Invalid player section");
                return;
            }

            // Get team name
            System.out.print("Enter team name: ");
            String teamName = scanner.nextLine();
            // Load existing teams
            List<Team> teams = new ArrayList<>();
            if (teamFile.exists() && teamFile.length() > 0) {
                teams = mapper.readValue(teamFile, new TypeReference<List<Team>>() {});
            }

            // Generate teamId
            int nextId = 1;
            for (Team t : teams) {
                if (t.getTeamID() >= nextId) {
                    nextId = t.getTeamID() + 1;
                }
            }

            // Create new team
            Team newTeam = new Team(nextId, teamName, Arrays.asList(player1, player2));
            teams.add(newTeam);

            // Save JSON
            mapper.writerWithDefaultPrettyPrinter().writeValue(teamFile, teams);
            System.out.println("Team created successfully!");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // List all teams
    public static void listTeams() {
        try {
            if (!teamFile.exists()) {
                System.out.println("No teams found.");
                return;
            }
            List<Team> teams = mapper.readValue(teamFile, new TypeReference<List<Team>>() {});
            System.out.println("\n Team List:");
            for (Team t : teams) {
                System.out.println(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

