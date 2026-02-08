package logic;

/**
 * The orchestrator.
 * Holds the logic.Board, tracks which logic.Player's turn it is, manages game state (in progress, won, draw),
 * and enforces turn-based rules.
 * When a player makes a move, logic.Game validates it, tells logic.Board to place the disc,
 * checks if that move won, and switches turns.
 */

enum GameState {
    In_Progress,
    WON,
    DRAW
}

public class Game {
    private final  Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private GameState state;
    private Player winner;

    public Game(Player player1, Player player2) {
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = player1;
        state = GameState.In_Progress;
        winner = null;
    }

    public Board getBoard() {
        return board;
    }

    public String getBoardPresentation() {
        return this.board.toString();
    }
    public Player getPlayer1() {
        return player1;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public GameState getState() {
        return state;
    }

    public Player getWinner() {
        return winner;
    }

    public boolean isOver() {
        return getState() != GameState.In_Progress;
    }

    public int makeMove(Player player, int column) {
        if (state != GameState.In_Progress) {
            return -1;
        }
        if (player != this.currentPlayer) {
            return -1;
        }

        // place disc
        int row = board.placeDisc(column, player.getColor());
        if (row == -1) {
            return -1;
        }

        if (board.checkWin(row, column, player.getColor())) {
            state = GameState.WON;
            winner = player;
        } else if (board.isFull()) {
            state = GameState.DRAW;
        } else {
            currentPlayer = (player == this.player1) ? this.player2 : this.player1; // switch turn
        }
        return row;
    }
//    makeMove(player, column)
//    if state != IN_PROGRESS
//        return false
//                // ... place disc ...
//
//                if board.checkWin(...)
//    state = WON
//            winner = player
//    else if board.isFull()
//    state = DRAW
//    // ...
}
