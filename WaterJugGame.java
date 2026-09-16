import javax.swing.*;
import java.awt.*;

public class WaterJugGame extends JFrame {

    private final int capacityA = 4;
    private final int capacityB = 3;
    private int jugA = 0;
    private int jugB = 0;
    private int moves = 0;
    private int target = 2;
    private boolean gameWon = false;
    private JProgressBar jugAProgress;
    private JProgressBar jugBProgress;

    private JLabel waterLevelLabel;
    private JLabel moveLabel;
    private JLabel targetLabel;
    private JLabel statusLabel;

    private JComboBox<String> levelBox;

    private JButton fillA;
    private JButton fillB;
    private JButton emptyA;
    private JButton emptyB;
    private JButton transferAB;
    private JButton transferBA;
    private JButton resetButton;

    public WaterJugGame() {

        setTitle("Water Jug Game");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        JPanel levelPanel = new JPanel();

        levelPanel.add(new JLabel("Select Level:"));

        levelBox = new JComboBox<>(
                new String[]{"Level 1 - Target 2L",
                             "Level 2 - Target 1L",
                             "Level 3 - Target 3L"}
        );

        levelPanel.add(levelBox);

        add(levelPanel, BorderLayout.NORTH);
        JPanel gamePanel = new JPanel(new GridLayout(6, 1, 5, 5));

        gamePanel.add(new JLabel("Jug A (Capacity: 4L)"));

        jugAProgress = new JProgressBar(0, capacityA);
        jugAProgress.setStringPainted(true);
        gamePanel.add(jugAProgress);
        gamePanel.add(new JLabel("Jug B (Capacity: 3L)"));
        jugBProgress = new JProgressBar(0, capacityB);
        jugBProgress.setStringPainted(true);
        gamePanel.add(jugBProgress);
        waterLevelLabel = new JLabel(
                "Water Level: A = 0L, B = 0L"
        );

        gamePanel.add(waterLevelLabel);

        targetLabel = new JLabel(
                "Target: 2L"
        );

        gamePanel.add(targetLabel);
        add(gamePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 2, 5, 5));

        fillA = new JButton("Fill A");
        fillB = new JButton("Fill B");

        emptyA = new JButton("Empty A");
        emptyB = new JButton("Empty B");

        transferAB = new JButton("A → B");
        transferBA = new JButton("B → A");

        resetButton = new JButton("Reset");

        buttonPanel.add(fillA);
        buttonPanel.add(fillB);

        buttonPanel.add(emptyA);
        buttonPanel.add(emptyB);

        buttonPanel.add(transferAB);
        buttonPanel.add(transferBA);

        buttonPanel.add(resetButton);


        add(buttonPanel, BorderLayout.SOUTH);

        JPanel statusPanel = new JPanel(new GridLayout(3, 1));

        moveLabel = new JLabel("Moves: 0");

        statusLabel = new JLabel(
                "Status: Game Started"
        );

        statusPanel.add(moveLabel);
        statusPanel.add(statusLabel);

        add(statusPanel, BorderLayout.WEST);

        fillA.addActionListener(e -> fillJugA());

        fillB.addActionListener(e -> fillJugB());

        emptyA.addActionListener(e -> emptyJugA());

        emptyB.addActionListener(e -> emptyJugB());

        transferAB.addActionListener(e -> transferAtoB());

        transferBA.addActionListener(e -> transferBtoA());

        resetButton.addActionListener(e -> resetGame());

        levelBox.addActionListener(e -> changeLevel());

        updateState();

