import java.util.Scanner;

public class GameLauncher {
    // receive input from the user
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // asks user to select a game 
        System.out.println("Welcome to Aidan's Game Center!");
        System.out.println("Enter 'T' to play Tic Tac Toe, or 'Q' to quit:");

        // loop through until the user selects a valid game mode. 
        boolean validInput = false;
        while (!validInput) {
            // put input as upper case
            String input = scanner.nextLine().toUpperCase();
            switch (input) {
                // If 'T', play Tic Tac Toe
                case "T":
                    TicTacToeVsComputer.main(new String[]{});
                    validInput = true;
                    break;
                // If 'Q', quit the game
                case "Q":
                    System.out.println("Thank you for playing!");
                    validInput = true;
                    break;
                // otherwise, ask again for input
                default:
                    System.out.println("Invalid input. Please enter 'T' for Tic Tac Toe, or 'Q' to quit:");
                    break;
            }
        }
    }
}