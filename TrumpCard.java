/**
 * Object representing a TrumpCard, a card which can be played with special
 * abilities. Has a type and a value dictating its functions, with getters,
 * setters, and an equality override.
 * 
 * @author Brian
 * @version 5-26-2025
 */
public class TrumpCard
    implements Card
{
    private int     value;
    private String  type;
    private boolean isPlayed;

    /**
     * No args constructor for a new TrumpCard, sets value to 0, type to an
     * empty string, and isPlayed to false
     */
    public TrumpCard()
    {
        value = 0;
        type = "";
        isPlayed = false;
    }


    /**
     * Constructs a Trump Card with a given value and type, setting isPlayed to false
     * The type of the trump card can be "draw", "gofor", "shield", or "up". The
     * meaning of the value of the trump card will vary, based on the type
     * 
     * @param value -  int dictating the value of the Card
     * @param type - String dictating the type of the Card
     */
    public TrumpCard(int value, String type)
    {
        this.type = type;
        this.value = value;
        this.isPlayed = false;
    }

    
    /**
     * Gets the value of the card
     * 
     * @return - int value of the TrumpCard
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Gets the type of the card
     * 
     * @return - String value of the TrumpCard
     */
    public String getType()
    {
        return type;
    }

    /**
     * Gets whether or not the card has been played
     * 
     * @return - boolean saying if it is played
     */

    public boolean isPlayed()
    {
        return isPlayed;
    }

    /**
     * Set if the TrumpCard has been played
     * 
     * @param status - boolean to be set
     */
    public void setIsPlay(boolean status)
    {
        isPlayed = status;
    }

    /**
     * Equals method comparing whether or not
     * the value and the type are the same
     * 
     * @param other - Object to be compared to
     * @return - boolean signifying equality
     */
    public boolean equals(Object other)
    {
        boolean valueEqual = false;
        boolean stringEqual = false;

        if (this.getValue() == ((TrumpCard)other).getValue())
        {
            valueEqual = true;
        }

        if (this.getType() == ((TrumpCard)other).getType())
        {
            stringEqual = true;
        }

        return (valueEqual && stringEqual);
    }

    // GUI
}
