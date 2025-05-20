import javax.sound.sampled.*;
import java.io.File;
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
    private AudioInputStream audio;
    private URL url;

    public Sound(String fp)
    {
        filePath = fp;
    }

    public void loadSound()
    {
        try
        {
            url = getClass().getResource(filePath);
            audio = AudioSystem.getAudioInputStream(url);
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
