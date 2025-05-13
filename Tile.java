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
public class Tile
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
