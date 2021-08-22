package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe implements ActionListener {

    private Random random = new Random();
    private JFrame frame = new JFrame();
    private JPanel titlePanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JLabel textField = new JLabel();
    private JButton[] buttons = new JButton[9];
    private int counter = 0;
    private boolean player_1;

    public TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setTitle("Tic Tac Toe");
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField.setBackground(new Color(120, 20, 124));
        textField.setForeground(new Color(25, 255, 0));
        textField.setFont(new Font("Ink Free", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic Tac Toe");
        textField.setOpaque(true);
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);
        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setBackground(new Color(150, 150, 150));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("Ink Free", Font.PLAIN, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        titlePanel.add(textField);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel);
        startGame();
    }

    private void startGame() {
        try {
            textField.setText("Загрузка....");
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int chance = random.nextInt(100);
        if (chance % 2 == 0) {
            player_1 = true;
            textField.setText("Начинают Х");
        } else {
            player_1 = false;
            textField.setText("Начинают О");
        }
    }

    private void gameOver(String s) {
        counter = 0;
        Object[] option = {"Начать заново", "Закончить"};
        int n = JOptionPane.showOptionDialog(frame, "Конец игры\n" + s, "Конец игры", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
        frame.dispose();
        if (n == 0) {
            new TicTacToe();
        }

    }

    private void matchCheck() {
        if (someCheck(0, "X") && someCheck(1, "X") && someCheck(2, "X")) {
            xWins(0, 1, 2);
        } else if (someCheck(0, "X") && someCheck(4, "X") && someCheck(8, "X")) {
            xWins(0, 4, 8);
        } else if (someCheck(0, "X") && someCheck(3, "X") && someCheck(6, "X")) {
            xWins(0, 3, 6);
        } else if (someCheck(1, "X") && someCheck(4, "X") && someCheck(7, "X")) {
            xWins(1, 4, 7);
        } else if (someCheck(2, "X") && someCheck(4, "X") && someCheck(6, "X")) {
            xWins(2, 4, 6);
        } else if (someCheck(2, "X") && someCheck(5, "X") && someCheck(8, "X")) {
            xWins(2, 5, 8);
        } else if (someCheck(3, "X") && someCheck(4, "X") && someCheck(5, "X")) {
            xWins(3, 4, 5);
        } else if (someCheck(6, "X") && someCheck(7, "X") && someCheck(8, "X")) {
            xWins(6, 7, 8);
        } else if (someCheck(0, "O") && someCheck(1, "O") && someCheck(2, "O")) {
            oWins(0, 1, 2);
        } else if (someCheck(0, "O") && someCheck(3, "O") && someCheck(6, "O")) {
            oWins(0, 3, 6);
        } else if (someCheck(0, "O") && someCheck(4, "O") && someCheck(8, "O")) {
            oWins(0, 4, 8);
        } else if (someCheck(1, "O") && someCheck(4, "O") && someCheck(7, "O")) {
            oWins(1, 4, 7);
        } else if (someCheck(2, "O") && someCheck(4, "O") && someCheck(6, "O")) {
            oWins(2, 4, 6);
        } else if (someCheck(2, "O") && someCheck(5, "O") && someCheck(8, "O")) {
            oWins(2, 5, 8);
        } else if (someCheck(3, "O") && someCheck(4, "O") && someCheck(5, "O")) {
            oWins(3, 4, 5);
        } else if (someCheck(6, "O") && someCheck(7, "O") && someCheck(8, "O")) {
            oWins(6, 7, 8);
        } else if (counter == 9) {
            textField.setText("Ничья");
            gameOver("Ничья");
        }
    }

    private boolean someCheck(int index, String symbol) {
        return buttons[index].getText().equals(symbol);
    }


    private void xWins(int x1, int x2, int x3) {
        wins(x1, x2, x3, "X");
    }

    private void oWins(int x1, int x2, int x3) {
        wins(x1, x2, x3, "O");
    }

    private void wins(int x1, int x2, int x3, String text) {
        buttons[x1].setBackground(Color.RED);
        buttons[x2].setBackground(Color.RED);
        buttons[x3].setBackground(Color.RED);
        for (int j = 0; j < 9; j++) {
            buttons[j].setEnabled(false);
        }
        textField.setText(text + " Победили!");
        gameOver(text + " Победили!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (player_1) {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                        player_1 = false;
                        textField.setText("O ходят");
                        counter++;
                        matchCheck();
                    }
                } else {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText("O");
                        player_1 = true;
                        textField.setText("X ходят");
                        counter++;
                        matchCheck();
                    }
                }
            }
        }
    }
}

