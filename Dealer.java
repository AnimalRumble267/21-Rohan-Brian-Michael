/**
 * 
 * 
 * @author
 * @version
 * 
 */
public class Dealer
{
    private Player[] players;
    
    public Dealer()
    {
        players = new Player[2];
    }

    public Dealer(Player p1, Player p2)
    {
        players = new Player[2];
        players[0] = p1;
        players[1] = p2;
    }

    /**
     * Deals cards to Players and continues the round until a Player
     * is eliminated.
     */
    public void deal()
    {

    }

    public Player[] getPlayers()
    {
        return players;
    }
}