import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main (String[] args) {
        createBag();
        //newGame();

        /**Board creation for 4 players
        // Game Board: Create a game board with a hexagonal grid representing a quilting pattern. Each hexagon can have
        // different colors and patterns.

        // Tiles: Develop a tile system to represent different quilt patterns. Players will be placing these tiles onto
        // their individual quilts during the game.

        // Player Boards: Implement player boards that represent individual quilts. These boards can have spaces for
        // players to place tiles and score points based on specific patterns or color combinations.

        // Tile Selection: Allow players to choose tiles from a central display or a common pool. The selection process
        // may involve a draft or other mechanics.

        // Scoring Mechanism: Develop a scoring system based on achieving specific goals, such as creating color
        // patterns, connecting certain tiles, or forming groups of similar patterns on the player's quilt.

        // Challenge Tiles: Integrate challenge tiles or cards that provide specific goals for players to achieve
        // during the game, adding a strategic element.

        // Cat Tokens: In "Calico," players can attract cats to their quilt by creating specific patterns. Implement a
        // mechanism where players can place cat tokens on their quilts for bonus points.

        // Endgame Conditions: Determine the conditions that trigger the end of the game, such as filling the entire
        // quilt, achieving a certain score, or completing a specific number of challenge tiles.

        // Component Interaction: Code the interaction between different game components, such as how tiles interact
        // with each other on the quilt and how scoring conditions are checked.

        // AI (if applicable): If you plan to have AI opponents, consider implementing AI logic for their tile
        // placement and strategy.

        // User Interface (UI): Design a user-friendly interface for players to interact with the game, including
        //dragging and dropping tiles, viewing their quilts, and accessing information about the game state. **/
    }

    public static void newGame () {
        Player[] players = new Player[4];
        int numOfPlayers = howManyPlayers();

        players = makePlayers(numOfPlayers,players);

        for (int i = 0; i < numOfPlayers; i++) {
            System.out.println(players[i].getName());
        }
    }

    public static Player makePlayer (String playerName) {
        return new Player(playerName);
    }

    public static int howManyPlayers () {
        int numPlayers;

        do {
            System.out.println("How many players are playing? 1-4:");
            numPlayers = sc.nextInt();
        } while (numPlayers > 4 || numPlayers < 1);

        return numPlayers;
    }

    public static Player[] makePlayers (int numPlayer, Player[] players) {
        for (int i = 0; i < numPlayer; i++) {
            players[i] = new Player(newName(i));
        }

        return players;
    }

    public static String newName (int playerNum) {
        playerNum++;
        System.out.println("What is the name of player " + playerNum + ": ");
        return sc.next();
    }

    public static void createBag() {
        String[] color = {"Blue", "Green", "Purple", "Pink", "Cyan", "Yellow"};
        String[] pattern = {"Stripe", "Fishbone", "Flowers", "Paisley", "Quatrefoil", "Vines"};
        String type = "playable";
        Tile[] bag = new Tile[108];
        int cnt = 0;

        for (int i = 0; i < color.length; i++) {
            for (int j = 0; j < pattern.length; j++) {
                for (int k = 0; k < 3; k++) {
                    bag[cnt] = new Tile(color[i], pattern[j], type);
                    cnt++;
                }
            }
        }

        for (int i = 0; i < bag.length; i++) {
            System.out.println("c: " + bag[i].getColor() + " p: " + bag[i].getPattern());
        }
    }
}