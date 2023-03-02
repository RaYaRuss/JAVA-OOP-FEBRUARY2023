package WorkingWithAbstraction_Exercise;

import java.util.Arrays;
import java.util.Scanner;

public class JediGalaxyNew {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = readPositions(scanner);
        int rows = dimensions[0];
        int cols = dimensions[1];

        int[][] galaxy = new int[rows][cols];

        fillInGalaxy(rows, cols, galaxy);
        long starsCollected = 0;

            int[] jediPositions = readPositions(scanner);
            int[] evilPositions = readPositions(scanner);
            int rowEvil = evilPositions[0];
            int colEvil = evilPositions[1];

        moveEvil(galaxy, rowEvil, colEvil);

        int rowJedi = jediPositions[0];
            int colJedi = jediPositions[1];

        starsCollected = moveJedi(galaxy, rowJedi, colJedi);
        System.out.println(starsCollected);
    }

    private static int[] readPositions(Scanner scanner) {
        return Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private static int moveJedi(int[][] galaxy, int rowJedi, int colJedi) {
        int starsCollected = 0;
        while (rowJedi >= 0 && colJedi < galaxy[1].length) {
            if (isInBounds(rowJedi, colJedi, galaxy)) {
                starsCollected += galaxy[rowJedi][colJedi];
            }
            colJedi++;
            rowJedi--;
        }
        return starsCollected;
    }

    private static void moveEvil(int[][] galaxy, int rowEvil, int colEvil) {
        while (rowEvil >= 0 && colEvil >= 0) {
            if (isInBounds(rowEvil, colEvil, galaxy)) {
                galaxy[rowEvil][colEvil] = 0;
            }
            rowEvil--;
            colEvil--;
        }
    }
    private static boolean isInBounds(int row, int col, int[][] galaxy) {
        return row >= 0 && col >= 0 && row < galaxy.length && col < galaxy.length;
    }
    private static void fillInGalaxy(int rows, int cols, int[][] galaxy) {
        int value = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                galaxy[row][col] = value++;
            }
        }
    }
}
