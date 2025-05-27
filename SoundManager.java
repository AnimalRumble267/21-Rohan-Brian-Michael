
/**
 * 
 * 
 * @author Michael Lee
 * @version
 * 
 */
public class SoundManager 
{
    private Sound[] music;
    private Sound[] soundEffects;

    private boolean soundStarted = false;

    public SoundManager()
    {
        music = new Sound[2];
        soundEffects = new Sound[2];
    }

    public void start()
    {
        music[0] = new Sound("/sound/chopinnocturneop9no2.wav");
        music[1] = new Sound("/sound/21hungariandances.wav");
        music[0].loadSound();
        music[1].loadSound();

        soundEffects[0] = new Sound("/sound/trigger.wav");
        soundEffects[1] = new Sound("/sound/blast.wav");
        soundEffects[0].loadSound();
        soundEffects[1].loadSound();
    }

    public void playMusic(int index)
    {
        if (!soundStarted)
        {
            System.out.println("ERROR: Must start sound first - playMusic");
            
        }
        music[index].loop();
    }

    public void stopMusic(int index)
    {
        music[index].stop();
        music[index].setFramePosition(0);
    }

    public void playSoundEffect(int index)
    {
        soundEffects[index].loop();
    }

    public void stopSoundEffect(int index)
    {
        soundEffects[index].stop();
        soundEffects[index].setFramePosition(0);
    }
}
