import java.io.File;
import java.util.Scanner;

public class Board {
    // Dimensions of the quilt board shifting the tiles to the left
    private final int rows = 7;
    private final int columns = 7;
    private final String color; // Representation of the quilt board
    private final Tile[][] tiles; // Representation of the board tiles
    private final Tile[] goalArea1;
    private final Tile[] goalArea2;
    private final Tile[] goalArea3;
    String borderFilePath = "src/main/Resources/boarder.txt";

    // Constructors
    public Board(String color) {
        this.color = color;
        this.tiles = new Tile[rows][columns]; // Creates a new board
        // Sets the color of the board
        if (color.toLowerCase().equals("blue")) {
            boardSetup(1); // Sets up the board
        } else if (color.toLowerCase().equals("yellow")) {
            boardSetup(2);
        } else if (color.toLowerCase().equals("green")) {
            boardSetup(3);
        } else {
            boardSetup(4);
        }
        this.goalArea1 = new Tile[6];
        this.goalArea2 = new Tile[6];
        this.goalArea3 = new Tile[6];
    }

    // Place a tile on the board at specified coordinates
    public boolean placeTile(Tile tile, int r, int c) {
        if (isValidPosition(r, c) && this.tiles[r][c] == null) { // Checks if the position is valid
            this.tiles[r][c] = tile;
            return true;
        }
        return false;
    }

    // Read the contents of the boarder.txt file as CSV and update the tiles array
    public void boardSetup(int boardNumber) {
        boardNumber--; // Adjust the index to start from 0
        try {
            Scanner scanner = new Scanner(new File("src/border.txt")); // Open the file
            for (int i = 0; i < boardNumber * 24; i++) { // Skip to the correct row
                scanner.nextLine();
            }

            for (int j = 0; j < 24; j++) { // Read the next 24 lines
                String line = scanner.nextLine();
                String[] parts = line.split(","); // Split the line by comma
                int row = Integer.parseInt(parts[0]); // Parse the row and column values
                int column = Integer.parseInt(parts[1]);
                this.tiles[row][column] = new PlayableTile(parts[2], parts[3], true, true); // Create a new tile and place it on the board
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Check if a position on the board is valid
    private boolean isValidPosition(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    // Turn Goal tile on
    public void setGoalTiles(String[] goalName) {
        this.tiles[2][3] = new GoalTile(goalName[0], true);
        this.tiles[3][4] = new GoalTile(goalName[1], true);
        this.tiles[4][2] = new GoalTile(goalName[2], true);
        setCheckArea();
    }

    // Set check area for goals
    public void setCheckArea() {
        int[][] goalArea = {{-1, -1}, {-1, 0}, {0, -1}, {0, 1}, {1, -1}, {1, 0}};
        int goalRow;
        int goalCol;

        for (int i = 0; i < goalArea.length; i++) {
            goalRow = goalArea[i][0];
            goalCol = goalArea[i][1];
            this.goalArea1[i] = this.tiles[2 + goalRow][3 + goalCol];
        }

        for (int i = 0; i < goalArea.length; i++) {
            goalRow = goalArea[i][0];
            goalCol = goalArea[i][1];
            this.goalArea2[i] = this.tiles[3 + goalRow][4 + goalCol];
        }

        for (int i = 0; i < goalArea.length; i++) {
            goalRow = goalArea[i][0];
            goalCol = goalArea[i][1];
            this.goalArea3[i] = this.tiles[4 + goalRow][2 + goalCol];
        }
    }

    // Getter methods for rows, columns, and tiles
    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public String getColor() {
        return color;
    }

    public Tile[] getGoalArea1() {
        return goalArea1;
    }

    public Tile[] getGoalArea2() {
        return goalArea2;
    }

    public Tile[] getGoalArea3() {
        return goalArea3;
    }

    public Boolean getGoal(int row, int column) {
        return tiles[row][column].getPlayable();
    }

    public void changeTileColorAndPattern(int row, int column, Tile tile) {
        if (isValidPosition(row, column) && tiles[row][column] != null) {
            tiles[row][column] = tile;
        }
    }

    public void printTileColors() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (tiles[i][j] == null) {
                    System.out.println(i + "," + j + ": null");
                }
                if (tiles[i][j] != null) {
                    System.out.println(i + "," + j + ": " + tiles[i][j].printTileString());
                }
            }
        }
    }

    public void printGoalAreaTiles() {
        System.out.println("Goal Area 1 Tiles:");
        for (Tile tile : goalArea1) {
            System.out.println(tile.printTileString());
        }

        System.out.println("Goal Area 2 Tiles:");
        for (Tile tile : goalArea2) {
            System.out.println(tile.printTileString());
        }

        System.out.println("Goal Area 3 Tiles:");
        for (Tile tile : goalArea3) {
            System.out.println(tile.printTileString());
        }
    }
}
