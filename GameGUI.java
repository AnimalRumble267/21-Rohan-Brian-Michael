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

    private static final String[] TRUMP_CARD_FILE_PATHS = {""}; // TODO Write file paths
    public static final Tile[] NUMBER_CARD_TILES = new Tile[2]; // TODO CHANGE SIZE LATER
    public static final Tile[] TRUMP_CARD_TILES = new Tile[TRUMP_CARD_FILE_PATHS.length];
    private static boolean tilesLoaded = false;

    public static void loadTiles()
    {
        Tile newTile;

        // Loading numbercards;
        for (int i = 0; i < 2; i++)
        {
            newTile = new Tile(UNIT_SIZE, UNIT_SIZE);
            newTile.loadImage("/images/numbercard" + (i + 1) + ".png");
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

    public static boolean pointInBounds(Point point, Component component)
    {
        return ((point.getX() >= 0 && point.getX() < component.getWidth()) &&
                 point.getY() >= 0 && point.getY() < component.getHeight());
    }
}
