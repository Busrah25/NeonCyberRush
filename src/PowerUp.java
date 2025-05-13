// File: src/PowerUp.java

import java.awt.*;
import java.util.Random;

/**
 * Represents falling power-up items with special effects.
 */
public class PowerUp {
    public enum Type {
        EXPAND, SLOW, MULTI;

        /**
         * Returns a random power-up type.
         */
        public static Type random() {
            Type[] values = values();
            return values[new Random().nextInt(values.length)];
        }
    }

    private int x, y, size = 20, speed = 2;
    private final Type type;
    private boolean collected = false;

    public PowerUp(int x, int y, Type type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    /**
     * Updates the position of the falling power-up.
     */
    public void update() {
        y += speed;
    }

    /**
     * Draws the power-up item.
     */
    public void draw(Graphics2D g) {
        Color color = switch (type) {
            case EXPAND -> Color.green;
            case SLOW -> Color.cyan;
            case MULTI -> Color.magenta;
        };
        g.setColor(color);
        g.fillOval(x, y, size, size);

        g.setColor(Color.white);
        g.drawOval(x, y, size, size);
    }

    /**
     * Checks collision with the paddle.
     */
    public boolean collidesWith(Paddle paddle) {
        Rectangle powerBounds = new Rectangle(x, y, size, size);
        Rectangle paddleBounds = new Rectangle(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());
        return powerBounds.intersects(paddleBounds);
    }

    /**
     * Applies the effect of this power-up to the game entities.
     */
    public void applyEffect(Paddle paddle, Ball ball) {
        switch (type) {
            case EXPAND -> paddle.expand();
            case SLOW -> ball.slowDown();
            case MULTI -> {} // Reserved for future multiball implementation
        }
    }

    /**
     * Marks this power-up as collected.
     */
    public void collect() {
        collected = true;
    }

    /**
     * Returns true if this power-up has been collected.
     */
    public boolean isCollected() {
        return collected;
    }

    public Type getType() {
        return type;
    }
}
