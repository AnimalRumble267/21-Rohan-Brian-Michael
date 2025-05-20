import javax.sound.sampled.*;
import java.net.URL;

/**
 * 
 * 
 * @author
 * @version
 * 
 */
public class Sound 
{
    private String filePath;
    private Clip clip;
    private URL url;

    public Sound(String fp)
    {
        filePath = fp; 
    }

    public void setSound()
    {
        try
        {
            url.getClass().getResourceAsStream(filePath);
            AudioInputStream audio = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audio);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void play()
    {
        clip.start();
    }

    public void stop()
    {
        clip.stop();
    }

}
