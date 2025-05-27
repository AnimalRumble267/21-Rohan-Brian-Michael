import java.util.*;

/**
 * A class meant to store and retrieve cards. Capable of initializing,
 * shuffling, and retrieving either the first or a targetted card from the Deck.
 * 
 * @author Rohan Chaudhary
 * @version 5-26-2025
 */
public class Deck
{
    private ArrayList<Card>  idleDeck;
    private LinkedList<Card> activeDeck;

    /**
     * A constructor that initializes a linkedlist and arraylist based off a
     * linkedlist of Cards ArrayList is the "idle" deck meant to store the Cards
     * between resets LinkedList is the active deck which has cards taken in and
     * out of it
     * 
     * @param i - Initial ArrayList dictating the contents of Deck
     */
    public Deck(ArrayList<Card> i)
    {
        idleDeck = i;
        activeDeck = new LinkedList<Card>();
    }


    /**
     * Resets the active deck, filling it in with values from the idle deck
     * before shuffling it using a method from Collections
     */
    public void shuffle()
    {
        activeDeck.clear();
        for (int i = 0; i < idleDeck.size(); i++)
        {
            activeDeck.add(idleDeck.get(i));
        }
        Collections.shuffle(activeDeck, new Random(System.currentTimeMillis()));
    }


    /**
     * Returns the top Card from the activeDeck
     * 
     * @return - null if activeDeck is empty, else the top Card
     */
    public Card draw()
    {
        if (activeDeck.isEmpty())
        {
            return null;
        }
        return (activeDeck.removeFirst());
    }


    /**
     * Searches for a Card in the activeDeck, searching for a targeted value
     * 
     * @param value - int value of desired card
     * @return - Desired Card, or null
     */
    public Card getCard(int value)
    {
        for (Card card : activeDeck)
        {
            if (card.getValue() == value)
            {
                return card;
            }
        }
        return null;
    }
}
