// CalculatorGUI.java
// GUI of calculator

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CalculatorGUI implements ActionListener {

    //1. INSTANTIATE THE ENGINE

    CalculatorEngine engine = new CalculatorEngine();

    JFrame frame;
    JTextField display;
    JLabel currentMathLabel;
    JTextArea historyLog;

    JPanel topPanel, topHeader, keypadPanel;
    JButton themeButton;
    ArrayList<JButton> allButtons = new ArrayList<>();
    boolean isDarkMode = false;

    public CalculatorGUI() {
        frame = new JFrame("My Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 450);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        // --- TOP PANEL SETUP ---
        topPanel = new JPanel(new BorderLayout());
        topHeader = new JPanel(new BorderLayout());

        themeButton = new JButton("🌙 Dark Mode");
        themeButton.setFocusPainted(false);
        themeButton.addActionListener(this);

        currentMathLabel = new JLabel("");
        currentMathLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        currentMathLabel.setForeground(Color.GRAY);
        currentMathLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        topHeader.add(themeButton, BorderLayout.WEST);
        topHeader.add(currentMathLabel, BorderLayout.CENTER);

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);

        topPanel.add(topHeader, BorderLayout.NORTH);
        topPanel.add(display, BorderLayout.SOUTH);
        frame.add(topPanel, BorderLayout.NORTH);

        // --- HISTORY SIDEBAR ---
        historyLog = new JTextArea();
        historyLog.setFont(new Font("Arial", Font.PLAIN, 14));
        historyLog.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(historyLog);
        scrollPane.setPreferredSize(new Dimension(200, 0));
        scrollPane.setBorder(BorderFactory.createTitledBorder("History"));

        frame.add(scrollPane, BorderLayout.EAST);

        // --- KEYPAD ---
        keypadPanel = new JPanel();
        keypadPanel.setLayout(new GridLayout(5, 4, 2, 2));

        String[] buttonLabels = {
                "C", "DEL", "(", ")",
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                ".", "0", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setFocusPainted(false);
            button.addActionListener(this);

            allButtons.add(button);
            keypadPanel.add(button);
        }

        frame.add(keypadPanel, BorderLayout.CENTER);

        applyTheme();

        frame.setVisible(true);
    }

    private void applyTheme() {
        Color darkBg = new Color(30, 30, 30);
        Color darkPanelBg = new Color(45, 45, 45);
        Color darkText = Color.WHITE;
        Color darkButtonBg = new Color(60, 60, 60);

        Color lightBg = new Color(238, 238, 238);
        Color lightText = Color.BLACK;
        Color lightButtonBg = UIManager.getColor("Button.background");

        if (isDarkMode) {
            frame.getContentPane().setBackground(darkBg);
            topPanel.setBackground(darkBg);
            topHeader.setBackground(darkBg);
            keypadPanel.setBackground(darkBg);

            display.setBackground(darkPanelBg);
            display.setForeground(darkText);
            display.setCaretColor(darkText);

            historyLog.setBackground(darkPanelBg);
            historyLog.setForeground(darkText);

            themeButton.setText("☀️ Light Mode");
            themeButton.setBackground(darkButtonBg);
            themeButton.setForeground(darkText);

            for (JButton btn : allButtons) {
                btn.setBackground(darkButtonBg);
                btn.setForeground(darkText);
            }
        } else {
            frame.getContentPane().setBackground(lightBg);
            topPanel.setBackground(lightBg);
            topHeader.setBackground(lightBg);
            keypadPanel.setBackground(lightBg);

            display.setBackground(Color.WHITE);
            display.setForeground(lightText);

            historyLog.setBackground(Color.WHITE);
            historyLog.setForeground(lightText);

            themeButton.setText("🌙 Dark Mode");
            themeButton.setBackground(lightButtonBg);
            themeButton.setForeground(lightText);

            for (JButton btn : allButtons) {
                btn.setBackground(lightButtonBg);
                btn.setForeground(lightText);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == themeButton) {
            isDarkMode = !isDarkMode;
            applyTheme();
            return;
        }

        String command = e.getActionCommand();

        if (command.equals("C")) {
            display.setText("");
            currentMathLabel.setText("");
        }
        else if (command.equals("DEL")) {
            String currentText = display.getText();
            if (!currentText.isEmpty() && !currentText.equals("Error")) {
                display.setText(currentText.substring(0, currentText.length() - 1));
            }
        }
        else if (command.equals("=")) {
            String expression = display.getText();

            if (!expression.isEmpty() && !expression.equals("Error")) {
                try {
                    // 2. Sending the string to calculator Engine
                    // Instead of calculating it ourselves, we pass the string to our Engine!
                    double result = engine.evaluate(expression);

                    DecimalFormat df = new DecimalFormat("#.##########");
                    String formattedResult = df.format(result);

                    currentMathLabel.setText(expression + " =");
                    display.setText(formattedResult);
                    historyLog.append(expression + " = " + formattedResult + "\n");
                } catch (Exception ex) {
                    display.setText("Error");
                }
            }
        }
        else {
            if (display.getText().equals("Error")) display.setText("");
            display.setText(display.getText() + command);
        }
    }

    public static void main(String[] args) {
        // Run the GUI
        new CalculatorGUI();
    }
}