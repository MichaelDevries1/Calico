import java.util.Scanner;
import java.util.Arrays;

public class Player{
    private Board curBoard;
    private String name;
    private Tile[] hand;
    private final Scanner sc = new Scanner(System.in);

    public Player(int playerNum) {
        this.hand = new Tile[2];    // Requests the player to choose a color for the board

        newName(playerNum);
    }

    public Player (String name) {
        this.name = name;
        this.hand = new Tile[2];
    }
    // Sets the name and number of the player
    public void newName (int playerNum) {
        playerNum++;
        System.out.println("What is the name of player " + playerNum + ": ");
        this.name = sc.next();
    }

    // Asks for the requested board, and creates that board
    public String createNewBoard(String[] validColors) {
        String chosenColor = ""; // Variable to store the player's chosen color
        boolean validColorChosen = false; // Flag to track if a valid color has been chosen

        while (!validColorChosen) {

            System.out.println("Choose a color for the board: " + Arrays.toString(validColors)); // Displays the available colors
            chosenColor = sc.next(); // Asks the player to input a color for the board
            for (String color : validColors) { // Checks if the color is valid
                if (chosenColor.equalsIgnoreCase(color)) {
                    validColorChosen = true;
                    break;
                }
            }
            if (!validColorChosen) {
                System.out.println("Invalid color choice. Please try again.");
            }
        }

        this.curBoard = new Board(chosenColor);

        return chosenColor;
    }

    // deals out two tiles to the player from the bag
    public void setNewHand(Tile tile1, Tile tile2) {
        this.hand[0] = tile1;
        this.hand[1] = tile2;
    }

    public String getName () {
        return this.name;
    }

    public Tile[] getHand() {
        return this.hand;
    }

    public Board getBoard() {
        return this.curBoard;
    }

    public void printHand() {
        for (int i = 0; i < 2; i++) {
            System.out.println(this.hand[i].printTileString());
        }
    }
}