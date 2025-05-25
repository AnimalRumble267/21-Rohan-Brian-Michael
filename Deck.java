import java.util.*;

/**
 * 
 * 
 * @author
 * @version
 * 
 */
public class Deck
{
    private ArrayList<Card> idleDeck;
    private LinkedList<Card> activeDeck;

    /**
     * constructor that takes in any type of deck (number card or trump) and as idleDeck and instantiates activeDeck
     * (idleDeck = base deck that is used to reset // 
     * activeDeck = copy of idleDeck that will undergo all the changes throughout the rounds/games)
     * @param i
     */
    public Deck(ArrayList<Card> i) 
    {
        idleDeck = i;
        activeDeck = new LinkedList<Card>();
    }

    /**
     * uses the java collections shuffle algorithm to shuffle the activeDeck's cards (assumes that round just starts)
     */
    public void shuffle() 
    {
        activeDeck.clear();
        for (int i = 0; i < idleDeck.size(); i++) {
            activeDeck.add(idleDeck.get(i));
        }
        Collections.shuffle(activeDeck, new Random(System.currentTimeMillis()));
    }

    /**
     * remove the top most card from selected deck
     * @return top Card
     */
    public Card draw() 
    {
        if (activeDeck.isEmpty()) {
            return null;
        }
        return (activeDeck.removeFirst());
    }

    /**
     * gets the value of a given number or trump card
     * @param value
     * @return
     */
    public Card getCard(int value) {
        for (Card card : activeDeck) {
            if (card.getValue() == value) {
                return card;
            }
        }
        return null;
    }
}