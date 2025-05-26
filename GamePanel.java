import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Represents the panel within the game window. Draws the screen based on the information
 * given to it by its dealer. Extends JPanel.
 * 
 * @author Michael Lee
 * @version 5/28/2025
 * 
 */
public class GamePanel extends JPanel
{
    private Dealer dealer;
    private Player[] players;
    private Sound[] soundEffects;
    private Font comicSansHalf = new Font("Comic Sans MS", Font.BOLD, GameGUI.UNIT_SIZE / 2);
    private Font comicSansFull = new Font("Comic Sans MS", Font.BOLD, GameGUI.UNIT_SIZE);
    private Font timesNewRomanHalf = new Font("Times New Roman", Font.BOLD, GameGUI.UNIT_SIZE / 2);
    private Font timesNewRomanFull = new Font("Times New Roman", Font.BOLD, GameGUI.UNIT_SIZE);
    private Color darkGreen = new Color(25, 87, 30);
    private Color brown = new Color(79, 59, 52);
    private BasicStroke border = new BasicStroke(5);

    /** The most recent mouse click */
    public static MouseEvent mouseClick = null;

    /**
     * Initializes a <code>GamePanel</code> object which uses the information
     * from the given dealer.
     * @param d the dealer
     */
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

    /**
     * Redraws this panel based on the information from this <code>GamePanel</code>'s dealer.
     * This method should not be called directly and instead should be called through the 
     * <code>repaint</code> method.
     * 
     * @param g the graphics object of this <code>GamePanel</code>
     */
    public void paintComponent(Graphics g)
    {
        //Something to note: the screen only updates after the entire method has finished executing
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        FontMetrics fontMetrics = g2.getFontMetrics();
        int stringWidth;
        
        g2.setColor(Color.DARK_GRAY);
        g2.drawRect(0, 0, this.getWidth(), this.getHeight());
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());

