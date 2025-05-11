import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
    private Graphics2D graphics2D;
    private JFrame gameWindow;
    private JFrame frame1;
    private JFrame frame2;
    private JPanel panel1;
    private JPanel panel2;

    private boolean guiStarted;

    private final int ORIGINAL_TILE_SIZE = 16;
    private final int SCALE = 3;
    private final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    private final int MAX_SCREEN_ROW = 3;       // TODO Change to fit
    private final int MAX_SCREEN_COL = 8;       // TODO Change to fit
    private final int SCREEN_HEIGHT = MAX_SCREEN_ROW * TILE_SIZE;
    private final int SCREEN_WIDTH = MAX_SCREEN_COL * TILE_SIZE;

    /**
     * Constructs the GUI for a Player which will be shown only to the Player
     * @param p the Player
     * @param g2 the Graphics2D object for the Player's JPanel
     */
    public PlayerGUI(Player p1, Player p2, Graphics2D g2, JFrame gw)
    {
        player1 = p1;
        player2 = p2;
        graphics2D = g2;
        gameWindow = gw;
    }

    /**
     * Starts and creates the GUI (JFrames and JPanels)
     */
    public void start()
    {
        guiStarted = true;

        /* INITIALIZING PLAYER 1's FRAME AND PANEL */
        frame1 = new JFrame();
        panel1 = new JPanel();

        // Makes the game window the parent Component of the Player's Frame
        gameWindow.add(frame1);

        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setResizable(false);
        frame1.setTitle(player1.getName() + "'s Hand");
        
        // Places and shows the Player 1's Frame at the bottom left corner
        // of the game window
        frame1.setLocation(0, gameWindow.getHeight() - SCREEN_HEIGHT);
        frame1.setVisible(true);

        // Makes the player's Frame the parent Component of the Player's Panel
        frame1.add(panel1);

        /* INITIALIZING PLAYER 2's FRAME AND PANEL */
        frame2 = new JFrame();
        panel2 = new JPanel();

        // Makes the game window the parent Component of the Player's Frame
        gameWindow.add(frame2);

        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setResizable(false);
        frame2.setTitle(player2.getName() + "'s Hand");
        
        // Places and shows the Player 2's Frame at the bottom right corner
        // of the game window
        frame2.setLocation(gameWindow.getWidth() - SCREEN_WIDTH, 
                           gameWindow.getHeight() - SCREEN_HEIGHT);
        frame2.setVisible(true);

        // Makes the player's Frame the parent Component of the Player's Panel
        frame2.add(panel2);

        // Makes the game window fit the preferred dimensions of its components
        gameWindow.pack();
    }

    public void load

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
            for (Card numberCard : player1.get)
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
