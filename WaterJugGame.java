import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class WaterJugGame extends JFrame {


    private final int capacityA = 4;
    private final int capacityB = 3;

    private int jugA = 0;
    private int jugB = 0;

    private int moves = 0;
    private int currentLevel = 1;
    private int target = 2;
    private JugPanel jugPanelA;
    private JugPanel jugPanelB;

    private JLabel levelLabel;
    private JLabel targetLabel;
    private JLabel moveLabel;
    private JLabel statusLabel;

    private JComboBox<String> levelBox;

    private JButton fillA;
    private JButton fillB;
    private JButton emptyA;
    private JButton emptyB;
    private JButton transferAB;
    private JButton transferBA;
    private JButton resetButton;
    private JButton nextLevelButton;
    public WaterJugGame() {

        setTitle("Water Jug Puzzle");
        setSize(850, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createGUI();

        updateState();

        setVisible(true);
    }
    private void createGUI() {

        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(236, 248, 255));
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(new Color(35, 120, 190));
        header.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        JLabel title = new JLabel("💧 WATER JUG PUZZLE");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel(
                "Challenge Your Brain • Measure the Target Water"
        );
        subtitle.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitle.setForeground(new Color(225, 245, 255));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(title);
        header.add(Box.createVerticalStrut(7));
        header.add(subtitle);

        mainPanel.add(header, BorderLayout.NORTH);
        JPanel infoPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        infoPanel.setBackground(new Color(236, 248, 255));
        infoPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 25, 10, 25)
        );

        levelLabel = createInfoLabel("LEVEL 1");
        targetLabel = createInfoLabel("TARGET: 2 L");
        moveLabel = createInfoLabel("MOVES: 0");

        infoPanel.add(levelLabel);
        infoPanel.add(targetLabel);
        infoPanel.add(moveLabel);

        mainPanel.add(infoPanel, BorderLayout.CENTER);

        JPanel jugArea = new JPanel(new GridLayout(1, 2, 50, 0));
        jugArea.setBackground(new Color(236, 248, 255));
        jugArea.setBorder(
                BorderFactory.createEmptyBorder(10, 80, 10, 80)
        );

        jugPanelA = new JugPanel(
                "JUG A",
                capacityA,
                new Color(52, 152, 219)
        );

        jugPanelB = new JugPanel(
                "JUG B",
                capacityB,
                new Color(46, 204, 113)
        );

        jugArea.add(jugPanelA);
        jugArea.add(jugPanelB);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(236, 248, 255));

        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        buttonPanel.setBackground(new Color(236, 248, 255));
        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 25, 10, 25)
        );

        fillA = createButton("FILL A", new Color(52, 152, 219));
        fillB = createButton("FILL B", new Color(46, 204, 113));

        emptyA = createButton("EMPTY A", new Color(231, 76, 60));
        emptyB = createButton("EMPTY B", new Color(230, 126, 34));

        transferAB = createButton(
                "A  →  B",
                new Color(155, 89, 182)
        );

        transferBA = createButton(
                "B  →  A",
                new Color(241, 196, 15)
        );

        resetButton = createButton(
                "RESET",
                new Color(52, 73, 94)
        );

        nextLevelButton = createButton(
                "NEXT LEVEL",
                new Color(26, 188, 156)
        );

        buttonPanel.add(fillA);
        buttonPanel.add(fillB);
        buttonPanel.add(emptyA);
        buttonPanel.add(emptyB);
        buttonPanel.add(transferAB);
        buttonPanel.add(transferBA);
        buttonPanel.add(resetButton);
        buttonPanel.add(nextLevelButton);

        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBackground(Color.WHITE);
        statusPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 25, 10, 25)
        );

        statusLabel = new JLabel(
                "  STATUS: Game Started",
                SwingConstants.CENTER
        );

        statusLabel.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        statusLabel.setForeground(
                new Color(52, 73, 94)
        );

        statusPanel.add(
                statusLabel,
                BorderLayout.CENTER
        );
        JPanel levelPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER)
        );

        levelPanel.setBackground(Color.WHITE);

        JLabel selectLabel = new JLabel(
                "Select Level:"
        );

        selectLabel.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        String[] levels = {
            "Level 1 - Target 2L",
            "Level 2 - Target 1L",
            "Level 3 - Target 3L"
        };

        levelBox = new JComboBox<>(levels);

        levelBox.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        levelPanel.add(selectLabel);
        levelPanel.add(levelBox);

        statusPanel.add(
                levelPanel,
                BorderLayout.SOUTH
        );

        bottomPanel.add(
                statusPanel,
                BorderLayout.SOUTH
        );

        JPanel centerArea = new JPanel(
                new BorderLayout()
        );

        centerArea.setBackground(
                new Color(236, 248, 255)
        );

        centerArea.add(
                jugArea,
                BorderLayout.CENTER
        );

        centerArea.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                centerArea,
                BorderLayout.SOUTH
        );

        fillA.addActionListener(e -> fillJugA());

        fillB.addActionListener(e -> fillJugB());

        emptyA.addActionListener(e -> emptyJugA());

        emptyB.addActionListener(e -> emptyJugB());

        transferAB.addActionListener(
                e -> transferAtoB()
        );

        transferBA.addActionListener(
                e -> transferBtoA()
        );

        resetButton.addActionListener(
                e -> resetGame()
        );

        nextLevelButton.addActionListener(
                e -> nextLevel()
        );

        levelBox.addActionListener(
                e -> changeLevel()
        );

        nextLevelButton.setEnabled(false);

        add(mainPanel);
    }

    private JLabel createInfoLabel(String text) {

        JLabel label = new JLabel(
                text,
                SwingConstants.CENTER
        );

        label.setOpaque(true);
        label.setBackground(Color.WHITE);

        label.setForeground(
                new Color(44, 62, 80)
        );

        label.setFont(
                new Font("Arial", Font.BOLD, 17)
        );

        label.setBorder(
                BorderFactory.createLineBorder(
                        new Color(190, 220, 235),
                        2
                )
        );

        return label;
    }

    private JButton createButton(
            String text,
            Color color) {

        JButton button = new JButton(text);

        button.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        button.setForeground(Color.WHITE);
        button.setBackground(color);

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        12, 10, 12, 10
                )
        );

        return button;
    }

    private void fillJugA() {

        if (jugA == capacityA) {

            statusLabel.setText(
                    "  STATUS: Jug A is already full!"
            );

            return;
        }

        jugA = capacityA;
        moves++;

        statusLabel.setText(
                "  STATUS: Jug A Filled"
        );

        updateState();

        checkWin();
    }

    private void fillJugB() {

        if (jugB == capacityB) {

            statusLabel.setText(
                    "  STATUS: Jug B is already full!"
            );

            return;
        }

        jugB = capacityB;
        moves++;

        statusLabel.setText(
                "  STATUS: Jug B Filled"
        );

        updateState();

        checkWin();
    }

    private void emptyJugA() {

        if (jugA == 0) {

            statusLabel.setText(
                    "  STATUS: Jug A is already empty!"
            );

            return;
        }

        jugA = 0;
        moves++;

        statusLabel.setText(
                "  STATUS: Jug A Emptied"
        );

        updateState();
    }

    private void emptyJugB() {

        if (jugB == 0) {

            statusLabel.setText(
                    "  STATUS: Jug B is already empty!"
            );

            return;
        }

        jugB = 0;
        moves++;

        statusLabel.setText(
                "  STATUS: Jug B Emptied"
        );

        updateState();
    }

    private void transferAtoB() {

        if (jugA == 0) {

            statusLabel.setText(
                    "  STATUS: Jug A is empty!"
            );

            return;
        }

        if (jugB == capacityB) {

            statusLabel.setText(
                    "  STATUS: Jug B is full!"
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
                "  STATUS: Water transferred A → B"
        );

        updateState();

        checkWin();
    }

    private void transferBtoA() {

        if (jugB == 0) {

            statusLabel.setText(
                    "  STATUS: Jug B is empty!"
            );

            return;
        }

        if (jugA == capacityA) {

            statusLabel.setText(
                    "  STATUS: Jug A is full!"
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
                "  STATUS: Water transferred B → A"
        );

        updateState();

        checkWin();
    }

    private void changeLevel() {

        int selectedLevel =
                levelBox.getSelectedIndex();

        currentLevel = selectedLevel + 1;

        if (currentLevel == 1) {

            target = 2;

        } else if (currentLevel == 2) {

            target = 1;

        } else {

            target = 3;
        }

        resetGame();
    }

    private void checkWin() {

        if (jugA == target ||
                jugB == target) {

            statusLabel.setText(
                    "  STATUS: 🎉 YOU WIN!"
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Congratulations!\n\n"
                    + "You completed Level "
                    + currentLevel
                    + " in "
                    + moves
                    + " moves!",
                    "🎉 Level Completed",
                    JOptionPane.INFORMATION_MESSAGE
            );

            disableButtons();

            if (currentLevel < 3) {

                nextLevelButton.setEnabled(true);
            }
        }
    }

    private void disableButtons() {

        fillA.setEnabled(false);
        fillB.setEnabled(false);

        emptyA.setEnabled(false);
        emptyB.setEnabled(false);

        transferAB.setEnabled(false);
        transferBA.setEnabled(false);
    }

    private void enableButtons() {

        fillA.setEnabled(true);
        fillB.setEnabled(true);

        emptyA.setEnabled(true);
        emptyB.setEnabled(true);

        transferAB.setEnabled(true);
        transferBA.setEnabled(true);
    }

    private void nextLevel() {

        if (currentLevel < 3) {

            currentLevel++;

            if (currentLevel == 2) {

                target = 1;

            } else if (currentLevel == 3) {

                target = 3;
            }

            levelBox.setSelectedIndex(
                    currentLevel - 1
            );

            resetGame();
        }
    }

    private void resetGame() {

        jugA = 0;
        jugB = 0;
        moves = 0;

        nextLevelButton.setEnabled(false);

        enableButtons();

        statusLabel.setText(
                "  STATUS: Game Reset"
        );

        updateState();
    }



    private void updateState() {

        jugPanelA.setWaterLevel(jugA);
        jugPanelB.setWaterLevel(jugB);

        levelLabel.setText(
                "LEVEL " + currentLevel
        );

        targetLabel.setText(
                "TARGET: " + target + " L"
        );

        moveLabel.setText(
                "MOVES: " + moves
        );
    }


    // =====================================
    // MAIN METHOD
    // =====================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new WaterJugGame()
        );
    }


    class JugPanel extends JPanel {

        private String jugName;
        private int capacity;
        private int waterLevel;
        private Color jugColor;

        public JugPanel(
                String jugName,
                int capacity,
                Color jugColor) {

            this.jugName = jugName;
            this.capacity = capacity;
            this.jugColor = jugColor;
            this.waterLevel = 0;

            setPreferredSize(
                    new Dimension(280, 300)
            );

            setBackground(
                    new Color(236, 248, 255)
            );
        }


        public void setWaterLevel(int level) {

            waterLevel = level;

            repaint();
        }


        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(
                    new Color(44, 62, 80)
            );

            g2.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            22
                    )
            );

            FontMetrics fm =
                    g2.getFontMetrics();

            int titleX =
                    (getWidth()
                            - fm.stringWidth(jugName))
                            / 2;

            g2.drawString(
                    jugName,
                    titleX,
                    30
            );


            int jugWidth = 140;
            int jugHeight = 200;

            int x =
                    (getWidth() - jugWidth) / 2;

            int y = 50;

            g2.setColor(Color.WHITE);

            g2.fillRoundRect(
                    x,
                    y,
                    jugWidth,
                    jugHeight,
                    30,
                    30
            );

            g2.setColor(jugColor);

            g2.setStroke(
                    new BasicStroke(4)
            );

            g2.drawRoundRect(
                    x,
                    y,
                    jugWidth,
                    jugHeight,
                    30,
                    30
            );

            double percentage =
                    (double) waterLevel / capacity;

            int waterHeight =
                    (int) (jugHeight * percentage);

            int waterY =
                    y + jugHeight - waterHeight;

            if (waterHeight > 0) {

                g2.setColor(
                        new Color(
                                52,
                                152,
                                219,
                                190
                        )
                );

                g2.fillRoundRect(
                        x + 4,
                        waterY,
                        jugWidth - 8,
                        waterHeight,
                        25,
                        25
                );
            }

            String waterText =
                    waterLevel
                    + " L / "
                    + capacity
                    + " L";

            g2.setColor(
                    new Color(44, 62, 80)
            );

            g2.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            20
                    )
            );

            FontMetrics waterFM =
                    g2.getFontMetrics();

            int textX =
                    (getWidth()
                            - waterFM.stringWidth(
                                    waterText))
                            / 2;

            g2.drawString(
                    waterText,
                    textX,
                    y + jugHeight + 40
            );

            g2.setFont(
                    new Font(
                            "Arial",
                            Font.PLAIN,
                            14
                    )
            );

            String capacityText =
                    "Capacity: "
                    + capacity
                    + " Liters";

            FontMetrics capFM =
                    g2.getFontMetrics();

            int capX =
                    (getWidth()
                            - capFM.stringWidth(
                                    capacityText))
                            / 2;

            g2.drawString(
                    capacityText,
                    capX,
                    y + jugHeight + 65
            );

            g2.dispose();
        }
    }
}