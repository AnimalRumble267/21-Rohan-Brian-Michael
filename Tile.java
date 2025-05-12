import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.IOException;
import javax.imageio.*;

/**
 * 
 * 
 * @author Michael Lee
 * @version
 * 
 */
public class Tile extends JPanel
{
    private BufferedImage image;
    private boolean imageLoaded = false;
    private int tileWidth;
    private int tileHeight;

    public Tile()
    {
        this(100, 100);
    }

    public Tile(int width, int height)
    {
        tileWidth = width;
        tileHeight = height;
        this.setPreferredSize(new Dimension(width, height));
    }

    public void loadImage(String filePath)
    {
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream(filePath));
            imageLoaded = true;
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image, this.getX(), this.getY(), GameGUI.UNIT_SIZE, GameGUI.UNIT_SIZE, null);
        g2.dispose();
    }

    public BufferedImage getImage()
    {
        return image;
    }

    


}
