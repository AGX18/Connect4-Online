import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
            game.makeMove(game.getCurrentPlayer(), column);
            if (game.isOver()) {
                System.out.println(game.getWinner().getName() + " is the winner!");
                break;
            }
        }
    }
}
