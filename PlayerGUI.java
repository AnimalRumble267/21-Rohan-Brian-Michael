import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

/**
 * 
 * 
 * @author Michael Lee
 * @version
 * @sources https://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html
 */
public class PlayerGUI
{

    // These dimensions are required because images will be drawn 
    public static final int PLAYER_PANEL_WIDTH = GameGUI.UNIT_SIZE * 8;
    public static final int PLAYER_PANEL_HEIGHT = GameGUI.UNIT_SIZE * 1 + 32;

    private Player player;
    private int playerNumber;
    private JFrame frame;
    private PlayerPanel panel;

    private boolean tilesLoaded;
    private boolean guiStarted;

    private PlayerListener listener;

    /**
     * Constructs the GUI for a Player which will be shown only to the Player
     * @param p the Player
     * @param g2 the Graphics2D object for the Player's JPanel
     */
    public PlayerGUI(Player p, int pnum)
    {
        player = p;
        playerNumber = pnum;
        listener = new PlayerListener();
    }

    /**
     * Starts and creates the GUI (JFrames and JPanels)
     */
    public void start()
    {
        /* INITIALIZING PLAYER 1's and 2's FRAME AND PANEL */
        frame = new JFrame();
        panel = new PlayerPanel(player);
        listener = new PlayerListener();
        setUpWindow();
        guiStarted = true;
    }

    private void setUpWindow()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle(player.getName() + "'s Hand");
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);
        panel.setPreferredSize(new Dimension(PLAYER_PANEL_WIDTH, PLAYER_PANEL_HEIGHT));
        frame.setSize(new Dimension(PLAYER_PANEL_WIDTH, PLAYER_PANEL_HEIGHT));
        panel.setDoubleBuffered(true);
        panel.setFocusable(true);
        panel.setVisible(false);      // Not visible yet
        frame.add(panel);
        frame.getContentPane().addMouseListener(listener);
        frame.setVisible(false);       // Not visible yet
        frame.setLocationRelativeTo(null);
    }

    public void setFrameLocation(int x, int y)
    {
        if (!guiStarted)
        {
            System.out.println("ERROR: Must start GUI first - setFrameLocation(int x, int y)");
            return;
        }
        frame.setLocation(x, y);
    }
    
    /**
     * Updates the Player's hand. Only draws it onto the screen if it is the
     * Player's turn. If it is not the Player's turn, will hide the Player's hand.
     * @param playerNumber 1 to draw player1's hand or 2 for player 2's hand
     */
    public void updateHand()
    {
        if (!guiStarted)
        {
            System.out.println("ERROR: Must start GUI first - updateHand()");
            return;
        }

        // Always repaints the panels; repaints either before making the window visible 
        // or after making the window hidden
        if (player.isTurn())
        {
            panel.repaint();
            frame.setVisible(true);
            panel.setVisible(true);
        }
        else
        {
            frame.setVisible(false);
            panel.setVisible(false);
            panel.repaint();
        }
    }

    /* I'm not sure if PlayerGUI should be in charge of the two methods below... */

    /**
     * Waits for mouse input and removes the card that was clicked
     * @param playerNumber
     */
    /* public NumberCard removeNumberCard(int playerNumber, Point click)
    {
        int clickCol = (int)click.getX() / GameGUI.UNIT_SIZE;
        int index;
        if (clickCol < players[playerNumber - 1].getNumberCardHand().size())
        {
            index = clickCol;
            return players[playerNumber - 1].getNumberCardHand().remove(index);
        }
        return null;
    } */

    /* public TrumpCard removeTrumpCard(int playerNumber, Point click)
    {
        int clickCol = (int)click.getX() / GameGUI.UNIT_SIZE;
        int index;
        if (clickCol >= players[playerNumber - 1].getNumberCardHand().size())
        {
            index = clickCol - players[playerNumber - 1].getNumberCardHand().size();
            return players[playerNumber - 1].getTrumpCardHand().remove(index);
        }
        return null;
    } */

    /**
     * Will wait until a point is clicked on the screen. Checks 30 times
     * per second. Returns the mouse event received from the listener.
     * @return the mouse event (can return which mouse button was pressed)
     */
    public MouseEvent nextMouseClick(int playerNumber)
    {
        if (!guiStarted)
        {
            System.out.println("ERROR: Must start GUI first");
            return null;
        }
        return panel.nextMouseClick();
    }
}
