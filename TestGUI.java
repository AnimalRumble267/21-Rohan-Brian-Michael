import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

public class TestGUI 
{
    public static void main(String[] args) 
    {
        JFrame window = new JFrame();
        JPanel playerPanel = new JPanel();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setTitle("Game Window");
        window.setSize(new Dimension(1500, 1000));
        window.setBackground(Color.RED);
        window.setLayout(new BorderLayout());

        window.add(playerPanel, BorderLayout.CENTER);
        playerPanel.setSize(new Dimension(0, 0));
        playerPanel.setBackground(Color.BLUE);

        //JPanel panel = new JPanel();
        //panel.setSize(new Dimension(100, 100));
        //panel.setBackground(Color.BLUE);
        //window.add(panel);
        //panel.setLocation(0, 0);
        //panel.setVisible(true);
        window.setVisible(true);

        Player p1 = new Player();
        Player p2 = new Player();
        // PlayerGUI p = new PlayerGUI(p1, p2, window);
        //p.start();
    }    
}
