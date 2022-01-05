package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problem_428_Codec {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
            children = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<>();
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


    class Codec {
        // Encodes a tree to a single string.
        // [1,[3,[5,6]],2,4]]
        public String serialize(Node root) {
            if (root == null) {
                return "#";
            }
            StringBuilder sb = new StringBuilder();
            serial(sb, root);
            return sb.toString();
        }

        //当前来到 head
        private void serial(StringBuilder sb, Node head) {
            sb.append(head.val + ",");
            if (!head.children.isEmpty()) { // 如果有孩子
                sb.append("[,"); // 确保用,号分割所有元素
                for (Node next : head.children) {
                    serial(sb, next);
                }
                sb.append("],");

            }
        }

        // Decodes your encoded data to tree.
        public Node deserialize(String data) {
            if (data.equals("#")) {
                return null;
            }
            String[] splits = data.split(",");
            Queue<String> queue = new LinkedList<>();
            for (String str : splits) {
                queue.offer(str);
            }
            return deserial(queue);
        }

        private Node deserial(Queue<String> queue) {
            Node cur = new Node(Integer.valueOf(queue.poll()));
            cur.children = new ArrayList<>();
            if (!queue.isEmpty() && queue.peek().equals("[")) { // 有孩子
                queue.poll(); //弹出[
                while (!queue.peek().equals("]")) { // 处理所有孩子节点
                    Node child = deserial(queue);
                    cur.children.add(child);
                }
                queue.poll(); //弹出]
            }
            return cur;
        }

        public void test() {
            Node a = new Node(1);
            Node b = new Node(2);
            Node c = new Node(3);
            Node d = new Node(4);
            Node e = new Node(5);
            Node f = new Node(6);
            Node g = new Node(7);
            a.children.add(b);
            a.children.add(c);
            a.children.add(d);
            b.children.add(e);
            b.children.add(f);
            e.children.add(g);
            String content = new Codec().serialize(a);
            System.out.println(content);
            Node head = new Codec().deserialize(content);
            System.out.println(content.equals(new Codec().serialize(head)));
        }
    }

    public static void main(String[] args) {
        var sl = new Problem_428_Codec().new Codec();
        sl.test();
    }

}
