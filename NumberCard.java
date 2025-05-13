/**
 * 
 * 
 * @author brian
 * @version
 * 
 */
public class NumberCard implements Card
{
    // attributes

    private int value;
    private boolean isHidden;


    // constructor
    public NumberCard() {
        value = -1;
        isHidden = false;
    }

    public NumberCard(int value, boolean isHidden) {
        this.value = value;
        this.isHidden = isHidden;
    }

    // methods
    public int getValue() {
        return value;
    }

    public boolean getIsHidden() {   
        return isHidden;
    }

    public void setValue(int v) {
        value = v;
    }

    public void setIsHidden(boolean hiddenStatus) {
        isHidden = hiddenStatus;
    }

    
    public boolean equals(Object other) {
        return (this.getValue() == ((NumebrCard)other.getValue());
    }
    

    // gui methods
}
