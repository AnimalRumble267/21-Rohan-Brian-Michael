import java.util.Stack;
import java.util.ArrayList;

/**
 * 
 * 
 * @author Brian // Rohan
 * @version
 * 
 */
public class Player 
{
    private String name;
    private boolean isTurn;
    private ArrayList<Card> numberCardHand;
    private ArrayList<Card> trumpCardHand;

    /**
     * [write description here]
     */
    public Player()
    {
        name = "";
        isTurn = false;
        numberCardHand = new ArrayList<Card>();
    }

    /**
     * [write description here]
     * @param name
     */
    public Player(String name)
    {
        this.name = name;
        isTurn = false;
        numberCardHand = new ArrayList<Card>();
    }

    

    /**
     * returns whether or not it is object player's turn
     * @return boolean isTurn
     */
    public boolean isTurn()
    {
        return isTurn;
    }

    /**
     * [write description here]
     * @param card
     */
    public void addNumberCard(Card card)
    {
        numberCardHand.add(Card);
    }

    /**
     * [write description here]
     * @param card
     */
    public void addTrumpCard(Card card)
    {
        trumpCardHand.add(Card);
    }

    /**
     * [write description here]
     * @return
     */
    public Card removeLastNumberCard()
    {
        numberCardHand.remove(numberCardHand.size() - 1);
    }

    /**
     * [write description here]
     * @param index is from 0 to length of size - 1 
     * @return
     */
    public Card removeTrumpCard(int index)
    {
        trumpCardHard.remove(index);
    }

    /**
     * [write description here]
     */
    public void resetHand()
    {
        numberCardHand = new ArrayList<Card>();
        trumpCardHand = new Arraylist<Card>();
    }

    /**
     * [write description here]
     * @param bet
     * @return boolean if player will die
     */
    public boolean punish(int bet)
    {
        int increment = 6;
        double probability = 1 / increment;

        for (int i = 0; i < bet; i++) {
            if (Math.random() < probability) {
                return true;
            }
            increment--;
        }

        return false;
    }

    /**
     * [write description here]
     * @return
     */
    public ArrayList<Card> getNumberCardHand()
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
