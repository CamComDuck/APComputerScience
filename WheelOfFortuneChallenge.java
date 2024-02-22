import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
public class WheelOfFortuneChallenge {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String secret_phrase = "";
        String category = "";
        String vowels = "aeiou";
        String consonants = "bcdfghjklmnpqrstvwxyz";
        String guessed_letter;
        ArrayList<String> guessed_letters = new ArrayList<String>();
        Random rand = new Random();
        int current_money = 0;
        int total_winnings = 0;

        String[] animals_list = {"spider", "cobra", "hamster", "seal", "leopard", "cheetah", "crab", "mouse", "bat", "alligator", "cat", "dog", "lion", "chicken", "piranha"};
        String[] fruits_list = {"apple", "banana", "blueberry", "cherry", "coconut", "elderberry", "grapefruit", "grape", "plum", "kiwi", "lemon", "olive", "orange", "pear"};
        String[] school_list = {"math", "english", "computer science", "history", "band", "orchestra", "art", "physics", "chemistry", "biology", "economics", "physical education"};
        String[] vegetables_list = {"asparagus", "beets", "broccoli", "carrot", "cabbage", "eggplant", "kale", "lettuce", "mushroom", "onion", "pepper", "potato", "radish"};
        String[] websites_list = {"youtube", "linkedin", "twitter", "instagram", "snapchat", "facebook", "amazon", "google", "tiktok", "discord", "twitch", "spotify"};

        System.out.println("Welcome to Mr Snowman's Lair. This time he has invited you to play Wheel of Death! What is your name, brave soul?");
        String player_name = scan.nextLine();
        Player player1 = new Player();

