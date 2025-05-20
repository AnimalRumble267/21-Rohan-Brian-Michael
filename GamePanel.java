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
    private Color darkGreen = new Color(25, 87, 30);

    public GamePanel(Dealer d)
    {
        dealer = d;
        players = new Player[2];
        players[0] = dealer.getPlayers().get(0);
        players[1] = dealer.getPlayers().get(1);
        soundEffects = new Sound[2];
        soundEffects[0] = new Sound("/sound/trigger.wav");
        soundEffects[1] = new Sound("/sound/blast.wav");
        soundEffects[0].loadSound();
        soundEffects[1].loadSound();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        FontMetrics fontMetrics = g2.getFontMetrics();
        
        g2.setColor(darkGreen);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        System.out.println("Made it to here");
        GameGUI.wait(10.0);

        // if the dealer is in the punishing stage, check each time if player is dead
        if (dealer.isPunishing())
        {
            g2.drawImage(GameGUI.NERF_GUN_TILE.getImage(), this.getWidth() / 2 - GameGUI.UNIT_SIZE, this.getHeight() / 2,
                         GameGUI.UNIT_SIZE * 2, GameGUI.UNIT_SIZE * 2, null);

            GameGUI.wait(2.0);

            // Paints the screen completely black
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, this.getWidth(), this.getHeight());

            GameGUI.wait(2.5);

            if (dealer.willDie())
            {
                soundEffects[1].play();
                GameGUI.wait(1.0);
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
                GameGUI.wait(0.5);
                soundEffects[0].stop();
                GameGUI.wait(1.0);
            }
        }
        else if (false) // dealer.isStarting()
        {
            // g2.fillRect(0, 0, );
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

            drawTrumpCards(g2, GameGUI.GAME_PANEL_WIDTH / 2 - GameGUI.UNIT_SIZE / 2, GameGUI.UNIT_SIZE * 3 - 10);

            // Drawing the bet
            int stringWidth;
            g2.setColor(Color.BLACK);
            g2.setFont(comicSans);

            // g2.drawLine(GameGUI.GAME_PANEL_WIDTH / 2, 0, GameGUI.GAME_PANEL_WIDTH / 2, GameGUI.GAME_PANEL_HEIGHT); // Reference Lines
            // g2.drawLine(0, GameGUI.GAME_PANEL_WIDTH / 2, GameGUI.GAME_PANEL_WIDTH, GameGUI.GAME_PANEL_WIDTH / 2); // Reference Lines

            stringWidth = fontMetrics.stringWidth("BET");
            g2.drawString("BET", GameGUI.GAME_PANEL_WIDTH / 2 - stringWidth * 2, GameGUI.UNIT_SIZE * 2);
            stringWidth = fontMetrics.stringWidth(dealer.getBet() + "");
            g2.setColor(Color.RED);
            g2.fillRect(GameGUI.GAME_PANEL_WIDTH / 2 - 2 - stringWidth * 3, (int)(GameGUI.UNIT_SIZE * 2.1), stringWidth * 6, GameGUI.UNIT_SIZE - 30);
            g2.setStroke(new BasicStroke(3));
            g2.setColor(Color.BLACK);
            g2.drawRect(GameGUI.GAME_PANEL_WIDTH / 2 - 2 - stringWidth * 3, (int)(GameGUI.UNIT_SIZE * 2.1), stringWidth * 6, GameGUI.UNIT_SIZE - 30);
            g2.drawString(dealer.getBet() + "", GameGUI.GAME_PANEL_WIDTH / 2 - stringWidth * 2, (int)(GameGUI.UNIT_SIZE * 2.6));
        }
        g2.dispose();
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
        for (i = y; i < y + numberCardHand.size() * GameGUI.UNIT_SIZE; i += GameGUI.UNIT_SIZE)
        {
            currentNumberCard = numberCardHand.get((i - y) / GameGUI.UNIT_SIZE);
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
    }

    private void drawTrumpCards(Graphics g2, int x, int y)
    {
        //ArrayList<TrumpCard> trumpCards = dealer.getTrumpCards();
        ArrayList<TrumpCard> trumpCards = new ArrayList<>();
        TrumpCard currentTrumpCard;
        Tile currentTile;
        int i;

        for (i = y; i < y + trumpCards.size() * GameGUI.UNIT_SIZE; i += GameGUI.UNIT_SIZE)
        {
            currentTrumpCard = trumpCards.get((i - y) / GameGUI.UNIT_SIZE);
            currentTile = GameGUI.TRUMP_CARD_TILES[GameGUI.getTrumpCardIndex(currentTrumpCard)];
            g2.drawImage(currentTile.getImage(), x, i, GameGUI.UNIT_SIZE, 
                         GameGUI.UNIT_SIZE, null);
        }
    }
}
