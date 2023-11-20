import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String args[]) throws FileNotFoundException {

        Scanner keyboardLitter = new Scanner(System.in);
        System.out.println("1 or 2 players(plass enter 1 or 2) : ");
        String numberOfPlayer = keyboardLitter.nextLine();

        String word;
        if (numberOfPlayer.equals("1")) {

            Scanner scanner = new Scanner(new File("C:/Users/Dell/Desktop/Game-Hangman/words_alpha.txt"));

            List<String> words = new ArrayList<>();
            while (scanner.hasNext()) {
                words.add(scanner.nextLine());
            }
            // Close the scanner after reading from the file
            scanner.close();

            Random rand = new Random();
            word = words.get(rand.nextInt(words.size()));
        } else {
            System.out.println("Player 1, please enter your word: ");
            word = keyboardLitter.nextLine();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Ready for player 2! Good luck!");
        }
        System.out.println(word);

        List<Character> newplayer = new ArrayList<>();

        int wrongGuessWordCount = 0;
        while (true) {

            printHangeMan(wrongGuessWordCount);

            if (wrongGuessWordCount >= 6) {
                System.out.println("The man said, I did not save");
                System.out.println("You lose!");
                break;
            }

            printWordState(word, newplayer);
            if (!getPlayerLitterGuess(keyboardLitter, word, newplayer)) {
                wrongGuessWordCount++;
            }

            if (printWordState(word, newplayer)) {
                break;
            }
            System.out.println("Please enter your guess for the word: ");
            if (keyboardLitter.nextLine().equals(word)) {
                System.out.println("You win ! Congratulations! ");
                break;
            } else {
                System.out.println("No ! Try again.! ");
            }
        }

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
                System.out.println("");
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
                System.out.println("");
            }
        }
        System.out.println("");
        System.out.println("");

    }

    private static boolean getPlayerLitterGuess(Scanner keyboardLitter, String word, List<Character> newplayer) {
        System.out.println("");
        System.out.println("Please enter a litter:");
        String letterPlayerGuess = keyboardLitter.nextLine();
        newplayer.add(letterPlayerGuess.charAt(0));

        return word.contains(letterPlayerGuess);

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