import javax.swing.*;
import java.awt.*;

public class WaterJugGame extends JFrame {

    private final int capacityA = 4;
    private final int capacityB = 3;
    private int jugA = 0;
    private int jugB = 0;
    private int moves = 0;
    private JProgressBar barA;
    private JProgressBar barB;
    private JLabel waterLabel;
    private JLabel moveLabel;
    private JLabel statusLabel;
    public WaterJugGame() {

        setTitle("Water Jug Game");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel(
                "WATER JUG GAME",
                JLabel.CENTER
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 26)
        );

        add(title, BorderLayout.NORTH);

        JPanel jugPanel = new JPanel(
                new GridLayout(1, 2, 40, 10)
        );

        barA = new JProgressBar(0, capacityA);
        barA.setOrientation(JProgressBar.VERTICAL);
        barA.setStringPainted(true);
        barA.setBorder(
                BorderFactory.createTitledBorder("Jug A (4L)")
        );

        barB = new JProgressBar(0, capacityB);
        barB.setOrientation(JProgressBar.VERTICAL);
        barB.setStringPainted(true);
        barB.setBorder(
                BorderFactory.createTitledBorder("Jug B (3L)")
        );

        jugPanel.add(barA);
        jugPanel.add(barB);

        add(jugPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(
                new GridLayout(4, 2, 10, 10)
        );

        JButton fillA = new JButton("Fill A");
        JButton fillB = new JButton("Fill B");

        JButton emptyA = new JButton("Empty A");
        JButton emptyB = new JButton("Empty B");

        JButton transferAB = new JButton("A → B");
        JButton transferBA = new JButton("B → A");

        JButton reset = new JButton("Reset");

        buttonPanel.add(fillA);
        buttonPanel.add(fillB);

        buttonPanel.add(emptyA);
        buttonPanel.add(emptyB);

        buttonPanel.add(transferAB);
        buttonPanel.add(transferBA);

        buttonPanel.add(reset);

        add(buttonPanel, BorderLayout.SOUTH);

        JPanel statusPanel = new JPanel(
                new GridLayout(3, 1)
        );

        waterLabel = new JLabel(
                "A: 0L B: 0L",
                JLabel.CENTER
        );

        moveLabel = new JLabel(
                "Moves: 0",
                JLabel.CENTER
        );

        statusLabel = new JLabel(
                "Game Started",
                JLabel.CENTER
        );

        statusPanel.add(waterLabel);
        statusPanel.add(moveLabel);
        statusPanel.add(statusLabel);

        add(statusPanel, BorderLayout.EAST);
        fillA.addActionListener(e -> {

            jugA = capacityA;
            moves++;

            statusLabel.setText("Jug A Filled");

            updateDisplay();
        });


        fillB.addActionListener(e -> {

            jugB = capacityB;
            moves++;

            statusLabel.setText("Jug B Filled");

            updateDisplay();
        });
 
        emptyA.addActionListener(e -> {

            jugA = 0;
            moves++;

            statusLabel.setText("Jug A Emptied");

            updateDisplay();
        });


        emptyB.addActionListener(e -> {

            jugB = 0;
            moves++;

            statusLabel.setText("Jug B Emptied");

            updateDisplay();
        });

        transferAB.addActionListener(e -> {

            int transfer = Math.min(
                    jugA,
                    capacityB - jugB
            );

            jugA = jugA - transfer;
            jugB = jugB + transfer;

            moves++;

            statusLabel.setText(
                    "Water transferred A → B"
            );

            updateDisplay();
        });

        transferBA.addActionListener(e -> {

            int transfer = Math.min(
                    jugB,
                    capacityA - jugA
            );

            jugB = jugB - transfer;
            jugA = jugA + transfer;

            moves++;

            statusLabel.setText(
                    "Water transferred B → A"
            );

            updateDisplay();
        });
        reset.addActionListener(e -> {

            jugA = 0;
            jugB = 0;
            moves = 0;

            statusLabel.setText("Game Reset");

            updateDisplay();
        });


        updateDisplay();

        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void updateDisplay() {

        // Update Jug A
        barA.setValue(jugA);
        barA.setString(jugA + "L");


        // Update Jug B
        barB.setValue(jugB);
        barB.setString(jugB + "L");


        // Update water level
        waterLabel.setText(
                "A: " + jugA + "L B: " + jugB + "L"
        );


        // Update moves
        moveLabel.setText(
                "Moves: " + moves
        );
    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new WaterJugGame();
        });
    }
}
