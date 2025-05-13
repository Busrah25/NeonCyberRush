// File: src/GameStateManager.java

import javax.swing.*;

/**
 * Handles switching between different panels/screens in the game.
 * e.g., Start screen, gameplay, and menus.
 */
public class GameStateManager {
    private JFrame frame;
    private JPanel currentPanel;

    public GameStateManager(JFrame frame) {
        this.frame = frame;
        switchToStartScreen(); // Default state on game start
    }

    /**
     * Displays the start screen.
     */
    public void switchToStartScreen() {
        if (currentPanel != null) frame.remove(currentPanel);
        currentPanel = new StartScreenPanel(this);
        frame.add(currentPanel);
        frame.pack();
        frame.revalidate();
    }

    /**
     * Starts a new game session.
     */
    public void switchToGame() {
        if (currentPanel != null) frame.remove(currentPanel);
        currentPanel = new GamePanel(this);
        frame.add(currentPanel);
        frame.pack();
        frame.revalidate();
        currentPanel.requestFocusInWindow();
    }
}
