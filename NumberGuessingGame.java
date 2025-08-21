import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        printWelcomeMessage();

        int secretNumber = (int) (Math.random() * 100) + 1;

        try (Scanner scanner = new Scanner(System.in)) {
            int level = getDifficultyLevel(scanner);
            int maxAttempts = getMaxAttempts(level);
            playGame(scanner, secretNumber, maxAttempts);
        }
    }

    private static void printWelcomeMessage() {
        System.out.println("\nWelcome to the Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100.\n");
    }

    private static int getDifficultyLevel(Scanner scanner) {
        System.out.println("""
            Please select the difficulty level:
                1. Easy (10 chances)
                2. Medium (5 chances)
                3. Hard (3 chances)""");
        
        while (true) {
            System.out.print("\nChoose a level: ");

            if (scanner.hasNextInt()) {
                int level = scanner.nextInt();

                if (level >= 1 && level <= 3) {
                    return level;
                } else {
                    System.out.print("Invalid level. ");
                    System.out.println("Please choose 1, 2, or 3.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // consume invalid input
            }
        }
    }

    private static int getMaxAttempts(int level) {
        return switch (level) {
            case 1 -> 10;
            case 2 -> 5;
            case 3 -> 3;
            default -> throw new IllegalArgumentException("Invalid level");
        };
    }

    private static void playGame(Scanner scanner, int secretNumber, int maxAttempts) {
        int userGuess;
        int numberOfGuesses = 0;

        System.out.printf("%nYou have " + maxAttempts + " attempts ");
        System.out.println("to guess the number.");

        while (numberOfGuesses < maxAttempts) {
            System.out.print("\nEnter your guess: ");
            
            if (scanner.hasNextInt()) {
                userGuess = scanner.nextInt();
                numberOfGuesses++;

                if (userGuess < 1 || userGuess > 100) {
                    System.out.print("Out of range! ");
                    System.out.println("The number is between 1 and 100.");
                } else if (userGuess < secretNumber) {
                    System.out.println("Too small!");
                } else if (userGuess > secretNumber) {
                    System.out.println("Too big!");
                } else {
                    System.out.print("Bingo! You guessed it in ");
                    System.out.println(numberOfGuesses + " attempts.\n");
                    return; // exit game
                }
            } else {
                System.out.println("That's not a number!");
                scanner.next(); // consume invalid input
            }
        }

        // run out of attempts
        System.out.printf("%nYou ran out of attempts! ");
        System.out.println("The number was " + secretNumber + ".\n");
    }
}
