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
        Dealer dealer = new Dealer(player1, player2, 1);
        
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
            int turnIndicator;
            
            // 1 = end game, someone died; 2 = passed the turn; 3 = drew a trump card, ask for another action
            int returnerAction = -1;
            
            if (player1.isTurn) {
                playerAction(player1, 1, dealer.getBet());
                
                turnIndicator = 1;
            }
            else {
                playerAction(player2, 2, dealer.getBet());
                turnIndicator = 2;
            }

            // check for punishment
            if (returnerAction == 1) {
                resetGame();
            }
                
            else if (returnerAction == 3) {
                if (turnIndicator == 1) {
                    playerAction(player1, 1, dealer.getBet());
                }
                else { 
                    playerAction(player2, 2, dealer.getBet());
                }
            }

            //second turn in one round
            if (turnIndicator == 1) {
                playerAction(player2, 2, dealer.getBet());
            }
                
            else {
                playerAction(player1, 1, dealer.getBet());
            }

            // check for punishment
            if (returnerAction == 1) {
                resetGame();
            }
                
            else if (returnerAction == 3) {
                if (turnInicator == 1) {
                    playerAction(player2, 2, dealer.getBet());
                }
                else { 
                    playerAction(player1, 1, dealer.getBet());
                }
            }
            
        }
    }

    public static void playerAction(Player player, int playerNumber, int action) {
        // note: selecting trump card does not end your turn
        
        if (action == 1) {
            // hit: draw, calcaulte value, if it's over 21 run punishment
            player.giveNumberCard(active); // TODO how to handle trump card...
            player.update();
            if (player.calcualte() > 21) {
                boolean deathStatus = dealer.punish(player, dealer.getBet());
                if (deathStatus) {
                    returnerAction =  1;
                }
            }
        }

        else if (action == 2) {
            // stand: end player turn
            player.setTurn(false);

            if (playerNumber == 1) {
                player2.setTurn(true);
            }
                
            else {
                player1.setTurn(true);
            }

            returnerAction = 2;
        }

        else {
            // TODO: activate the action for the trump card
            int trumpCardIdentifer = action - 3; 

            
            returnerAction = 3;
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


