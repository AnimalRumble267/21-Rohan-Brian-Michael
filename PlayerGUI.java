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
    private final int PANEL_WIDTH = GameGUI.UNIT_SIZE * 8;
    private final int PANEL_HEIGHT = GameGUI.UNIT_SIZE * 1;

    private Player[] players;
    private JFrame gameWindow;
    private JFrame[] frames;
    private PlayerPanel[] panels;

    private boolean tilesLoaded;
    private boolean guiStarted;

    private PlayerListener listener = new PlayerListener();

    /**
     * Constructs the GUI for a Player which will be shown only to the Player
     * @param p the Player
     * @param g2 the Graphics2D object for the Player's JPanel
     */
    public PlayerGUI(Player p1, Player p2, JFrame gw)
    {
        players = new Player[2];
        frames = new JFrame[2];
        panels = new PlayerPanel[2];
        players[0] = p1;
        players[1] = p2;
        gameWindow = gw;
    }

    /**
     * Starts and creates the GUI (JFrames and JPanels)
     */
    public void start()
    {
        /* INITIALIZING PLAYER 1's and 2's FRAME AND PANEL */
        for (int i = 0; i < 2; i++)
        {
            frames[i] = new JFrame();
            panels[i] = new PlayerPanel(players[i]);
            setUpWindow(i + 1);
        }
        guiStarted = true;
    }

    private void setUpWindow(int playerNumber)
    {
        int index = 0;
        if (playerNumber == 1)
        {
            index = 0;
        }
        else if (playerNumber == 2)
        {
            index = 1;
        }
        else
        {
            System.out.println("ERROR: Invalid player number");
            return;
        }
        JFrame chosenFrame = frames[index];
        Player chosenPlayer = players[index];
        JPanel chosenPanel = panels[index];
        chosenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chosenFrame.setResizable(false);
        chosenFrame.setTitle(chosenPlayer.getName() + "'s Hand");
        chosenFrame.setFocusable(true);
        chosenPanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        chosenPanel.setDoubleBuffered(true);
        chosenPanel.addMouseListener(listener);
        chosenPanel.setFocusable(true);
        chosenPanel.setVisible(true);
        chosenFrame.add(chosenPanel);
        chosenFrame.pack();
        // Places this player's frame on the bottom left corner of the game's window
        chosenFrame.setLocation(gameWindow.getX() + (-chosenFrame.getWidth() + (index * chosenFrame.getWidth() + index * gameWindow.getWidth())),
                                gameWindow.getY() + (gameWindow.getHeight() - chosenFrame.getHeight()));
        chosenFrame.setVisible(true);
        chosenFrame.getContentPane().setVisible(true);
    }

    /**
     *   
     * @param playerNumber 1 to draw player1's hand or 2 for player 2's hand
     */
    public void drawPlayerHand(int playerNumber)
    {
        int index = 0;
        if (!guiStarted)
        {
            System.out.println("ERROR: Must start GUI first");
            return;
        }
        else
        {
            if (playerNumber == 1)
            {
                index = 0;
            }
            else if (playerNumber == 2)
            {
                index = 1;
            }
            else
            {
                System.out.println("ERROR: Invalid player number");
                return;
            }
        }
        // Repainting the panel works; don't know about repainting the frame though
        panels[index].repaint();
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
     * The screen will go black, then play a sound based on if the player
     * was hit or not, then cut back to the player being hit or not hit
     * @param bet
     */
    public void punishAnimation(boolean dead)
    {
        
    }

    /**
     * Will wait until a point is clicked on the screen. Checks 
     * 30 times per second.
     * @return the point that was clicked
     */
    public Point nextMouseClick(int playerNumber)
    {
        int index;
        if (!guiStarted)
        {
            System.out.println("ERROR: Must start GUI first");
            return null;
        }
        else
        {
            if (playerNumber == 1)
            {
                index = 0;
            }
            else if (playerNumber == 2)
            {
                index = 1;
            }
            else
            {
                System.out.println("ERROR: Invalid player number");
                return null;
            }
        }

        Point pointClicked = null;
        PlayerPanel chosenPanel = panels[index];
        while (pointClicked == null)
        {
            pointClicked = chosenPanel.nextMouseClick();
            if (!GameGUI.pointInBounds(pointClicked, chosenPanel))
            {
                pointClicked = null;
            }
        }
        return pointClicked;
    }
}
