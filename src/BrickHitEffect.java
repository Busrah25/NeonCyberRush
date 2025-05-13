// File: src/BrickHitEffect.java

import java.awt.*;

/**
 * Visual effect displayed briefly when a brick is destroyed.
 */
public class BrickHitEffect {
    private final int x, y, size = 20;
    private int alpha = 255;
    private boolean done = false;

    public BrickHitEffect(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gradually fade out the visual effect.
     */
    public void update() {
        alpha -= 15;
        if (alpha <= 0) done = true;
    }

    /**
     * Draws the fading effect.
     */
    public void draw(Graphics2D g) {
        if (!done) {
            g.setColor(new Color(255, 255, 255, Math.max(alpha, 0)));
            g.fillOval(x, y, size, size);
        }
    }

    /**
     * @return true if the effect has fully faded out.
     */
    public boolean isDone() {
        return done;
    }
}
