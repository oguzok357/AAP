public class Main {
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();

        list.addFirst(3);
        list.addFirst(2);
        list.addLast(4);
        list.addLast(5);
        list.add(2, 10);

        System.out.print("Список: ");
        list.display();

        System.out.print("В обратном порядке: ");
        list.displayReverse();

        System.out.println("Элемент с индексом 2: " + list.get(2));
        System.out.println("Первый элемент: " + list.getFirst());
        System.out.println("Последний элемент: " + list.getLast());

        list.remove(2);
        System.out.print("После удаления по индексу 2: ");
        list.display();

        list.remove((Integer)3);
        System.out.print("После удаления значения 3: ");
        list.display();

        System.out.println("Содержит ли список 4 - " + list.contains(4));
        System.out.println("Размер: " + list.size());

        list.clear();
        System.out.println("После очистки пустой ли список -" + list.isEmpty());
    }
}

class Node {
    Integer data;
    Node prev;
    Node next;
    
    public Node(Integer data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void addFirst(Integer data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(Integer data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void removeFirst() {
        if (head == null) {
            System.out.println("Список пуст!");
            return;
        }
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
    }

    public void removeLast() {
        if (tail == null) {
            System.out.println("Список пуст!");
            return;
        }
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
    }

    public void remove(Integer data) {
        if (head == null) {
            System.out.println("Список пуст!");
            return;
        }

        Node current = head;
        while (current != null && !current.data.equals(data)) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Элемент не найден.");
            return;
        }

        if (current == head) removeFirst();
        else if (current == tail) removeLast();
        else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
            size--;
        }
    }

    public boolean contains(Integer data) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(data)) return true;
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void display() {
        if (head == null) {
            System.out.println("Список пуст!");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public void add(int index, Integer data) {
        if (index < 0 || index > size) {
            System.out.println("Неверный индекс!");
            return;
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        if (index == size) {
            addLast(data);
            return;
        }

        Node newNode = new Node(data);
        Node current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        newNode.prev = current.prev;
        newNode.next = current;
        current.prev.next = newNode;
        current.prev = newNode;

        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Неверный индекс!");
            return;
        }

        if (index == 0) {
            removeFirst();
            return;
        }

        if (index == size - 1) {
            removeLast();
            return;
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
    }

    public Integer get(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Неверный индекс!");
            return null;
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    public void displayReverse() {
        if (tail == null) {
            System.out.println("Список пуст!");
            return;
        }
        Node current = tail;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.prev;
        }
        System.out.println();
    }

    public Integer getFirst() {
        return (head != null) ? head.data : null;
    }

    public Integer getLast() {
        return (tail != null) ? tail.data : null;
    }
}
