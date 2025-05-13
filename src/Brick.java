// File: src/Brick.java

import java.awt.*;

/**
 * Basic destructible brick in the game.
 * Can be rendered and marked invisible when hit.
 */
public class Brick {
    protected int x, y;
    protected int width = 60, height = 20;
    public boolean visible = true;
    protected Color color;

    public Brick(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    /**
     * Renders the brick with neon-style visuals.
     */
    public void draw(Graphics2D g) {
        if (!visible) return;

        // Outer glow
        g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 100));
        g.fillRoundRect(x - 3, y - 3, width + 6, height + 6, 12, 12);

        // Gradient fill
        GradientPaint gradient = new GradientPaint(x, y, color.brighter(), x + width, y + height, color.darker());
        g.setPaint(gradient);
        g.fillRoundRect(x, y, width, height, 10, 10);

        // Border
        g.setColor(new Color(255, 255, 255, 70));
        g.setStroke(new BasicStroke(2f));
        g.drawRoundRect(x, y, width, height, 10, 10);
    }

    /**
     * Handles brick being hit: becomes invisible.
     * @return true if brick was destroyed
     */
    public boolean handleHit() {
        visible = false;
        return true;
    }

    /**
     * Returns the rectangle for collision detection.
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
