// File: src/StartScreenPanel.java

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Start screen UI panel with background, title, and start button.
 * Initializes the game upon user interaction.
 */
public class StartScreenPanel extends JPanel {

    private final GameStateManager gsm;
    private final JButton startButton;
    private Image backgroundImage;

    public StartScreenPanel(GameStateManager gsm) {
        this.gsm = gsm;
        setPreferredSize(new Dimension(800, 600));
        setLayout(null); // Absolute positioning

        loadBackground();
        startButton = createStartButton();
        add(startButton);
    }

    /**
     * Loads the neon-themed background image.
     */
    private void loadBackground() {
        try {
            backgroundImage = ImageIO.read(new File("assets/neon_background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates and styles the "START GAME" button.
     */
    private JButton createStartButton() {
        JButton button = new JButton("START GAME");
        button.setBounds(300, 430, 200, 50);
        button.setFont(new Font("Consolas", Font.BOLD, 20));
        button.setBackground(new Color(0, 0, 0, 180));
        button.setForeground(Color.cyan);
        button.setBorder(BorderFactory.createLineBorder(Color.cyan, 2));
        button.setFocusPainted(false);

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(0, 255, 180, 200));
                button.setForeground(Color.black);
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(0, 0, 0, 180));
                button.setForeground(Color.cyan);
            }
        });

        // Start the game on click
        button.addActionListener(e -> gsm.switchToGame());
        return button;
    }

    /**
     * Draws the background and title.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("Consolas", Font.BOLD, 50));

        // Shadow layer
        g2.setColor(new Color(0, 0, 0, 150));
        g2.drawString("NEON CYBER", 242, 162);
        g2.drawString("RUSH", 332, 222);

        // Neon text
        g2.setColor(Color.cyan);
        g2.drawString("NEON CYBER", 240, 160);
        g2.drawString("RUSH", 330, 220);

        // Footer credit
        g2.setFont(new Font("Consolas", Font.PLAIN, 14));
        g2.setColor(new Color(255, 255, 255, 80));
        g2.drawString("Made by Bushra Ahmed © 2025", 10, getHeight() - 10);
    }
}
