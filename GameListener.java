import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Represents a mouse listener for a <code>GamePanel</code>
 * 
 * @author Michael Lee
 * @version 5/26/2025
 * 
 */
public class GameListener implements MouseListener
{
    /**
     * Sets the <code>GamePanel</code>'s <code>mouseClick</code> to the mouse event.
     * @param e the mouse event
     */
    public void mouseClicked(MouseEvent e) 
    {
        GamePanel.mouseClick = e;
    }

    /**
     * Does nothing.
     * @param e the mouse event
     */
    public void mousePressed(MouseEvent e) 
    {
        
    }

    /**
     * Does nothing.
     * @param e the mouse event
     */
    public void mouseReleased(MouseEvent e) 
    {
        
    }

    /**
     * Does nothing.
     * @param e the mouse event
     */
    public void mouseEntered(MouseEvent e) 
    {
        
    }

    /**
     * Does nothing.
     * @param e the mouse event
     */
    public void mouseExited(MouseEvent e) 
    {
        
    }
    
}