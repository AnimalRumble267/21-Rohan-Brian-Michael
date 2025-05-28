import javax.sound.sampled.*;
import java.net.URL;

/**
 * Represents a singular audio clip. Can play, stop, restart, and loop the audio clip.
 * 
 * @author Michael Lee
 * @version 5/28/2025
 * 
 */
public class Sound 
{
    private String filePath;
    private Clip clip;
    private double duration;

    /**
     * Initializes the file path for this <code>Sound</code> object. Does not load the audio clip yet.
     * The file path must lead to a 16-bit WAV audio clip.
     * @param fp the file path for the 16-bit WAV audio clip
     */
    public Sound(String fp)
    {
        filePath = fp;
    }

    /**
     * Loads the audio clip.
     */
    public void loadSound()
    {
        try
        {
            URL url = getClass().getResource(filePath);
            AudioInputStream audio = AudioSystem.getAudioInputStream(url);
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

    /**
     * Plays the audio clip.
     */
    public void play()
    {
        clip.start();
    }

    /**
     * Pauses the audio clip.
     */
    public void stop()
    {
        clip.stop();
    }

    /**
     * Loops the audio clip.
     */
    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Sets the frame position (essentially the starting point) of the audio clip. Setting
     * the frame position to 0 puts the frame position at the beginning of the audio clip.
     * @param framePosition the frame position
     */
    public void setFramePosition(int framePosition)
    {
        clip.setFramePosition(framePosition);
    }

    /**
     * Returns the duration of the audio clip in seconds.
     * @return the duration of the audio clip in seconds
     */
    public double getDuration()
    {
        return duration;
    }
}
