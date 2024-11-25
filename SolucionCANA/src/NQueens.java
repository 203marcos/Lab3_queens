import java.util.Arrays;

public class NQueens {
    static int N = 8; // Dimensão do tabuleiro (8x8 por padrão)

    public static void main(String[] args) {
        int[][] board = new int[N][N]; // Inicializa o tabuleiro vazio
        if (solveNQueens(board, 0)) {
            printBoard(board);
        } else {
            System.out.println("Nenhuma solução encontrada.");
        }
    }

    // Função para resolver o problema das N damas
    static boolean solveNQueens(int[][] board, int row) {
        if (row >= N) return true; // Todas as damas foram colocadas

        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1; // Coloca a dama na posição
                if (solveNQueens(board, row + 1)) return true;
                board[row][col] = 0; // Remove a dama (backtrack)
            }
        }
        return false;
    }

    // Verifica se é seguro colocar a dama
    static boolean isSafe(int[][] board, int row, int col) {
        for (int i = 0; i < row; i++) // Verifica a coluna acima
            if (board[i][col] == 1) return false;

        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) // Diagonal superior esquerda
            if (board[i][j] == 1) return false;

        for (int i = row - 1, j = col + 1; i >= 0 && j < N; i--, j++) // Diagonal superior direita
            if (board[i][j] == 1) return false;

        return true;
    }

    // Imprime o tabuleiro
    static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell == 1 ? "Q " : ". ");
            }
            System.out.println();
        }
    }
}
