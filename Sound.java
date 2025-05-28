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
    private double duration;

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
            
            AudioFormat format = audio.getFormat();
            long frames = audio.getFrameLength();
            duration = (double)frames / format.getFrameRate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("\n" + filePath + "\n");
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

    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void setFramePosition(int framePosition)
    {
        clip.setFramePosition(framePosition);
    }

    public double getDuration()
    {
        return duration;
    }
}
