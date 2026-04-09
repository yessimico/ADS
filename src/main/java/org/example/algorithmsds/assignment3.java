package org.example.algorithmsds;

public class assignment3 {

    static class BST {
        int value;
        BST left, right;

        BST(int value) {
            this.value = value;
        }
    }

    static BST insert(BST root, int value){
        if (root == null) return new BST(value);
        if (value < root.value) root.left = insert(root.left, value);
        else if (value > root.value) root.right = insert(root.right, value);
        return root;
    }

    static BST delete(BST root, int value) {
        if (root == null) return null;
        if (value < root.value) {
            root.left = delete(root.left, value);
        } else if (value > root.value) {
            root.right = delete(root.right, value);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            // Replace with inorder successor (min of right subtree)
            BST successor = root.right;
            while (successor.left != null) successor = successor.left;
            root.value = successor.value;
            root.right = delete(root.right, successor.value);
        }
        return root;
    }

    static void displayInOrder(BST root) {
        if (root == null) return;
        displayInOrder(root.left);
        System.out.print(root.value + " ");
        displayInOrder(root.right);
    }

    // Prints tree structure visually
    static void displayTree(BST root, String prefix, boolean isLeft) {
        if (root == null) return;
        System.out.println(prefix + (isLeft ? "├── " : "└── ") + root.value);
        String childPrefix = prefix + (isLeft ? "│   " : "    ");
        displayTree(root.left, childPrefix, true);
        displayTree(root.right, childPrefix, false);
    }

    static void printTree(BST root) {
        if (root == null) { System.out.println("(empty)"); return; }
        System.out.println(root.value);
        displayTree(root.left, "", true);
        displayTree(root.right, "", false);
    }

    // Task 3: Count even and odd elements
    static int[] countEvenOdd(BST root) {
        if (root == null) return new int[]{0, 0};
        int[] left = countEvenOdd(root.left);
        int[] right = countEvenOdd(root.right);
        int even = left[0] + right[0] + (root.value % 2 == 0 ? 1 : 0);
        int odd  = left[1] + right[1] + (root.value % 2 != 0 ? 1 : 0);
        return new int[]{even, odd};
    }

    // Task 4: Max (rightmost) and Min (leftmost) in BST
    static int bstMax(BST root) {
        while (root.right != null) root = root.right;
        return root.value;
    }

    static int bstMin(BST root) {
        while (root.left != null) root = root.left;
        return root.value;
    }

    // Task 5: Check empty, clear tree
    static boolean isEmpty(BST root) {
        return root == null;
    }

