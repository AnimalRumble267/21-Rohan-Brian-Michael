import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
/**
 * Defines player -- holds values like name, the player's current number and trump card hand
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
    private ArrayList<TrumpCard> trumpCardHand;
    
    private PlayerGUI playerGUI;
    private int playerNumber;

    private boolean hasWon;

    /**
     * Constructor that takes in name and player #, which will be used later for actions in other classes
     * Player is now assumed to be alive (isAlive = true)
     * Initializes arraylists for deck hands and playerGUI
     * @param name
     */
    public Player(String name, int playerNum)
    {
        this.name = name;
        isTurn = false;
        isAlive = true;
        hasWon = false;
        numberCardHand = new ArrayList<NumberCard>();
        trumpCardHand = new ArrayList<TrumpCard>();
        playerGUI = new PlayerGUI(this, playerNum);
        playerNumber = playerNum;
    }

    /**
     * starts the playerHand GUI
     */
    public void startGUI()
    {
        playerGUI.start();
    }

    /**
     * stops the playerHand GUI
     */
    public void stopGUI()
    {
        playerGUI.stop();
    }

    /**
     * updates GUI for the player hand window based on playerGUI's updateHand()
     */
    public void updateHand()
    {
        playerGUI.updateHand();
    }

    /**
     * sets frame of player hand on parent screen
     * 
     * @param x
     * @param y
     */
    public void setFrameLocation(int x, int y)
    {
        playerGUI.setFrameLocation(x, y);
    }

    /**
     * gives description of trump card (type: "gofor", "bet", "draw"; value: value of the type // ex. "draw-2 --> type: "draw", value: 2)
     * 
     * @param value
     * @param type
     */
    public void writeTrumpCardDescription(int value, String type)
    {
        playerGUI.writeTrumpCardDescription(value, type);
    }

    /**
     * clears any current description for targetted trump card
     */
    public void clearTrumpCardDescription()
    {
        playerGUI.clearTrumpCardDescription();
    }

    /**
     * returns whether or not it is object player's turn
     * 
     * @return boolean isTurn
     */
    public boolean isTurn()
    {
        return isTurn;
    }

    /**
     * sets player turn
     * 
     * @param turn
     */
    public void setTurn(boolean turn)
    {
        isTurn = turn;
    }

    /** 
     * returns whether or not player is alive
     * 
     * @return boolean isAlive
     */
    public boolean isAlive()
    {
        return isAlive;
    }

    /**
     * sets isAlive status 
     * (true = player is alive // false = player is not alive)
     * 
     * @param status
     */
    public void setAlive(boolean status) 
    {
        isAlive = status;
    }

    /**
     * Sets this player's status which indicates if they have won
     * @param status the status which indicates if this player has won
     */
    public void setWon(boolean status)
    {
        hasWon = status;
    }

    /**
     * Returns true if this player has won, false otherwise
     * @return true if this player has won, false otherwise
     */
    public boolean hasWon()
    {
        return hasWon;
    }

    /**
     * adds number card to player deck
     * 
     * @param card
     */
    public void giveNumberCard(NumberCard card)
    {
        numberCardHand.add(card);
    }

    /**
     * adds trump card to player deck
     * 
     * @param card
     */
    public void giveTrumpCard(TrumpCard card)
    {
        trumpCardHand.add(card);
    }

    /**
     * removes the last number card from the player's number deck
     * 
     * @return
     */
    public NumberCard removeLastNumberCard()
    {
        return numberCardHand.remove(numberCardHand.size() - 1);
    }

    /**
     * removes a specific trump card from a player's trump card hand given an index;
     * 
     * @param index is from 0 to length of size - 1 
     * @return
     */
    public TrumpCard removeTrumpCard(int index)
    {
        return trumpCardHand.remove(index);
    }

    /** 
     * calculates total value of numberCardHand
     * 
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
     *
     *  @return the entire number card hand of the player
     */
    public ArrayList<NumberCard> getNumberCardHand()
    {
        return numberCardHand;
    }

    /**
     * get method
     * 
     * @return the the entire trump card hand of the player
     */
    public ArrayList<TrumpCard> getTrumpCardHand()
    {
        return trumpCardHand;
    }

    /**
     * get method
     * 
     * @return name of the player
     */
    public String getName()
    {
        return name;
    }

    /**
     * handles player's selection of action from mouse click 
     * 0 = trumpdescription
     * 1 = hit
     * 2 = stand
     * 3(#) = play trump
     * 
     * @return
     */
    public int getInput() {
        MouseEvent input = playerGUI.nextMouseClick();
        
        //System.out.println(input);
        if (input.getButton() == MouseEvent.BUTTON3) {
            Point loc = input.getPoint();
            double locx = loc.getX();
            double trumpThreshold =  (GameGUI.UNIT_SIZE) * numberCardHand.size();
            if (locx > trumpThreshold) {
                int numTrump = (int)((locx - trumpThreshold) / (GameGUI.UNIT_SIZE));
                if (numTrump < trumpCardHand.size() && numTrump >= 0) {
                    
                    playerGUI.writeTrumpCardDescription(trumpCardHand.get(numTrump).getValue(), trumpCardHand.get(numTrump).getType());
                    
                    return 0;
                }
            }
            
            return 2;
            
        }
        else { 
            Point loc = input.getPoint();
            double locx = loc.getX();
            System.out.println(locx);
            double trumpThreshold =  (GameGUI.UNIT_SIZE) * numberCardHand.size();
            //System.out.println(trumpThreshold);
            if (locx > trumpThreshold) {
                int numTrump = (int)((locx - trumpThreshold) / (GameGUI.UNIT_SIZE));
                //System.out.println(numTrump);
                
                return 30 + numTrump; // TRUMP
            }
            else {
               
                return 1; // HIT
            }
        }
        
    }

    /**
     * plays card flip sound
     */
    public void playCardFlipSound() {
        Sound flipSound = new Sound("/sound/cardflip.wav");
        flipSound.loadSound();
    }
}
