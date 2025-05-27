/**
 * Object representing a Card with a number 1-11. Can be hidden, or unhidden,
 * with standard getters, setters, and an equals() override.
 * 
 * @author Brian
 * @version 5-26-2025
 */
public class NumberCard
    implements Card
{
    private int     value;
    private boolean isHidden;

    /**
     * A no arguments constructor which sets value to -1, and isHidden to false.
     */
    public NumberCard()
    {
        value = -1;
        isHidden = false;
    }


    /**
     * Constructor which creates a NumberCard with inputted value and isHidden
     * status
     * 
     * @param value - int value of new card
     * @param isHidden - boolean signifying if card is hidden
     */
    public NumberCard(int value, boolean isHidden)
    {
        this.value = value;
        this.isHidden = isHidden;
    }


    /**
     * Retrieves value of card
     * 
     * @return int value of the card
     */
    public int getValue()
    {
        return value;
    }


    /**
     * Retrieves ishidden status of card
     * 
     * @return boolean whether or not the card is hidden
     */
    public boolean getIsHidden()
    {
        return isHidden;
    }


    /**
     * Sets the value of a number card
     * 
     * @param v - integer value to be set
     */
    public void setValue(int v)
    {
        value = v;
    }


    /**
     * Sets whether or not a card is hidden
     * 
     * @param hiddenStatus - boolean value to be set
     */
    public void setIsHidden(boolean hiddenStatus)
    {
        isHidden = hiddenStatus;
    }


    /**
     * equals method oveeride comparing 
     * numberCard values of two cards
     * 
     * @param other - Object to be compared with
     * @return  - boolean true or false
     */
    public boolean equals(Object other)
    {
        return (this.getValue() == ((NumberCard)other).getValue());
    }
}
