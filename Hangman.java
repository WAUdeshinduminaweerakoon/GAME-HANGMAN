import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hangman {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:/Users/Dell/Desktop/Game-Hangman/words_alpha.txt"));

        List<String> words = new ArrayList<>();
        While(scanner.hasNext());
        {
            words.add(scanner.nextLine());
        }
    }

    private static void While(boolean hasNext) {
    }

}