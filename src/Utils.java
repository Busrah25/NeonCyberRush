// File: src/Utils.java

import java.awt.*;

/**
 * Utility class for reusable graphic functions.
 */
public class Utils {
    /**
     * Draws a glowing outline around any shape.
     * Useful for neon-style visuals.
     */
    public static void drawGlowOutline(Graphics2D g, Shape shape, Color glowColor) {
        for (int i = 4; i > 0; i--) {
            g.setColor(new Color(glowColor.getRed(), glowColor.getGreen(), glowColor.getBlue(), 40 * i));
            g.setStroke(new BasicStroke(2 * i));
            g.draw(shape);
        }
    }
}
