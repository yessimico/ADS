import java.util.ArrayList;
import java.util.List;

public class DataStructuresLab {

    static class StackNode {
        double data;
        StackNode next;
        StackNode(double data) { this.data = data; }
    }

    static class QueueNode {
        int data;
        QueueNode next;
        QueueNode(int data) { this.data = data; }
    }

    static class DynamicStack {
        private StackNode top;

        public boolean isEmpty() { return top == null; }

        public void push(double data) {
            StackNode newNode = new StackNode(data);
            newNode.next = top;
            top = newNode;
        }

        public Double pop() {
            if (isEmpty()) return null;
            double data = top.data;
            top = top.next;
            return data;
        }

        public void display() {
            if (isEmpty()) { System.out.println("Стек пуст"); return; }
            StackNode curr = top;
            System.out.print("Стек (сверху вниз): ");
            while (curr != null) {
                System.out.print(curr.data + " ");
                curr = curr.next;
            }
            System.out.println();
        }

        public void clear() { top = null; }

        public void removeBelowAverage() {
            if (isEmpty()) return;
            double sum = 0;
            int count = 0;
            StackNode curr = top;
            while (curr != null) {
                sum += curr.data;
                count++;
                curr = curr.next;
            }
            double avg = sum / count;
            System.out.println("Среднее арифметическое: " + avg);

            List<Double> filtered = new ArrayList<>();
            curr = top;
            while (curr != null) {
                if (curr.data >= avg) filtered.add(curr.data);
                curr = curr.next;
            }
            this.clear();
            for (int i = filtered.size() - 1; i >= 0; i--) {
                this.push(filtered.get(i));
            }
        }
    }

    static class DynamicQueue {
        private QueueNode head, tail;

        public boolean isEmpty() { return head == null; }

        public void enqueue(int data) {
            QueueNode newNode = new QueueNode(data);
            if (isEmpty()) { head = tail = newNode; return; }
            tail.next = newNode;
            tail = newNode;
        }

        public Integer dequeue() {
            if (isEmpty()) return null;
            int data = head.data;
            head = head.next;
            if (head == null) tail = null;
            return data;
        }

        public void display() {
            if (isEmpty()) { System.out.println("Очередь пуста"); return; }
            QueueNode curr = head;
            System.out.print("Очередь: ");
            while (curr != null) {
                System.out.print(curr.data + " ");
                curr = curr.next;
            }
            System.out.println();
        }

        public void clear() { head = tail = null; }

        public void removeEvens() {
            int size = 0;
            QueueNode temp = head;
            while (temp != null) { size++; temp = temp.next; }

            for (int i = 0; i < size; i++) {
                int val = dequeue();
                if (val % 2 != 0) enqueue(val);
            }
        }
    }

    public static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) largest = left;
        if (right < n && arr[right] > arr[largest]) largest = right;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== ТЕСТ СТЕКА (Задачи 1-2) ===");
        DynamicStack stack = new DynamicStack();
        stack.push(10.5);
        stack.push(2.0);
        stack.push(15.0);
        stack.push(3.5);
        stack.display();
        stack.removeBelowAverage();
        stack.display();

        System.out.println("\n=== ТЕСТ ОЧЕРЕДИ (Задачи 3-4) ===");
        DynamicQueue queue = new DynamicQueue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.display();
        queue.removeEvens();
        queue.display();

        System.out.println("\n=== ТЕСТ HEAPIFY (Задача 5) ===");
        int[] arr = {4, 10, 3, 5, 1};
        System.out.print("Массив до heapify: ");
        for(int x : arr) System.out.print(x + " ");

        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, arr.length, i);
        }

        System.out.print("\nМассив после heapify (Max-Heap): ");
        for(int x : arr) System.out.print(x + " ");
        System.out.println();
    }
}