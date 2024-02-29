public interface Tile {
    Boolean playable = true;
    Boolean getPlayable();
    void setPlayable(Boolean playable);
    String printTileString();
    String getPattern();
    String getColor();
}