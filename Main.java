package adventuregame;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        // Game variables
        String[] enemies = {"Skeleton", "Zombie", "Warrior", "Assassin"};
        int enemyMaxHealth = 75; // Enemies maximum health
        int enemyAttackDamage = 25; // Enemy attack damage

        // Player variables
        int playerHealth = 100; // Player health
        int playerAttackDamage = 50; // Player attack damage
        int playerHealthPotions = 3; // The amount of health potions the player carries
        int playerHealthPotionHealAmount = 30; // The amount the potion heals back
        int playerHealthPotionDropChance = 50; // Percentage of potion drop chance from enemy

        boolean running = true; // Is game running

        // Start of the game:
        System.out.println("Welcome to the Dungeon!");

        GAME:
        while(running) {
            System.out.println("----------------------------------------------");

            String enemy = enemies[rand.nextInt(enemies.length)]; // Pick a random enemy from the array
            int enemyHealth = rand.nextInt(enemyMaxHealth); // Set a random number of health for the next enemy
            System.out.println("\t# " + enemy + " has appeared! #\n");

            while(enemyHealth > 0) {
                // Print out the parameters of the player and the current enemy
                System.out.println("\tYour HP: " + playerHealth);
                System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink health potion");
                System.out.println("\t3. Run!");

                // Let the player choose an action
                System.out.print("Enter your choice: ");
                int input = in.nextInt();
                // Attack:
                if (input == 1) {
                    int damageDealt = rand.nextInt(playerAttackDamage);
                    int damageTaken = rand.nextInt(enemyAttackDamage);

                    enemyHealth -= damageDealt;
                    System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage.");
                    // Damage player if enemy still alive
                    if (enemyHealth > 0) {
                        playerHealth -= damageTaken;
                        System.out.println("\t> Your receive " + damageTaken + " in retaliation!");
                        // If player dies, end the game
                        if (playerHealth < 1) {
                            System.out.println("\t> You have taken too much damage, you are too weak to go on!");
                            break;
                        }
                    } else {
                        System.out.println("\t> You successfully killed the " + enemy + "!");
                    }

                // Drink a potion
                } else if (input == 2) {
                    // Check if player has any potion to drink
                    if (playerHealthPotions > 0) {
                        playerHealth += playerHealthPotionHealAmount;
                        playerHealthPotions--;
                        System.out.println("\t> You healed back to " + playerHealth + ". Health potions left: " + playerHealthPotions + ".");

                        int damageTaken = rand.nextInt(enemyAttackDamage);
                        playerHealth -= damageTaken;
                        System.out.println("\t> Your receive " + damageTaken + " in retaliation!");
                        // If player dies, end the game
                        if (playerHealth < 1) {
                            System.out.println("\t> You have taken too much damage, you are too weak to go on!");
                            break;
                        }
                    // If there is no potion left
                    } else {
                        System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one.");
                    }
                // Run away
                } else if (input == 3) {
                    System.out.println("\t> You run away from the " + enemy + "!");
                    continue GAME;
                // Invalid number given
                } else {
                    System.out.println("\tINVALID COMMAND!");
                }
            }
            // Check if we break out from the fight loop because player is dead, then end the game
            if (playerHealth < 1) {
                System.out.println("You limp out of the dungeon, weak from battle.");
                break;
            }
            // Else, we defeated the enemy, show current player status
            System.out.println("----------------------------------------------");
            System.out.println(" # " + enemy + " was defeated! # ");
            System.out.println(" # You have " + playerHealth + " HP left. # ");
            // Check if enemy drop a health potion, give it to the player
            if (rand.nextInt(100) > playerHealthPotionDropChance) {
                playerHealthPotions++;
                System.out.println(" # " + enemy + " dropped a health potion! # ");
                System.out.println(" # You have " + playerHealthPotions + " health potion(s). # ");
            }
            System.out.println("----------------------------------------------");
            // Give the player the choice to continue the game, or quit
            System.out.println("What would you like to do now?");
            System.out.println("1. Continue fighting");
            System.out.println("2. Exit dungeon");
            System.out.print("Enter choice: ");
            int input = in.nextInt();
            // Accept only one of the two number as an answer
            while (input != 1 && input != 2) {
                System.out.println("Invalid command");
                System.out.print("Enter choice: ");
                input = in.nextInt();
            }
            // Continue game
            if (input == 1) {
                System.out.println("You continue on your adventure!");
            // Exit game
            } else if (input == 2) {
                System.out.println("You exit the dungeon, successful from your adventures!");
                break;
            }
        }
        // END OF GAME
        System.out.println(" ###### END OF GAME ###### ");
        System.out.println("Thanks for playing!");
        System.out.println("##################################");
    }
}
