import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.IOException;
import javax.imageio.*;

/**
 * Represents one tile on the screen. Each tile loads and
 * stores an image.
 * 
 * @author Michael Lee
 * @version
 * 
 */
public class Tile
{
    private BufferedImage image;
    private boolean imageLoaded = false;
    private int tileWidth;
    private int tileHeight;

    public Tile(int width, int height)
    {
        tileWidth = width;
        tileHeight = height;
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

    public BufferedImage getImage()
    {
        return image;
    }
}
