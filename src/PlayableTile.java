class PlayableTile implements Tile {
    private String color;
    private String pattern;
    private Boolean playable;
    private Boolean border;

    public PlayableTile(String color, String pattern, Boolean border, Boolean playable) {
        this.color = color;
        this.pattern = pattern;
        this.border = border;
        this.playable = playable;
    }

    @Override
    public Boolean getPlayable() {
        return this.playable;
    }

    public String getColor() {
        return this.color;
    }

    public String getPattern() {
        return this.pattern;
    }

    public Boolean getBorder() {
        return this.border;
    }

    public void setPlayable(Boolean playable) {
        this.playable = playable;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public void setBorder(Boolean border) {
        this.border = border;
    }

    public void printTile() {
        System.out.println("Color: " + this.color);
        System.out.println("Pattern: " + this.pattern);
        System.out.println("BorderTile: " + this.border);
    }

    public String printTileString() {
        return "Color: " + this.color + ", Pattern: " + this.pattern + ", BorderTile: " + this.border;
    }
}