        // if the dealer is playing the game normally
        if (dealer.getStatus() == 1)
        {
            g2.setColor(darkGreen);
            g2.fillRect(0, 0, GameGUI.GAME_WINDOW_WIDTH, GameGUI.GAME_WINDOW_HEIGHT);
            int playerCardX;
            int playerCardY;
            for (int i = 0; i < 2; i++)
            {
                // Drawing table and player cards
                playerCardX = (int)(GameGUI.UNIT_SIZE * 1.5) + (i * (int)(-GameGUI.UNIT_SIZE * 1.5) + 
                              i * (GameGUI.GAME_WINDOW_WIDTH - (int)(2.5 * GameGUI.UNIT_SIZE)));
                playerCardY = (int)(GameGUI.UNIT_SIZE * 2.4);
                if (players[i].getNumberCardHand().size() > 0)
                {
                    drawRectWithBorder(g2, playerCardX - GameGUI.UNIT_SIZE / 8, playerCardY - GameGUI.UNIT_SIZE / 8 - 10,
                                   (int)(GameGUI.UNIT_SIZE * 1.25), (int)(GameGUI.UNIT_SIZE * 0.125) + 
                                   GameGUI.UNIT_SIZE * players[i].getNumberCardHand().size() + 
                                   (int)(GameGUI.UNIT_SIZE * 0.125), brown, Color.BLACK);
                }           
                drawPlayerHand(g2, i + 1, playerCardX, playerCardY - 10);

                // Drawing player profile pictures + background
                drawRectWithBorder(g2, playerCardX - GameGUI.UNIT_SIZE / 8, playerCardY - (int)(GameGUI.UNIT_SIZE * 1.7),
                                   (int)(GameGUI.UNIT_SIZE * 1.25), (int)(GameGUI.UNIT_SIZE * 1.25), 
                                   Color.DARK_GRAY, Color.BLACK);
                g2.drawImage(GameGUI.PLAYER_TILES[0].getImage(), playerCardX, playerCardY - (int)(GameGUI.UNIT_SIZE * 1.7),
                             GameGUI.UNIT_SIZE, GameGUI.UNIT_SIZE, null);
            }

            // Drawing trump cards that have been played
            if (dealer.getTrumpCards().size() != 0)
            {
                drawRectWithBorder(g2, GameGUI.GAME_WINDOW_WIDTH / 2 - GameGUI.UNIT_SIZE / 8 - GameGUI.UNIT_SIZE / 2, (int)(GameGUI.UNIT_SIZE * 3.3) - GameGUI.UNIT_SIZE / 8 + 5,
                                (int)(GameGUI.UNIT_SIZE * 1.25), GameGUI.UNIT_SIZE * dealer.getTrumpCards().size() + 15, Color.RED, Color.BLACK);
                drawTrumpCards(g2, GameGUI.GAME_WINDOW_WIDTH / 2 - GameGUI.UNIT_SIZE / 2, (int)(GameGUI.UNIT_SIZE * 3.3));
            }

            // Drawing the current max
            g2.setColor(Color.BLACK);
            g2.setFont(comicSansHalf);
            stringWidth = fontMetrics.stringWidth("GOAL");
            drawRectWithBorder(g2, GameGUI.GAME_WINDOW_WIDTH / 2 - stringWidth * 4, 15, stringWidth * 8,
                               (int)(GameGUI.UNIT_SIZE * 0.7), brown, Color.BLACK);
            g2.drawString("GOAL", GameGUI.GAME_WINDOW_WIDTH / 2 - stringWidth * 2, (int)(GameGUI.UNIT_SIZE * 0.5) + 20);
            stringWidth = fontMetrics.stringWidth(dealer.getGoal() + "");
            drawRectWithBorder(g2, GameGUI.GAME_WINDOW_WIDTH / 2 - stringWidth * 6, (int)(GameGUI.UNIT_SIZE * 0.8) + 6, 
                               stringWidth * 12, GameGUI.UNIT_SIZE - 30, Color.YELLOW, Color.BLACK);
            g2.drawString(dealer.getGoal() + "", GameGUI.GAME_WINDOW_WIDTH / 2 - stringWidth * 2, (int)(GameGUI.UNIT_SIZE * 1.3) + 6);

            // Drawing the bet
            g2.setColor(Color.BLACK);
            g2.setFont(comicSansHalf);
            stringWidth = fontMetrics.stringWidth("BET");
            drawRectWithBorder(g2, GameGUI.GAME_WINDOW_WIDTH / 2 - stringWidth * 4, (int)(GameGUI.UNIT_SIZE * 1.4) + 25, stringWidth * 8,
                               (int)(GameGUI.UNIT_SIZE * 0.7), brown, Color.BLACK);
            g2.drawString("BET", GameGUI.GAME_WINDOW_WIDTH / 2 - stringWidth * 2, (int)(GameGUI.UNIT_SIZE * 1.9) + 25);
            stringWidth = fontMetrics.stringWidth(dealer.getBet() + "");
            drawRectWithBorder(g2, GameGUI.GAME_WINDOW_WIDTH / 2 - stringWidth * 9, (int)(GameGUI.UNIT_SIZE * 2.1) + 25, 
                               stringWidth * 18, GameGUI.UNIT_SIZE - 30, Color.YELLOW, Color.BLACK);
            g2.drawString(dealer.getBet() + "", GameGUI.GAME_WINDOW_WIDTH / 2 - stringWidth * 2, (int)(GameGUI.UNIT_SIZE * 2.6) + 25);

            if (dealer.isFirstDeal())
            {
                g2.setColor(Color.RED);
                int index;
                if (players[0].isTurn())
                {
                    index = 0;
                }
                else
                {
                    index = 1;
                }

                stringWidth = fontMetrics.stringWidth(players[index].getName() + " is going first...");
                g2.drawString(players[index].getName() + " is going first...", GameGUI.GAME_WINDOW_WIDTH / 2 - stringWidth * 2, 
                              (int)(GameGUI.UNIT_SIZE* 6));
            }
        }
        // if the dealer is punishing a player
        else if (dealer.getStatus() == 2)
        {
            if (dealer.getPunishStatus() == 0)
            {
                // Setting background
                g2.setColor(Color.DARK_GRAY);
                g2.fillRect(0, 0, this.getWidth(), this.getHeight());

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
            }
            else if (dealer.getPunishStatus() == 1)
            {
                // Paints the screen completely black
                g2.setColor(Color.BLACK);
                g2.fillRect(0, 0, this.getWidth(), this.getHeight());
            }
            else if (dealer.getPunishStatus() == 2)
            {
                g2.setColor(Color.BLACK);
                g2.fillRect(0, 0, this.getWidth(), this.getHeight());
                if (dealer.playerWillBeEliminated())
                {
                    soundEffects[1].play();
                }   
                else
                {
                    soundEffects[0].play();
                }
            }
            else if (dealer.getPunishStatus() == 3)
            {
                g2.setColor(Color.DARK_GRAY);
                g2.drawRect(0, 0, this.getWidth(), this.getHeight());
                if (dealer.playerWillBeEliminated())
                {
                    soundEffects[1].stop();
                    soundEffects[1].setFramePosition(0);
                    g2.drawImage(GameGUI.PLAYER_TILES[1].getImage(), this.getWidth() / 2 - GameGUI.UNIT_SIZE,
                                 this.getHeight() / 2 - GameGUI.UNIT_SIZE, GameGUI.UNIT_SIZE * 2, GameGUI.UNIT_SIZE * 2, null);
                }
                else
                {
                    soundEffects[0].stop();
                    soundEffects[0].setFramePosition(0);
                    g2.drawImage(GameGUI.PLAYER_TILES[0].getImage(), this.getWidth() / 2 - GameGUI.UNIT_SIZE,
                                 this.getHeight() / 2 - GameGUI.UNIT_SIZE, GameGUI.UNIT_SIZE * 2, GameGUI.UNIT_SIZE * 2, null);
                }
            }
        }
        // if the dealer is starting the game
        else if (dealer.getStatus() == 0)
        {
            g2.setColor(Color.DARK_GRAY);
            g2.fillRect(0, 0, GameGUI.GAME_WINDOW_WIDTH, GameGUI.GAME_WINDOW_HEIGHT);

            g2.setColor(Color.RED);
            g2.setFont(comicSansFull);
            stringWidth = fontMetrics.stringWidth("21");
            g2.drawString("21", GameGUI.GAME_WINDOW_WIDTH / 2 - stringWidth * 3, (int)(GameGUI.UNIT_SIZE * 3));
        }
        // if the dealer is ending the game
        else // (dealer.getStatus() == 3)
        {
            this.setFocusable(true); // Can only receive mouse input after game is over
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, GameGUI.GAME_WINDOW_WIDTH, GameGUI.GAME_WINDOW_HEIGHT);
            g2.setFont(timesNewRomanFull);
            stringWidth = fontMetrics.stringWidth("Game Over");
            g2.setColor(Color.RED);
            g2.drawString("Game Over", this.getWidth() / 2 - stringWidth * 4, this.getHeight() / 2 - GameGUI.UNIT_SIZE);
            g2.setFont(timesNewRomanHalf);
            stringWidth = fontMetrics.stringWidth("Play Again?");
            g2.drawString("Play Again?", this.getWidth() / 2 - stringWidth * 4, this.getHeight() / 2 + (int)(GameGUI.UNIT_SIZE * 0.6));

            // TODO make sure to setFocusable(false) after the game over screen is done
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
        ArrayList<TrumpCard> trumpCards = dealer.getTrumpCards();
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

    /**
     * Waits for and returns the next mouse click within this <code>GamePanel</code>
     * @return the next mouse click
     */
    public MouseEvent nextMouseClick()
    {
        long currentTime = System.nanoTime();
        long lastTime = currentTime;
        long delta = 0;
        long waitTime = 1000000000 / 10; // checks 10 times per second

        while (mouseClick == null)
        {
            currentTime = System.nanoTime();
            delta += currentTime - lastTime;
            if (delta >= waitTime)
            {
                System.out.print("");
                delta = 0;
            }
            lastTime = currentTime;
        }
        MouseEvent temp = mouseClick;
        mouseClick = null;
        return temp;
    }
}
