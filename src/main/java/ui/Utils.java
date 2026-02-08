package ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Utils {
    public static Button createButton(String text) {
        Button btn = new Button(text);
        btn.setPrefWidth(200);
        btn.setPrefHeight(50);
        btn.setStyle("-fx-background-color: #4f46e5; -fx-text-fill: white; -fx-font-size: 16px;");
        return btn;
    }
    public static Label createTitle(String text) {
        Label title = new Label(text);
        title.setFont(new Font("Arial", 48));
        title.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        return title;
    }
}
