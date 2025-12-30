# Neon Cyber Rush

A futuristic 2D arcade style brick breaker game developed in Java using Swing. The project demonstrates object oriented design, event driven programming, and real time graphical rendering through a complete desktop game implementation.

## Overview
Neon Cyber Rush recreates classic brick breaker gameplay with modern visuals and a structured architecture. The project focuses on separating game logic, rendering, input handling, and state management into clear components. It was built to practice Java GUI programming and game development fundamentals in an academic setting.

## Key Features
- Interactive start screen with controlled game flow  
- Keyboard based paddle and ball mechanics  
- Collision detection between ball, bricks, paddle, and walls  
- Score tracking with win and loss conditions  
- Power up logic and advanced brick behavior  
- Neon themed visuals and animated effects  

## Technologies Used
- Java  
- Java Swing  
- Object Oriented Programming  
- Git and GitHub  

## Core Logic
The game uses a Swing Timer to implement a consistent game loop. User input is handled through KeyListener events, while rendering is managed using Graphics2D. A centralized game state manager controls transitions between start, gameplay, win, and loss states.

## Project Structure
NeonCyberRush/
├── src/
│ ├── Main.java
│ ├── GameFrame.java
│ ├── GamePanel.java
│ ├── Paddle.java
│ ├── Ball.java
│ ├── Brick.java
│ ├── AdvancedBrick.java
│ ├── PowerUp.java
│ ├── GameStateManager.java
│ └── GameState.java
├── assets/
│ └── visual assets
└── manifest.txt

## How to Run Locally
1. Ensure Java is installed  
2. Clone the repository  
3. Compile and run from the src directory  

javac src/*.java
java -cp src Main


## Future Improvements
- Multiple levels with increasing difficulty  
- Sound effects and background music  
- Additional power up variations  
