package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problem_429_LevelOrder {

    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
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

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                level.add(cur.val);
                for (Node next : cur.children) { // NOTE: children 为空时, 循环不会进入
                    queue.offer(next);
                }
            }
            res.add(level);
        }
        return res;
    }


    public static void main(String[] args) {
        Node root = new Node(1);
        List<Node> list = new ArrayList<>();
        list.add(new Node(3));
        list.add(new Node(2));
        list.add(new Node(4));
        root.children = list;
        List<Node> list2 = new ArrayList<>();
        list2.add(new Node(5));
        list2.add(new Node(6));
        list.get(0).children = list2;
        var ans = new Problem_429_LevelOrder().levelOrder(root);

    }

}
