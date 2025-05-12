import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Stack;

/**
 * 
 * 
 * @author Michael Lee
 * @version
 * @sources https://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html
 */
public class PlayerGUI
{
    private Player player1;
    private Player player2;
    private Graphics2D graphics2D1; // will instantiate once GUI is started
    private Graphics2D graphics2D2; // will instantiate once GUI is started
    private JFrame gameWindow;
    private JFrame frame1;
    private JFrame frame2;
    private PlayerPanel panel1;
    private PlayerPanel panel2;

    private boolean guiStarted;

    /**
     * Constructs the GUI for a Player which will be shown only to the Player
     * @param p the Player
     * @param g2 the Graphics2D object for the Player's JPanel
     */
    public PlayerGUI(Player p1, Player p2, JFrame gw)
    {
        player1 = p1;
        player2 = p2;
        gameWindow = gw;
    }

    /**
     * Starts and creates the GUI (JFrames and JPanels)
     */
    public void start()
    {
        guiStarted = true;

        /* INITIALIZING PLAYER 1's FRAME AND PANEL */
        // TODO frame1 = new JFrame();
        panel1 = new PlayerPanel(player1);
        graphics2D1 = (Graphics2D)panel1.getGraphics();

        // Makes it so that the Panel is eligible to receive key input
        panel1.setFocusable(true);

        gameWindow.add(panel1);

        // Makes the game window the parent Component of the Player's Frame
        // TODO gameWindow.add(frame1);

        // TODO frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // TODO frame1.setResizable(false);
        // TODO frame1.setTitle(player1.getName() + "'s Hand");
        
        // Places and shows the Player 1's Frame at the bottom left corner
        // of the game window
        // TODO frame1.setLocation(0, gameWindow.getHeight() - PlayerPanel.PANEL_HEIGHT);
        // TODO frame1.setVisible(true);

        // Makes the player's Frame the parent Component of the Player's Panel
        // TODO frame1.add(panel1);
        // Makes the player's Frame fit the preferred dimensions of the player's Panel
        // TODO frame1.pack();

        /* INITIALIZING PLAYER 2's FRAME AND PANEL */
        frame2 = new JFrame();
        panel2 = new PlayerPanel(player2);
        graphics2D2 = (Graphics2D)panel2.getGraphics();

        // Makes it so that the Panel is eligible to receive key input
        panel2.setFocusable(true);

        // Makes the game window the parent Component of the Player's Frame
        // TODO gameWindow.add(frame2);

        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setResizable(false);
        frame2.setTitle(player2.getName() + "'s Hand");
        
        // Places and shows the Player 2's Frame at the bottom right corner
        // of the game window
        frame2.setLocation(gameWindow.getWidth() - PlayerPanel.PANEL_WIDTH, 
                           gameWindow.getHeight() - PlayerPanel.PANEL_HEIGHT);
        frame2.setVisible(true);

        // Makes the player's Frame the parent Component of the Player's Panel
        frame2.add(panel2);
        // Makes the player's Frame fit the preferred dimensions of the player's Panel
        frame2.pack();

        // Makes the game window fit the preferred dimensions of its components
        // TODO gameWindow.pack();
    }

    

    /**
     * 
     * @param playerNumber 1 to draw player1's hand or 2 for player 2's hand
     */
    public void drawPlayerHand(int playerNumber)
    {
        if (!guiStarted)
        {
            System.out.println("ERROR: Must start GUI first");
        }
        else if (playerNumber == 1)
        {
            Stack<Card> origNumberCardHand = player1.getNumberCardHand();
            Stack<Card> temp = new Stack<Card>();

            while (!origNumberCardHand.isEmpty())
            {
                temp.push(origNumberCardHand.pop());
            }

            
        }
        else
        {

        }
    }

    /**
     * Displays a card, with number value visible, being delt to the player.
     * @param card
     */
    public void dealCardAnimation(Card card)
    {

    }

    /**
     * Displays the punishment for the player at the end of a round.
     * The animation changes based on the final bet of the round.
     * @param bet
     */
    public void punishAnimation(int bet)
    {

    }
}
