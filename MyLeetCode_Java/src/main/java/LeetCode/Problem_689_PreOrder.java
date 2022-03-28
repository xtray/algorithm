package LeetCode;

import java.util.*;

public class Problem_689_PreOrder {
    public static class Node {
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

    // 递归写法
    public List<Integer> preorder(Node root) {
        if(root == null) {
            return new ArrayList<>();
        }

        List<Integer> ans = new ArrayList<>();
        process2(root, ans);
        return ans;
    }

    private void process(Node root, List<Integer> ans) {
        if(root == null) {
            return;
        }
        ans.add(root.val);
        for(Node next : root.children) {
            process(next, ans);
        }
    }


    private void process2(Node root, List<Integer> ans) {
        if(root == null) {
            return;
        }
        LinkedList<Node> stack = new LinkedList<>();
        stack.addLast(root);
        Set<Node> set = new HashSet<>();
        while (!stack.isEmpty()) {
            Node cur = stack.pollLast();
            if(!set.contains(cur)) { // 第一次弹出的时候加结果
                ans.add(cur.val);
                set.add(cur);
            }
            for(Node next : cur.children) {
                if(!set.contains(next)) { // 只走没走过的分支
                    stack.addLast(cur); // 父节点压回去
                    stack.addLast(next);
                    break;
                }
            }
        }
    }


    // IMP: 标准的前序遍历写法
    // 弹出就打印, 倒着添加!
    public List<Integer> preorder3(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<Node> stack = new ArrayDeque<Node>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            res.add(node.val);
            for (int i = node.children.size() - 1; i >= 0; --i) {
                stack.push(node.children.get(i));
            }
        }
        return res;
    }


    public static void main(String[] args) {
        Node root = new Node(1, new ArrayList<>());
        root.children.add(new Node(3, new ArrayList<>()));
        root.children.add(new Node(2, new ArrayList<>()));
        root.children.add(new Node(4, new ArrayList<>()));
        root.children.get(0).children.add(new Node(5, new ArrayList<>()));
        root.children.get(0).children.add(new Node(6, new ArrayList<>()));
        var ans = new Problem_689_PreOrder().preorder(root);
        for(int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

}
