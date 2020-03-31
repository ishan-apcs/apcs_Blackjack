/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.Scanner;

/**
 *
 * @author dot
 */
public class Blackjack {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Deck deck;

        Player player;
        Player house;

        Scanner input = new Scanner(System.in);

        boolean run = true;

        int total = 0;

        System.out.println("Hello there, and welcome to Blackjack!");

        while (run) {
            System.out.println("\nHow much will you bet? Please respond with an integer value of dollars (USD).");
            int bet = (int) Double.parseDouble(input.nextLine().replaceAll("[^.0123456789]", ""));
            total -= bet;

            System.out.print("\nYou have bet $" + bet + ". ");

            if (total < 0) {
                System.out.println("You now owe $" + (total * -1) + ".");
            } else if (total > 0) {
                System.out.println("You now have $" + (total) + " in winnings.");
            } else {
                System.out.println("You have no winnings.");
            }

            boolean continuePlay = true;

            deck = new Deck();

            player = new Player();
            house = new Player();

            player.addCard(deck.getTopCard());
            player.addCard(deck.getTopCard());

            house.addCard(deck.getTopCard());
            house.addCard(deck.getTopCard());

            String reply = "hit";

            if (player.getScore() == 21) {
                continuePlay = false;
                System.out.println("\nYour hand: " + player.getHand());
                System.out.println("Your score: " + player.getScore());
                System.out.println("Congratulations! You got a blackjack, and you won!");
                total += bet * 2.5;
            }

            while (reply.equals("hit") && player.getScore() < 21 && continuePlay) {
                System.out.println("\nYour hand: " + player.getHand());
                System.out.println("Your score: " + player.getScore());
                System.out.println("House's visible card: " + house.getHand().get(0));

                System.out.println("\nWould you like to stand or hit? Please reply \"stand\" or \"hit\".");

                reply = input.nextLine().toLowerCase().replaceAll("[^standhit]", "");

                while (!(reply.equals("stand") || reply.equals("hit"))) {
                    System.out.println("\nI'm sorry, that's not a valid response. Please reply \"stand\" or \"hit\".");
                    reply = input.nextLine().toLowerCase().replaceAll("[^standhit]", "");
                }

                if (reply.equals("hit")) {
                    player.addCard(deck.getTopCard());
                }
            }

            if (continuePlay) {
                System.out.println("\nYour hand: " + player.getHand());
                System.out.println("Your score: " + player.getScore());
            }

            if (player.getScore() > 21) {
                System.out.println("\nOops! You busted! That's too bad...");
                continuePlay = false;
            } else if (!(reply.equals("stand") || reply.equals("hit"))) {
                exit();
            }

            if (continuePlay) {
                while (house.getScore() < 17) {
                    house.addCard(deck.getTopCard());
                }

                System.out.println("\nHouse's hand: " + house.getHand());
                System.out.println("House's score: " + house.getScore());

                if (house.getScore() > 21) {
                    System.out.println("\nCongratulations! The house busted, and you win!");
                    total += bet * 2;
                } else if (house.getScore() > player.getScore()) {
                    System.out.println("\nOops! You lost! That's too bad...");
                } else if (house.getScore() < player.getScore()) {
                    System.out.println("\nCongratulations! You win!");
                    total += bet * 2;
                } else {
                    System.out.println("\nIt's a push!");
                    total += bet;
                }
            }

            if (total < 0) {
                System.out.println("You now owe $" + (total * -1) + ".");
            } else if (total > 0) {
                System.out.println("You now have $" + (total) + " in winnings.");
            } else {
                System.out.println("You have no winnings.");
            }

            System.out.println("Do you want to play again? Please reply \"yes\" or \"no\".");
            reply = input.nextLine().toLowerCase().replaceAll("[^yesno]", "");

            while (!(reply.equals("yes") || reply.equals("no"))) {
                System.out.println("\nI'm sorry, that's not a valid response. Please reply \"yes\" or \"no\".");
                reply = input.nextLine().toLowerCase().replaceAll("[^yesno]", "");
            }

            if (reply.equals("no")) {
                run = false;
                System.out.println("That's too bad. Bye!");
            } else if (!reply.equals("yes")) {
                exit();
            }
        }
        input.close();
    }

    public static void exit() {
        System.out.println("\n* * * * * * * * * * * * * * * * * * *"
                + "\nI'm sorry, it appears that my creator can't code, or your computer can't Java. Either way, I broke. Goodbye!"
                + "\n* * * * * * * * * * * * * * * * * * *");
        System.exit(0);
    }
}
