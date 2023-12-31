import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Hangman {
    private static final int MAX_WRONG_GUESSES = 6;

    static String numberOfPlayer;

    static boolean isfirstletteradded = false;

    public static void main(String[] args) throws FileNotFoundException {

        Scanner keyboardLitter = new Scanner(System.in);
        System.out.println("1 or 2 players(plass enter 1 or 2) : ");
        numberOfPlayer = keyboardLitter.nextLine();

        String word;
        if (numberOfPlayer.equals("1")) {
            List<String> words = readWordsFromFile();

            System.out.println("Enter the number of letters in the word:");
            int numberOfLetters = getValidNumberOfLetters(keyboardLitter);

            word = getRandomWordWithLetters(words, numberOfLetters);
        } else {
            System.out.println("Player 1, please enter your word: ");
            word = keyboardLitter.nextLine();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Ready for player 2! Good luck!");
            List<String> words = readWordsFromFile();

            System.out.println("Enter the number of letters in the word: ");
            int numberOfLetters = getValidNumberOfLetters(keyboardLitter);

            word = getRandomWordWithLetters(words, numberOfLetters);
        }

        List<Character> incorrectGuesses = new ArrayList<>();

        int wrongGuessWordCount = 0;
        while (true) {
            printHangeMan(wrongGuessWordCount);

            if (wrongGuessWordCount >= MAX_WRONG_GUESSES) {
                System.out.println("The man said, I did not save");
                System.out.println("You lose!");
                System.out.println("The word was: " + word);
                break;
            }

            // Get the player's letter guess first
            if (!getPlayerLitterGuess(keyboardLitter, word, incorrectGuesses)) {
                wrongGuessWordCount++;
            }

            // Print the word state after getting the player's guess
            if (printWordState(word, incorrectGuesses)) {
                break;
            }

            System.out.println("Incorrect letters guessed so far: " + incorrectGuesses);
            System.out.println("Please enter your guess for the word: ");
            if (keyboardLitter.nextLine().equals(word)) {
                System.out.println("You win! Congratulations!");
                break;
            } else {
                System.out.println("No! Try again!");
            }
        }
    }

    private static List<String> readWordsFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:/Users/Dell/Desktop/Game-Hangman/words_alpha.txt"));
        List<String> words = new ArrayList<>();
        while (scanner.hasNext()) {
            words.add(scanner.nextLine());
        }
        scanner.close();
        return words;
    }

    private static int getValidNumberOfLetters(Scanner keyboardLitter) {
        if (Objects.equals(numberOfPlayer, "1") || Objects.equals(numberOfPlayer, "2")) {

            //System.out.println("");
            while (!keyboardLitter.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                keyboardLitter.next(); // Consume the invalid input
            }
        }
        return keyboardLitter.nextInt();
    }

    private static String getRandomWordWithLetters(List<String> words, int numberOfLetters) {
        Random random = new Random();
        String selectedWord;
        do {
            selectedWord = words.get(random.nextInt(words.size()));
        } while (selectedWord.length() != numberOfLetters);
        return selectedWord;
    }

    private static void printHangeMan(Integer wrongGuessWordCount) {
        System.out.println("I'm a hangman!, save me..........");

        System.out.println("---------");
        System.out.println(" |      |");
        if (wrongGuessWordCount >= 1) {
            System.out.println(" O");
        }
        if (wrongGuessWordCount >= 2) {
            System.out.print("\\");
            if (wrongGuessWordCount >= 3) {
                System.out.println(" /");
            } else {
                System.out.println(" ");
            }
        }
        if (wrongGuessWordCount >= 4) {
            System.out.println(" |");
        }
        if (wrongGuessWordCount >= 5) {
            System.out.print("/");
            if (wrongGuessWordCount >= 6) {
                System.out.println(" \\");
            } else {
                System.out.println(" ");
            }
        }
        System.out.println(" ");
        System.out.println(" ");

    }

    // private static boolean getPlayerLitterGuess(Scanner keyboardLitter, String
    // word, List<Character> newplayer) {
    // System.out.println("");
    // System.out.println("Please enter a litter:");
    // String letterPlayerGuess = keyboardLitter.nextLine();
    // newplayer.add(letterPlayerGuess.charAt(0));

    // return word.contains(letterPlayerGuess);

    // }
    private static boolean getPlayerLitterGuess(Scanner keyboardLitter, String word, List<Character> newplayer) {
        System.out.println(" ");
        System.out.print("Please enter a letter: ");
        if (!isfirstletteradded) {
            keyboardLitter.nextLine();
            isfirstletteradded = true;
        }
        // Consume the newline character left in the buffer
        String letterPlayerGuess = keyboardLitter.nextLine().toLowerCase(); // Convert to lowercase for case-insensitive
        // comparison

        if (letterPlayerGuess.length() == 1 && Character.isLetter(letterPlayerGuess.charAt(0))) {
            // Check if the input is a single letter
            char guess = letterPlayerGuess.charAt(0);
            newplayer.add(guess);
            return word.toLowerCase().contains(String.valueOf(guess));
        } else {
            System.out.println("Invalid input. Please enter a single letter.");
            return false;
        }
    }

    private static boolean printWordState(String word, List<Character> newplayer) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (newplayer.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;

            } else {

                System.out.print("-");
            }

        }
        System.out.println();
        return (word.length() == correctCount);
    }
}