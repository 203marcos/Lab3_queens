import javax.swing.*;
import java.awt.*;

public class NQueensGUI {
    static int N = 20; // Dimensão do tabuleiro
    static JFrame frame = new JFrame("N Queens Problem");

    public static void main(String[] args) {
        int[][] board = new int[N][N];
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);

        if (solveNQueens(board, 0)) {
            JOptionPane.showMessageDialog(frame, "Solução encontrada!");
        } else {
            JOptionPane.showMessageDialog(frame, "Nenhuma solução encontrada.");
        }
    }

    static boolean solveNQueens(int[][] board, int row) {
        if (row >= N) return true;

        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1;
                updateBoard(board); // Atualiza a interface gráfica
                sleep(500); // Espera 500ms para visualizar a mudança
                if (solveNQueens(board, row + 1)) return true;
                board[row][col] = 0;
                updateBoard(board); // Atualiza a interface gráfica
                sleep(500);
            }
        }
        return false;
    }

    static boolean isSafe(int[][] board, int row, int col) {
        for (int i = 0; i < row; i++)
            if (board[i][col] == 1) return false;

        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1) return false;

        for (int i = row - 1, j = col + 1; i >= 0 && j < N; i--, j++)
            if (board[i][j] == 1) return false;

        return true;
    }

    static void updateBoard(int[][] board) {
        frame.getContentPane().removeAll();
        frame.setLayout(new GridLayout(N, N));
        for (int[] row : board) {
            for (int cell : row) {
                JPanel panel = new JPanel();
                panel.setBackground(cell == 1 ? Color.RED : Color.WHITE);
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Adiciona as linhas de separação
                frame.add(panel);
            }
        }
        frame.revalidate();
        frame.repaint();
    }

    static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
