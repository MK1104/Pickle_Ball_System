package Player;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PlayerManagement {
    private static ObjectMapper mapper = new ObjectMapper();
    private static File file = new File("src/main/resources/players.json");

    public static void savePlayers() {
        try {
            // 1. Load existing players
            List<Player> players = new ArrayList<>();
            if (file.exists() && file.length() > 0) {
                players = mapper.readValue(file, new TypeReference<List<Player>>() {});
            }

            // 2. Get next ID
            int nextId = 1;
            for (Player player : players) {
                if (player.getPlayerId() >= nextId){
                    nextId = player.getPlayerId() + 1;
                }
            }

            // 3. Create new profile
            Player newPlayer = Player.createProfile(nextId);
            players.add(newPlayer);

            // 4. Save updated list back to JSON
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, players);

            System.out.println("Player saved successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void listPlayers() {
        try {
            if (!file.exists()) {
                System.out.println("️No players found.");
                return;
            }

            List<Player> players = mapper.readValue(file, new TypeReference<List<Player>>() {});
            System.out.println("\n Player List:");
            for (Player p : players) {
                System.out.println(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
