package ui.components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Disc extends Circle {
    public Disc(boolean isRed) {
        super(40, isRed ? Color.RED : Color.YELLOW);
    }
}