        for (int game_num = 1 ; game_num <= 3 ; game_num++) {
            String output_string = "";
            Boolean win_game = false;
            int letters_found = 0;
            Boolean guessed_wrong_letter = false; 
            Boolean guessed_again = false;
            int number_spun = 0;
            int total_vowels = 0;
            int vowels_guessed = 0;
            int category_number = rand.nextInt(6 - 0);

            switch (category_number) {
                case 1 :
                    return animals_list[rand.nextInt(15 - 0)];
                    break;

                case 2 :
                    return fruits_list[rand.nextInt(14 - 0)];
                    break;

                case 3 :
                    return school_list[rand.nextInt(12 - 0)];
                    break;

                case 4 :
                    return vegetables_list[rand.nextInt(13 - 0)];
                    break;

                case 5 :
                    return websites_list[rand.nextInt(12 - 0)];
                    break;
            }

            for (int k = 0 ; k < secret_phrase.length() ; k++) { // Count vowels in password
                if (secret_phrase.charAt(k) == 'a' || secret_phrase.charAt(k) == 'e' || secret_phrase.charAt(k) == 'i' || secret_phrase.charAt(k) == 'o' || secret_phrase.charAt(k) == 'u') {
                    total_vowels += 1;
                }
            }

            for (int i = 0 ; i < secret_phrase.length() ; i++) { // Create output string
                if (secret_phrase.charAt(i) == ' ') {
                    output_string += " ";
                } else {
                    output_string += "_";
                }
            }
            
            
            while (win_game == false) {
                System.out.println();
                if (game_num == 1) {
                    current_money = player1.get_winnings1();
                } else if (game_num == 2) {
                    current_money = player1.get_winnings2();
                } else {
                    current_money = player1.get_winnings3();
                }
                System.out.println("Puzzle: " + output_string);
                System.out.println("Category: " + category);
                System.out.println("Player: " + player_name);
                System.out.println("Winnings: " + current_money);
                if (vowels_guessed < total_vowels) {
                    System.out.println("Would you like to spin the Wheel of Death (spin), purchase a vowel with your death dollars (vowel), or attempt to solve Mr. Snowman's puzzle (solve)?");
                } else {
                    System.out.println("Would you like to spin the Wheel of Death (spin) or attempt to solve Mr. Snowman's puzzle (solve)?");
                }
                letters_found = 0;
                String player_choice = scan.nextLine();
                if (player_choice.equals("spin")) { // PLAYER CHOICE: SPIN

                    // Spin the spinner and end turn if it lands on 0
                    number_spun = rand.nextInt(12 - 0) * 100;
                    if (number_spun == 0) {
                        System.out.println("You landed on 0 death dollars! Turn over!");
                        continue;
                    } else if (number_spun == 1100) {
                        current_money = 0;
                        if (game_num == 1) {
                            player1.set_winnings1(current_money);
                        } else if (game_num == 2) {
                            player1.set_winnings2(current_money);
                        } else if (game_num == 3) {
                            player1.set_winnings3(current_money);
                        }
                        System.out.println("You landed on bankrupt! Mr. Snowman steals all his money back! Turn over!");
                        continue;
                    } else {
                        System.out.println("You landed on " + number_spun + " death dollars!");
                    }

                    System.out.println("Which letter would you like to guess?");
                    do{ // Enter letter and check if its a 1 letter consonant that hasn't been guessed
                        guessed_wrong_letter = false;
                        guessed_again = false;
                        guessed_letter = scan.nextLine().toLowerCase();

                        if (guessed_letter.length() != 1) System.out.println("Thats not a letter! Try again: ");
                        if (consonants.indexOf(guessed_letter) == -1 && guessed_letter.length() == 1) {
                            guessed_wrong_letter = true;
                            System.out.print("That's not a consonant! Try again: ");
                        }
                        if (guessed_letters.indexOf(guessed_letter) != -1 && guessed_letter.length() == 1 && guessed_wrong_letter == false) {
                            guessed_again = true;
                            System.out.println("You already entered that letter! Turn over!");
                        }
                        
                    } while (guessed_letter.length() != 1 || guessed_wrong_letter == true);

                    if (guessed_again) continue; // player guessed a letter again

                    for (int o = 0; o<secret_phrase.length(); o++) { // check if guessed letter is in password
                        if (secret_phrase.charAt(o) == guessed_letter.charAt(0)) {
                            letters_found += 1;
                            output_string = output_string.substring(0,o) + secret_phrase.charAt(o) + output_string.substring(o+1);
                        }
                    }
                    guessed_letters.add(guessed_letter);

                    if (letters_found == 0) { // guessed letter isn't in password
                        System.out.println("'"+ guessed_letter + "' wasn't a part of Mr. Snowman's puzzle. Turn over!");
                        continue;
                    } else { // guessed letter is in password
                        System.out.println("You found "+ letters_found + " '" + guessed_letter + "' in Mr. Snowman's puzzle!");
                    }

                    if (output_string.equals(secret_phrase)) { // check if user has guessed the password
                        win_game = true;
                    }

                    current_money += number_spun * letters_found;
                    if (game_num == 1) {
                        player1.set_winnings1(current_money);
                    } else if (game_num == 2) {
                        player1.set_winnings2(current_money);
                    } else if (game_num == 3) {
                        player1.set_winnings3(current_money);
                    }

                } else if (player_choice.equals("vowel") && vowels_guessed < total_vowels) { // PLAYER CHOICE: VOWEL

                    if (current_money >= 250) { // player has enough money for a vowel
                        System.out.println("Which vowel would you like to purchase?");
                        current_money -= 250;
                        if (game_num == 1) {
                            player1.set_winnings1(current_money);
                        } else if (game_num == 2) {
                            player1.set_winnings2(current_money);
                        } else if (game_num == 3) {
                            player1.set_winnings3(current_money);
                        }
                        do{ // Enter letter and check if its a 1 letter vowel that hasn't been guessed
                            guessed_wrong_letter = false;
                            guessed_again = false;
                            guessed_letter = scan.nextLine().toLowerCase();

                            if (guessed_letter.length() != 1) System.out.println("Thats not a letter! Try again: ");
                            if (vowels.indexOf(guessed_letter) == -1 && guessed_letter.length() == 1) {
                                guessed_wrong_letter = true;
                                System.out.print("That's not a vowel! Try again: ");
                            }
                            if (guessed_letters.indexOf(guessed_letter) != -1 && guessed_letter.length() == 1 && guessed_wrong_letter == false) {
                                guessed_again = true;
                                System.out.println("You already entered that letter! Turn over!");
                            }

                        } while (guessed_letter.length() != 1 || guessed_wrong_letter == true);

                        if (guessed_again) continue; // player guessed a letter again

                        for (int o = 0; o<secret_phrase.length(); o++) { // check if guessed letter is in password
                            if (secret_phrase.charAt(o) == guessed_letter.charAt(0)) {
                                letters_found += 1;
                                output_string = output_string.substring(0,o) + secret_phrase.charAt(o) + output_string.substring(o+1);
                            }
                        }

                        if (letters_found == 0) { // guessed letter isn't in password
                            System.out.println("'"+ guessed_letter + "' wasn't a part Mr. Snowman's puzzle. Your turn continues: ");
                        } else { // guessed letter is in password
                            System.out.println("You found "+ letters_found + " '" + guessed_letter + "' in Mr. Snowman's puzzle!");
                            vowels_guessed += letters_found;
                        }

                        if (output_string.equals(secret_phrase)) { // check if user has guessed the password
                            win_game = true;
                        }

                        guessed_letters.add(guessed_letter);
                        if (vowels_guessed == total_vowels) System.out.println("You have purchased all of Mr. Snowman's vowels!");

                    } else { // player doesn't have enough money for a vowel
                        System.out.println("You don't have enough death dollars for a vowel! Try another option.");
                    }

                } else if (player_choice.equals("solve")) { // PLAYER CHOICE: SOLVE

                    System.out.println("Enter the secret puzzle: ");
                    guessed_letter = scan.nextLine().toLowerCase();

                    if (guessed_letter.equals(secret_phrase)) { // check if user has guessed the password
                        win_game = true;
                    } else {
                        System.out.println("You didn't guess the secret puzzle! Turn over!");
                        continue;
                    }

                } else { // PLAYER CHOICE: NONE
                    System.out.println("You didn't pick a valid option. Try again!");
                }
            }

            // Player won the round
            if (game_num == 1 || game_num == 2) {
                System.out.println();
                if (game_num == 1) {
                    total_winnings = player1.get_winnings1();
                } else {
                    total_winnings = player1.get_winnings2();
                }
                System.out.println("You guessed Mr. Snowman's puzzle correctly! The puzzle was '" + secret_phrase + "'. You collected " + total_winnings + " death dollars!");
                System.out.println("Oh no! Mr. Snowman's door is still locked! Looks like you have to solve another puzzle!");
                guessed_letters.clear();
            }
        }

        // Player won the game
        System.out.println();
        total_winnings = player1.get_winnings1() + player1.get_winnings2() + player1.get_winnings3();
        System.out.println("You successfully solved all of Mr. Snowman's puzzles! The final puzzle was '" + secret_phrase + "'.");
        System.out.println("Round 1: " + player1.get_winnings1() + " death dollars");
        System.out.println("Round 2: " + player1.get_winnings2() + " death dollars");
        System.out.println("Round 3: " + player1.get_winnings3() + " death dollars");
        System.out.println("The door finally opens and you escape with " + total_winnings + " death dollars!");
        scan.close();
    }
}

// javac WheelOfFortuneChallenge.java ; java WheelOfFortuneChallenge