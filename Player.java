import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
/**
 * Object representing the Player, a class which contains the playerGUI,
 * and represents the status of the player, including their name, status,
 * and card hand. 
 * 
 * @author - Brian & Rohan
 * @version - 5/27/2025
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
    private int icon;
    private boolean isBeingPunished = false;

    /**
     * Player onstructor that takes in name and player #, 
     * which will be used later for actions in other classes
     * Player is now assumed to be alive (isAlive = true)
     * Initializes arraylists for deck hands and playerGUI
     * @param name - String name of Player
     * @param playerNum - int number of Player
     */
    public Player(String name, int playerNum)
    {
        this.name = name;
        isTurn = false;
        isAlive = true;
        hasWon = false;
        numberCardHand = new ArrayList<NumberCard>();
        trumpCardHand = new ArrayList<TrumpCard>();
        playerGUI = new PlayerGUI(this);
        playerNumber = playerNum;
    }

    /**
     * Starts the playerHand GUI
     */
    public void startGUI()
    {
        playerGUI.start();
    }

    /**
     * Stops the playerHand GUI
     */
    public void stopGUI()
    {
        playerGUI.stop();
    }

    /**
     * Updates GUI for the player hand window based on playerGUI's updateHand()
     */
    public void updateHand()
    {
        playerGUI.updateHand();
    }

    public void setAbleToGetClick(boolean status)
    {
        playerGUI.setAbleToGetClick(status);
    }

    /**
     * Sets the location of the playerGUI
     * frame using playerGUI.
     * 
     * @param x - int x coordinate of loc
     * @param y - int y coordinate of loc
     */
    public void setFrameLocation(int x, int y)
    {
        playerGUI.setFrameLocation(x, y);
    }

    /**
     * Initializes description of trump card (type: "gofor", "bet", "draw";
     * value: value of the type // ex. "draw-2 --> type: "draw", value: 2)
     * 
     * @param value - int value dictating scale / type of function
     * @param type - String type dictating function
     */
    public void writeTrumpCardDescription(int value, String type)
    {
        playerGUI.writeTrumpCardDescription(value, type);
    }

    /**
     * Clears all Trump Card descriptions
     */
    public void clearTrumpCardDescription()
    {
        playerGUI.clearTrumpCardDescription();
    }

    /** 
     * Sets this player's icon to the icon number
     * @param iconNum the icon number
     */
    public void setPlayerIcon(int iconNum)
    {
        icon = iconNum;
    }

    /**
     * Returns this player's icon
     * @return this player's icon
     */
    public int getPlayerIcon()
    {
        return icon;
    }

    /**
     * Sets if this player is being punished currently
     * @param status if this player is being punished currently
     */
    public void setBeingPunished(boolean status)
    {
        isBeingPunished = status;
    }

    /**
     * Returns if this player is being punished currently
     * @return if this player is being punished currently
     */
    public boolean isBeingPunished()
    {
        return isBeingPunished;
    }

    /**
     * Returns whether or not it is object player's turn
     * 
     * @return - boolean isTurn
     */
    public boolean isTurn()
    {
        return isTurn;
    }

    /**
     * Sets player turn to true or false
     * 
     * @param turn - boolean turn to be set
     */
    public void setTurn(boolean turn)
    {
        isTurn = turn;
    }

    /** 
     * Returns whether or not player is alive
     * 
     * @return - boolean isAlive
     */
    public boolean isAlive()
    {
        return isAlive;
    }

    /**
     * Sets isAlive status 
     * (true = player is alive // false = player is not alive)
     * 
     * @param status - boolean whetehr the player is alive or not
     */
    public void setAlive(boolean status) 
    {
        isAlive = status;
    }

    /**
     * Sets this player's status which indicates if they have won
     * 
     * @param status boolean status which indicates if this player has won
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
     * Adds number card to the end of player hand
     * 
     * @param card - NumberCard to be added
     */
    public void giveNumberCard(NumberCard card)
    {
        numberCardHand.add(card);
    }

    /**
     * Adds trump card to the end of player hand
     * 
     * @param card - TrumpCard to be added
     */
    public void giveTrumpCard(TrumpCard card)
    {
        trumpCardHand.add(card);
    }

    /**
     * Removes the last number card from the player's number hand
     * 
     * @return - NumberCard removed
     */
    public NumberCard removeLastNumberCard()
    {
        return numberCardHand.remove(numberCardHand.size() - 1);
    }

    /**
     * Removes a specific Trump Card from a player's trump card hand given an index;
     * 
     * @param index int index, is from 0 to length of size - 1 
     * @return - TrumpCard removed
     */
    public TrumpCard removeTrumpCard(int index)
    {
        return trumpCardHand.remove(index);
    }

    /** 
     * Calculates total value of numberCardHand
     * 
     * @returns int value of the numberCardHand
     */
    public int calculate() {
        int temp = 0;
        for (NumberCard nC : numberCardHand) {
            temp += nC.getValue();
        }
        return temp;
    }
    
    /**
     * Clears both trump card and number card hands
     */
    public void resetHand()
    {
        numberCardHand = new ArrayList<NumberCard>();
        trumpCardHand = new ArrayList<TrumpCard>();
    }

    /**
     * Gets numbercard hand of the Player
     *
     *  @return - ArrayList<NumberCard> signifying hand
     */
    public ArrayList<NumberCard> getNumberCardHand()
    {
        return numberCardHand;
    }

    /**
     * Gets trumpcard hand of the Player
     * 
     * @return - ArrayList<TrumpCard> signifying hand
     */
    public ArrayList<TrumpCard> getTrumpCardHand()
    {
        return trumpCardHand;
    }

    /**
     * Gets name of the Player
     * 
     * @return - String name of the player
     */
    public String getName()
    {
        return name;
    }

    /**
     * Based on the location of the next mouseclick, returns
     * a status based on the location of the mouse and the 
     * input type, signifying the action which is meant to
     * be executed
     * 
     * 0 = trumpdescription
     * 1 = hit
     * 2 = stand
     * 30 + X = play trump X
     * 
     * @return int action number
     */
    public int getInput() {
        MouseEvent input = playerGUI.nextMouseClick();
        
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
            double trumpThreshold =  (GameGUI.UNIT_SIZE) * numberCardHand.size();
            if (locx > trumpThreshold) {
                int numTrump = (int)((locx - trumpThreshold) / (GameGUI.UNIT_SIZE));
                
                return 30 + numTrump; // TRUMP
            }
            else {
               
                return 1; // HIT
            }
        }
        
    }
}
