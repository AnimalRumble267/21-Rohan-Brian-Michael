
/**
 * Represents the manager for all sounds of the game. Plays and stops
 * music and sound effects.
 * 
 * @author Michael Lee
 * @version 5/28/2025
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
        soundEffects = new Sound[3];
    }

    public void start()
    {
        music[0] = new Sound("/sound/chopinnocturneop9no2.wav");
        music[1] = new Sound("/sound/21hungariandances.wav");
        music[0].loadSound();
        music[1].loadSound();

        soundEffects[0] = new Sound("/sound/trigger.wav");
        soundEffects[1] = new Sound("/sound/blast.wav");
        soundEffects[2] = new Sound("/sound/cardflip.wav");
        soundEffects[0].loadSound();
        soundEffects[1].loadSound();
        soundEffects[2].loadSound();
    }

    public void playMusic(int index)
    {
        if (!soundStarted)
        {
            System.out.println("ERROR: Must start sound first - playMusic");
            return;
        }
        music[index].loop();
    }

    public void stopMusic(int index)
    {
        if (!soundStarted)
        {
            System.out.println("ERROR: Must start sound first - stopMusic");
            return;
        }
        music[index].stop();
        music[index].setFramePosition(0);
    }

    public void playSoundEffect(int index)
    {
         if (!soundStarted)
        {
            System.out.println("ERROR: Must start sound first - playSoundEffect");
            return;
        }
        soundEffects[index].loop();
    }

    public void stopSoundEffect(int index)
    {
         if (!soundStarted)
        {
            System.out.println("ERROR: Must start sound first - stopSoundEffect");
            return;
        }
        soundEffects[index].stop();
        soundEffects[index].setFramePosition(0);
    }
}
