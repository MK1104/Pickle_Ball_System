import Player.PlayerManagement;
import Team.TeamManagement;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Pickleball System ===");
            System.out.println("1. Create Player");
            System.out.println("2. List Players");
            System.out.println("3. Create Team");
            System.out.println("4. List Team");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> PlayerManagement.savePlayers();
                case 2 -> PlayerManagement.listPlayers();
                case 3 -> TeamManagement.createTeam();
                case 4 -> TeamManagement.listTeams();
                case 5 -> { System.out.println("Goodbye!"); return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
