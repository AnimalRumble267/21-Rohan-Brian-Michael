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
    
    public boolean equals(Object other) {
        boolean valueEqual = false;
        boolean stringEqual = false;

        if (this.getValue() == ((TrumpCard)other).getValue()) { 
            valueEqual = true;
        }

        if (this.getType() == ((TrumpCard)other).getType()) {
            stringEqual = true;
        }

        return (valueEqual && stringEqual);
    }

    // gui
}
