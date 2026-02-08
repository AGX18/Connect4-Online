package ui.manager;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.Player;
import ui.view.GamePane;
import ui.view.MenuPane;
import ui.view.View;


public class StageManager {
    private final Stage stage;

    public StageManager(Stage stage) {
        this.stage = stage;
    }
    public void switchTo(View view, Object data) {
        Pane viewNode = switch (view) {

            // The Logic of creation lives HERE, not in the buttons
            case MENU ->  new MenuPane(this);

            case GAME -> {
                Player[] players = (Player[]) data;
                yield new GamePane(this, players[0], players[1]);
            }

        };

        stage.getScene().setRoot(viewNode);
    }
}
