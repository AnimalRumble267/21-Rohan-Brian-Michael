import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

/**
 * Represents the panel within the player's window. Draws the screen based on the information
 * given to it by its player. Extends JPanel.
 * 
 * @author Michael Lee
 * @version 5/28/2025
 * 
 */
public class PlayerPanel extends JPanel
{
    public static MouseEvent mouseClick;
    private Player player;
    private Color brown = new Color(79, 59, 52);

    /**
     * Initializes a <code>PlayerPanel</code> object which uses the information
     * from the given player.
     * @param p the player
     */
    public PlayerPanel(Player p)
    {
        player = p;
    }

    /**
     * Redraws this panel based on the information from this <code>PlayerPanel</code>'s player.
     * This method should not be called directly and instead should be called through the 
     * <code>repaint</code> method.
     * 
     * @param g the graphics object of this <code>PlayerPanel</code>
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(brown);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());

        int i;
        Tile currentTile;
        NumberCard currentNumberCard;
        TrumpCard currentTrumpCard;
        ArrayList<NumberCard> numberCardHand = player.getNumberCardHand();
        ArrayList<TrumpCard> trumpCardHand = player.getTrumpCardHand();

        // Drawing the card tile images from left to right in order
        // Drawing number cards
        for (i = 0; i < numberCardHand.size() * GameGUI.UNIT_SIZE; i += GameGUI.UNIT_SIZE)
        {
            currentNumberCard = numberCardHand.get(i / GameGUI.UNIT_SIZE);
            currentTile = GameGUI.NUMBER_CARD_TILES[currentNumberCard.getValue() - 1];
            g2.drawImage(currentTile.getImage(), i, this.getHeight() / 2 - GameGUI.UNIT_SIZE / 2, GameGUI.UNIT_SIZE, 
                         GameGUI.UNIT_SIZE, null);
        }
        // Drawing trump cards
        for ( ; i < numberCardHand.size() * GameGUI.UNIT_SIZE + 
             trumpCardHand.size() * GameGUI.UNIT_SIZE; i += GameGUI.UNIT_SIZE)
        {
            currentTrumpCard = trumpCardHand.get(i / GameGUI.UNIT_SIZE - numberCardHand.size());
            currentTile = GameGUI.TRUMP_CARD_TILES[GameGUI.getTrumpCardIndex(currentTrumpCard)];
            g2.drawImage(currentTile.getImage(), i, this.getHeight() / 2 - GameGUI.UNIT_SIZE / 2, GameGUI.UNIT_SIZE, 
                         GameGUI.UNIT_SIZE, null);
        }

        g2.dispose();
    }

    /**
     * Waits for and returns the next mouse click within the game window.
     * @return the next mouse click
     */
    public MouseEvent nextMouseClick()
    {
        long currentTime = System.nanoTime();
        long lastTime = currentTime;
        long delta = 0;
        long waitTime = 1000000000 / 10; // checks 10 times per second

        // This loop may cause problems because Swing is not thread safe
        while (mouseClick == null)
        {
            currentTime = System.nanoTime();
            delta += currentTime - lastTime;
            if (delta >= waitTime)
            {
                // For some reason, the code does not work unless I have this print statement...
                System.out.print("");
                delta = 0; // this loop is only necessary for testing
            }
            lastTime = currentTime;
        }
        MouseEvent temp = mouseClick;
        mouseClick = null;
        return temp;
    }
}
