import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int value) {
        data = value;
        left = right = null;
    }
}

class BinaryTree {
    Node root;

    BinaryTree() {
        root = null;
    }

    BinaryTree(int value) {
        root = new Node(value);
    }

    List<Integer> preOrder() {
        List<Integer> result = new ArrayList<>();
        preOrder(root, result);
        return result;
    }

    private void preOrder(Node node, List<Integer> result) {
        if (node == null) return;
        result.add(node.data);
        preOrder(node.left, result);
        preOrder(node.right, result);
    }

    List<Integer> inOrder() {
        List<Integer> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(Node node, List<Integer> result) {
        if (node == null) return;
        inOrder(node.left, result);
        result.add(node.data);
        inOrder(node.right, result);
    }

    List<Integer> postOrder() {
        List<Integer> result = new ArrayList<>();
        postOrder(root, result);
        return result;
    }

    private void postOrder(Node node, List<Integer> result) {
        if (node == null) return;
        postOrder(node.left, result);
        postOrder(node.right, result);
        result.add(node.data);
    }

    List<List<Integer>> levelOrder() {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                level.add(current.data);

                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            result.add(level);
        }
        return result;
    }

    int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return -1;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }


    void buildFullBinaryTree() {
        root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right.left = new Node(6);
        root.right.right = new Node(7);

        root.left.left.left = new Node(8);
        root.left.left.right = new Node(9);

        root.left.right.left = new Node(10);
        root.left.right.right = new Node(11);
    }

    // Проверка, является ли дерево полным
    boolean isFull() {
        return isFull(root);
    }

    private boolean isFull(Node node) {
        if (node == null) return true;

        if (node.left == null && node.right == null) {
            return true;
        }

        if (node.left != null && node.right != null) {
            return isFull(node.left) && isFull(node.right);
        }

        return false;
    }


    void insertBST(int data) {
        root = insertBST(root, data);
    }

    private Node insertBST(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }

        if (data < node.data) {
            node.left = insertBST(node.left, data);
        } else if (data > node.data) {
            node.right = insertBST(node.right, data);
        }

        return node;
    }

    void buildBST(int[] values) {
        for (int value : values) {
            insertBST(value);
        }
    }

    boolean searchBST(int key) {
        return searchBST(root, key);
    }

    private boolean searchBST(Node node, int key) {
        if (node == null) return false;

        if (key == node.data) return true;

        if (key < node.data) {
            return searchBST(node.left, key);
        } else {
            return searchBST(node.right, key);
        }
    }


    void buildBalancedFromSortedArray(int[] sortedArray) {
        Arrays.sort(sortedArray);
        root = buildBalancedFromSortedArray(sortedArray, 0, sortedArray.length - 1);
    }

    private Node buildBalancedFromSortedArray(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        Node node = new Node(arr[mid]);

        node.left = buildBalancedFromSortedArray(arr, start, mid - 1);
        node.right = buildBalancedFromSortedArray(arr, mid + 1, end);

        return node;
    }

    boolean isBalanced() {
        return isBalanced(root) != -1;
    }

    private int isBalanced(Node node) {
        if (node == null) return 0;

        int leftHeight = isBalanced(node.left);
        if (leftHeight == -1) return -1;

        int rightHeight = isBalanced(node.right);
        if (rightHeight == -1) return -1;

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }


    void insertLevelOrder(int data) {
        if (root == null) {
            root = new Node(data);
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.left == null) {
                current.left = new Node(data);
                break;
            } else {
                queue.add(current.left);
            }

            if (current.right == null) {
                current.right = new Node(data);
                break;
            } else {
                queue.add(current.right);
            }
        }
    }

    void delete(int key) {
        if (root == null) return;

        if (root.left == null && root.right == null) {
            if (root.data == key) {
                root = null;
            }
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        Node keyNode = null;
        Node lastNode = null;
        Node parentOfLast = null;

        while (!queue.isEmpty()) {
            lastNode = queue.poll();

            if (lastNode.data == key) {
                keyNode = lastNode;
            }

            if (lastNode.left != null) {
                parentOfLast = lastNode;
                queue.add(lastNode.left);
            }

            if (lastNode.right != null) {
                parentOfLast = lastNode;
                queue.add(lastNode.right);
            }
        }

        if (keyNode != null && lastNode != null) {

            keyNode.data = lastNode.data;


            if (parentOfLast != null) {
                if (parentOfLast.right == lastNode) {
                    parentOfLast.right = null;
                } else if (parentOfLast.left == lastNode) {
                    parentOfLast.left = null;
                }
            }
        }
    }


    void printTree() {
        printTree(root, "", true);
    }

    private void printTree(Node node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.data);
            printTree(node.left, prefix + (isLeft ? "│   " : "    "), true);
            printTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    }
}

public class BinaryTrees {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        System.out.println("1. БАЗОВЫЕ МЕТОДЫ ОБХОДА");

        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        System.out.println("Preorder: " + tree.preOrder());
        System.out.println("Inorder: " + tree.inOrder());
        System.out.println("Postorder: " + tree.postOrder());
        System.out.println("Level Order: " + tree.levelOrder());
        System.out.println("Высота дерева: " + tree.height());

        System.out.println("\n2. ПОЛНОЕ БИНАРНОЕ ДЕРЕВО");
        BinaryTree fullTree = new BinaryTree();
        fullTree.buildFullBinaryTree();
        System.out.println("Является ли полным: " + fullTree.isFull());
        System.out.println("Высота полного дерева: " + fullTree.height());

        System.out.println("\n3. БИНАРНОЕ ДЕРЕВО ПОИСКА (BST)");
        BinaryTree bst = new BinaryTree();
        int[] bstValues = {50, 30, 70, 20, 40, 60, 80};
        bst.buildBST(bstValues);
        System.out.println("Inorder обход BST (должен быть отсортирован): " + bst.inOrder());
        System.out.println("Поиск 40 в BST: " + bst.searchBST(40));
        System.out.println("Поиск 100 в BST: " + bst.searchBST(100));

        System.out.println("\n4. СБАЛАНСИРОВАННОЕ ДЕРЕВО");
        BinaryTree balancedTree = new BinaryTree();
        int[] sortedArray = {10, 20, 30, 40, 50, 60, 70};
        balancedTree.buildBalancedFromSortedArray(sortedArray);
        System.out.println("Inorder обход сбалансированного дерева: " + balancedTree.inOrder());
        System.out.println("Является ли сбалансированным: " + balancedTree.isBalanced());

        System.out.println("\n5. БАЗОВЫЕ ОПЕРАЦИИ (Level Order Strategy)");
        BinaryTree levelOrderTree = new BinaryTree(10);
        levelOrderTree.insertLevelOrder(20);
        levelOrderTree.insertLevelOrder(30);
        levelOrderTree.insertLevelOrder(40);
        levelOrderTree.insertLevelOrder(50);

        System.out.println("Дерево до удаления:");
        levelOrderTree.printTree();
        System.out.println("Level Order: " + levelOrderTree.levelOrder());

        levelOrderTree.delete(20);
        System.out.println("\nДерево после удаления 20:");
        levelOrderTree.printTree();
        System.out.println("Level Order: " + levelOrderTree.levelOrder());

        System.out.println("\n=== СРАВНЕНИЕ РЕЗУЛЬТАТОВ ===");
        System.out.println("Высота BST: " + bst.height());
        System.out.println("Высота сбалансированного дерева: " + balancedTree.height());
        System.out.println("Высота Level Order дерева: " + levelOrderTree.height());
    }
}
