import java.io.*;
import java.nio.charset.StandardCharsets;

public class Matrix {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("INPUT.TXT"), StandardCharsets.UTF_8));
        PrintWriter pw = new PrintWriter(new File("OUTPUT.TXT"));
        Integer countOfMatrix = readInt(reader);
        Integer sizeOfMatrix = readInt(reader);
        Integer numbRow = readInt(reader) - 1;
        Integer numbCol = readInt(reader) - 1;
        Integer p = readInt(reader);
        int[][] resultMatrix = readMatrixFile(reader, sizeOfMatrix);
        for (int i = 0; i < countOfMatrix - 1; i++) {
            resultMatrix = multiplyMatrix(resultMatrix, readMatrixFile(reader, sizeOfMatrix),
                    p, numbRow);
        }
        pw.write(resultMatrix[numbRow][numbCol] + "");
        pw.close();
    }


    private static int[][] readMatrixFile(BufferedReader reader, int sizeOfMatrix) throws IOException {
        int[][] matrix = new int[sizeOfMatrix][sizeOfMatrix];
        for (int i = 0; i < sizeOfMatrix; i++) {
            for (int j = 0; j < sizeOfMatrix; j++) {
                matrix[i][j] = readInt(reader);
            }
        }
        return matrix;
    }

    /**
     * Перемножаем матрицы, для большей производительности
     * записываем только нужную @numbRow
     */
    private static int[][] multiplyMatrix(final int[][] firstMatrix,
                                          final int[][] secondMatrix,
                                          int p, int numbRow) {
        final int rowCount = firstMatrix.length;
        final int colCount = secondMatrix[0].length;
        final int sumLength = secondMatrix.length;
        final int[][] result = new int[rowCount][colCount];

        for (int col = 0; col < colCount; ++col) {
            int sum = 0;
            for (int i = 0; i < sumLength; ++i)
                sum += firstMatrix[numbRow][i] * secondMatrix[i][col];
            if (sum >= p) {
                sum %= p;
            }
            result[numbRow][col] = sum;
        }
        return result;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        int ret = 0;
        boolean dig = false;

        for (int c; (c = reader.read()) != -1; ) {
            if (c >= '0' && c <= '9') {
                dig = true;
                ret = ret * 10 + c - '0';
            } else if (dig) break;
        }

        return ret;
    }
}
