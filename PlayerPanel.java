import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * 
 * 
 * @author Michael Lee
 * @version
 * 
 */
public class PlayerPanel extends JPanel
{
    private Player player;

    public PlayerPanel()
    {
        player = null;
    }

    public PlayerPanel(Player p)
    {
        player = p;
    }

    public void paintComponents(Graphics g)
    {
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) g;

        ArrayList<Card> numberCardHand = player.getNumberCardHand();
        ArrayList<Card> trumpCardHand = player.getTrumpCardHand();
        int i = 0;        

        // The row does not change, only the column changes
        for (int c = 0; c < numberCardHand.size() * GameGUI.UNIT_SIZE; c += GameGUI.UNIT_SIZE)
        {
            g2.drawImage();
        }
    }
}
