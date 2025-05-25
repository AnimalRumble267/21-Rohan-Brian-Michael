/**
 * 
 * 
 * @author brian
 * @version
 * 
 */
public class NumberCard implements Card
{
    private int value;
    private boolean isHidden;

    /**
     * no args constructor //
     * by default numberCard value is -1 and is not hidden 
     */
    public NumberCard() {
        value = -1;
        isHidden = false;
    }

    /**
     * instantiates new numberCard through establishing value and whether or not a card is hidden
     * 
     * @param value
     * @param isHidden
     */
    public NumberCard(int value, boolean isHidden) {
        this.value = value;
        this.isHidden = isHidden;
    }

    /**
     * getter method
     * 
     * @return int value of the card
     */
    public int getValue() {
        return value;
    }

    /**
     * getter method
     * 
     * @return whether or not the card is hidden
     */
    public boolean getIsHidden() {   
        return isHidden;
    }

    /**
     * sets the value of a number card
     * 
     * @param v
     */
    public void setValue(int v) {
        value = v;
    }

    /**
     * sets whether or not a card is hidden
     * 
     * @param hiddenStatus
     */
    public void setIsHidden(boolean hiddenStatus) {
        isHidden = hiddenStatus;
    }

    /**
     * equals method comparing numberCard values of two cards
     * 
     * @return boolean true or false
     */
    public boolean equals(Object other) {
        return (this.getValue() == ((NumberCard)other).getValue());
    }
}
