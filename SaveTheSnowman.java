import java.util.Scanner;
public class SaveTheSnowman{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Mr Snowman's cave, you must save Mr Snowman by guessing his secret password.");
        int snowman_parts = 10;
        int guesses_count = 0;
        String secret_phrase = "pat the willump";
        String output_string = "";
        String guessed_letter;
        Boolean win_game = false;

        for (int i = 0 ; i < secret_phrase.length() ; i++){ // Create output string
            if (secret_phrase.charAt(i) == ' ') {
                output_string += " ";
            } else {
                output_string += "_";
            }
        }
        System.out.println(output_string);

        while(snowman_parts > 0 && win_game == false){ // Start game
            Boolean found_letter = false;
            int letters_found = 0;
            System.out.println("Mr Snowman has " + snowman_parts + " parts left. Guess a letter: ");
            do{
                guessed_letter = scan.nextLine().toLowerCase();
                if (guessed_letter.length() != 1) System.out.println("Thats not a letter! Try again:");
            } while (guessed_letter.length() != 1);

            for (int o = 0; o<secret_phrase.length(); o++) { // check if guessed letter is in password
                if (secret_phrase.charAt(o) == guessed_letter.charAt(0)) {
                    letters_found += 1;
                    found_letter = true;
                    output_string = output_string.substring(0,o) + secret_phrase.charAt(o) + output_string.substring(o+1);
                }
            }

            if (found_letter == false) { // guessed letter isn't in password
                System.out.println("'"+ guessed_letter + "' wasn't a part of the secret password.\nMr Snowman lost a part!");
                snowman_parts -= 1;

            } else { // guessed letter is in password
                System.out.println("You found "+ letters_found + " '" + guessed_letter + "' in Mr Snowman's password!");
            }

            if (output_string.equals(secret_phrase)) { // check if user has guessed the password
                win_game = true;
            } else {
                System.out.println();
                System.out.println(output_string);
                guesses_count += 1;
            }
        }
        if (snowman_parts == 0) {
            System.out.println("You couldn't guess Mr Snowman's password before he died.");
            System.out.println("You took " + guesses_count + " guesses to kill Mr Snowman.");
        } else {
            System.out.println("You saved Mr Snowman! His password is '" + secret_phrase+ "'.");
            System.out.println("You took " + guesses_count + " guesses to save Mr Snowman.");
        }
        scan.close();

    }
}