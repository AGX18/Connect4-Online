package logic;

public class Player {
    private final Disc color;
    private final String name;

    public Player(Disc color, String name) {
        this.color = color;
        this.name = name;
    }

    public Disc getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
