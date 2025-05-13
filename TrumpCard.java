/**
 * 
 * 
 * @author brian
 * @version
 * 
 */
public class TrumpCard implements Card
{
    private int value;
    private String type;
    private boolean isPlayed;

    // construcotrs

    public TrumpCard() {
        value = 0;
        type = "";
        isPlayed = false;
    }

    
    public TrumpCard(int value, String type) {
        this.type = type;
        this.value = value;
        this.isPlayed = false;
    }

    public TrumpCard(String t, 

    // methods

    public int getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public boolean isPlayed() {
        return isPlayed;
    }
    
    public void setIsPlay(boolean status) {
        isPlayed = status;
    }
    


    // gui
}
