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
    private Font comicSansHalf = new Font("Comic Sans MS", Font.BOLD, GameGUI.UNIT_SIZE / 2);
    private Font comicSansFull = new Font("Comic Sans MS", Font.BOLD, GameGUI.UNIT_SIZE);
    private Font timesNewRomanFull = new Font("Times New Roman", Font.BOLD, GameGUI.UNIT_SIZE * 4);
    private Color darkGreen = new Color(25, 87, 30);
    private Color brown = new Color(79, 59, 52);
    private BasicStroke border = new BasicStroke(5);

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
        
        g2.setColor(Color.DARK_GRAY);
        g2.drawRect(0, 0, this.getWidth(), this.getHeight());
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());

        // if the dealer is playing the game normally
        if (dealer.getStatus() == 1)
        {
            g2.setColor(darkGreen);
            g2.fillRect(0, 0, GameGUI.GAME_PANEL_WIDTH, GameGUI.GAME_PANEL_HEIGHT);
            int playerCardX;
            int playerCardY;
            for (int i = 0; i < 2; i++)
            {
                // Drawing table and cards
                playerCardX = (int)(GameGUI.UNIT_SIZE * 1.5) + (i * (int)(-GameGUI.UNIT_SIZE * 1.5) + 
                              i * (GameGUI.GAME_PANEL_WIDTH - (int)(2.5 * GameGUI.UNIT_SIZE)));
                playerCardY = (int)(GameGUI.UNIT_SIZE * 2.4);
                drawRectWithBorder(g2, playerCardX - GameGUI.UNIT_SIZE / 8, playerCardY - GameGUI.UNIT_SIZE / 8,
                                   (int)(GameGUI.UNIT_SIZE * 1.25), (int)(GameGUI.UNIT_SIZE * 0.125) + 
                                   GameGUI.UNIT_SIZE * players[i].getNumberCardHand().size() + 
                                   (int)(GameGUI.UNIT_SIZE * 0.125), brown, Color.BLACK);
                drawPlayerHand(g2, i + 1, playerCardX, playerCardY);

                // Drawing player profile pictures + background
                drawRectWithBorder(g2, playerCardX - GameGUI.UNIT_SIZE / 8, playerCardY - (int)(GameGUI.UNIT_SIZE * 1.7),
                                   (int)(GameGUI.UNIT_SIZE * 1.25), (int)(GameGUI.UNIT_SIZE * 1.25), 
                                   Color.DARK_GRAY, Color.BLACK);
                g2.drawImage(GameGUI.PLAYER_TILES[0].getImage(), playerCardX, playerCardY - (int)(GameGUI.UNIT_SIZE * 1.7),
                             GameGUI.UNIT_SIZE, GameGUI.UNIT_SIZE, null);
            }

            // Drawing trump cards that have been played
            // TODO CHANGE AFTER TESTING IS DONE
            drawRectWithBorder(g2, GameGUI.GAME_PANEL_WIDTH / 2 - GameGUI.UNIT_SIZE / 8 - GameGUI.UNIT_SIZE / 2, (int)(GameGUI.UNIT_SIZE * 3) - GameGUI.UNIT_SIZE / 8,
                               (int)(GameGUI.UNIT_SIZE * 1.25), (int)(GameGUI.UNIT_SIZE * 0.25) + GameGUI.UNIT_SIZE * 2 + 
                               (int)(GameGUI.UNIT_SIZE * 0.25), Color.RED, Color.BLACK);
            /* drawRectWithBorder(g2, GameGUI.GAME_PANEL_WIDTH / 2 - GameGUI.UNIT_SIZE / 8 - GameGUI.UNIT_SIZE / 2, (int)(GameGUI.UNIT_SIZE * 3) - GameGUI.UNIT_SIZE / 8,
                               (int)(GameGUI.UNIT_SIZE * 1.25), (int)(GameGUI.UNIT_SIZE * 0.25) + GameGUI.UNIT_SIZE * dealer.getTrumpCards().size() + 
                               (int)(GameGUI.UNIT_SIZE * 0.25), Color.RED, Color.BLACK); */
            drawTrumpCards(g2, GameGUI.GAME_PANEL_WIDTH / 2 - GameGUI.UNIT_SIZE / 2, (int)(GameGUI.UNIT_SIZE * 3.3));

            // Drawing the bet
            int stringWidth;
            g2.setColor(Color.BLACK);
            g2.setFont(comicSansHalf);
            stringWidth = fontMetrics.stringWidth("BET");
            drawRectWithBorder(g2, GameGUI.GAME_PANEL_WIDTH / 2 - stringWidth * 4, (int)(GameGUI.UNIT_SIZE * 1.4), stringWidth * 8,
                               (int)(GameGUI.UNIT_SIZE * 0.7), brown, Color.BLACK);
            g2.drawString("BET", GameGUI.GAME_PANEL_WIDTH / 2 - stringWidth * 2, (int)(GameGUI.UNIT_SIZE * 1.9));
            stringWidth = fontMetrics.stringWidth(dealer.getBet() + "");
            drawRectWithBorder(g2, GameGUI.GAME_PANEL_WIDTH / 2 - stringWidth * 9, (int)(GameGUI.UNIT_SIZE * 2.1), 
                               stringWidth * 18, GameGUI.UNIT_SIZE - 30, Color.YELLOW, Color.BLACK);
            g2.drawString(dealer.getBet() + "", GameGUI.GAME_PANEL_WIDTH / 2 - stringWidth * 2, (int)(GameGUI.UNIT_SIZE * 2.6));
        }
        // if the dealer is punishing a player
        else if (dealer.getStatus() == 2)
        {
            // Drawing the NERF gun
            g2.setStroke(border);
            g2.setColor(brown);
            g2.fillArc(this.getWidth() / 2 - GameGUI.UNIT_SIZE, this.getHeight() / 2 - GameGUI.UNIT_SIZE, 
                       (int)(GameGUI.UNIT_SIZE * 2), (int)(GameGUI.UNIT_SIZE * 2), 0, 360);
            g2.setColor(Color.BLACK);
            g2.drawArc(this.getWidth() / 2 - GameGUI.UNIT_SIZE, this.getHeight() / 2 - GameGUI.UNIT_SIZE, 
                       (int)(GameGUI.UNIT_SIZE * 2), (int)(GameGUI.UNIT_SIZE * 2), 0, 360);
            g2.drawImage(GameGUI.NERF_GUN_TILE.getImage(), this.getWidth() / 2 - GameGUI.UNIT_SIZE / 2, 
                         this.getHeight() / 2 - GameGUI.UNIT_SIZE / 2, GameGUI.UNIT_SIZE, GameGUI.UNIT_SIZE, null);
            GameGUI.wait(2.0);

            // Paints the screen completely black
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, this.getWidth(), this.getHeight());

            GameGUI.wait(2.0);

            if (dealer.playerWillDie())
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
        // if the dealer is starting the game
        else if (dealer.getStatus() == 0)
        {
            g2.setColor(Color.DARK_GRAY);
            g2.fillRect(0, 0, GameGUI.GAME_PANEL_WIDTH, GameGUI.GAME_PANEL_HEIGHT);

            int stringWidth;
            g2.setColor(Color.RED);
            g2.setFont(comicSansFull);
            stringWidth = fontMetrics.stringWidth("21");
            g2.drawString("21", GameGUI.GAME_PANEL_WIDTH / 2 - stringWidth * 3, (int)(GameGUI.UNIT_SIZE * 3));
        }
        // if the dealer is ending the game
        else if (dealer.getStatus() == 3)
        {

        }
        else
        {

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
                currentTile = GameGUI.NUMBER_CARD_TILES[11];
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
        trumpCards.add(new TrumpCard(17, "gofor"));
        trumpCards.add(new TrumpCard(2, "draw"));
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

    private void drawRectWithBorder(Graphics2D g2, int x, int y, int width, int height, Color fillColor, Color borderColor)
    {
        g2.setColor(fillColor);
        g2.fillRect(x, y, width, height);
        g2.setStroke(border);
        g2.setColor(borderColor);
        g2.drawRect(x, y, width, height);
    }
}
