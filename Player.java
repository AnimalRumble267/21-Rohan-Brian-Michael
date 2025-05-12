import java.util.Stack;
import java.util.ArrayList;

/**
 * 
 * 
 * @author
 * @version
 * 
 */
public class Player 
{
    private String name;
    private boolean isTurn;
    private Stack<Card> numberCardHand;
    private ArrayList<Card> trumpCardHand;

    /**
     * [write description here]
     */
    public Player()
    {

    }

    /**
     * [write description here]
     * @param name
     */
    public Player(String name)
    {

    }

    /**
     * [write description here]
     * @param port
     * @return
     */
    public boolean createRoom(int port)
    {
        return false; //TODO fix this
    }

    /**
     * [write description here]
     * @param port
     * @return
     */
    public boolean joinRoom(int port)
    {
        return false; //TODO fix this
    }

    /**
     * [write description here]
     * @return
     */
    public boolean isTurn()
    {
        return isTurn;
    }

    /**
     * [write description here]
     * @param card
     */
    public void giveCard(Card card)
    {

    }

    /**
     * [write description here]
     * @param card
     */
    public void addNumberCard(Card card)
    {

    }

    /**
     * [write description here]
     * @param card
     */
    public void addTrumpCard(Card card)
    {

    }

    /**
     * [write description here]
     * @return
     */
    public Card removeLastNumberCard()
    {
        return null; // TODO fix this
    }

    /**
     * [write description here]
     */
    public void resetHand()
    {

    }

    /**
     * [write description here]
     * @param bet
     * @return
     */
    public boolean punish(int bet)
    {
        return false; // TODO fix this
    }

    /**
     * [write description here]
     * @return
     */
    public Stack<Card> getNumberCardHand()
    {
        return null; // TODO fix this
    }

    /**
     * [write description here]
     * @return
     */
    public ArrayList<Card> getTrumpCardHand()
    {
        return null; // TODO fix this
    }

    /**
     * [write description here]
     * @return
     */
    public String getName()
    {
        return name;
    }

    // TODO ADD OTHER SIGNATURES
}
