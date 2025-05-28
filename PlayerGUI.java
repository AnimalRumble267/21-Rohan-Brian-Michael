import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Represents the graphical user interface (GUI) for a player. Diplays a player's number cards
 * and trump cards. Displays a trump card's description if prompted to.
 * 
 * @author Michael Lee
 * @version 5/28/2025
 * @sources https://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html
 */
public class PlayerGUI
{
    /** The width of the player's window */
    public static final int PLAYER_WINDOW_WIDTH = GameGUI.UNIT_SIZE * 8;
    /** The height of the player's window */
    public static final int PLAYER_WINDOW_HEIGHT = (int)(GameGUI.UNIT_SIZE * 1.5);

    private Player player;
    private int playerNumber;
    private JFrame frame;
    private PlayerPanel panel;
    private JFrame dFrame;
    private JPanel dPanel;
    private JTextPane dPane;
    private boolean tilesLoaded;
    private boolean guiStarted;
    private PlayerListener listener;

    /**
     * Initializes a <code>PlayerGUI</code> object which uses the information from
     * the given player. Stores the player's number only for window formatting.
     * @param p the player
     * @param pnum the player's number
     */
    public PlayerGUI(Player p, int pnum)
    {
        player = p;
        playerNumber = pnum;
    }

    /**
     * Starts the GUI. Initializes the window and sounds.
     */
    public void start()
    {
        /* INITIALIZING PLAYER 1's and 2's FRAME AND PANEL */
        frame = new JFrame();
        panel = new PlayerPanel(player);
        listener = new PlayerListener();
        setUpWindow();

        dFrame = new JFrame();
        dPanel = new JPanel();
        dPane = new JTextPane();
        setUpDWindow();

        guiStarted = true;
    }

    /**
     * Stops the GUI. Closes the game window in a fashion similar to hitting the "x"
     * manually.
     */
    public void stop()
    {
        guiStarted = false;
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    public void setAbleToGetClick(boolean status)
    {
        frame.setFocusable(status);
        panel.setFocusable(status);
    }

    private void setUpWindow()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.setTitle(player.getName() + "'s Hand");
        frame.setFocusable(false);
        frame.setLocationRelativeTo(null);
        panel.setPreferredSize(new Dimension(PLAYER_WINDOW_WIDTH, PLAYER_WINDOW_HEIGHT));
        frame.setSize(new Dimension(PLAYER_WINDOW_WIDTH, PLAYER_WINDOW_HEIGHT));
        panel.setDoubleBuffered(true);
        panel.setFocusable(false);
        panel.setVisible(false);      // Not visible yet
        frame.add(panel);
        frame.getContentPane().addMouseListener(listener);
        frame.setVisible(false);       // Not visible yet
        frame.setLocationRelativeTo(null);
    }

    private void setUpDWindow()
    {
        // dFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dFrame.setAlwaysOnTop(true);
        dFrame.setResizable(false);
        dFrame.setTitle("Description");
        dFrame.setFocusable(false);
        dPanel.setPreferredSize(new Dimension(GameGUI.UNIT_SIZE * 4, GameGUI.UNIT_SIZE));
        dPane.setPreferredSize(new Dimension(GameGUI.UNIT_SIZE * 4, GameGUI.UNIT_SIZE));
        dPane.setFocusable(false);
        dFrame.setSize(new Dimension(GameGUI.UNIT_SIZE * 4, GameGUI.UNIT_SIZE));
        dPanel.setDoubleBuffered(true);
        dPanel.setFocusable(false);
        dPanel.setVisible(false);
        dPane.setVisible(false);
        dPanel.add(dPane);
        dFrame.add(dPanel);
        dFrame.setVisible(false);
        dFrame.setLocation(frame.getX(), frame.getY() - dFrame.getHeight());
    }

    /**
     * Sets the frame's location on the screen.
     * @param x the x coordinate of the location
     * @param y the y coordinate of the location
     */
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
     * Redraws this player's hand based on the player's information
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

    /**
     * Writes the description for the trump card based on its information
     * @param value the value of the trump card
     * @param type the type of the trump card
     */
    public void writeTrumpCardDescription(int value, String type)
    {
        dFrame.setTitle((GameGUI.TRUMP_CARD_DESCRIPTIONS.get(type + value))[0]);
        dPane.setText((GameGUI.TRUMP_CARD_DESCRIPTIONS.get(type + value))[1]);
        dPane.setVisible(true);
        dPanel.setVisible(true);
        dFrame.setVisible(true);
    }

    /** Clears the description for the trump card*/
    public void clearTrumpCardDescription()
    {
        dFrame.setTitle("Description");
        dPane.setText("");
        dFrame.setVisible(false);
        dPanel.setVisible(false);
        dPane.setVisible(false);
    }

    /**
     * Waits for and returns the next mouse click within the player's window.
     * @return the next mouse click
     */
    public MouseEvent nextMouseClick()
    {
        if (!guiStarted)
        {
            System.out.println("ERROR: Must start GUI first");
            return null;
        }
        return panel.nextMouseClick();
    }
}
