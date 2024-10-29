package Sudoku;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SudokuSolver sudokuSolver = new SudokuSolver();
            sudokuSolver.setVisible(true);
        });
    }
}
