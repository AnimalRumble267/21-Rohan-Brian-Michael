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
    public static final int GAME_PANEL_WIDTH = GameGUI.UNIT_SIZE * 10;
    public static final int GAME_PANEL_HEIGHT = GameGUI.UNIT_SIZE * 10;

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
    public static final Tile[] NUMBER_CARD_TILES = new Tile[12];
    public static final Tile[] TRUMP_CARD_TILES = new Tile[TRUMP_CARD_FILE_PATHS.length];
    public static final Tile NERF_GUN_TILE = new Tile(UNIT_SIZE, UNIT_SIZE);
    public static final Tile[] PLAYER_TILES = new Tile[2];

    private static boolean tilesLoaded = false;
    private boolean guiStarted = false;

    private JFrame gameWindow;
    private GamePanel gamePanel;
    private Dealer dealer;

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
        guiStarted = true;
    }

    private void setUpWindow()
    {
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle("21");
        gameWindow.setFocusable(true);
        gamePanel.setPreferredSize(new Dimension(GAME_PANEL_WIDTH, GAME_PANEL_HEIGHT));
        gamePanel.setBackground(Color.DARK_GRAY);
        gamePanel.setDoubleBuffered(true);
        gamePanel.setFocusable(true);
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
            System.out.println("ERROL: Must start GUI first");
            return;
        }
        gamePanel.repaint();
    }

    // TODO this is static only for testing purposes
    public static void loadTiles()
    {
        if (tilesLoaded)
        {
            return;
        }

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

        // Loading numbercards
        for (int i = 0; i < TRUMP_CARD_FILE_PATHS.length; i++)
        {
            newTile = new Tile(UNIT_SIZE, UNIT_SIZE);
            newTile.loadImage(TRUMP_CARD_FILE_PATHS[i]);
            TRUMP_CARD_TILES[i] = newTile;
        }

        NERF_GUN_TILE.loadImage("/images/nerfgun.png");

        // TODO ADD THESE IMAGES TO THE PLAYER FOLDER
        PLAYER_TILES[0] = new Tile(GameGUI.UNIT_SIZE, GameGUI.UNIT_SIZE);
        PLAYER_TILES[0].loadImage("/images/player/livingplayer.png");
        PLAYER_TILES[1] = new Tile(GameGUI.UNIT_SIZE, GameGUI.UNIT_SIZE);
        //PLAYER_TILES[1].loadImage("/image/player/notlivingplayer.png");

        tilesLoaded = true;
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
        return -1;
    }

    // TODO static only for testing
    public static boolean tilesLoaded()
    {
        return tilesLoaded;
    }

    // TODO static only for testing
    public static Tile[] getNumberCardTiles()
    {
        return NUMBER_CARD_TILES;
    }

    // TODO static only for testing
    public static Tile[] getTrumpCardTiles()
    {
        return TRUMP_CARD_TILES;
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

    /* public static boolean pointInBounds(Point point, Component component)
    {
        return ((point.getX() >= 0 && point.getX() < component.getWidth()) &&
                 point.getY() >= 0 && point.getY() < component.getHeight());
    } */
}
