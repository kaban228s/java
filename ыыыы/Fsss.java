import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Fsss extends JPanel {
    private int boxX = 50;
    private int boxY = 50;
    private final int BOX_SIZE = 20;
    private final int MOVE_SPEED = 5;

    public Fsss() {
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.WHITE);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        boxY = Math.max(boxY - MOVE_SPEED, 0);
                        break;
                    case KeyEvent.VK_DOWN:
                        boxY = Math.min(boxY + MOVE_SPEED, getHeight() - BOX_SIZE);
                        break;
                    case KeyEvent.VK_LEFT:
                        boxX = Math.max(boxX - MOVE_SPEED, 0);
                        break;
                    case KeyEvent.VK_RIGHT:
                        boxX = Math.min(boxX + MOVE_SPEED, getWidth() - BOX_SIZE);
                        break;
                }
                repaint();
            }
        });
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(boxX, boxY, BOX_SIZE, BOX_SIZE);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Simple Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Fsss());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Fsss::createAndShowGUI);
    }
}
