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
    /* 
     * (not updated as of 4/20, will update to include the description of the fourt parts + static methods)
     * game loop (input name) -- use scanner; wait for both of the players to submit their name;
     * constantly check for player turn, and whether or not player is alive
     * 
     * deck, name, loop rounds until deck returns exit code of either player winning/losing (game deals with punish)
     * player pannel is visible based on if it is their turn
     * restart by asking the names
     * 
     */
    public static void main(String[] args)
    {
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
        
        // Card
        // TODO NumberCard, TrumpCard Deck?
        Card card = new Card();
        
        // give each player 1 hidden card and 1 exposed card

        // TODO help wth value
        player1.giveNumberCard(new NumberCard<Card>(active.draw()), true);
        player1.giveNumberCard(new NumberCard<Card>(active.draw()), false);
        player1.updateHand();

        player2.giveNumberCard(new NumberCard<Card>(active.draw()), true);
        player2.giveNumberCard(new NumberCard<Card>(active.draw()), false);
        player2.updateHand();

        // TODO inquire abt instatiating all variations of number and trump cards within Dealer
        // Deck
        // TODO discuss this bc I have no clue where the card deck is need card deck to instantiate Deck
        Deck active = new Deck(SOMETHINGHEREARGHHHWEHGEWGL:JWEL:GJKL:WEJGL:WJEL:TJLK:);
        deck.shuffle();
        
        // Part 2: build start of game interface (GUI stuff)

        // very not done but will do this part last
        player1.startGUI();
        player2.startGUI();
        
    
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
            // first turn in one round

            // 1 means player 1 went first, 2 means player 2 went first
            int temp;
            
            if (player1.isTurn) {
                playerAction(player1, 1, dealer.getBet());
                temp = 1;
            }
            else {
                playerAction(player2, 2, dealer.getBet());
                temp = 2;
            }
            
            //second turn in one round
            if (temp == 1) {
                playerAction(player2, 2, dealer.getBet());
            }
            else {
                playerAction(player1, 1, dealer.getBet());
            }
            
            // TODO scanner action for mouseclick

            // check for punishment

            if (player1.numberCardHand
                
            
        }

        // reset game will be handled in playerAction 
    }

    public static int playerAction(Player player, int playerNumber, int action) 
    {
        // note: selecting trump card does not end your turn

        // 1 = end game, someone died; 2 = passed the turn; 3 = drew a trump card, ask for another action
        int temp = -1;
        
        
        if (action == 1) 
        {
            // hit: draw, calcaulte value, if it's over 21 run punishment
            player.giveNumberCard(active); // TODO how to handle trump card...
            player.update();
            if (player.calcualte() > 21) 
            {
                boolean deathStatus = dealer.punish(player, dealer.getBet());
                if (deathStatus) 
                {
                    return 1;
                }
            }
        }

        else if (action == 2) 
        {
            // stand: end player turn
            player.setTurn(false);

            if (playerNumber == 1)
            {
                player2.setTurn(true);
            }
                
            else 
            {
                player1.setTurn(true);
            }

            return 2;
        }

        else 
        {
            int trumpCardIdentifer = action - 3; 
            // TODO do action based on trump card
            return 3;
        }
    }

    
    // Part 4: end of Game/restart
    public static void resetGame() {
        // assumption that at least one round has begun so interface has already been instantiated

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


