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

    public NumberCard(int v, boolean hiddenStatus) {
        value = v;
        isHidden = hiddenStatus;
    }

    // methods
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }
    
    public boolean isHidden() {   
        return isHidden;
    }

    // gui methods
}
