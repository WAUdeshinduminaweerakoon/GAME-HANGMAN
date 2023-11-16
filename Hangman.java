import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:/Users/Dell/Desktop/Game-Hangman/words_alpha.txt"));

        List<String> words = new ArrayList<>();
        while (scanner.hasNext()) {
            words.add(scanner.nextLine());
        }
        // Close the scanner after reading from the file
        scanner.close();

        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));
        System.out.println(word);
    }
}
