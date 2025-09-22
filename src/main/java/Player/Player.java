package Player;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Scanner;

@JsonPropertyOrder({ "playerId", "name", "type", "win", "lose" })
public class Player {
    private int player_id;
    private String name;
    private String type; //Single/ Double/ Both
    private int win;
    private int lose;

    public Player(){}
    public Player(int id, String name, String type){
        this.player_id = id;
        this.name = name;
        this.type = type;
        this.win = 0;
        this.lose = 0;
    }

    // Getters and setters
    public int getPlayerId() {return player_id;}
    public void setPlayerId(int player_id) {this.player_id = player_id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getWin() { return win; }
    public void setWin(int win) { this.win = win; }

    public int getLose() { return lose; }
    public void setLose(int lose) { this.lose = lose; }

    // Create player profile
    public static Player createProfile(int nextID){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Player name: ");
        String name = sc.nextLine();
        String type;
        while (true) {
            System.out.print("Competition Format (Single/Double/Both): ");
            type = sc.nextLine();
            if (type.equalsIgnoreCase("Single") || type.equalsIgnoreCase("Double") || type.equalsIgnoreCase("Both")) {
                break; // valid input
            }
            System.out.println("Invalid input. Please enter Single, Double, or Both.");
        }
        return new Player(nextID, name, type);
    }

    @Override
    public String toString(){
        return "Player{" +
                "id=" + player_id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", wins=" + win +
                ", losses=" + lose +
                '}';

    }
}

