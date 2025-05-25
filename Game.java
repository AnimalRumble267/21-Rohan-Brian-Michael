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

    private static Player player1;
    private static Player player2;

    private static Dealer dealer;

    private static boolean gameOver = false;

    public static void main(String[] args)
    {
        // Part 1: Instantiations and Game Setup

        // Player and name readings
        System.out.println("Player 1, enter your name: ");
        player1name = scanner.nextLine();
        player1 = new Player(player1name, 1);
        System.out.println("Player 2, enter your name: ");
        player2name = scanner.nextLine(); 
        player2 = new Player(player2name, 2);

        dealer = new Dealer(player1, player2, 1);
     
        // Part 2: build start of game interface (GUI stuff)
        dealer.startGUI();
        player1.startGUI();
        player2.startGUI();
        player1.setFrameLocation(dealer.getGameWindowX() - PlayerGUI.PLAYER_WINDOW_WIDTH, 
                                 dealer.getGameWindowY() + GameGUI.GAME_WINDOW_HEIGHT - PlayerGUI.PLAYER_WINDOW_HEIGHT);
        player2.setFrameLocation(dealer.getGameWindowX() + GameGUI.GAME_WINDOW_WIDTH, 
                                 dealer.getGameWindowY() + GameGUI.GAME_WINDOW_HEIGHT - PlayerGUI.PLAYER_WINDOW_HEIGHT);
        
        while (!gameOver) {
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
                    if (turnResult == 1) {
                        dealer.punish(player2, dealer.getBet());
                    }
                    else {
                        dealer.punish(player1, dealer.getBet());
                    }
                }

                if ((player1.isAlive() && player2.isAlive())) {
                    dealer.incrementBet();
                }
                
                
            }

            // Part 4: reset Game
            System.out.println("Player 1, do you wish to continue the game? (Y/N): ");
            player1Choice = scanner.nextLine();
            System.out.println("Player 2, do you wish to continue the game? (Y/N): "); 
            player2Choice = scanner.nextLine();

            if (player1Choice == "Y" && player2Choice == "Y") {
                player1 = new Player(player1.getName(), 1);
                player2 = new Player(player2.getName(), 2);
                dealer = new Dealer(player1, player2, 1);
            }
            else {
                // end whole program
                gameOver = true;
            }
        }
    }
}