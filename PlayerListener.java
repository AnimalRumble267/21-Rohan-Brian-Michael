import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Represents a mouse listener for a <code>PlayerPanel</code>
 * 
 * @author Michael Lee
 * @version 5/26/2025
 * 
 */
public class PlayerListener implements MouseListener
{
    /**
     * Sets the <code>PlayerPanel</code>'s <code>mouseClick</code> to the mouse event.
     * @param e the mouse event
     */
    public void mouseClicked(MouseEvent e) 
    {
        PlayerPanel.mouseClick = e;
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
};

