import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        newGame();
    }

    public static void newGame () {
        int numOfPlayers = howManyPlayers(); // Asks how many players are playing
        Player[] players = makePlayers(numOfPlayers); // Creates the players

        Bag  tileBag = new Bag();
        dealTilesToPlayers(players, tileBag);
    }

    // Asks how many players are playing
    public static int howManyPlayers () {
        int numPlayers; //

        // Requests the number of players playing and re-requests if the number is not between 1 and 4
        do {
            System.out.println("How many players are playing? 1-4:");
            numPlayers = sc.nextInt();
        } while (numPlayers > 4 || numPlayers < 0);

        return numPlayers;
    }

    // Creates the players, names them, and creates their board
    public static Player[] makePlayers (int numPlayer) {
        Player[] players = new Player[numPlayer]; // Creates the array of players
        String[] validColors = {"blue", "green", "yellow", "purple"}; // List of valid colors
        String chosenColor = "";

        for (int i = 0; i < numPlayer; i++) {
            players[i] = new Player(i); // Creates a new player
            chosenColor = players[i].createNewBoard(validColors);

            List<String> validColorsList = new ArrayList<>(Arrays.asList(validColors));
            validColorsList.remove(chosenColor); // Remove the chosen color from the available options
            validColors = validColorsList.toArray(new String[0]);
        }

        return players;
    }

    // Deals the tiles to the players
    public static void dealTilesToPlayers(Player[] players, Bag tileBag) {
        for (Player player : players) {
            Tile tile1 = tileBag.drawTile();
            Tile tile2 = tileBag.drawTile();
            player.setNewHand(tile1, tile2);
        }
    }

    // Prints out the players name and hand
    public static void printPlayers(Player[] players) {
        for (Player player : players) {
            System.out.println(player.getName());
            player.printHand();
        }
    }

    // Asks the 1st player if they want to play with goal tiles and sets the boards based on their response
    public static void askFirstPlayerForGoalTiles(Player[] players, Board board) {
        System.out.println("Player 1, do you want to play with goal tiles? (yes/no):");
        String response = sc.next();

        if (response.equalsIgnoreCase("yes")) {
            String[] goalNames = {null, null, null};
            for (Player player : players) {
                player.getBoard().setGoalTiles(goalNames);
            }
        }
    }

    // Tests
    @Test
    public void boardTile() {
        Board board = new Board("blue");
        Tile tile = new PlayableTile("blue", "s", false, true);
        board.placeTile(tile, 5, 5);
        assertEquals(board.getTiles()[5][5], tile);
    }

    @Test
    public void boardTile2() {
        Board board = new Board("blue");
        Tile tile = new PlayableTile("purple", "d", false, true);
        board.placeTile(tile, 6, 6);
        assertEquals(board.getTiles()[6][6], tile);
    }

    @Test
    public void checkTileBagSize() {
        Bag tileBag = new Bag();
        assertEquals(108, tileBag.getUnusedSize());
    }

    @Test
    public void checkTileBagColors() {
        Bag tileBag = new Bag();
        Tile[] bagTiles = tileBag.getBagTiles();
        int[] colorCounts = new int[6]; // Index corresponds to color: 0-Blue, 1-Green, 2-Purple, 3-Pink, 4-Cyan, 5-Yellow

        for (Tile tile : bagTiles) {
            String color = tile.getColor();
            switch (color) {
                case "blue" -> colorCounts[0]++;
                case "green" -> colorCounts[1]++;
                case "purple" -> colorCounts[2]++;
                case "pink" -> colorCounts[3]++;
                case "cyan" -> colorCounts[4]++;
                case "yellow" -> colorCounts[5]++;
            }
        }

        for (int count : colorCounts) {
            assertEquals(18, count);
        }
    }

    @Test
    public void checkTilePatternCount() {
        Bag tileBag = new Bag();
        Tile[] bagTiles = tileBag.getBagTiles();
        int[] patternCounts = new int[6]; // Index corresponds to patterns: 0-Stripe, 1-Fishbone, 2-Flowers, 3-Paisley, 4-Quatrefoil, 5-Vines

        for (Tile tile : bagTiles) {
            String pattern = tile.getPattern();
            switch (pattern) {
                case "stripe" -> patternCounts[0]++;
                case "fishbone" -> patternCounts[1]++;
                case "flowers" -> patternCounts[2]++;
                case "paisley" -> patternCounts[3]++;
                case "quatrefoil" -> patternCounts[4]++;
                case "vines" -> patternCounts[5]++;
            }
        }

        for (int count : patternCounts) {
            assertEquals(18, count);
        }
    }

    @Test
    public void playerHand() {
        Player player = new Player("Michael");
        Bag tileBag = new Bag();

        Tile tile1 = tileBag.drawTile();
        Tile tile2 = tileBag.drawTile();

        player.setNewHand(tile1, tile2);

        assertEquals(2, player.getHand().length);
    }

    @Test
    public void RemovedFromBag() {
        Player player = new Player("Michael");
        Bag tileBag = new Bag();

        Tile tile1 = tileBag.drawTile();
        Tile tile2 = tileBag.drawTile();

        player.setNewHand(tile1, tile2);

        assertEquals(106, tileBag.getUnusedSize());
    }

    @Test
    public void goalTilesTurnedOn() {
        Board board = new Board("blue");
        String[] goalTiles = {"AAABBB", "AAAABB", "NOT EQUAL"};

        board.setGoalTiles(goalTiles);

        assertTrue(board.getTiles()[2][3].getPlayable());
        assertTrue(board.getTiles()[3][4].getPlayable());
        assertTrue(board.getTiles()[4][2].getPlayable());
    }

    @Test
    public void checkGoalArrays() {
        Board board = new Board("blue");
        String[] goalTiles = {"AAABBB", "AAAABB", "NOT EQUAL"};
        PlayableTile one = new PlayableTile("blue", "q", false, true);
        Tile two = new PlayableTile("pink", "s", false, true);
        Tile three = new PlayableTile("yellow", "d", false, true);
        Tile four = new PlayableTile("purple", "f", false, true);
        Tile five = new PlayableTile("green", "p", false, true);
        Tile six = new PlayableTile("cyan", "v", false, true);

        board.placeTile(one,1,2);
        board.placeTile(two, 1, 3);
        board.placeTile(three, 2, 2);
        board.placeTile(four, 2, 4);
        board.placeTile(five, 3, 2);
        board.placeTile(six, 3, 3);

        board.setGoalTiles(goalTiles);

        Tile[] expectedGoalTiles = new Tile[6];
        expectedGoalTiles[0] = one;
        expectedGoalTiles[1] = two;
        expectedGoalTiles[2] = three;
        expectedGoalTiles[3] = four;
        expectedGoalTiles[4] = five;
        expectedGoalTiles[5] = six;

        assertArrayEquals(expectedGoalTiles, board.getGoalArea1());
    }

}

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