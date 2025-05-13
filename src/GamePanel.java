// File: src/GamePanel.java


import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;



/**
 * The main panel where the game is rendered and logic executed.
 * Handles input, game loop, rendering, power-ups, collisions, score, and levels.
 */
public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private final int WIDTH = 800, HEIGHT = 600;
    private final GameStateManager gsm;
    private final Timer timer;

    private Paddle paddle;
    private Ball ball;
    private List<Brick> bricks;
    private List<PowerUp> powerUps;
    private List<BrickHitEffect> effectsList;
    private EffectManager effectManager;

    private Image backgroundImage;
    private int score = 0;
    private int lives = 3;
    private int highScore = 0;
    private int level = 1;
    private GameState gameState = GameState.PLAYING;

    public GamePanel(GameStateManager gsm) {
        this.gsm = gsm;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Theme.BACKGROUND);
        setFocusable(true);
        addKeyListener(this);

        loadHighScore();
        loadAssets();
        initializeGameObjects();

        timer = new Timer(10, this);
        timer.start();
    }

    private void loadAssets() {
        try {
            backgroundImage = Toolkit.getDefaultToolkit().getImage("assets/background.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeGameObjects() {
        paddle = new Paddle(WIDTH / 2 - 60, HEIGHT - 50);
        ball = new Ball(WIDTH / 2, HEIGHT / 2);
        effectManager = new EffectManager();
        bricks = new ArrayList<>();
        powerUps = new ArrayList<>();
        effectsList = new ArrayList<>();
        generateBricks();
    }

    private void generateBricks() {
        bricks.clear();
        int cols = 10, rows = 4 + level;
        int brickWidth = 60, brickHeight = 20;
        int hGap = 10, vGap = 10;
        int totalWidth = (brickWidth + hGap) * cols - hGap;
        int startX = (WIDTH - totalWidth) / 2;
        int startY = 50;

        Random rand = new Random();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = startX + col * (brickWidth + hGap);
                int y = startY + row * (brickHeight + vGap);
                Color color = Theme.NEON_COLORS[rand.nextInt(Theme.NEON_COLORS.length)];

                if (row % 3 == 0) {
                    bricks.add(new AdvancedBrick(x, y, color, 2));
                } else {
                    bricks.add(new Brick(x, y, color));
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (backgroundImage != null)
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        effectManager.drawTrail(g2);
        for (Brick b : bricks) b.draw(g2);
        for (PowerUp p : powerUps) p.draw(g2);
        for (BrickHitEffect e : effectsList) e.draw(g2);

        paddle.draw(g2);
        ball.draw(g2);

        // HUD
        g2.setFont(Theme.HUD_FONT);
        g2.setColor(Color.black);
        g2.drawString("Score: " + score, 22, 27);
        g2.setColor(Color.cyan);
        g2.drawString("Score: " + score, 20, 25);

        g2.setColor(Color.black);
        g2.drawString("Lives: " + lives, 682, 27);
        g2.setColor(Color.cyan);
        g2.drawString("Lives: " + lives, 680, 25);

        g2.setColor(Color.lightGray);
        g2.setFont(Theme.TEXT_FONT);
        g2.drawString("High Score: " + highScore, 20, HEIGHT - 10);

        // State messages
        if (gameState == GameState.PAUSED) {
            g2.setFont(Theme.TITLE_FONT);
            g2.setColor(Color.yellow);
            g2.drawString("PAUSED", 300, 280);
        } else if (gameState == GameState.LOST) {
            g2.setFont(Theme.TITLE_FONT);
            g2.setColor(Color.red);
            g2.drawString("GAME OVER", 250, 280);
            g2.setFont(Theme.TEXT_FONT);
            g2.setColor(Color.white);
            g2.drawString("Press SPACE to Restart", 280, 320);
        } else if (gameState == GameState.WON) {
            g2.setFont(Theme.TITLE_FONT);
            g2.setColor(Color.green);
            g2.drawString("YOU WIN!", 270, 280);
            g2.setFont(Theme.TEXT_FONT);
            g2.setColor(Color.white);
            g2.drawString("Press SPACE for Next Level", 240, 320);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameState != GameState.PLAYING) return;

        // ✅ Stop all logic once player wins
        if (bricks.stream().noneMatch(b -> b.visible)) {
            gameState = GameState.WON;
            level++;
            saveHighScore();
            repaint();  // Show WIN message right away
            return;     // ✅ Prevent paddle/ball update after win
        }

        paddle.update();
        ball.update(paddle, bricks, this, effectManager);

        for (Brick b : bricks) {
            if (!b.visible) continue;
            if (ball.getBounds().intersects(b.getBounds())) {
                if (b.handleHit()) {
                    addScore();
                    effectsList.add(new BrickHitEffect(b.x, b.y));
                    if (Math.random() < 0.25) {
                        powerUps.add(new PowerUp(b.x, b.y, PowerUp.Type.random()));
                    }
                }
                ball.reverseY();
                break;
            }
        }

        for (PowerUp p : powerUps) {
            p.update();
            if (p.collidesWith(paddle)) {
                p.applyEffect(paddle, ball);
                p.collect();
            }
        }

        for (BrickHitEffect efx : effectsList) efx.update();
        powerUps.removeIf(PowerUp::isCollected);
        effectsList.removeIf(BrickHitEffect::isDone);

        repaint();
    }

    public void loseLife() {
        lives--;
        if (lives <= 0) {
            gameState = GameState.LOST;
            saveHighScore();
        } else {
            ball.reset();
        }
    }

    public void addScore() {
        score += 100;
    }

    private void restartGame() {
        score = 0;
        lives = 3;
        gameState = GameState.PLAYING;
        powerUps.clear();
        effectsList.clear();
        initializeGameObjects();
    }

    private void saveHighScore() {
        if (score > highScore) {
            highScore = score;
            try (PrintWriter out = new PrintWriter("highscore.txt")) {
                out.println(score);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadHighScore() {
        try {
            File file = new File("highscore.txt");
            if (file.exists()) {
                Scanner in = new Scanner(file);
                highScore = in.nextInt();
                in.close();
            }
        } catch (Exception e) {
            highScore = 0;
        }
    }

    @Override public void keyPressed(KeyEvent e) {
        paddle.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_P) gameState = (gameState == GameState.PAUSED) ? GameState.PLAYING : GameState.PAUSED;
        if ((gameState == GameState.LOST || gameState == GameState.WON) && e.getKeyCode() == KeyEvent.VK_SPACE) restartGame();
    }

    @Override public void keyReleased(KeyEvent e) { paddle.keyReleased(e); }
    @Override public void keyTyped(KeyEvent e) {}
}
