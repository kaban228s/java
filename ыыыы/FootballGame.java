import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class FootballGame extends JPanel implements ActionListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int BALL_SIZE = 20;
    private static final int PLAYER_SIZE = 50;
    private static final int GOAL_WIDTH = 100;
    private static final int GOAL_HEIGHT = 200;
    private static final int WINNING_SCORE = 5;

    private int ballX, ballY, ballVelX, ballVelY;
    private int player1X, player1Y, player2X, player2Y;
    private boolean up1, down1, left1, right1, up2, down2, left2, right2;

    private boolean singlePlayer;
    private Random random;
    private int scorePlayer1, scorePlayer2;

    public FootballGame(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
        random = new Random();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.GREEN);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_W -> up1 = true;
                    case KeyEvent.VK_S -> down1 = true;
                    case KeyEvent.VK_A -> left1 = true;
                    case KeyEvent.VK_D -> right1 = true;
                    case KeyEvent.VK_UP -> up2 = true;
                    case KeyEvent.VK_DOWN -> down2 = true;
                    case KeyEvent.VK_LEFT -> left2 = true;
                    case KeyEvent.VK_RIGHT -> right2 = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_W -> up1 = false;
                    case KeyEvent.VK_S -> down1 = false;
                    case KeyEvent.VK_A -> left1 = false;
                    case KeyEvent.VK_D -> right1 = false;
                    case KeyEvent.VK_UP -> up2 = false;
                    case KeyEvent.VK_DOWN -> down2 = false;
                    case KeyEvent.VK_LEFT -> left2 = false;
                    case KeyEvent.VK_RIGHT -> right2 = false;
                }
            }
        });

        Timer timer = new Timer(30, this);
        timer.start();
        resetGame();
    }

    private void resetGame() {
        ballX = WIDTH / 2 - BALL_SIZE / 2;
        ballY = HEIGHT / 2 - BALL_SIZE / 2;
        ballVelX = random.nextBoolean() ? 5 : -5;
        ballVelY = random.nextBoolean() ? 5 : -5;

        player1X = 100;
        player1Y = HEIGHT / 2 - PLAYER_SIZE / 2;
        player2X = WIDTH - 150;
        player2Y = HEIGHT / 2 - PLAYER_SIZE / 2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(WIDTH / 2 - 1, 0, 2, HEIGHT);
        g.fillRect(0, HEIGHT / 2 - GOAL_HEIGHT / 2, 10, GOAL_HEIGHT);
        g.fillRect(WIDTH - 10, HEIGHT / 2 - GOAL_HEIGHT / 2, 10, GOAL_HEIGHT);

        g.setColor(Color.BLUE);
        g.fillOval(player1X, player1Y, PLAYER_SIZE, PLAYER_SIZE);

        g.setColor(Color.RED);
        g.fillOval(player2X, player2Y, PLAYER_SIZE, PLAYER_SIZE);

        g.setColor(Color.BLACK);
        g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);

        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Player 1: " + scorePlayer1, 50, 50);
        g.drawString("Player 2: " + scorePlayer2, WIDTH - 200, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        movePlayers();
        moveBall();
        checkCollisions();
        repaint();
    }

    private void movePlayers() {
        if (up1) player1Y = Math.max(player1Y - 5, 0);
        if (down1) player1Y = Math.min(player1Y + 5, HEIGHT - PLAYER_SIZE);
        if (left1) player1X = Math.max(player1X - 5, 0);
        if (right1) player1X = Math.min(player1X + 5, WIDTH / 2 - PLAYER_SIZE);

        if (singlePlayer) {
            moveAI();
        } else {
            if (up2) player2Y = Math.max(player2Y - 5, 0);
            if (down2) player2Y = Math.min(player2Y + 5, HEIGHT - PLAYER_SIZE);
            if (left2) player2X = Math.max(player2X - 5, WIDTH / 2);
            if (right2) player2X = Math.min(player2X + 5, WIDTH - PLAYER_SIZE);
        }
    }

    private void moveAI() {
        if (ballY < player2Y) player2Y = Math.max(player2Y - 3, 0);
        if (ballY > player2Y + PLAYER_SIZE) player2Y = Math.min(player2Y + 3, HEIGHT - PLAYER_SIZE);
    }

    private void moveBall() {
        ballX += ballVelX;
        ballY += ballVelY;

        if (ballY <= 0 || ballY >= HEIGHT - BALL_SIZE) {
            ballVelY = -ballVelY;
        }

        if (ballX <= 0 || ballX >= WIDTH - BALL_SIZE) {
            ballVelX = -ballVelX;
        }
    }

    private void checkCollisions() {
        if (ballX <= 10 && ballY >= HEIGHT / 2 - GOAL_HEIGHT / 2 && ballY <= HEIGHT / 2 + GOAL_HEIGHT / 2) {
            scorePlayer2++;
            checkWinningCondition();
            resetGame();
        }

        if (ballX >= WIDTH - 10 - BALL_SIZE && ballY >= HEIGHT / 2 - GOAL_HEIGHT / 2 && ballY <= HEIGHT / 2 + GOAL_HEIGHT / 2) {
            scorePlayer1++;
            checkWinningCondition();
            resetGame();
        }

        // Проверка столкновений для круглых объектов
        if (circleCollision(ballX, ballY, BALL_SIZE, player1X, player1Y, PLAYER_SIZE)) {
            ballVelX = -ballVelX;
        }

        if (circleCollision(ballX, ballY, BALL_SIZE, player2X, player2Y, PLAYER_SIZE)) {
            ballVelX = -ballVelX;
        }
    }

    private boolean circleCollision(int x1, int y1, int size1, int x2, int y2, int size2) {
        int radius1 = size1 / 2;
        int radius2 = size2 / 2;
        int centerX1 = x1 + radius1;
        int centerY1 = y1 + radius1;
        int centerX2 = x2 + radius2;
        int centerY2 = y2 + radius2;

        int deltaX = centerX1 - centerX2;
        int deltaY = centerY1 - centerY2;
        int distanceSquared = deltaX * deltaX + deltaY * deltaY;
        int radiusSum = radius1 + radius2;

        return distanceSquared < radiusSum * radiusSum;
    }

    private void checkWinningCondition() {
        if (scorePlayer1 == WINNING_SCORE) {
            JOptionPane.showMessageDialog(this, "Player 1 wins!");
            resetScores();
        } else if (scorePlayer2 == WINNING_SCORE) {
            JOptionPane.showMessageDialog(this, "Player 2 wins!");
            resetScores();
        }
    }

    private void resetScores() {
        scorePlayer1 = 0;
        scorePlayer2 = 0;
    }

    private static void createAndShowGUI(boolean singlePlayer) {
        JFrame frame = new JFrame("Football Game 1 on 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new FootballGame(singlePlayer));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        String[] options = {"Single Player", "Two Players"};
        int mode = JOptionPane.showOptionDialog(null, "Choose Game Mode", "Football Game 1 on 1",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        SwingUtilities.invokeLater(() -> createAndShowGUI(mode == 0));
    }
}
