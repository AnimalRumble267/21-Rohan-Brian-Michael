import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.sound.sampled.*;

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
    private Sound[] soundEffects;
    private Font comicSans = new Font("Comic Sans MS", Font.BOLD, GameGUI.UNIT_SIZE / 2);

    public GamePanel(Dealer d)
    {
        dealer = d;
        players = new Player[2];
        players[0] = dealer.getPlayers().get(0);
        players[1] = dealer.getPlayers().get(1);
        soundEffects = new Sound[2];
        soundEffects[0] = new Sound("/sound/trigger.wav");
        soundEffects[1] = new Sound("/sound/blast.wav");
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        FontMetrics fontMetrics = g2.getFontMetrics();
        
        g2.setColor(Color.DARK_GRAY);
        g2.drawRect(0, 0, this.getWidth(), this.getHeight());

        // if the dealer is in the punishing stage, check each time if player is dead
        if (false) // dealer.isPunishing()
        {
            g2.drawImage(GameGUI.NERF_GUN_TILE.getImage(), this.getWidth() / 2 - GameGUI.UNIT_SIZE, this.getHeight() / 2,
                         GameGUI.UNIT_SIZE * 2, GameGUI.UNIT_SIZE * 2, null);

            GameGUI.wait(2.0);

            // Paints the screen completely black
            g2.setColor(Color.BLACK);
            g2.drawRect(0, 0, this.getWidth(), this.getHeight());

            GameGUI.wait(2.5);

            if (false) // dealer.playerIsEliminated()
            {
                soundEffects[1].play();
                soundEffects[1].stop();
                g2.setColor(Color.DARK_GRAY);
                g2.drawRect(0, 0, this.getWidth(), this.getHeight());
                g2.drawImage(GameGUI.PLAYER_TILES[1].getImage(), this.getWidth() / 2 - GameGUI.UNIT_SIZE,
                             this.getHeight() / 2, GameGUI.UNIT_SIZE * 2, GameGUI.UNIT_SIZE * 2, null);
                GameGUI.wait(2.0);
            }   
            else
            {
                soundEffects[0].play();
                soundEffects[0].stop();
                GameGUI.wait(2.0);
            }
        }
        else if (false) // dealer.isStarting()
        {
            // g2.drawRect(0, 0, );
        }
        else if (false) // dealer.isEnding()
        {

        }
        else
        {
            for (int i = 1; i < 3; i++)
            {
                drawPlayerHand(g2, i, GameGUI.UNIT_SIZE + (i * (-GameGUI.UNIT_SIZE) + i * (GameGUI.GAME_PANEL_WIDTH - GameGUI.UNIT_SIZE)),
                GameGUI.UNIT_SIZE);
            }

            int stringWidth;
            int stringHeight;
            //Might be Comicbd.ttf
            g2.setColor(Color.BLACK);
            g2.setFont(comicSans);
            // g2.drawLine(GameGUI.GAME_PANEL_WIDTH / 2, 0, GameGUI.GAME_PANEL_WIDTH / 2, GameGUI.GAME_PANEL_HEIGHT); // Reference Lines
            // g2.drawLine(0, GameGUI.GAME_PANEL_WIDTH / 2, GameGUI.GAME_PANEL_WIDTH, GameGUI.GAME_PANEL_WIDTH / 2); // Reference Lines
            stringWidth = fontMetrics.stringWidth("BET");
            System.out.println(stringWidth);
            g2.drawString("BET", GameGUI.GAME_PANEL_WIDTH / 2 - stringWidth * 2, GameGUI.UNIT_SIZE * 2);
            stringWidth = fontMetrics.stringWidth(dealer.getBet() + "");
            stringHeight = fontMetrics.get // TODO FINISH THIS
            System.out.println(stringWidth);
            g2.drawString(dealer.getBet() + "", GameGUI.GAME_PANEL_WIDTH / 2 - stringWidth * 2, (int)(GameGUI.UNIT_SIZE * 2.6));
            g2.setColor(Color.YELLOW);
            g2.drawRect(GameGUI.GAME_PANEL_WIDTH / 2 - stringWidth * 3, (int)(GameGUI.UNIT_SIZE * 2.6), stringWidth * 3, );
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
            currentTrumpCard = trumpCardHand.get(i / GameGUI.UNIT_SIZE);
            currentTile = GameGUI.TRUMP_CARD_TILES[GameGUI.getTrumpCardIndex(currentTrumpCard)];
            g2.drawImage(currentTile.getImage(), x, i, GameGUI.UNIT_SIZE, 
                         GameGUI.UNIT_SIZE, null);
        }
    }
}
