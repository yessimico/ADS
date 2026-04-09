package org.example.algorithmsds;

public class assignment2 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class LinkedList {
        Node head;

        // 1. Add element to the beginning
        public void addFirst(int data) {
            Node newNode = new Node(data);
            newNode.next = head;
            head = newNode;
        }

        // 2. Add element to the end
        public void addLast(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = newNode;
                return;
            }
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        // 3. Remove the last element
        public void removeLast() {
            if (head == null) return;
            if (head.next == null) {
                head = null;
                return;
            }
            Node current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
        }

        // 4. Print all elements
        public void printList() {
            Node current = head;
            while (current != null) {
                System.out.print(current.data);
                if (current.next != null) System.out.print(" -> ");
                current = current.next;
            }
            System.out.println();
        }

        // 5. Search for a specific element
        public boolean search(int target) {
            Node current = head;
            while (current != null) {
                if (current.data == target) return true;
                current = current.next;
            }
            return false;
        }

        // 6. Insert element at a given position (0-indexed)
        public void insertAt(int position, int data) {
            if (position == 0) {
                addFirst(data);
                return;
            }
            Node newNode = new Node(data);
            Node current = head;
            for (int i = 0; i < position - 1 && current != null; i++) {
                current = current.next;
            }
            if (current == null) {
                System.out.println("Position out of bounds");
                return;
            }
            newNode.next = current.next;
            current.next = newNode;
        }

        // 7. Remove element by value
        public void removeByValue(int value) {
            if (head == null) return;
            if (head.data == value) {
                head = head.next;
                return;
            }
            Node current = head;
            while (current.next != null && current.next.data != value) {
                current = current.next;
            }
            if (current.next != null) {
                current.next = current.next.next;
            }
        }

        // 8. Combine two linked lists into one
        public static LinkedList combine(LinkedList list1, LinkedList list2) {
            LinkedList result = new LinkedList();
            Node current = list1.head;
            while (current != null) {
                result.addLast(current.data);
                current = current.next;
            }
            current = list2.head;
            while (current != null) {
                result.addLast(current.data);
                current = current.next;
            }
            return result;
        }

        // 9. Reverse the linked list
        public void reverse() {
            Node prev = null;
            Node current = head;
            while (current != null) {
                Node next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
            head = prev;
        }

        // 10. Sort using insertion sort
        public void insertionSort() {
            if (head == null || head.next == null) return;
            Node sorted = null;
            Node current = head;
            while (current != null) {
                Node next = current.next;
                if (sorted == null || sorted.data >= current.data) {
                    current.next = sorted;
                    sorted = current;
                } else {
                    Node temp = sorted;
                    while (temp.next != null && temp.next.data < current.data) {
                        temp = temp.next;
                    }
                    current.next = temp.next;
                    temp.next = current;
                }
                current = next;
            }
            head = sorted;
        }
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        // 1 & 2: addFirst / addLast
        list.addLast(3);
        list.addLast(5);
        list.addFirst(1);
        System.out.print("After addFirst(1), addLast(3,5): ");
        list.printList(); // 1 -> 3 -> 5

        // 3: removeLast
        list.removeLast();
        System.out.print("After removeLast: ");
        list.printList(); // 1 -> 3

        // 4: printList already used above

        // 5: search
        System.out.println("Search 3: " + list.search(3));   // true
        System.out.println("Search 9: " + list.search(9));   // false

        // 6: insertAt
        list.insertAt(1, 2);
        System.out.print("After insertAt(1, 2): ");
        list.printList(); // 1 -> 2 -> 3

        // 7: removeByValue
        list.removeByValue(2);
        System.out.print("After removeByValue(2): ");
        list.printList(); // 1 -> 3

        // 8: combine
        LinkedList list2 = new LinkedList();
        list2.addLast(4);
        list2.addLast(5);
        LinkedList combined = LinkedList.combine(list, list2);
        System.out.print("Combined: ");
        combined.printList(); // 1 -> 3 -> 4 -> 5

        // 9: reverse
        combined.reverse();
        System.out.print("Reversed: ");
        combined.printList(); // 5 -> 4 -> 3 -> 1

        // 10: insertionSort
        LinkedList unsorted = new LinkedList();
        unsorted.addLast(4);
        unsorted.addLast(2);
        unsorted.addLast(7);
        unsorted.addLast(1);
        unsorted.insertionSort();
        System.out.print("Sorted: ");
        unsorted.printList(); // 1 -> 2 -> 4 -> 7
    }
}
