package LeetCode;

import java.util.ArrayList;
import java.util.List;

// TODO: 非递归的写法
// ref: https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/solution/gong-shui-san-xie-shu-de-sou-suo-yun-yon-pse1/

public class Problem_589_PreOrder {

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
    };

    public List<Integer> preorder(Node root) {
        if(root == null) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        process(root, ans);
        return ans;

    }

    private void process(Node root, List<Integer> ans) {
        if(root == null) {
            return;
        }
        ans.add(root.val);
        for(Node child : root.children) {
            process(child, ans);
        }
    }
}
