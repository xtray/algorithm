package LeetCode;

import java.util.*;

public class Problem_590_PostOrder {
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) {
            return ans;
        }
        process2(root, ans);
        return ans;
    }

    private void process(Node root, List<Integer> ans) {
        if(root == null) {
            return;
        }
        for(Node next : root.children) {
            process(next, ans);
        }
        ans.add(root.val);
    }

    private void process2(Node root, List<Integer> ans) {
        if(root == null) {
            return;
        }
        LinkedList<Object[]> stack = new LinkedList<>();
        stack.addLast(new Object[]{0, root});
        while (!stack.isEmpty()) {
            Object[] obj = stack.pollLast();
            int cnt = (int)obj[0];
            Node curNode = (Node) obj[1];
            if(cnt == curNode.children.size()) {
                ans.add(curNode.val);
            } else {
                stack.addLast(new Object[]{cnt+1, curNode});
                stack.addLast(new Object[]{0, curNode.children.get(cnt)});
            }
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1, new ArrayList<>());
        root.children.add(new Node(3, new ArrayList<>()));
        root.children.add(new Node(2, new ArrayList<>()));
        root.children.add(new Node(4, new ArrayList<>()));
        root.children.get(0).children.add(new Node(5, new ArrayList<>()));
        root.children.get(0).children.add(new Node(6, new ArrayList<>()));
        var ans = new Problem_590_PostOrder().postorder(root);
        for(var num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
