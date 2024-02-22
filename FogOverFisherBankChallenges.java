import java.util.Scanner;
import java.util.Random;

public class FogOverFisherBankChallenges {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        String[][] player_field = {{"-","-","-","-","-","-","-","-"},{"-","-","-","-","-","-","-","-"},{"-","-","-","-","-","-","-","-"},{"-","-","-","-","-","-","-","-"},
        {"-","-","-","-","-","-","-","-"},{"-","-","-","-","-","-","-","-"},{"-","-","-","-","-","-","-","-"},{"-","-","-","-","-","-","-","-"}};
        String[][] secret_field = {{"-","-","-","-","-","-","-","-"},{"-","-","-","-","-","-","-","-"},{"-","-","-","-","-","-","-","-"},{"-","-","-","-","-","-","-","-"},
        {"-","-","-","-","-","-","-","-"},{"-","-","-","-","-","-","-","-"},{"-","-","-","-","-","-","-","-"},{"-","-","-","-","-","-","-","-"}};
        String[][] output_field = {{"-","-","-","-","-","-","-","-"},{"-","-","-","-","-","-","-","-"},{"-","-","-","-","-","-","-","-"},{"-","-","-","-","-","-","-","-"},
        {"-","-","-","-","-","-","-","-"},{"-","-","-","-","-","-","-","-"},{"-","-","-","-","-","-","-","-"},{"-","-","-","-","-","-","-","-"}};
        int row = 0;
        int col = 0;
        int row_com = 0;
        int col_com = 0;
        int player_sunken_ship = 0;
        int ai_sunken_ship = 0;
        
        for (int i = 1; i < 5; i++) { // Placing player's ships
            
            do {
                System.out.println("Placing ship #" + i);
                System.out.println("Please enter the row you want to place at:");
                row = scan.nextInt();
                System.out.println("Please enter the column you want to place at:");
                col = scan.nextInt();
            } while (row >= 8 || col >= 8 || player_field[row][col] == "O");
            
            player_field[row][col] = "O";

        }
         
        for (int i = 0; i < 4; i++) { // Placing computer's ships
            
            do {
                row_com = rand.nextInt(8);
                col_com = rand.nextInt(8);
            } while (player_field[row_com][col_com] == "O");
            
            secret_field[row_com][col_com] = "O";

        }
        
        while (player_sunken_ship < 4 && ai_sunken_ship < 4) {
            int player_guessed_row = 0;
            int player_guessed_col = 0;
            int ai_guessed_row = 0;
            int ai_guessed_col = 0;
            int ship_count = 0;

            // Print out current state of the boards

            for (int row_ai_print = 0; row_ai_print < output_field.length; row_ai_print++) {

                for (int col_ai_print = 0; col_ai_print < output_field[0].length; col_ai_print++) {
            
                    System.out.print(output_field[row_ai_print][col_ai_print]);
        
                }
    
                System.out.println();
        
            }
    
            System.out.println("==============");
            
            for (int row_player_print = 0; row_player_print < player_field.length; row_player_print++) {
    
                for (int col_player_print = 0; col_player_print < player_field[0].length; col_player_print++) {
            
                    System.out.print(player_field[row_player_print][col_player_print]);
        
                }
        
                System.out.println();
    
            }

            System.out.println();

            System.out.println("Player ships sunken: " + player_sunken_ship);
            System.out.println("AI ships sunken: " + ai_sunken_ship);

            System.out.println();

            // Player shoots somewhere

            do {
                System.out.println("Please enter the row you want to shoot at:");
                player_guessed_row = scan.nextInt();
                System.out.println("Please enter the column you want to shoot at:");
                player_guessed_col = scan.nextInt();
            } while (player_guessed_row >= 8 || player_guessed_col >= 8 || output_field[player_guessed_row][player_guessed_col] != "-");

            // Did the player shoot a ship

            if (secret_field[player_guessed_row][player_guessed_col] == "O") { // Player found a ship
                System.out.println("You found a ship!");
                System.out.println();
                output_field[player_guessed_row][player_guessed_col] = "X";
                ai_sunken_ship += 1;
            } else { // Player didn't find a ship
                for (int i = 0; i < 8; i++) {
                    if (secret_field[player_guessed_row][i] == "O") {
                        ship_count += 1;
                    }
                }
                for (int i = 0; i < 8; i++) {
                    if (secret_field[i][player_guessed_col] == "O") {
                        ship_count += 1;
                    }
                }
                output_field[player_guessed_row][player_guessed_col] = String.valueOf(ship_count);
            }

            // Computer shoots at player

            do {
                ai_guessed_row = rand.nextInt(8);
                ai_guessed_col = rand.nextInt(8);
            } while (player_field[ai_guessed_row][ai_guessed_col] != "-" && player_field[ai_guessed_row][ai_guessed_col] != "O");
            
            ship_count = 0;

            if (player_field[ai_guessed_row][ai_guessed_col] == "O") { // AI found a ship
                System.out.println("The AI found a ship!");
                System.out.println();
                player_field[ai_guessed_row][ai_guessed_col] = "X";
                player_sunken_ship += 1;
            } else { // AI didn't find a ship
                for (int i = 0; i < 8; i++) {
                    if (player_field[ai_guessed_row][i] == "O" || player_field[i][ai_guessed_col] == "X") {
                        ship_count += 1;
                    }
                }
                for (int i = 0; i < 8; i++) {
                    if (player_field[i][ai_guessed_col] == "O" || player_field[i][ai_guessed_col] == "X" ) {
                        ship_count += 1;
                    }
                }
                player_field[ai_guessed_row][ai_guessed_col] = String.valueOf(ship_count);
            }
        }

        // Print out current state of the boards

        for (int row_ai_print = 0; row_ai_print < output_field.length; row_ai_print++) {

            for (int col_ai_print = 0; col_ai_print < output_field[0].length; col_ai_print++) {
        
                System.out.print(output_field[row_ai_print][col_ai_print]);
    
            }

            System.out.println();
    
        }

        System.out.println("==============");
        
        for (int row_player_print = 0; row_player_print < player_field.length; row_player_print++) {

            for (int col_player_print = 0; col_player_print < player_field[0].length; col_player_print++) {
        
                System.out.print(player_field[row_player_print][col_player_print]);
    
            }
    
            System.out.println();

        }

        System.out.println();

        // Print whether the player won, lost, or tied
        
        if (player_sunken_ship == 4 && ai_sunken_ship == 4) {
            System.out.println("You tied!");
        } else if (player_sunken_ship == 4){
            System.out.println("You lost!");
        } else {
            System.out.println("You won!");
        }
        scan.close();
    }
}
