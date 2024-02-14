public class Tile {
    String color;
    String pattern;
    Boolean Border;
    String type;

    public Tile(String color, String pattern, String type) {
        this.color = color;
        this.pattern = pattern;
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public String getPattern() {
        return pattern;
    }

    public String getType() {
        return type;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public void setType(String type) {
        this.type = type;
    }
}
