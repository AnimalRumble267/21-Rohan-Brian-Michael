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
    private PlayerGUI playerGUI;
    private int playerNumber;

    /**
     * [write description here]
     * @param name
     */
    public Player(String name, int playerNum)
    {
        this.name = name;
        isTurn = false;
        numberCardHand = new ArrayList<NumberCard>();
        trumpCardHand = new ArrayList<TrumpCard>();
        playerGUI = new PlayerGUI(this, playerNum);
        playerNumber = playerNum;
    }

    public void startGUI()
    {
        playerGUI.start();
    }

    public void updateHand()
    {
        playerGUI.updateHand();
    }

    /**
     * returns whether or not it is object player's turn
     * @return boolean isTurn
     */
    public boolean isTurn()
    {
        return isTurn;
    }

    public void setTurn(boolean turn)
    {
        isTurn = turn;
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
