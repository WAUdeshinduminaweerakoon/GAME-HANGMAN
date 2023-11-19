import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:/Users/Dell/Desktop/Game-Hangman/words_alpha.txt"));
        Scanner keyboardLitter = new Scanner(System.in);

        List<String> words = new ArrayList<>();
        while (scanner.hasNext()) {
            words.add(scanner.nextLine());
        }
        // Close the scanner after reading from the file
        scanner.close();

        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));
        System.out.println(word);

        List<Character> newplayer = new ArrayList<>();

        int wrongGuessWordCount = 0;
        while (true) {
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

            printWordState(word, newplayer);
            if (!getPlayerLitterGuess(keyboardLitter, word, newplayer)) {
                wrongGuessWordCount++;
            }
            ;

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