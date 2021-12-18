package _BasicConcept;

public class MorrisTraversal {

    public class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int val) {
            value = val;
        }
    }

    public static void process(Node root) {
        if (root == null) {
            return;
        }
        // 1
        process(root.left);
        // 2
        process(root.right);
        // 3
    }

    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                // 找到左树最右节点
                while (mostRight.right != null && mostRight.right != cur ){
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }

    // 中序遍历
    // 对于能回到自己两次的节点，你在第二次到它时候处理,
    // 对于只会到达自己一次的节点你直接处理就是中序
    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                // 找到左树最右节点
                while (mostRight.right != null && mostRight.right != cur ){
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            System.out.println(cur.value);
            cur = cur.right;
        }
    }
}

