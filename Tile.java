import java.awt.*;
import javax.swing.*;

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
    private Image image;
    private String filePath;

    public Tile(String fp)
    {
        filePath = fp;
    }

    public void loadImage()
    {
        try
        {
            image = new ImageIcon(getClass().getResource(filePath)).getImage();;
            // image = ImageIO.read(getClass().getResourceAsStream(filePath));
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("ERROR - loadImage() " + filePath);
        }
    }

    public Image getImage()
    {
        return image;
    }
}
