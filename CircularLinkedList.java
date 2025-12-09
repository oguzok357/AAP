public class Main {
    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);

        System.out.print("Исходный список: ");
        list.display();

        list.rotate();
        System.out.print("После rotate(): ");
        list.display();

        System.out.println("findCycle(): " + list.findCycle());
        System.out.println("contains(4): " + list.contains(4));
        System.out.println("find(5): " + (list.find(5) != null ? "найден" : "не найден"));

        CircularLinkedList[] halves = list.splitIntoTwo();
        if (halves != null) {
            System.out.print("Первая половина: ");
            halves[0].display();
            System.out.print("Вторая половина: ");
            halves[1].display();
        }
    }
}

class Node {
    Integer data;
    Node next;

    public Node(Integer data) {
        this.data = data;
        this.next = null;
    }
}

class CircularLinkedList {
    private Node tail;
    private int size;

    public CircularLinkedList() {
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Integer data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            tail = newNode;
            tail.next = tail;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
        }
        size++;
    }

    public void addLast(Integer data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            tail = newNode;
            tail.next = tail;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void removeFirst() {
        if (isEmpty()) {
            System.out.println("Список пуст!");
            return;
        }
        if (tail.next == tail) {
            tail = null;
        } else {
            tail.next = tail.next.next;
        }
        size--;
    }

    public void removeLast() {
        if (isEmpty()) {
            System.out.println("Список пуст!");
            return;
        }
        if (tail.next == tail) {
            tail = null;
        } else {
            Node current = tail.next;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = tail.next;
            tail = current;
        }
        size--;
    }

    public void remove(Integer data) {
        if (isEmpty()) {
            System.out.println("Список пуст!");
            return;
        }

        Node current = tail.next;
        Node prev = tail;
        do {
            if (current.data.equals(data)) {
                if (current == tail && current == tail.next) {
                    tail = null;
                } else {
                    prev.next = current.next;
                    if (current == tail) {
                        tail = prev;
                    }
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        } while (current != tail.next);

        System.out.println("Элемент не найден.");
    }

    public boolean contains(Integer data) {
        if (isEmpty()) return false;
        Node current = tail.next;
        do {
            if (current.data.equals(data)) return true;
            current = current.next;
        } while (current != tail.next);
        return false;
    }

    public void clear() {
        tail = null;
        size = 0;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Список пуст!");
            return;
        }
        Node current = tail.next;
        do {
            System.out.print(current.data + " ");
            current = current.next;
        } while (current != tail.next);
        System.out.println();
    }

    public void rotate() {
        if (!isEmpty()) {
            tail = tail.next;
        }
    }

    public boolean findCycle() {
        return !isEmpty();
    }

    public Node find(Integer data) {
        if (isEmpty()) return null;
        Node current = tail.next;
        do {
            if (current.data.equals(data)) return current;
            current = current.next;
        } while (current != tail.next);
        return null;
    }

    public CircularLinkedList[] splitIntoTwo() {
        if (size < 2) {
            System.out.println("Недостаточно элементов для разделения.");
            return null;
        }

        int mid = size / 2;
        Node current = tail.next;
        for (int i = 1; i < mid; i++) {
            current = current.next;
        }

        Node head2 = current.next;
        current.next = tail.next;

        CircularLinkedList firstHalf = new CircularLinkedList();
        CircularLinkedList secondHalf = new CircularLinkedList();

        Node temp = tail.next;
        do {
            firstHalf.addLast(temp.data);
            if (temp == current) break;
            temp = temp.next;
        } while (temp != tail.next);

        temp = head2;
        do {
            secondHalf.addLast(temp.data);
            temp = temp.next;
        } while (temp != head2);

        CircularLinkedList[] result = new CircularLinkedList[2];
        result[0] = firstHalf;
        result[1] = secondHalf;
        return result;
    }
}
