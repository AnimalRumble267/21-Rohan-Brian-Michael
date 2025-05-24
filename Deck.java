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

    public Deck(ArrayList<Card> i) 
    {
        idleDeck = i;
        activeDeck = new LinkedList<Card>();
    }

    public void shuffle() 
    {
        activeDeck.clear();
        for (int i = 0; i < idleDeck.size(); i++) {
            activeDeck.add(idleDeck.get(i));
        }
        Collections.shuffle(activeDeck, new Random(System.currentTimeMillis()));
    }

    public Card draw() 
    {
        if (activeDeck.isEmpty()) {
            return null;
        }
        return (activeDeck.removeFirst());
    }

    public Card getCard(int value) {
        return (activeDeck.remove(value));
    }
}