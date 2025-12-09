public class Main {
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        list.addFirst(3);
        list.addFirst(2);
        list.addLast(4);
        list.addLast(5);

        System.out.print("Список: ");
        list.display();

        System.out.println("Есть ли число 3 - " + list.contains(3));
        System.out.println("Размер: " + list.size());

        list.removeFirst();
        list.removeLast();
        list.remove(3);

        System.out.print("После удаления: ");
        list.display();

        System.out.println("Пустой ли список - " + list.isEmpty());
        list.clear();
        System.out.println("После очистки пустой - " + list.isEmpty());
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

class SinglyLinkedList {
    private Node head;
    private int size;

    public SinglyLinkedList() {
        head = null;
        size = 0;
    }

    public void addFirst(Integer data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addLast(Integer data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public void removeFirst() {
        if (head == null) {
            System.out.println("Список пуст!");
            return;
        }
        head = head.next;
        size--;
    }

    public void removeLast() {
        if (head == null) {
            System.out.println("Список пуст!");
            return;
        }
        if (head.next == null) {
            head = null;
        } else {
            Node current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
        }
        size--;
    }

    public void remove(Integer data) {
        if (head == null) {
            System.out.println("Список пуст!");
            return;
        }
        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return;
        }
        Node current = head;
        while (current.next != null && !current.next.data.equals(data)) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
            size--;
        } else {
            System.out.println("Элемент не найден.");
        }
    }

    public boolean contains(Integer data) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
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
        head = null;
        size = 0;
    }
}
