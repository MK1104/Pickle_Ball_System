import Player.PlayerManagement;
import Team.TeamManagement;
import Match.MatchManagement;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Pickle Ball System ===");
            System.out.println("1. Player Management");
            System.out.println("2. Team Management");
            System.out.println("3. Match Management");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> playerMenu(sc);
                case 2 -> teamMenu(sc);
                case 3 -> matchMenu(sc);
                case 4 -> { System.out.println("Goodbye!"); return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
    // === Player Menu ===
    private static void playerMenu(Scanner sc){
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Player Management ---");
            System.out.println("1. Add Player");
            System.out.println("2. List Players");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose: ");

            int c = sc.nextInt();
            sc.nextLine();

            switch (c) {
                case 1 -> PlayerManagement.savePlayers();
                case 2 -> PlayerManagement.listPlayers();
                case 0 -> back = true; // exit the player menu
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    // === Team Menu ===
    private static void teamMenu(Scanner sc){
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Team Management ---");
            System.out.println("1. Create Team");
            System.out.println("2. List Teams");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose: ");

            int c = sc.nextInt();
            sc.nextLine();

            switch (c) {
                case 1 -> TeamManagement.createTeam();
                case 2 -> TeamManagement.listTeams();
                case 0 -> back = true;
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    // === Match Menu ===
    private static void matchMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Match Management ---");
            System.out.println("1. Create Single Match");
            System.out.println("2. Create Team Match");
            System.out.println("3. List Matches");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose: ");

            int c = sc.nextInt();
            sc.nextLine();

            switch (c) {
                case 1 -> MatchManagement.createSingleMatch();
                case 2 -> MatchManagement.createTeamMatch();
                case 3 -> MatchManagement.listMatches();
                case 0 -> back = true;
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }
}
