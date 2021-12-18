package LeetCode;

import java.util.*;

public class Problem_559_MaxDepth {
    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                for (Node child : node.children) {
                    queue.add(child);
                }
            }
        }
        return level;
    }

    public int maxDepth1(Node root) {
        if (root == null) {
            return 0;
        }
        LinkedList<Node> stack = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        LinkedList<Integer> level = new LinkedList<>();
        set.add(root); // 用 set 标记已经走过的分支
        stack.addLast(root);
        level.addLast(1); // 记录当前节点对应的深度
        int maxLevel = Integer.MIN_VALUE;
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            int curLevel = level.pollLast();
            if(!node.children.isEmpty()) {
                for (Node child : node.children) {
                    if(!set.contains(child)) {
                        stack.addLast(node);
                        stack.addLast(child);
                        level.addLast(curLevel);
                        level.addLast(curLevel+1);
                        set.add(child);
                        break;
                    }
                }
            } else {
                maxLevel =Math.max(maxLevel, curLevel);
            }
        }
        return maxLevel;
    }

    public int maxDepth2(Node root) {
        if (root == null) {
            return 0;
        }
        int maxDepeth = 1; /// root 至少一层
        for(Node node : root.children) {
            maxDepeth = Math.max(maxDepeth, maxDepth2(node) + 1);
        }
        return maxDepeth;
    }

    public void test() {
        Node root = new Node(1, new ArrayList<>());
        root.children.add(new Node(3, new ArrayList<>()));
        root.children.add(new Node(2, new ArrayList<>()));
        root.children.add(new Node(4, new ArrayList<>()));
        root.children.get(0).children.add(new Node(5, new ArrayList<>()));
        root.children.get(0).children.add(new Node(6, new ArrayList<>()));
        int res = maxDepth(root);
        System.out.println(res);
        int res1 = maxDepth1(root);
        System.out.println(res1);
        int res2 = maxDepth2(root);
        System.out.println(res2);
    }

    public static void main(String[] args) {
        Problem_559_MaxDepth sl = new Problem_559_MaxDepth();
        sl.test();
    }
}
