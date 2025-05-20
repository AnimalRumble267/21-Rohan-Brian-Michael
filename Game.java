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
        
        // check for who's alive, players' turns, then player action, in that order
        while (player1.isAlive() && player2.isAlive()) {
            // for round: 0 is draw, 1 is player 1 wins, 2 is player 2 wins
            int turnResult = deal();
            
            // run punishment and if dead, update GUI and resetGame();
        }
    }

    
    // Part 4: end of Game/restart
    public static void resetGame() {
        // assumption that at least one round has begun so interface has already been instantiated

        // this part will be converted to buttons/mouse click interface
        System.out.println("Player 1, do you wish to continue the game? (Y/N): ");
        char player1Choice = scanner.nextChar();
        System.out.println("Player 2, do you wish to continue the game? (Y/N): "); 
        char player2Choice = scanner.nextChar();

        if ((player1Choice == player2Choice)) ==  'Y') {
            // TODO restart the game with the same names
        }
            
        else {
            // TODO boot everyone out and return to start of game interface
        }
    }

}


