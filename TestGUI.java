import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

public class TestGUI 
{
    private static JFrame createBasicFrame(int width, int height)
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(new Dimension(width, height));
        frame.setVisible(true);
        return frame;
    }

    private static void boxLayoutTest()
    {
        JFrame window = new JFrame();
        JPanel windowPane = (JPanel) window.getContentPane();

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.BLUE);
        bluePanel.setPreferredSize(new Dimension(100, 100));
        bluePanel.setSize(100,100);

        window.setVisible(true);

        /* These lines are not needed */
        // bluePanel.setFocusable(true);

        /* These lines are not needed */
        //window.createBufferStrategy(2);
        //BufferStrategy buffer = window.getBufferStrategy();

        /* Make sure to add preferred size when using pack() */
        JPanel yellowPanel = new JPanel();
        yellowPanel.setBackground(Color.YELLOW);
        yellowPanel.setPreferredSize(new Dimension(100, 100));
        yellowPanel.setSize(100,100);

        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);
        redPanel.setPreferredSize(new Dimension(100, 100));
        redPanel.setSize(100,100);

        JPanel greenPanel = new JPanel();
        greenPanel.setBackground(Color.GREEN);
        greenPanel.setPreferredSize(new Dimension(100, 100));
        greenPanel.setSize(100, 100);

        JPanel redPanel1 = new JPanel();
        redPanel1.setBackground(Color.RED);
        redPanel1.setPreferredSize(new Dimension(100, 100));
        redPanel1.setSize(100,100);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(true);
        window.setTitle("Game Window");
        window.setSize(new Dimension(1200,500));
        windowPane.setBackground(Color.PINK);
        window.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        BoxLayout boxLayout = new BoxLayout(windowPane, BoxLayout.X_AXIS);

        /* THIS LINE IS NEEDED TO CREATE ALL FOUR SQUARES */
        windowPane.setLayout(boxLayout);

        /* These lines are not needed */
        /* bluePanel.setAlignmentX(Component.TOP_ALIGNMENT);
        redPanel.setAlignmentX(Component.TOP_ALIGNMENT);
        yellowPanel.setAlignmentX(Component.TOP_ALIGNMENT);
        greenPanel.setAlignmentX(Component.TOP_ALIGNMENT); */

        //bluePanel.setAlignmentY(Component.TOP_ALIGNMENT);
        //bluePanel.setLayout(new BoxLayout(bluePanel, BoxLayout.LINE_AXIS));

        //redPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        //redPanel.setLayout(new BoxLayout(redPanel, BoxLayout.LINE_AXIS));

        //yellowPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        //greenPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        /* Both methods work: adding to content pane and JFrame works */
        windowPane.add(bluePanel);
        windowPane.add(redPanel);
        window.add(yellowPanel);
        window.add(greenPanel);
        window.add(redPanel1);

        window.pack();

        //JPanel panel = new JPanel();
        //panel.setSize(new Dimension(100, 100));
        //panel.setBackground(Color.BLUE);
        //window.add(panel);
        //panel.setLocation(0, 0);
        //panel.setVisible(true);

        /* Graphics2D g2 = (Graphics2D) buffer.getDrawGraphics();
        g2.setFont(new Font("Arial", Font.PLAIN, 50));
        g2.setColor(Color.BLACK);
        g2.drawString("Hello", 0, 50);
        g2.dispose();
        buffer.show(); */

        // Player p1 = new Player();
        // Player p2 = new Player();
        // PlayerGUI p = new PlayerGUI(p1, p2, window);
        //p.start();
    }

    private static void frameTest()
    {
        JFrame frame1 = createBasicFrame(100, 100);
        JFrame frame2 = createBasicFrame(100, 100);

        frame1.getContentPane().setBackground(Color.BLUE);
        frame2.getContentPane().setBackground(Color.RED);

        frame1.setLocationRelativeTo(null);
        frame2.setLocation(frame1.getX() + frame1.getWidth(), frame1.getY());
    }

    private static void tileTest()
    {
        Tile tile1 = new Tile(100, 100);
        Tile tile2 = new Tile(100, 100);
        Tile tile3 = new Tile(100, 100);

        tile1.loadImage("/images/sample.png");
        tile2.loadImage("/images/sample.png");
        tile3.loadImage("/images/sample.png");

        GameGUI.loadTiles();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        Player player = new Player("Brian");
        player.giveNumberCard(new NumberCard(1, false));
        player.giveNumberCard(new NumberCard(2, false));
        PlayerPanel panel = new PlayerPanel(player);
        panel.setPreferredSize(new Dimension(200,100));
        panel.setVisible(true);
        frame.add(panel);
        frame.pack();
        panel.repaint();

        frame.setVisible(true);
    }

    public static void playerPanelTest()
    {
        GameGUI.loadTiles();
        JFrame gameWindow = new JFrame();
        gameWindow.setSize(new Dimension(1000, 1000));
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setTitle("Brian Lee");
        gameWindow.setVisible(true);
        Player p1 = new Player("Aidan");
        Player p2 = new Player("Roy");
        PlayerGUI pGUI = new PlayerGUI(p1, p2, gameWindow);
        pGUI.start();
        p1.giveNumberCard(new NumberCard(1, true));
        p1.giveNumberCard(new NumberCard(2, true));
        pGUI.drawPlayerHand(1);
    }

    public static void main(String[] args) 
    {
        playerPanelTest();
    }    
}
