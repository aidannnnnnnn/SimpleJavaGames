import java.util.Random;
import java.util.Scanner;

public class TicTacToeVsComputer {
    // initialise board
    private static final char[][] board = new char[3][3];

    // user input variable
    private static final Scanner scanner = new Scanner(System.in);

    // random number
    private static final Random random = new Random();

    public static void main(String[] args) {
        // initialise and print the board
        initializeBoard();
        printBoard();

        // declares if user wants to play game again upon winning/losing
        String again;
        while (true) {
            // make the player do their turn, then print the board
            playerTurn();
            printBoard();

            // if the user has won tell the user they've won
            if (isGameOver('X')) {
                System.out.println("Player wins!");
                break;
            }

            // if there is no winner, let the user know 
            if (isBoardFull()) {
                System.out.println("It's a tie!");
                break;
            }

            // after a user goes, let the computer have a go
            computerTurn();

            // print the board again
            printBoard();

            // if the computer has won tell the user they've lost
            if (isGameOver('O')) {
                System.out.println("Computer wins!");
                break;
            }

            // if there is no winner, let the user know 
            if (isBoardFull()) {
                System.out.println("It's a tie!");
                break;
            }
        }

        // ask the user if they want to play again
        System.out.println("Do you want to play again? (yes/no): ");
        again = scanner.nextLine().trim();

        // keep asking for input until user says yes or no
        while (!again.equalsIgnoreCase("no") && !again.equalsIgnoreCase("yes")){
            System.out.println("Do you want to play again? (yes/no): ");
            again = scanner.nextLine().trim();
            if (again.equalsIgnoreCase("no") || again.equalsIgnoreCase("yes")){
                break;
            }
        }
        
        // restart if yes, return to main if no
        if (again.equalsIgnoreCase("no") || again.equalsIgnoreCase("yes")){
            if (again.equalsIgnoreCase("yes")){
                TicTacToeVsComputer.main(new String[]{});
            } else {
                GameLauncher.main(new String[]{});
            }
        }
    }

    // create a 3x3 board of the '-' character
    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // print a 3 by 3 board to space out character
    private static void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // ask the user to enter a spot to put their character in 
    private static void playerTurn() {
        int row = -1, col = -1;
        boolean isValidInput = false;
        while (!isValidInput) {
            System.out.println("Enter your move (row[1-3] column[1-3]): ");

            // get input and space it by " "
            String input = scanner.nextLine();
            String[] parts = input.split("\\s+");

            // if the input is in the structure "a b"
            if (parts.length == 2) {
                try {
                    // check if the input is valid - row and col is empty and is between 1 and 3 (-1)
                    row = Integer.parseInt(parts[0]) - 1;
                    col = Integer.parseInt(parts[1]) - 1;
                    if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
                        isValidInput = true;
                    } else {
                        System.out.println("Invalid move, try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter numbers only.");
                }
            } else {
                System.out.println("Invalid format, please enter your move as two numbers separated by a space.");
            }
        }
        // place the X on the board if the above is valid
        board[row][col] = 'X';
    }

    // gets the computer to select their position randomly
    private static void computerTurn() {
        int row, col;
        while (true) {
            row = random.nextInt(3);
            col = random.nextInt(3);
            if (board[row][col] == '-') {
                board[row][col] = 'O';
                System.out.println("Computer placed O at (" + (row + 1) + ", " + (col + 1) + ")");
                break;
            }
        }
    }

    // checks if there is three in a row
    private static boolean isGameOver(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true;
        return false;
    }

    // checks if the board is full - loops through the board - false if one of the positions is '-'
    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}