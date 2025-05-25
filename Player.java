import java.util.Stack;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
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
    private boolean isAlive;
    
    private ArrayList<NumberCard> numberCardHand;
    private int value;
    
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
        isAlive = true;
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

    public void setFrameLocation(int x, int y)
    {
        playerGUI.setFrameLocation(x, y);
    }

    public void writeTrumpCardDescription(int value, String type)
    {
        playerGUI.writeTrumpCardDescription(value, type);
    }

    public void clearTrumpCardDescription()
    {
        playerGUI.clearTrumpCardDescription();
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

    public boolean isAlive()
    {
        return isAlive;
    }

    public void setAlive(boolean status) 
    {
        isAlive = status;
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
     * calculates total value of numberCardHand
     * @param 
     * @returns value
     */
    public int calculate() {
        int temp = 0;
        for (NumberCard nC : numberCardHand) {
            temp += nC.getValue();
        }
        return temp;
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

    public int getInput() {
        MouseEvent input = playerGUI.nextMouseClick();
        //System.out.println(input);
        if (input.getButton() == MouseEvent.BUTTON3) {
            return 2; // STAND
        }
        else { // TODO ADD RIGHT CLICK TO VIEW INFO ABOUT TRUMP CARDS
            Point loc = input.getLocationOnScreen();
            double locx = loc.getX();
            if (playerNumber == 2) {
                locx -= 672;
            }
            System.out.println(locx);
            double trumpThreshold =  66 * numberCardHand.size();
            System.out.println(trumpThreshold);
            if (loc.getX() > trumpThreshold) {
                int numTrump = (int)((locx - trumpThreshold) / 66);
                System.out.println(numTrump);
                return 30 + numTrump; // TRUMP
            }
            else {
                return 1; // HIT
            }
        }
        
    }
}
