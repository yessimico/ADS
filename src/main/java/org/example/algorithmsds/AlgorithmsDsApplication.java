package org.example.algorithmsds;
import java.util.Scanner;

public class AlgorithmsDsApplication {

    // 1
    public static int sumSquares(int n) {
        if (n == 1) return 1;
        return n * n + sumSquares(n - 1);
    }

    // 2
    public static int sumFirstN(int[] arr, int n) {
        if (n == 0) return 0;
        return arr[n - 1] + sumFirstN(arr, n - 1);
    }

    // 3
    public static int sumPositive(int n) {
        if (n == 1) return 1;
        return n + sumPositive(n - 1);
    }

    // 4
    public static int power(int b, int n) {
        if (n == 0) return 1;
        return b * power(b, n - 1);
    }

    public static int sumPowers(int b, int n) {
        if (n == 0) return 1;
        return power(b, n) + sumPowers(b, n - 1);
    }

    // 5
    public static void printReverseNumbers(int n, Scanner sc) {
        if (n == 0) return;
        int x = sc.nextInt();
        printReverseNumbers(n - 1, sc);
        System.out.print(x + " ");
    }

    // 6
    public static void printReverseStrings(int n, Scanner sc) {
        if (n == 0) return;
        String s = sc.nextLine();
        printReverseStrings(n - 1, sc);
        System.out.println(s);
    }

    // 7 hellllo world
    public static void fillSpiral(int[][] matrix, int top, int left, int bottom, int right, int value) {
        if (top > bottom || left > right) return;

        for (int j = left; j <= right; j++) {
            matrix[top][j] = value++;
        }

        for (int i = top + 1; i <= bottom; i++) {
            matrix[i][right] = value++;
        }

        if (top < bottom) {
            for (int j = right - 1; j >= left; j--) {
                matrix[bottom][j] = value++;
            }
        }

        if (left < right) {
            for (int i = bottom - 1; i > top; i--) {
                matrix[i][left] = value++;
            }
        }

        fillSpiral(matrix, top + 1, left + 1, bottom - 1, right - 1, value);
    }

    public static void printMatrix(int[][] matrix, int i, int j) {
        int n = matrix.length;
        if (i == n) return;
        if (j == n) {
            System.out.println();
            printMatrix(matrix, i + 1, 0);
            return;
        }
        System.out.print(matrix[i][j] + " ");
        printMatrix(matrix, i, j + 1);
    }

    // 8
    public static void generateSequences(int[] arr, int index, int n, int k) {
        if (index == n) {
            printArray(arr, 0);
            System.out.println();
            return;
        }

        for (int i = 1; i <= k; i++) {
            arr[index] = i;
            generateSequences(arr, index + 1, n, k);
        }
    }

    public static void printArray(int[] arr, int index) {
        if (index == arr.length) return;
        System.out.print(arr[index] + " ");
        printArray(arr, index + 1);
    }

    // 9
    public static void permute(char[] arr, int index) {
        if (index == arr.length) {
            System.out.println(new String(arr));
            return;
        }

        for (int i = index; i < arr.length; i++) {
            swap(arr, index, i);
            permute(arr, index + 1);
            swap(arr, index, i);
        }
    }

    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 10
    public static boolean isPowerOfTwo(int n) {
        if (n == 1) return true;
        if (n <= 0 || n % 2 != 0) return false;
        return isPowerOfTwo(n / 2);
    }

    public static void main(String[] args) {
        System.out.println("1) " + sumSquares(4)); // 30

        int[] arr = {5, 2, 7, 1, 3};
        System.out.println("2) " + sumFirstN(arr, 3)); // 14

        System.out.println("3) " + sumPositive(5)); // 15

        System.out.println("4) " + sumPowers(4, 3)); // 85

        System.out.println("\n7) Spiral 4x4:");
        int n = 4;
        int[][] matrix = new int[n][n];
        fillSpiral(matrix, 0, 0, n - 1, n - 1, 1);
        printMatrix(matrix, 0, 0);

        System.out.println("\n8) Sequences:");
        int[] seq = new int[2];
        generateSequences(seq, 0, 2, 3);

        System.out.println("\n9) Permutations:");
        permute("IOX".toCharArray(), 0);

        System.out.println("\n10) Power of two:");
        for (int i = 0; i <= 32; i++) {
            if (isPowerOfTwo(i)) {
                System.out.println(i + " is a power of two");
            } else {
                System.out.println(i + " is not a power of two");
            }
        }
    }

}
