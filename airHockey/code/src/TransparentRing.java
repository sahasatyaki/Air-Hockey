import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

public class TransparentRing extends JComponent {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set the color to non-transparent border color
        g.setColor(Color.GREEN);

        // Draw the outer circle
        g.drawOval(250, 250, 200, 200);

        // Set the color to transparent
        g.setColor(new Color(0, 0, 0, 0));

        // Draw the inner circle
        g.drawOval(250, 250, 100, 100);
    }

    @Override
    public Dimension getPreferredSize() {

        return new Dimension(300, 300);

    }
}
