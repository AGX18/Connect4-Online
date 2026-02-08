package ui.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import logic.Disc;
import logic.Player;
import ui.Utils;
import ui.manager.StageManager;

public class MenuPane extends VBox {
    StageManager stageManager;
    public MenuPane(StageManager stageManager) {
        this.setAlignment(Pos.CENTER);
        this.setMaxWidth(300);
        this.setSpacing(20);

        this.getStylesheets().add(
                getClass().getResource("/styles.css").toExternalForm()
        );

        Label title = Utils.createTitle("Connect4");

        title.getStyleClass().add("game-title");


        TextField player1Field = makeTextField("Enter Player 1 Name: ");
        player1Field.setMaxWidth(300);
        TextField player2Field = makeTextField("Enter Player 2 Name: ");
        player2Field.setMaxWidth(300);
        Button startBtn = Utils.createButton("Start");
        startBtn.getStyleClass().add("play-btn");

        Player[] players = new Player[2];
        ComboBox<Disc> p1Box = new ComboBox<>();
        p1Box.getItems().addAll(Disc.values());
        ComboBox<Disc> p2Box = new ComboBox<>();
        p2Box.getItems().addAll(Disc.values());
        Label errorLabel = new Label("");
        // TODO: Take the Players data and send it to the stage manager!!

        startBtn.setOnAction(event -> {
            String player1Name = player1Field.getText().trim();
            String player2Name = player2Field.getText().trim();
            if (player1Name.isEmpty()) {
                player1Name = "Player 1";
            }
            if (player2Name.isEmpty()) {
                player2Name = "Player 2";
            }

            Disc c1 = p1Box.getValue();
            Disc c2 = p2Box.getValue();

            if (c1 == c2) {
                // SHOW ERROR
                errorLabel.setText("Please select different colors!");
                errorLabel.setStyle("-fx-text-fill: red;");

                // SHAKE ANIMATION (Optional)
                p2Box.setStyle("-fx-border-color: red;");
                return;
            }
            players[0] = new Player(c1, player1Name);
            players[1] = new Player(c2, player2Name);
            stageManager.switchTo(View.GAME, players);

        });
        this.getChildren().addAll(title,player1Field, p1Box, player2Field, p2Box, startBtn);
    }

    private TextField makeTextField(String text) {
        TextField txtField = new TextField();
        txtField.setPromptText(text);
        txtField.setStyle(
                "-fx-font-size: 14; " +
                        "-fx-padding: 10; " +
                        "-fx-control-inner-background: #ecf0f1; " +
                        "-fx-border-color: #3498db; " +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 5; " +
                        "-fx-text-fill: #2c3e50; " +
                        "-fx-font-family: Arial; " +
                        "-fx-cursor: text;"
        );
        txtField.setPrefWidth(200);
        return txtField;
    }
}
