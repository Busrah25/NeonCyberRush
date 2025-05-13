// File: src/EffectManager.java

import java.awt.*;
import java.util.LinkedList;

/**
 * Manages and draws a trailing effect behind the ball.
 */
public class EffectManager {
    private final LinkedList<Point> trail = new LinkedList<>();

    /**
     * Adds a new point to the trail.
     */
    public void updateTrail(int x, int y) {
        trail.addFirst(new Point(x, y));
        if (trail.size() > 12) trail.removeLast(); // Limit trail length
    }

    /**
     * Draws fading trail effect.
     */
    public void drawTrail(Graphics2D g) {
        int alpha = 200;
        for (Point p : trail) {
            g.setColor(new Color(0, 255, 180, alpha));
            g.fillOval(p.x, p.y, 16, 16);
            alpha -= 15;
            if (alpha <= 0) break;
        }
    }
}
