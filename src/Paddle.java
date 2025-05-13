// File: src/Paddle.java

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Paddle controlled by the player using arrow keys.
 * Handles movement and drawing.
 */
public class Paddle {
    private int x, y;
    private int width = 120, height = 12;
    private int speed = 10;
    private boolean leftPressed = false, rightPressed = false;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Updates the paddle's position based on key presses.
     */
    public void update() {
        if (leftPressed && x > 0) x -= speed;
        if (rightPressed && x < 800 - width) x += speed;

        // Gradually shrink back to normal width
        if (width > 120) width--;
    }

    /**
     * Draws the paddle with a glowing gradient effect.
     */
    public void draw(Graphics2D g) {
        long t = System.currentTimeMillis();
        int pulse = 100 + (int) (Math.sin(t * 0.008) * 60);

        g.setColor(new Color(0, 255, 255, pulse));
        g.fillRoundRect(x - 4, y - 4, width + 8, height + 8, 20, 20);

        GradientPaint gradient = new GradientPaint(x, y, Theme.PADDLE_GRADIENT_LEFT, x + width, y + height, Theme.PADDLE_GRADIENT_RIGHT);
        g.setPaint(gradient);
        g.fillRoundRect(x, y, width, height, 15, 15);

        g.setColor(new Color(255, 255, 255, 80));
        g.setStroke(new BasicStroke(1.5f));
        g.drawRoundRect(x, y, width, height, 15, 15);
    }

    /**
     * Triggered when a key is pressed.
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = true;
    }

    /**
     * Triggered when a key is released.
     */
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = false;
    }

    /**
     * Expands the paddle width (used by power-ups).
     */
    public void expand() {
        width += 60;
    }

    // Getters for position and size
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
