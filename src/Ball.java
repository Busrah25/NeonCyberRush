// File: src/Ball.java

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Ball object that moves, bounces, and detects collisions.
 */
public class Ball {
    private int x, y, size = 16;
    private int xSpeed = 3, ySpeed = 3;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Updates ball position and handles screen boundaries.
     */
    public void update(Paddle paddle, java.util.List<Brick> bricks, GamePanel panel, EffectManager effects) {
        x += xSpeed;
        y += ySpeed;

        // Visual trail effect
        effects.updateTrail(x, y);

        // Wall collisions
        if (x <= 0 || x >= 800 - size) xSpeed *= -1;
        if (y <= 0) ySpeed *= -1;

        // Ball out of bounds (bottom)
        if (y >= 600) {
            panel.loseLife();
            return;
        }

        // Paddle collision
        Rectangle ballBounds = getBounds();
        Rectangle paddleBounds = new Rectangle(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());
        if (ballBounds.intersects(paddleBounds)) {
            ySpeed *= -1;
            y = paddle.getY() - size;
        }
    }

    /**
     * Draws the ball on the screen.
     */
    public void draw(Graphics2D g) {
        g.setColor(Theme.BALL_COLOR);
        g.fillOval(x, y, size, size);
    }

    /**
     * Resets ball to center and default speed.
     */
    public void reset() {
        x = 400;
        y = 300;
        xSpeed = 3;
        ySpeed = 3;
    }

    /**
     * Makes the ball slower (used by power-ups).
     */
    public void slowDown() {
        if (xSpeed > 1) xSpeed--;
        if (ySpeed > 1) ySpeed--;
    }

    /**
     * Reverses vertical direction.
     */
    public void reverseY() {
        ySpeed *= -1;
    }

    /**
     * Returns the bounding rectangle of the ball.
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }
}
