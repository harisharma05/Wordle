import java.nio.file.*;
import java.util.*;

public class Wordle {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        List<String> dictionary = Files.readAllLines(Paths.get("wordle.txt"));
        String playAgain = "";
        String RESET = "\u001B[0m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String RED = "\u001B[31m";
        

        do{
            if (!dictionary.isEmpty()) {
                Random rand = new Random();
                String secret = dictionary.get(rand.nextInt(dictionary.size()));                
                int lives = 6;
                boolean win = false;

                while (lives != 0) {
                    System.out.println("Attempts Left: " + lives);
                    System.out.println("Enter a guess: ");
                    String guess = scanner.nextLine().toLowerCase();

                    if (guess.length() != 5) {
                        System.out.println("Guess must be 5 letter.");
                        continue;
                    }

                    if (guess.equals(secret)) {
                        System.out.println(GREEN + secret + RESET);
                        System.out.println("You win!");
                        win = true;
                        break;
                    }

                    for (int i = 0; i < 5; i++) {
                        char c = guess.charAt(i);
                        if (c == secret.charAt(i)) {
                            System.out.print(GREEN + c + RESET);
                        } else if (secret.contains(String.valueOf(c))) {
                            System.out.print(YELLOW + c + RESET);
                        } else {
                            System.out.print(RED + c + RESET);
                        }
                    }
                    System.out.println();
                    lives--;
                }
                if (!win) {
                    System.out.println("No more attempts. The word was: " + secret);
                }
                System.out.println("Want to play again? y/n");
                playAgain = scanner.nextLine().toLowerCase();
            }
        } while (playAgain.equals('y'));    
        
        scanner.close();
        System.out.println("Thanks for playing!");
    }

}