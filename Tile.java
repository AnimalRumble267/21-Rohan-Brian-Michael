import java.awt.*;
import javax.swing.*;

/**
 * Represents one tile on the screen. Each tile stores an image.
 * 
 * @author Michael Lee
 * @version  5/28/2025
 * 
 */
public class Tile
{
    private Image image;
    private String filePath;

    /**
     * Initializes a <code>Tile</code> with the file path. Does not load the image yet.
     * @param fp the file path
     */
    public Tile(String fp)
    {
        filePath = fp;
    }

    /**
     * Loads the image.
     */
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

    /**
     * Returns the image
     * @return the image
     */
    public Image getImage()
    {
        return image;
    }
}
