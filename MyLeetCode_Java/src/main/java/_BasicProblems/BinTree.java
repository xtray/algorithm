package _BasicProblems;

import java.util.LinkedList;

public class BinTree {

    public static class Node {
        public int val;
        public Node left;
        public Node right;

        Node(int v) {
            val = v;
        }
    }

    public void preRecursive(Node head) {
        if (head == null) {
            return;
        }
        pre(head);
    }

    private void pre(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.val + " ");
        pre(head.left);
        pre(head.right);
    }

    public static void preNonRecursive(Node head) {
        if (head == null) {
            return;
        }
        LinkedList<Node> stack = new LinkedList<>();
        stack.addFirst(head);
        while (!stack.isEmpty()) {
            Node node = stack.pollFirst();
            System.out.print(node.val + " ");
            if (node.right != null) {
                stack.addFirst(node.right);
            }
            if (node.left != null) {
                stack.addFirst(node.left);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.left.left = new Node(3);
        head.left.right = new Node(4);
        head.right = new Node(5);
        head.right.left = new Node(6);
        preNonRecursive(head);
    }
}
