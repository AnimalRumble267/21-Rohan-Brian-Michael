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
    private final int PANEL_WIDTH = GameGUI.UNIT_SIZE * 10;
    private final int PANEL_HEIGHT = GameGUI.UNIT_SIZE * 10;

    private static final String[] TRUMP_CARD_FILE_PATHS =  {"/images/trumpcards/trumpdraw2", 
                                                            "/images/trumpcards/trumpdraw3",
                                                            "/images/trumpcards/trumpdraw4",
                                                            "/images/trumpcards/trumpdraw5",
                                                            "/images/trumpcards/trumpdraw6",
                                                            "/images/trumpcards/trumpdraw7",
                                                            "/images/trumpcards/trumpgofor17",
                                                            "/images/trumpcards/trumpgofor24",
                                                            "/images/trumpcards/trumpgofor27",
                                                            "/images/trumpcards/trumpshield",
                                                            "/images/trumpcards/trumpupone",
                                                            "/images/trumpcards/trumpuptwo"};
    public static final Tile[] NUMBER_CARD_TILES = new Tile[11];
    public static final Tile[] TRUMP_CARD_TILES = new Tile[TRUMP_CARD_FILE_PATHS.length];
    private static boolean tilesLoaded = false;
    private boolean guiStarted = false;

    private JFrame gameWindow;
    private GamePanel gamePanel;
    private Dealer dealer;

    public GameGUI(Dealer d)
    {
        gameWindow = new JFrame();
        gamePanel = new GamePanel();
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
        gamePanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
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

    public static void loadTiles()
    {
        if (tilesLoaded)
        {
            return;
        }

        Tile newTile;

        // Loading numbercards;
        for (int i = 0; i < 2; i++)
        {
            newTile = new Tile(UNIT_SIZE, UNIT_SIZE);
            newTile.loadImage("/images/numbercards/numbercard" + (i + 1) + ".png");
            NUMBER_CARD_TILES[i] = newTile;
        }

        // Loading numbercards
        for (int i = 0; i < TRUMP_CARD_FILE_PATHS.length; i++)
        {
            newTile = new Tile(UNIT_SIZE, UNIT_SIZE);
            newTile.loadImage(TRUMP_CARD_FILE_PATHS[i]);
            TRUMP_CARD_TILES[i] = newTile;
        }
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

    public static boolean tilesLoaded()
    {
        return tilesLoaded;
    }

    public static Tile[] getNumberCardTiles()
    {
        return NUMBER_CARD_TILES;
    }

    public static Tile[] getTrumpCardTiles()
    {
        return TRUMP_CARD_TILES;
    }

    /* public static boolean pointInBounds(Point point, Component component)
    {
        return ((point.getX() >= 0 && point.getX() < component.getWidth()) &&
                 point.getY() >= 0 && point.getY() < component.getHeight());
    } */
}
