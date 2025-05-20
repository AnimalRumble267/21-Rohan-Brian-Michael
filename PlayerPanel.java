import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

/**
 * 
 * 
 * @author Michael Lee
 * @version
 * 
 */
public class PlayerPanel extends JPanel
{
    public static MouseEvent mouseClick;
    private Player player;

    public PlayerPanel(Player p)
    {
        player = p;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

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
            if (currentNumberCard.getIsHidden())
            {
                currentTile = GameGUI.NUMBER_CARD_TILES[11];
                g2.drawImage(currentTile.getImage(), i, 0, GameGUI.UNIT_SIZE, 
                             GameGUI.UNIT_SIZE, null);
            }
            else
            {
                currentTile = GameGUI.NUMBER_CARD_TILES[currentNumberCard.getValue() - 1];
                g2.drawImage(currentTile.getImage(), i, 0, GameGUI.UNIT_SIZE, 
                             GameGUI.UNIT_SIZE, null);
            }
        }
        // Drawing trump cards
        for ( ; i < numberCardHand.size() * GameGUI.UNIT_SIZE + 
             trumpCardHand.size() * GameGUI.UNIT_SIZE; i += GameGUI.UNIT_SIZE)
        {
            currentTrumpCard = trumpCardHand.get(i);
            currentTile = GameGUI.TRUMP_CARD_TILES[GameGUI.getTrumpCardIndex(currentTrumpCard)];
            g2.drawImage(currentTile.getImage(), i, 0, GameGUI.UNIT_SIZE, 
                         GameGUI.UNIT_SIZE, null);
        }

        g2.dispose();
    }

    /**
     * Will wait until a point is clicked on the screen. Checks 
     * 30 times per second.
     * @return the point that was clicked
     */
    public MouseEvent nextMouseClick()
    {
        long currentTime = System.nanoTime();
        long lastTime = currentTime;
        long delta = 0;
        long waitTime = 1000000000;

        // This loop may cause problems because Swing is not thread safe
        while (mouseClick == null)
        {
            currentTime = System.nanoTime();
            delta += currentTime - lastTime;
            if (delta >= waitTime)
            {
                System.out.println(System.nanoTime());
                delta = 0; // this loop is only necessary for testing
            }
            lastTime = currentTime;
        }
        MouseEvent temp = mouseClick;
        mouseClick = null;
        return temp;
    }
}