    // Task 6: Height of BST (empty tree = 0)
    static int height(BST root) {
        if (root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    // ==================== HASH TABLE (open addressing, linear probing) ====================

    static class HashTable {
        private static final int SIZE = 10;
        private final int[] table = new int[SIZE];
        private final boolean[] occupied = new boolean[SIZE];

        private int hash(int key) {
            return Math.abs(key % SIZE);
        }

        // Task 7: Add element
        void add(int key) {
            int index = hash(key);
            int start = index;
            while (occupied[index]) {
                if (table[index] == key) return; // already exists
                index = (index + 1) % SIZE;
                if (index == start) { System.out.println("Hash table is full!"); return; }
            }
            table[index] = key;
            occupied[index] = true;
        }

        // Task 7: Remove element
        void remove(int key) {
            int index = hash(key);
            int start = index;
            while (occupied[index]) {
                if (table[index] == key) {
                    occupied[index] = false;
                    System.out.println("Removed: " + key);
                    return;
                }
                index = (index + 1) % SIZE;
                if (index == start) break;
            }
            System.out.println("Element " + key + " not found.");
        }

        void display() {
            System.out.println("Hash Table:");
            for (int i = 0; i < SIZE; i++) {
                System.out.println("  [" + i + "]: " + (occupied[i] ? table[i] : "empty"));
            }
        }

        // Task 8: Sum of elements
        int sum() {
            int total = 0;
            for (int i = 0; i < SIZE; i++)
                if (occupied[i]) total += table[i];
            return total;
        }

        // Task 9: Max element
        int max() {
            int maxVal = Integer.MIN_VALUE;
            for (int i = 0; i < SIZE; i++)
                if (occupied[i] && table[i] > maxVal) maxVal = table[i];
            return maxVal;
        }

        // Task 9: Min element
        int min() {
            int minVal = Integer.MAX_VALUE;
            for (int i = 0; i < SIZE; i++)
                if (occupied[i] && table[i] < minVal) minVal = table[i];
            return minVal;
        }

        // Task 10: Print even and odd elements
        void printEvenOdd() {
            System.out.print("Even elements: ");
            for (int i = 0; i < SIZE; i++)
                if (occupied[i] && table[i] % 2 == 0) System.out.print(table[i] + " ");
            System.out.println();
            System.out.print("Odd elements:  ");
            for (int i = 0; i < SIZE; i++)
                if (occupied[i] && table[i] % 2 != 0) System.out.print(table[i] + " ");
            System.out.println();
        }
    }

    // ==================== MAIN ====================

    public static void main(String[] args) {

        // ---------- TASK 1: Create BST, display, insert ----------
        System.out.println("===== TASK 1: Binary Tree + Insert =====");
        BST tree1 = null;
        for (int v : new int[]{5, 3, 7, 1, 4, 6, 8}) tree1 = insert(tree1, v);
        System.out.println("Tree structure:");
        printTree(tree1);
        System.out.print("In-order:  ");
        displayInOrder(tree1);
        System.out.println();
        tree1 = insert(tree1, 2);
        System.out.print("After insert(2): ");
        displayInOrder(tree1);
        System.out.println();

        // ---------- TASK 2: Create BST, display, delete ----------
        System.out.println("\n===== TASK 2: Binary Tree + Delete =====");
        BST tree2 = null;
        for (int v : new int[]{5, 3, 7, 1, 4, 6, 8}) tree2 = insert(tree2, v);
        System.out.print("Before delete: ");
        displayInOrder(tree2);
        System.out.println();
        tree2 = delete(tree2, 3);
        System.out.println("After delete(3):");
        printTree(tree2);
        System.out.print("In-order:  ");
        displayInOrder(tree2);
        System.out.println();

        // ---------- TASK 3: Count even/odd ----------
        System.out.println("\n===== TASK 3: Count Even/Odd =====");
        BST tree3 = null;
        for (int v : new int[]{5, 3, 7, 2, 4, 6, 8}) tree3 = insert(tree3, v);
        System.out.print("Tree: ");
        displayInOrder(tree3);
        System.out.println();
        int[] counts = countEvenOdd(tree3);
        System.out.println("Even count: " + counts[0]);
        System.out.println("Odd count:  " + counts[1]);

        // ---------- TASK 4: Max and Min ----------
        System.out.println("\n===== TASK 4: Max and Min =====");
        BST tree4 = null;
        for (int v : new int[]{5, 3, 7, 1, 4, 6, 9}) tree4 = insert(tree4, v);
        System.out.print("Tree: ");
        displayInOrder(tree4);
        System.out.println();
        System.out.println("Max: " + bstMax(tree4));
        System.out.println("Min: " + bstMin(tree4));

        // ---------- TASK 5: Check empty + clear ----------
        System.out.println("\n===== TASK 5: isEmpty + Clear Tree =====");
        BST tree5 = null;
        for (int v : new int[]{5, 3, 7, 1, 4}) tree5 = insert(tree5, v);
        System.out.print("Tree: ");
        displayInOrder(tree5);
        System.out.println();
        System.out.println("Is empty: " + isEmpty(tree5));
        tree5 = null; // clear tree
        System.out.println("After clearing:");
        System.out.println("Is empty: " + isEmpty(tree5));

        // ---------- TASK 6: Height ----------
        System.out.println("\n===== TASK 6: Height of BST =====");
        BST tree6 = null;
        for (int v : new int[]{5, 3, 7, 1, 4, 6, 8, 2}) tree6 = insert(tree6, v);
        System.out.println("Tree structure:");
        printTree(tree6);
        System.out.println("Height: " + height(tree6));
        System.out.println("Height of empty tree: " + height(null));

        // ---------- TASK 7: Hash Table add/remove ----------
        System.out.println("\n===== TASK 7: Hash Table Add/Remove =====");
        HashTable ht7 = new HashTable();
        for (int v : new int[]{15, 25, 35, 8, 42, 7}) ht7.add(v);
        ht7.display();
        ht7.remove(25);
        ht7.remove(99);
        System.out.println("After operations:");
        ht7.display();

        // ---------- TASK 8: Sum ----------
        System.out.println("\n===== TASK 8: Hash Table Sum =====");
        HashTable ht8 = new HashTable();
        for (int v : new int[]{10, 20, 30, 5, 15}) ht8.add(v);
        ht8.display();
        System.out.println("Sum: " + ht8.sum());

        // ---------- TASK 9: Max/Min ----------
        System.out.println("\n===== TASK 9: Hash Table Max/Min =====");
        HashTable ht9 = new HashTable();
        for (int v : new int[]{10, 45, 3, 27, 8}) ht9.add(v);
        ht9.display();
        System.out.println("Max: " + ht9.max());
        System.out.println("Min: " + ht9.min());

        // ---------- TASK 10: Even/Odd ----------
        System.out.println("\n===== TASK 10: Hash Table Even/Odd =====");
        HashTable ht10 = new HashTable();
        for (int v : new int[]{10, 45, 3, 27, 8, 14, 9}) ht10.add(v);
        ht10.display();
        ht10.printEvenOdd();
    }
}
