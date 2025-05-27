
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
        music = new Sound[7];
        soundEffects = new Sound[3];
    }

    public void start()
    {
        music[0] = new Sound("/sound/clairdelune.wav");
        music[1] = new Sound("/sound/chopinnocturneop9no2.wav");
        music[2] = new Sound("/sound/chopinfantasieimpromptu.wav");
        music[3] = new Sound("/sound/cancan.wav");
        music[4] = new Sound("/sound/hungariandanceno5.wav");
        music[5] = new Sound("/sound/summer.wav");
        music[6] = new Sound("/sound/carminaburana.wav");
        music[0].loadSound();
        music[1].loadSound();
        music[2].loadSound();
        music[3].loadSound();
        music[4].loadSound();
        music[5].loadSound();
        music[6].loadSound();

        soundEffects[0] = new Sound("/sound/trigger.wav");
        soundEffects[1] = new Sound("/sound/blast.wav");
        soundEffects[2] = new Sound("/sound/cardflip.wav");
        soundEffects[0].loadSound();
        soundEffects[1].loadSound();
        soundEffects[2].loadSound();

        soundStarted = true;
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
        soundEffects[index].play();
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
