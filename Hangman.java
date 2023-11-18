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

        printWordState(word, newplayer);
        System.out.println("");
        getPlayerLitterGuess(keyboardLitter, word, newplayer);

    }

    private static void getPlayerLitterGuess(Scanner keyboardLitter, String word, List<Character> newplayer) {

        System.out.println("Please enter a litter:");
        String letterPlayerGuess = keyboardLitter.nextLine();
        newplayer.add(letterPlayerGuess.charAt(0));

        printWordState(word, newplayer);

    }

    private static void printWordState(String word, List<Character> newplayer) {

        for (int i = 0; i < word.length(); i++) {
            if (newplayer.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));

            } else {
                System.out.print("-");
            }

        }

    }
}