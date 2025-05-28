import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;

/**
 * Represents the graphical user interface (GUI) for the game, which is visible to 
 * both players at all times. Displays each player's visible cards and the 
 * trump cards that have been played during the round. Displays the current 
 * "goal" (e.g., 21, 17, etc) and the current "bet" (e.g., 1, 2, etc). Displays 
 * the punishment at the end of each round, showing if the player has been 
 * eliminated or has survived with drawings and sound.
 * 
 * This class also contains other helpful methods such as a wait method for 
 * other classes to use. This class also contains public symbolic constants
 * such as the card images and dimensions of the screen for other classes to 
 * reference.
 * 
 * @author Michael Lee
 * @version 5/28/2025
 * 
 */
public class GameGUI 
{
    /** The original size each image tile */
    public static final int ORIGINAL_TILE_SIZE = 32;
    /** The scale used to scale up each image tile */
    public static final int SCALE = 3;
    /** The actual size of each image tile on the screen */
    public static final int UNIT_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    /** The width of the game window */
    public static final int GAME_WINDOW_WIDTH = GameGUI.UNIT_SIZE * 9;
    /** The height of the game window */
    public static final int GAME_WINDOW_HEIGHT = GameGUI.UNIT_SIZE * 9;

    private static final String[] TRUMP_CARD_FILE_PATHS =  {"/images/trumpcards/trumpdraw1.png",
                                                            "/images/trumpcards/trumpdraw2.png", 
                                                            "/images/trumpcards/trumpdraw3.png",
                                                            "/images/trumpcards/trumpdraw4.png",
                                                            "/images/trumpcards/trumpdraw5.png",
                                                            "/images/trumpcards/trumpdraw6.png",
                                                            "/images/trumpcards/trumpdraw7.png",
                                                            "/images/trumpcards/trumpdraw8.png",
                                                            "/images/trumpcards/trumpdraw9.png",
                                                            "/images/trumpcards/trumpdraw10.png",
                                                            "/images/trumpcards/trumpdraw11.png",
                                                            "/images/trumpcards/trumpgofor17.png",
                                                            "/images/trumpcards/trumpgofor24.png",
                                                            "/images/trumpcards/trumpgofor27.png",
                                                            "/images/trumpcards/trumpshield1.png",
                                                            "/images/trumpcards/trumpup1.png",
                                                            "/images/trumpcards/trumpup2.png"};
    /** 
     * The descriptions for all trump cards. Keys are represented by the concatenation of a
     * <code>TrumpCard</code>'s <code>getType</code> and <code>getValue</code> methods' return values 
     * in that order. Values represent the title of the description (element 0) and the full 
     * description (element 1).
     */
    public static final HashMap<String, String[]> TRUMP_CARD_DESCRIPTIONS = new HashMap<String, String[]>(12);
    /** 
     * The image tiles for all number cards. To find the index for a <code>NumberCard</code>, 
     * take the value from the <code>NumberCard</code>'s <code>getValue</code> method and 
     * subtract 1 from it. The last <code>Tile</code> of the array is the image tile for a 
     * hidden card.
     */ 
    public static final Tile[] NUMBER_CARD_TILES = new Tile[12];
    /** 
     * The image tiles for all trump cards. Use <code>getTrumpCardIndex</code> to 
     * find the index of a <code>TrumpCard</code>.
     */
    public static final Tile[] TRUMP_CARD_TILES = new Tile[TRUMP_CARD_FILE_PATHS.length];
    /** The image tile for the nerf gun */
    public static final Tile NERF_GUN_TILE = new Tile("/images/nerfgun.png");
    /** 
     * The image tiles for the players. Index 0 is the image tile for a player who has 
     * not been eliminated and Index 1 is the image tile for a player who has been
     * eliminated.
     */
    public static final Tile[] PLAYER_TILES = new Tile[3];

    private boolean guiStarted = false;
    private JFrame gameWindow;
    private GamePanel gamePanel;
    private JFrame turnFrame;
    private JPanel turnPanel;
    private JTextPane turnTextPane;
    private Color darkGreen = new Color(25, 87, 30);
    private Dealer dealer;
    private GameListener listener;

    /**
     * Initializes a <code>GameGUI</code> object which uses the information
     * from the given dealer.
     * @param d the dealer
     */
    public GameGUI(Dealer d)
    {
        dealer = d;
    }

