package ui.view;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import logic.Game;
import logic.Player;
import ui.Utils;
import ui.manager.StageManager;

public class GamePane extends BorderPane {
    private final StageManager stageManager;
    private final Game game;
    private final Pane discRoot = new Pane();
    private final Label errorLabel = Utils.createTitle("");
    public GamePane(StageManager stage, Player player1, Player player2) {
        this.stageManager = stage;
        this.game = new Game(player1, player2);
        StackPane boardStack = new StackPane();

        setupErrorLabel();
        // 1. Build the Board Stack
        boardStack.getChildren().addAll(
                discRoot,           // Bottom: Discs
                makeGridMask(),     // Middle: Blue Board
                makeClickTargets(), // Top: Invisible Columns
                errorLabel
        );

        // 2. Layout (BorderPane)
        setCenter(boardStack);
    }

    private Shape makeGridMask() {
        int cols = game.getBoard().cols;
        int rows = game.getBoard().rows;
        Shape grid = new Rectangle(cols * 80, rows * 80);

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                Circle hole = new Circle(35);
                hole.setCenterX(x * 80 + 40);
                hole.setCenterY(y * 80 + 40);

                grid = Shape.subtract(grid, hole);
            }
        }
        grid.setFill(Color.BLUE);
        return grid;
    }

    private Node makeClickTargets() {
        HBox columns = new HBox();
        for (int col = 0; col < game.getBoard().cols; col++) {
            Rectangle rect = new Rectangle(80, game.getBoard().rows * 80);
            rect.setFill(Color.TRANSPARENT);
            rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(255,255,255,0.1)));
            rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));

            final int c = col;
            rect.setOnMouseClicked(e -> playDisc((int) (rect.getX() / rect.getWidth())));

            columns.getChildren().add(rect);
        }
        return columns;
    }

    private void playDisc(int col) {
        int res = game.makeMove(game.getCurrentPlayer(), col);
        if (res == -1) {
            showError("Invalid Move");
        }
//        if (game.isOver()) {
//
//        }

    }

    private void showError(String msg) {
        errorLabel.setText(msg);
        errorLabel.setStyle("-fx-text-fill: #e74c3c;");
        errorLabel.setOpacity(1.0);
        errorLabel.setVisible(true);

        // Wait 3 seconds
        PauseTransition pause = new PauseTransition(Duration.seconds(2));

        // Then fade out
        FadeTransition fade = new FadeTransition(Duration.seconds(0.5), errorLabel);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setOnFinished(e -> errorLabel.setVisible(false));

        // Chain them together
        SequentialTransition sequence = new SequentialTransition(pause, fade);
        sequence.play();
    }

    private void setupErrorLabel() {
        errorLabel.setVisible(false); // Hidden by default
        errorLabel.setStyle("-fx-background-color: #ef4444; -fx-text-fill: white; -fx-padding: 10px; -fx-background-radius: 20;");
        errorLabel.setMouseTransparent(true); // IMPORTANT: Let clicks pass through!

        // Pin to top with margin
        StackPane.setAlignment(errorLabel, Pos.TOP_CENTER);
        StackPane.setMargin(errorLabel, new Insets(20, 0, 0, 0));
    }
}
