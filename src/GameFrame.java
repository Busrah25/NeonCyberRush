// File: src/GameFrame.java

import javax.swing.*;

/**
 * GameFrame represents the main window of the game.
 * It initializes the GameStateManager and sets up the JFrame.
 */
public class GameFrame extends JFrame {
    public static GameStateManager gsm;

    public GameFrame() {
        setTitle("Neon Cyber Rush");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        gsm = new GameStateManager(this); // Handles switching between screens

        setLocationRelativeTo(null); // Center the window
        setVisible(true); // Display the window
    }
}
