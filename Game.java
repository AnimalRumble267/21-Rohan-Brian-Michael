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
    private static Scanner scanner = new Scanner(System.in);
    
    private static String player1name;
    private static String player2name;
    private static String player1Choice;
    private static String player2Choice;

    private static Player player1 = new Player(player1name, 1);
    private static Player player2 = new Player(player2name, 2);

    

    private static Dealer dealer = new Dealer(player1, player2, 1);

    public static void main(String[] args)
    {
        // Part 1: Instantiations and Game Setup
        
        // Player and name readings
        System.out.println("Player 1, enter your name: ");
        player1name = scanner.nextLine();
        System.out.println("Player 2, enter your name: ");
        player2name = scanner.nextLine(); 
     
        // Part 2: build start of game interface (GUI stuff)

        // very not done but will do this part last
        player1.startGUI();
        player2.startGUI();
        
        // Part 3; Game loop
        // check for who's alive, players' turns, then player action, in that order
        while (player1.isAlive() && player2.isAlive()) {
            // for deal:
            // 0 = draw
            // 1 = player 1 wins
            // 2 = player 2 wins
            // 3 = error

            int turnResult = dealer.deal();

            // run punishment and if dead, update GUI and resetGame()

            if (turnResult == 1 || turnResult == 2) {
                dealer.punish(dealer.getBet());
            }
            
        }
    }

    
    // Part 4: end of Game/restart
    public static void resetGame() {
        // assumption that at least one round has begun so interface has already been instantiated

        // this part will be converted to buttons/mouse click interface
        System.out.println("Player 1, do you wish to continue the game? (Y/N): ");
        player1Choice = scanner.nextLine();
        System.out.println("Player 2, do you wish to continue the game? (Y/N): "); 
        player2Choice = scanner.nextLine();

        if (player1Choice == "Y" && player2Choice == "Y") {
            // dealer needs to update;
            dealer.resetBet();
                
        }
            
        else {
            // TODO boot everyone out and return to start of game interface
        }
    }

}