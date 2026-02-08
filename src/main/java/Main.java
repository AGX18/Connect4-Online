import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Board;
import logic.Disc;
import logic.Game;
import logic.Player;
import ui.manager.StageManager;
import ui.view.MenuPane;
import ui.view.View;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // 1. Create the Manager
        StageManager manager = new StageManager(stage);

        // 2. Show the First Screen
        Pane rootNode = new MenuPane(manager);
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);
        // Set up the stage
        stage.setTitle("Connect4");
        stage.setHeight(Board.rows * 80);
        stage.setWidth(Board.cols * 80);
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            // Stop music, save game, etc.
            Platform.exit();
            System.exit(0);
        });
        stage.show();
    }



    void CliApp() {
        Player p1 = new Player(Disc.RED, "Jimmy");
        Player p2 = new Player(Disc.YELLOW, "Claire");
        Game game = new Game(p1, p2);
        Scanner scanner = new Scanner(System.in);
        while (!game.isOver()) {
            System.out.println("state is:\n" + game.getBoardPresentation());
            System.out.println(game.getCurrentPlayer().getName() + " choose a column to play: ");
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a number!");
                scanner.next(); // IMPORTANT: Consume bad input
            }
            int column = scanner.nextInt();
            int res = game.makeMove(game.getCurrentPlayer(), column);
            if (res == -1) {
                System.out.println("Invalid move!");
            }
            if (game.isOver()) {
                System.out.println(game.getWinner().getName() + " is the winner!");
                break;
            }
        }

    }
}
