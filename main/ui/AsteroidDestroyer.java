package ui;

import exception.*;
import network.ReadWebPage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AsteroidDestroyer extends JFrame {

    public static final int width = 900;
    public static final int height = 700;
    public static String level;
    private GameComponent comp;


    private AsteroidDestroyer() {
        setResizable(false);
        comp = new GameComponent();
        add(comp);
    }

    public static void main(String[] args) throws IOException {
        ReadWebPage.getWebInfo();
        startGame();
    }

    // EFFECTS: start the game
    private static void startGame() {
        getValidLevel();
        AsteroidDestroyer frame = new AsteroidDestroyer();
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.comp.start();
    }

    // EFFECTS: ask the user to choose the difficulty level then return it to main
    // if the user choose other than 1,2,3 it throws level out of range exception
    private static String chooseLevel() throws LevelOutOfRangeException {
        JLabel label = new JLabel("Choose the difficulty level(1-3): ");
        //https://stackoverflow.com/questions/26913923/how-do-you-change-the-size-and-font-of-a-joptionpane
        label.setFont(new Font("Arial", Font.BOLD, 25));
        String level = JOptionPane.showInputDialog(null,label,"Enter the level here");
        switch (level) {
            case "1":
                return "Easy";
            case "2":
                return "Normal";
            case "3":
                return "Hard";
            default:
                throw new LevelOutOfRangeException();
        }
    }

    // EFFECTS: get the valid level
    private static void getValidLevel() {
        while (true) {
            try {
                level = chooseLevel();
                break; // break the loop only when valid level is entered
            } catch (InvalidInputException e) {
                e.showMessage();
            }
        }
        System.out.println("You have selected: " + level);
    }

}
