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


    // methods
    public int getValue() {
        return value;
    }

    public boolean isHidden() {   
        return isHidden;
    }
}