    /**
     * Starts the GUI. Initializes the game window and loads all image tiles and
     * trump card descriptions.
     */
    public void start()
    {
        gameWindow = new JFrame();
        gamePanel = new GamePanel(dealer);
        setUpWindow();

        turnFrame = new JFrame();
        turnPanel = new JPanel();
        turnTextPane = new JTextPane();
        setUpTurnFrame();

        listener = new GameListener();
        loadTiles();
        loadDescriptions();
        
        guiStarted = true;
    }

    /**
     * Stops the GUI. Closes the game window in a fashion similar to hitting the "x"
     * manually.
     */
    public void stop()
    {
        guiStarted = false;
        gameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameWindow.dispatchEvent(new WindowEvent(gameWindow, WindowEvent.WINDOW_CLOSING));
    }

    private void setUpWindow()
    {
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setAlwaysOnTop(true);
        gameWindow.setResizable(false);
        gameWindow.setTitle("21");
        gameWindow.setFocusable(true);
        gamePanel.setPreferredSize(new Dimension(GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT));
        gamePanel.setBackground(darkGreen);
        gamePanel.setDoubleBuffered(true);
        gamePanel.setFocusable(false);
        gamePanel.addMouseListener(listener);
        gamePanel.setVisible(true);
        gameWindow.add(gamePanel);
        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);
    }

    private void setUpTurnFrame()
    {
        turnFrame.setAlwaysOnTop(true);
        turnFrame.setResizable(false);
        turnFrame.setTitle("Changing Turns...");
        turnFrame.setFocusable(false);
        turnFrame.setVisible(false);
        turnPanel.setPreferredSize(new Dimension(GameGUI.UNIT_SIZE * 4, (int)(GameGUI.UNIT_SIZE * 0.67)));
        turnTextPane.setPreferredSize(new Dimension(GameGUI.UNIT_SIZE * 4, (int)(GameGUI.UNIT_SIZE * 0.67)));
        turnTextPane.setFocusable(false);
        turnFrame.setSize(new Dimension(GameGUI.UNIT_SIZE * 4, (int)(GameGUI.UNIT_SIZE * 0.67)));
        turnPanel.setDoubleBuffered(true);
        turnPanel.setFocusable(false);
        turnPanel.setVisible(false);
        turnTextPane.setVisible(false);
        turnPanel.add(turnTextPane);
        turnFrame.add(turnPanel);
        turnFrame.setLocationRelativeTo(null);
        turnTextPane.setText("  Please hand the computer to the other player");
    }

    private void loadTiles()
    {
        Tile newTile;

        // Loading numbercards;
        for (int i = 0; i < NUMBER_CARD_TILES.length - 1; i++)
        {
            newTile = new Tile("/images/numbercards/numbercard" + (i + 1) + ".png");
            newTile.loadImage();
            NUMBER_CARD_TILES[i] = newTile;
        }

        NUMBER_CARD_TILES[11] = new Tile("/images/numbercards/numbercardhidden.png");
        NUMBER_CARD_TILES[11].loadImage();

        // Loading trumpcards
        for (int i = 0; i < TRUMP_CARD_FILE_PATHS.length; i++)
        {
            newTile = new Tile(TRUMP_CARD_FILE_PATHS[i]);
            newTile.loadImage();
            TRUMP_CARD_TILES[i] = newTile;
        }

        NERF_GUN_TILE.loadImage();

        PLAYER_TILES[0] = new Tile("/images/player/livingplayer.png");
        PLAYER_TILES[0].loadImage();
        PLAYER_TILES[1] = new Tile("/images/player/notlivingplayer.png");
        PLAYER_TILES[1].loadImage();
        PLAYER_TILES[2] = new Tile("/images/player/sampleGIF.gif");
        PLAYER_TILES[2].loadImage();
    }

    private void loadDescriptions()
    {
        TRUMP_CARD_DESCRIPTIONS.put("draw1", new String[]{"Draw 1", "  If it exists in the deck, draw a card with the value of 1"});
        TRUMP_CARD_DESCRIPTIONS.put("draw2", new String[]{"Draw 2", "  If it exists in the deck, draw a card with the value of 2"});
        TRUMP_CARD_DESCRIPTIONS.put("draw3", new String[]{"Draw 3", "  If it exists in the deck, draw a card with the value of 3"});
        TRUMP_CARD_DESCRIPTIONS.put("draw4", new String[]{"Draw 4", "  If it exists in the deck, draw a card with the value of 4"});
        TRUMP_CARD_DESCRIPTIONS.put("draw5", new String[]{"Draw 5", "  If it exists in the deck, draw a card with the value of 5"});
        TRUMP_CARD_DESCRIPTIONS.put("draw6", new String[]{"Draw 6", "  If it exists in the deck, draw a card with the value of 6"});
        TRUMP_CARD_DESCRIPTIONS.put("draw7", new String[]{"Draw 7", "  If it exists in the deck, draw a card with the value of 7"});
        TRUMP_CARD_DESCRIPTIONS.put("gofor17", new String[]{"Go For 17", "  Sets the goal to 17, overwriting the current goal."});
        TRUMP_CARD_DESCRIPTIONS.put("gofor24", new String[]{"Go For 24", "  Sets the goal to 24, overwriting the current goal."});
        TRUMP_CARD_DESCRIPTIONS.put("gofor27", new String[]{"Go For 27", "  Sets the goal to 27, overwriting the current goal."});
        TRUMP_CARD_DESCRIPTIONS.put("shield1", new String[]{"Shield 1", "  Decrease the bet by 1"});
        TRUMP_CARD_DESCRIPTIONS.put("up1", new String[]{"Bet 1", "  Increase the bet by 1"});
        TRUMP_CARD_DESCRIPTIONS.put("up2", new String[]{"Bet 2", "  Increase the bet by 2"});
    }

    /** Writes the message telling the player that the turn is changing */
    public void writeTurnMessage()
    {
        turnFrame.setVisible(true);
        turnPanel.setVisible(true);
        turnTextPane.setVisible(true);
    }

    /** Clears the message telling the player that the turn is changing */
    public void clearTurnMessage()
    {
        turnFrame.setVisible(false);
        turnPanel.setVisible(false);
        turnTextPane.setVisible(false);
    }

    /**
     * Returns the index of the <code>TrumpCard</code>'s image tile in 
     * <code>TRUMP_CARD_TILES</code>.
     * @param trumpCard the trump card
     * @return the index of the trump card's image tile in <code>TRUMP_CARD_TILES</code>
     */
    public static int getTrumpCardIndex(TrumpCard trumpCard)
    {
        String type = trumpCard.getType();
        int value = trumpCard.getValue();
        for (int i = 0; i < TRUMP_CARD_FILE_PATHS.length; i++)
        {
            if (TRUMP_CARD_FILE_PATHS[i].contains(type) &&
                TRUMP_CARD_FILE_PATHS[i].contains(value + ""))
            {
                return i;
            }
        }
        System.out.println("Could not find trump card: " + type + " " + value);
        return -1;
    }

    /**
     * Redraws the game window based on the most current information from this
     * <code>GameGUI</code>'s dealer.
     */
    public void updateGameWindow()
    {
        if (!guiStarted)
        {
            System.out.println("ERROR: Must start GUI first - updateGameWindow()");
            return;
        }
        gamePanel.repaint();
    }

    /**
     * Causes the program to wait for a number of seconds.
     * @param sec the number of seconds
     */
    public static void wait(double sec)
    {
        long currentTime = System.nanoTime();
        long lastTime = currentTime;
        long delta = 0;
        double waitTime = sec * 1000000000L;

        while (delta < waitTime)
        {
            currentTime = System.nanoTime();
            delta += currentTime - lastTime;
            lastTime = currentTime;
        }
    }

    /**
     * Returns the x value of the game window's location.
     * @return the x value of the game window's location
     */
    public int getGameWindowX()
    {
        return gameWindow.getX();
    }

    /**
     * Returns the y value of the game window's location.
     * @return the y value of the game window's location
     */
    public int getGameWindowY()
    {
        return gameWindow.getY();
    }

    /**
     * Waits for and returns the next mouse click within the game window.
     * @return the next mouse click
     */
    public MouseEvent nextMouseClick()
    {
        if (!guiStarted)
        {
            System.out.println("ERROR: Must start GUI first");
            return null;
        }
        return gamePanel.nextMouseClick();
    }
}
