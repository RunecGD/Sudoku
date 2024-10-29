package Sudoku;

import javax.swing.*;
import java.awt.*;

public class SudokuSolver extends JFrame {
    private static final int GRID_SIZE = 9;
    private static final int CELL_SIZE = 50;

    private JTextField[][] cells;
    private JButton solveButton;

    public SudokuSolver() {
        setTitle("Sudoku Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(GRID_SIZE * CELL_SIZE + 16, GRID_SIZE * CELL_SIZE + 100);
        setLocationRelativeTo(null);

        JPanel sudokuPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE, 2, 2));
        sudokuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        cells = new JTextField[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(new Font("Arial", Font.BOLD, 24));
                sudokuPanel.add(cell);
                cells[i][j] = cell;
            }
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        solveButton = new JButton("Найти решение");
        solveButton.addActionListener(e -> solveSudoku());
        buttonPanel.add(solveButton);

        getContentPane().add(sudokuPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private boolean solveSudoku() {
        int[][] board = new int[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                String value = cells[i][j].getText();
                if (value.isEmpty()) {
                    board[i][j] = 0;
                } else {
                    board[i][j] = Integer.parseInt(value);
                }
            }
        }

        if (solveBoard(board)) {
            updateCells(board);
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "No solution found.");
            return false;
        }
    }

    private boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= GRID_SIZE; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveBoard(board)) {
                                return true;
                            }
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int[][] board, int row, int col, int num) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }


        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }


        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private void updateCells(int[][] board) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                cells[i][j].setText(String.valueOf(board[i][j]));
            }
        }
    }
}