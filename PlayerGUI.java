import java.awt.*;
import javax.swing.*;
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
    // These dimensions are required if images are DRAWN onto the screen.
    // If images are added to the screen, then pack() will take care of dimensions
    private final int FRAME_WIDTH = GameGUI.UNIT_SIZE * 8;
    private final int FRAME_HEIGHT = GameGUI.UNIT_SIZE * 1;

    private Player player1;
    private Player player2;
    private JFrame gameWindow;
    private JFrame frame1;
    private JFrame frame2;
    // TODO eventually factor these fields out into 
    // GameGUI or some other higher-level GUI class
    private Tile[] numberCardTiles;
    private Tile[] trumpCardTiles;

    private boolean tilesLoaded;
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
        

        /* INITIALIZING PLAYER 1's FRAME AND PANEL */
        // TODO frame1 = new JFrame();
        // TODO panel1 = new PlayerPanel(player1);
        // TODO graphics2D1 = (Graphics2D)panel1.getGraphics();

        // Makes it so that the Panel is eligible to receive key input
        // TODO panel1.setFocusable(true);

        // TODO gameWindow.add(panel1);

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
        // TODO panel2 = new PlayerPanel(player2);
        // TODO graphics2D2 = (Graphics2D)panel2.getGraphics();

        // Makes it so that the Panel is eligible to receive key input
        // TODO panel2.setFocusable(true);

        // Makes the game window the parent Component of the Player's Frame
        // TODO gameWindow.add(frame2);

        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setResizable(false);
        frame2.setTitle(player2.getName() + "'s Hand");
        
        // Places and shows the Player 2's Frame at the bottom right corner
        // of the game window
        // TODO frame2.setLocation(gameWindow.getWidth() - PlayerPanel.PANEL_WIDTH, 
        // TODO                    gameWindow.getHeight() - PlayerPanel.PANEL_HEIGHT);
        // TODO frame2.setVisible(true);

        // Makes the player's Frame the parent Component of the Player's Panel
        // TODO frame2.add(panel2);
        // Makes the player's Frame fit the preferred dimensions of the player's Panel
        // TODO frame2.pack();

        // Makes the game window fit the preferred dimensions of its components
        // TODO gameWindow.pack();

        guiStarted = true;
    }

    private void loadTiles()
    {

        tilesLoaded = true;
    }

    private void loadNumberCardTiles()
    {

    }

    private void loadTrumpCardTiles()
    {

    }

    private void setUpFrame(int playerNumber)
    {
        if (!tilesLoaded)
        {
            System.out.println("ERROR: Must call loadTiles first");
        }
        else if (playerNumber == 1)
        {
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame1.setResizable(false);
            frame1.setTitle(player1.getName() + "'s Hand");
            frame1.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
            frame1.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

            frame1.setLocation(gameWindow.getX() - frame1.getWidth(), 
                               gameWindow.getY() + gameWindow.getHeight());
        }
        else
        {

        }
        
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
