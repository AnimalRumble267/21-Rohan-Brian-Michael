import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
 * 
 * 
 * @author Michael Lee
 * @version
 * 
 */
public class GamePanel extends JPanel
{
    private Dealer dealer;
    private Player[] players;

    public GamePanel(Dealer d)
    {
        dealer = d;
        players = new Player[2];
        players[0] = dealer.getPlayers().get(0);
        players[1] = dealer.getPlayers().get(1);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        if (dealer.getPunishStatus())
        {
            // TODO Think of a way to know what to display during the punishment
        }
        else
        {
            for (int i = 0; i < 2; i++)
            {
                drawPlayerHand(g2, i, GameGUI.UNIT_SIZE + (i * (-GameGUI.UNIT_SIZE) + i * (GameGUI.GAME_PANEL_WIDTH - GameGUI.UNIT_SIZE)),
                GameGUI.UNIT_SIZE);
            }

        //Might be Comicbd.ttf
        g2.setFont(new Font("Comic Sans MS", Font.BOLD, GameGUI.UNIT_SIZE / 2));
        g2.drawString("BET", GameGUI.GAME_PANEL_WIDTH / 2 - 20, GameGUI.UNIT_SIZE);
        }
        
    }

    private void drawPlayerHand(Graphics2D g2, int playerNumber, int x, int y)
    {
        Player player = players[playerNumber - 1];
        ArrayList<NumberCard> numberCardHand = player.getNumberCardHand();
        ArrayList<TrumpCard> trumpCardHand = player.getTrumpCardHand();
        NumberCard currentNumberCard;
        TrumpCard currentTrumpCard;
        Tile currentTile;
        int i;

        // Drawing number cards
        for (i = y; i < numberCardHand.size() * GameGUI.UNIT_SIZE; i += GameGUI.UNIT_SIZE)
        {
            currentNumberCard = numberCardHand.get(i / GameGUI.UNIT_SIZE);
            if (currentNumberCard.getIsHidden())
            {
                currentTile = GameGUI.NUMBER_CARD_TILES[12];
            }
            else
            {
                currentTile = GameGUI.NUMBER_CARD_TILES[currentNumberCard.getValue() - 1];
            }
            g2.drawImage(currentTile.getImage(), x, i, GameGUI.UNIT_SIZE, 
                         GameGUI.UNIT_SIZE, null);
        }

        // TODO Decide how the GameGUI will know what trump cards have been played (so it knows what the draw where)
        // You could have dealer have two lists, one having player 1's played trump cards and two having player 2's played trump cards

        // Drawing trump cards
        for ( ; i < numberCardHand.size() * GameGUI.UNIT_SIZE + 
            trumpCardHand.size() * GameGUI.UNIT_SIZE; i += GameGUI.UNIT_SIZE)
        {
            currentTrumpCard = trumpCardHand.get(i);
            if ()
            currentTile = GameGUI.TRUMP_CARD_TILES[GameGUI.getTrumpCardIndex(currentTrumpCard)];
            g2.drawImage(currentTile.getImage(), x, i, GameGUI.UNIT_SIZE, 
                         GameGUI.UNIT_SIZE, null);
        }
    }
}
