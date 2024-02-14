public class Player {
    private String name;
    private Board playerBoard;
    private int score;
    private Tile[] hand;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.hand = new Tile[2];
    }

    public String getName (){
        return this.name;
    }

    public int getScore() {
        return this.score;
    }
}
