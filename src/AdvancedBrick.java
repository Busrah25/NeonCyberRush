// File: src/AdvancedBrick.java

import java.awt.*;

/**
 * A stronger brick that takes multiple hits to destroy.
 * Adds extra difficulty and variation to the game.
 */
public class AdvancedBrick extends Brick {
    private int hitsLeft;

    public AdvancedBrick(int x, int y, Color color, int hits) {
        super(x, y, color);
        this.hitsLeft = hits;
    }

    /**
     * Render brick differently based on durability.
     */
    @Override
    public void draw(Graphics2D g) {
        if (!visible) return;

        int alpha = hitsLeft == 2 ? 120 : 80;
        g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
        g.fillRoundRect(x - 4, y - 4, width + 8, height + 8, 14, 14);

        GradientPaint gradient = new GradientPaint(x, y, color.brighter(), x + width, y + height, color.darker());
        g.setPaint(gradient);
        g.fillRoundRect(x, y, width, height, 12, 12);

        g.setColor(new Color(255, 255, 255, 80));
        g.setStroke(new BasicStroke(2f));
        g.drawRoundRect(x, y, width, height, 12, 12);

        // Draw cross if one hit left
        if (hitsLeft == 1) {
            g.setColor(new Color(255, 255, 255, 120));
            g.setStroke(new BasicStroke(1.5f));
            g.drawLine(x + 10, y + 5, x + width - 10, y + height - 5);
            g.drawLine(x + width - 10, y + 5, x + 10, y + height - 5);
        }
    }

    /**
     * Reduce durability on hit, destroy when depleted.
     * @return true if destroyed
     */
    @Override
    public boolean handleHit() {
        hitsLeft--;
        if (hitsLeft <= 0) {
            visible = false;
            return true;
        }
        return false;
    }
}
