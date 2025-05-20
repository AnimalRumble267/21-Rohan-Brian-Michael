import java.util.*;
import java.util.Scanner;

/**
 * 
 * 
 * @author brian
 * @version
 * 
 */
public class Game
{
    public static void main(String[] args)
    {
        /*
         * [ignore] intializes all other class (major classes, which involves instantiates Dealer, Deck, Player, Card, NumberCard, TrumpCard, and GUI classes,  
         * [ignore] which involve GameGUI and PlayerGUI
         * 
         * game loop (input name) -- use scanner; wait for both of the players to submit their name;
         * constantly check for player turn, and whether or not player is alive
         * 
         * deck, name, loop rounds until deck returns exit code of either player winning/losing (game deals with punish)
         * player pannel is visible based on if it is their turn
         * restart by asking the names
         * 
         */

        // Part 1: Instantiations and Game Setup
        // TODO/things that have yet to be incldued: starting deck, starting cards, GUI/sound/interface instantiations

        // Player and name readings
        Scanner scanner = new Scanner(System.in);

        System.out.println("Player 1, enter your name: ");
        String player1name = scanner.nextLine();
        System.out.println("Player 2, enter your name: ");
        String player2name = scanner.nextLine(); 
        
        Player player1 = new Player(player1name, 1);
        Player player2 = new Player(player2name, 2);

        // Dealer
        Dealer dealer = new Dealer(player1, player2, 0);
        
        // Card, NumberCard, TrumpCard
        Card card = new Card();
        NumberCard numberCard = new NumberCard();
        TrumpCard trumpCard = new TrumpCard();
        // TODO inquire abt instatiating all variations of number and trump cards within Dealer
        
        // Deck
        // TODO discuss this bc I have no clue where the card deck is need card deck to instantiate Deck
        Deck active = new Deck();
        
        // Part 2: build start of game interface (GUI stuff)

        

        
        // Part 3; Game loop

        // determinig order first

        if (Math.random() < 0.5) {
            player1.setTurn(true);
        }
        else {
            player2.setTurn(true);
        }

        // check for who's alive, players' turns, then player action, in that order

        while (player1.isAlive() && player2.isAlive()) {

            if (
                // TODO scanner action for mouseclick




                // hit: draw, calcaulte value, if it's over 21 run punishment

                



                // stand: end player turn, 



                




            // check for punishment

            if (player1.numberCardHand


            
        }


            


        // Part 4: end of Game/restart
        
        // this part will be converted to buttons/mouse click interface
        System.out.println("Player 1, do you wish to continue the game? (Y/N): ");
        Char player1Choice = scanner.nextLine();
        System.out.println("Player 2, do you wish to continue the game? (Y/N): "); 
        Char player2Choice = scanner.nextLine();

        if ((player1Choice.equals(player2Choice)).equals("Y")) {
            // TODO restart the game with the same names
        }
            
        else {
            // TODO boot everyone out and return to start of game interface
        }
    }
}
