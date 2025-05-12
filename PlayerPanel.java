import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;

/**
 * 
 * 
 * @author Michael Lee
 * @version
 * 
 */
public class PlayerPanel extends JPanel
{
    private static final int MAX_PANEL_ROW = 10;       // TODO Change to fit
    private static final int MAX_PANEL_COL = 20;       // TODO Change to fit
    private static final int UNIT_SIZE = 48;
    public static final int PANEL_HEIGHT = MAX_PANEL_ROW * UNIT_SIZE;
    public static final int PANEL_WIDTH = MAX_PANEL_COL * UNIT_SIZE;

    private Player player;

    public PlayerPanel(Player p)
    {
        player = p;

        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLUE);
        this.setDoubleBuffered(true);

        
        this.setFocusable(true);
    }

    


}