        setVisible(true);
    }

    private void changeLevel() {

        int selectedLevel = levelBox.getSelectedIndex();

        if (selectedLevel == 0) {
            target = 2;
        }
        else if (selectedLevel == 1) {
            target = 1;
        }
        else {
            target = 3;
        }

        resetGame();
    }

    private void fillJugA() {

        if (gameWon) {
            return;
        }

        if (jugA == capacityA) {
            statusLabel.setText(
                    "Status: Jug A is already full!"
            );
            return;
        }

        jugA = capacityA;
        moves++;

        statusLabel.setText(
                "Status: Jug A Filled"
        );

        updateState();
        checkWin();
    }

    private void fillJugB() {

        if (gameWon) {
            return;
        }

        if (jugB == capacityB) {
            statusLabel.setText(
                    "Status: Jug B is already full!"
            );
            return;
        }

        jugB = capacityB;
        moves++;

        statusLabel.setText(
                "Status: Jug B Filled"
        );

        updateState();
        checkWin();
    }

    private void emptyJugA() {

        if (gameWon) {
            return;
        }

        if (jugA == 0) {
            statusLabel.setText(
                    "Status: Jug A is already empty!"
            );
            return;
        }

        jugA = 0;
        moves++;

        statusLabel.setText(
                "Status: Jug A Emptied"
        );

        updateState();
        checkWin();
    }

    private void emptyJugB() {

        if (gameWon) {
            return;
        }

        if (jugB == 0) {
            statusLabel.setText(
                    "Status: Jug B is already empty!"
            );
            return;
        }

        jugB = 0;
        moves++;

        statusLabel.setText(
                "Status: Jug B Emptied"
        );

        updateState();
        checkWin();
    }

    private void transferAtoB() {

        if (gameWon) {
            return;
        }

        if (jugA == 0) {
            statusLabel.setText(
                    "Status: Jug A is empty!"
            );
            return;
        }

        if (jugB == capacityB) {
            statusLabel.setText(
                    "Status: Jug B is full!"
            );
            return;
        }

        int transfer = Math.min(
                jugA,
                capacityB - jugB
        );

        jugA -= transfer;
        jugB += transfer;

        moves++;

        statusLabel.setText(
                "Status: Water transferred A → B"
        );

        updateState();
        checkWin();
    }

    private void transferBtoA() {

        if (gameWon) {
            return;
        }

        if (jugB == 0) {
            statusLabel.setText(
                    "Status: Jug B is empty!"
            );
            return;
        }

        if (jugA == capacityA) {
            statusLabel.setText(
                    "Status: Jug A is full!"
            );
            return;
        }

        int transfer = Math.min(
                jugB,
                capacityA - jugA
        );

        jugB -= transfer;
        jugA += transfer;

        moves++;

        statusLabel.setText(
                "Status: Water transferred B → A"
        );

        updateState();
        checkWin();
    }

    private void updateState() {

        jugAProgress.setValue(jugA);
        jugBProgress.setValue(jugB);

        jugAProgress.setString(jugA + " L");
        jugBProgress.setString(jugB + " L");

        waterLevelLabel.setText(
                "Water Level: A = " + jugA +
                "L, B = " + jugB + "L"
        );

        moveLabel.setText(
                "Moves: " + moves
        );

        targetLabel.setText(
                "Target: " + target + "L"
        );
    }

    private void checkWin() {

        if (jugA == target || jugB == target) {

            gameWon = true;

            statusLabel.setText(
                    "Status: YOU WIN!"
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Congratulations!\n"
                    + "You completed the game!\n"
                    + "Target: " + target + "L\n"
                    + "Total Moves: " + moves,
                    "Game Completed",
                    JOptionPane.INFORMATION_MESSAGE
            );

            disableGameButtons();
        }
    }

    private void disableGameButtons() {

        fillA.setEnabled(false);
        fillB.setEnabled(false);

        emptyA.setEnabled(false);
        emptyB.setEnabled(false);

        transferAB.setEnabled(false);
        transferBA.setEnabled(false);
    }


    private void resetGame() {

        jugA = 0;
        jugB = 0;
        moves = 0;

        gameWon = false;

        fillA.setEnabled(true);
        fillB.setEnabled(true);

        emptyA.setEnabled(true);
        emptyB.setEnabled(true);

        transferAB.setEnabled(true);
        transferBA.setEnabled(true);

        statusLabel.setText(
                "Status: Game Reset"
        );

        updateState();
    }
    public static void main(String[] args) {

        new WaterJugGame();
    }
}