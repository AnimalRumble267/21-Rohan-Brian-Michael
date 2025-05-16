import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
 * 
 * 
 * @author
 * @version
 * 
 */
public class GamePanel extends JPanel
{
    private Dealer dealer;

    public GamePanel()
    {
        this(null);
    }

    public GamePanel(Dealer d)
    {
        dealer = d;
    }

    public void paintComponent(Graphics g)
    {

    }
}
