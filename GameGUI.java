import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * 
 * 
 * @author
 * @version
 * 
 */
public class GameGUI 
{
    public static final int ORIGINAL_TILE_SIZE = 32;
    public static final int SCALE = 3;
    public static final int UNIT_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    public static final int GAME_PANEL_WIDTH = GameGUI.UNIT_SIZE * 9;
    public static final int GAME_PANEL_HEIGHT = GameGUI.UNIT_SIZE * 9;

    // TODO MUST UPDATE MONITOR DIMENSIONS IN "DisplayInformation.txt" BEFORE YOU START THE GAME
    /* private static Scanner scan = new Scanner("DisplayInformation.txt");
    private static final int MONITOR_WIDTH = scan.nextInt();
    private static final int MONITOR_HEIGHT = scan.nextInt(); */

    private static final String[] TRUMP_CARD_FILE_PATHS =  {"/images/trumpcards/trumpdraw2.png", 
                                                            "/images/trumpcards/trumpdraw3.png",
                                                            "/images/trumpcards/trumpdraw4.png",
                                                            "/images/trumpcards/trumpdraw5.png",
                                                            "/images/trumpcards/trumpdraw6.png",
                                                            "/images/trumpcards/trumpdraw7.png",
                                                            "/images/trumpcards/trumpgofor17.png",
                                                            "/images/trumpcards/trumpgofor24.png",
                                                            "/images/trumpcards/trumpgofor27.png",
                                                            "/images/trumpcards/trumpshield1.png",
                                                            "/images/trumpcards/trumpup1.png",
                                                            "/images/trumpcards/trumpup2.png"};
    public static final HashMap<String, String[]> TRUMP_CARD_DESCRIPTIONS = new HashMap<String, String[]>(12); 
    public static final Tile[] NUMBER_CARD_TILES = new Tile[12];
    public static final Tile[] TRUMP_CARD_TILES = new Tile[TRUMP_CARD_FILE_PATHS.length];
    public static final Tile NERF_GUN_TILE = new Tile(UNIT_SIZE, UNIT_SIZE);
    public static final Tile[] PLAYER_TILES = new Tile[2];

    private boolean guiStarted = false;

    private JFrame gameWindow;
    private GamePanel gamePanel;
    private Color darkGreen = new Color(25, 87, 30);
    private Dealer dealer;
    private GameListener listener = new GameListener();

    public GameGUI(Dealer d)
    {
        gameWindow = new JFrame();
        gamePanel = new GamePanel(d);
        dealer = d;
    }

    public void start()
    {
        setUpWindow();
        loadTiles();
        loadDescriptions();
        guiStarted = true;
    }

    private void setUpWindow()
    {
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setAlwaysOnTop(true);
        gameWindow.setResizable(false);
        gameWindow.setTitle("21");
        gameWindow.setFocusable(true);
        gamePanel.setPreferredSize(new Dimension(GAME_PANEL_WIDTH, GAME_PANEL_HEIGHT));
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

    public void updateGameWindow()
    {
        if (!guiStarted)
        {
            System.out.println("ERROR: Must start GUI first - updateGameWindow()");
            return;
        }
        gamePanel.repaint();
    }

    public void loadTiles()
    {
        Tile newTile;

        // Loading numbercards;
        for (int i = 0; i < NUMBER_CARD_TILES.length - 1; i++)
        {
            newTile = new Tile(UNIT_SIZE, UNIT_SIZE);
            newTile.loadImage("/images/numbercards/numbercard" + (i + 1) + ".png");
            NUMBER_CARD_TILES[i] = newTile;
        }

        NUMBER_CARD_TILES[11] = new Tile(UNIT_SIZE, UNIT_SIZE);
        NUMBER_CARD_TILES[11].loadImage("/images/numbercards/numbercardhidden.png");

        // Loading trumpcards
        for (int i = 0; i < TRUMP_CARD_FILE_PATHS.length; i++)
        {
            newTile = new Tile(UNIT_SIZE, UNIT_SIZE);
            newTile.loadImage(TRUMP_CARD_FILE_PATHS[i]);
            TRUMP_CARD_TILES[i] = newTile;
        }

        NERF_GUN_TILE.loadImage("/images/nerfgun.png");

        PLAYER_TILES[0] = new Tile(GameGUI.UNIT_SIZE, GameGUI.UNIT_SIZE);
        PLAYER_TILES[0].loadImage("/images/player/livingplayer.png");
        PLAYER_TILES[1] = new Tile(GameGUI.UNIT_SIZE, GameGUI.UNIT_SIZE);
        PLAYER_TILES[1].loadImage("/images/player/notlivingplayer.png");
    }

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

    // made public static for testing purposes
    public static void loadDescriptions()
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

    public int getGameWindowX()
    {
        return gameWindow.getX();
    }

    public int getGameWindowY()
    {
        return gameWindow.getY();
    }

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
