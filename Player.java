import java.util.Stack;
import java.util.ArrayList;

/**
 * 
 * 
 * @author Brian and Rohan
 * @version
 * 
 */
public class Player 
{
    private String name;
    private boolean isTurn;
    private ArrayList<NumberCard> numberCardHand;
    private ArrayList<TrumpCard> trumpCardHand;

    /**
     * [write description here]
     */
    public Player()
    {
        name = "";
        isTurn = false;
        numberCardHand = new ArrayList<NumberCard>();
        trumpCardHand = new ArrayList<TrumpCard>();
    }

    /**
     * [write description here]
     * @param name
     */
    public Player(String name)
    {
        this.name = name;
        isTurn = false;
        numberCardHand = new ArrayList<NumberCard>();
        trumpCardHand = new ArrayList<TrumpCard>();
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
    public void giveNumberCard(NumberCard card)
    {
        numberCardHand.add(card);
    }

    /**
     * [write description here]
     * @param card
     */
    public void giveTrumpCard(TrumpCard card)
    {
        trumpCardHand.add(card);
    }

    /**
     * [write description here]
     * @return
     */
    public NumberCard removeLastNumberCard()
    {
        return numberCardHand.remove(numberCardHand.size() - 1);
    }

    /**
     * removes a specific trump card from a player's trump card hand given an index;
     * @param index is from 0 to length of size - 1 
     * @return
     */
    public TrumpCard removeTrumpCard(int index)
    {
        return trumpCardHand.remove(index);
    }

    /**
     * clears both trump card and number card hands
     */
    public void resetHand()
    {
        numberCardHand = new ArrayList<NumberCard>();
        trumpCardHand = new ArrayList<TrumpCard>();
    }

    /**
     * generate changing probability of a player dying on a single shot. This probability will increase after each shot 
     * doesn't kill the player (probability is 1/n, and n decreases by 1 everyshot). if, on any given shot, the player
     * gets unlucky (randomly generated probability falls under probability), the player dies.
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
     * get method
     * @return the entire number card hand of the player
     */
    public ArrayList<NumberCard> getNumberCardHand()
    {
        return numberCardHand;
    }

    /**
     * get method
     * @return the the entire trump card hand of the player
     */
    public ArrayList<TrumpCard> getTrumpCardHand()
    {
        return trumpCardHand;
    }

    /**
     * get method
     * @return name of the player
     */
    public String getName()
    {
        return name;
    }

    // TODO ADD OTHER SIGNATURES
}
