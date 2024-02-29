class GoalTile implements Tile {
    private String name;
    private Boolean playable;

    public GoalTile(String name, Boolean playable) {
        this.name = name;
        this.playable = playable;
    }

    @Override
    public void setPlayable(Boolean playable) {
        this.playable = playable;
    }

    @Override
    public String printTileString() {
        return null;
    }

    @Override
    public String getPattern() {
        return null;
    }

    @Override
    public String getColor() {
        return null;
    }

    @Override
    public Boolean getPlayable() {
        return this.playable;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
