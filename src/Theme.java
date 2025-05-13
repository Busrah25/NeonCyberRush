// File: src/Theme.java

import java.awt.*;

/**
 * Centralized class for game colors and fonts.
 * Makes styling consistent and easy to manage.
 */
public class Theme {
    public static final Color BACKGROUND = new Color(10, 10, 30);

    public static final Color PADDLE_GRADIENT_LEFT = new Color(0, 255, 180);
    public static final Color PADDLE_GRADIENT_RIGHT = new Color(0, 150, 255);

    public static final Color BALL_COLOR = new Color(255, 255, 255);

    public static final Font TITLE_FONT = new Font("Consolas", Font.BOLD, 48);
    public static final Font TEXT_FONT = new Font("Consolas", Font.PLAIN, 18);
    public static final Font HUD_FONT = new Font("Consolas", Font.BOLD, 20);

    public static final Color[] NEON_COLORS = {
        new Color(255, 0, 255),   // Neon Magenta
        new Color(0, 255, 255),   // Cyan
        new Color(128, 0, 255),   // Deep Purple
        new Color(0, 191, 255),   // Electric Blue
        new Color(0, 255, 180)    // Aqua Mint
    };
}
