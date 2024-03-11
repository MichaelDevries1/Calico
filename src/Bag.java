import java.util.Random;

public class Bag {
    private final Tile[] bagTiles;
    private int unusedSize;

    public Bag() {
        this.unusedSize = 108;
        this.bagTiles = new Tile[this.unusedSize];
        String[] color = {"blue", "green", "purple", "pink", "cyan", "yellow"};
        String[] pattern = {"stripe", "fishbone", "flowers", "dots", "quatrefoil", "vines"};
        boolean border = false;
        int cnt = 0;

        for (String s : color) {
            for (String string : pattern) {
                for (int k = 0; k < 3; k++) {
                    this.bagTiles[cnt] = new PlayableTile(s, string, border, true);
                    cnt++;
                }
            }
        }
    }

    public Tile drawTile() {
        if (this.unusedSize == -1) {
            return new PlayableTile(null, null, false, false);
        }
        Random rand = new Random();
        int randIndex = rand.nextInt(this.unusedSize); // Generate a random index within the range of 0 and the unusedSize
        Tile drawn = this.bagTiles[randIndex]; // Get the tile at the random index
        rotateTiles(randIndex); // Rotate the tiles after drawing

        return drawn;
    }

    public void rotateTiles(int index) {
        Tile temp = this.bagTiles[index]; // Store the tile at the current index
        this.bagTiles[index] = this.bagTiles[this.unusedSize - 1]; // Replace the tile at the current index with the last tile
        this.bagTiles[this.unusedSize - 1] = temp; // Move the last tile to the current index
        this.unusedSize--;
    }

    public void printBag() {
        for (int i = 0; i < this.unusedSize; i++) {
            System.out.println(i + " c: " + this.bagTiles[i].getColor() + " p: " + this.bagTiles[i].getPattern());
        }
    }

    public int getUnusedSize() {
        return this.unusedSize;
    }

    public Tile[] getBagTiles() {
        return this.bagTiles;
    }
}