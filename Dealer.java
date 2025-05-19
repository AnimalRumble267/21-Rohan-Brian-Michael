import java.util.*;
/**
 * 
 * 
 * @author
 * @version
 * 
 */
public class Dealer
{
    private Deck numberCardDeck;
    private Deck trumpCardDeck;
    private int bet;
    private ArrayList<Player> players = new ArrayList<Player>();
    private boolean punishTracker = false;
    /**
     * Assigns players, creates Decks
     * @param playerOne
     * @param playerTwo
     * @param bet
     */
    public Dealer(Player playerOne, Player playerTwo, int bet) {
        players.add(playerOne);
        players.add(playerTwo);
        this.bet = bet;

        ArrayList<Card> tempNumberDeck = new ArrayList<Card>(11);
        for (int i = 1; i <= 11; i++) {
            tempNumberDeck.add(new NumberCard(i,true));
        }
        numberCardDeck = new Deck(tempNumberDeck);

       ArrayList<Card> tempTrumpDeck = new ArrayList<Card>(0);
       // TODO Finish when all trumpcards decided
       trumpCardDeck = new Deck(tempTrumpDeck);
        
    }
    /**
     * Deals cards to Players and continues the round until a Player
     * 
     */
    public int deal()
    {
        return 0;
    }
    
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void incrementBet() {
        bet++;
    }

    public void clearBet() {
        bet = 1;
    }

    public int getBet() {
        return bet;
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
        punishTracker = true;
        gameGUI.update(); // address here w/ michaelLLLLLLLLLLLELLWELGWELGWEGNLKWEJLTLW:EJT L:WEL:GKKJWE:gj;lwkjEL;KJEWG
        
        int increment = 6;
        double probability = 1 / increment;

        for (int i = 0; i < bet; i++) {
            if (Math.random() < probability) {
                return true;
            }
            increment--;
        }

        return false;
        punishTrucker = false;
    }
    
    public boolean getPunishStatus() {
        return punishTracker;
    }

}
