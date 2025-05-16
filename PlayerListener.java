import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PlayerListener implements MouseListener
{

    private Point point;

    public void mouseClicked(MouseEvent e) 
    {
        PlayerPanel.pointClicked = e.getPoint();
    }

    public void mousePressed(MouseEvent e) 
    {
        
    }

    public void mouseReleased(MouseEvent e) 
    {
        
    }

    public void mouseEntered(MouseEvent e) 
    {
        
    }

    public void mouseExited(MouseEvent e) 
    {
        
    }
};

