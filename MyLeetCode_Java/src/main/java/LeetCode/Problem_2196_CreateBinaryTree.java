package LeetCode;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem_2196_CreateBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public TreeNode createBinaryTree(int[][] descriptions) {
        if(descriptions == null || descriptions.length == 0 ||
           descriptions[0] == null || descriptions[0].length ==0) {
            return new TreeNode();
        }

        Set<Integer> set = new HashSet<>();
        // 哈希表用于记录已存在的值和对应的节点
        Map<Integer, TreeNode> map = new HashMap<>();

        // 遍历完成后, 所有节点都连起来了, 只是不知道谁是root
        for(int[] d : descriptions) {
            int p = d[0];
            int c = d[1];
            int isLeft = d[2];
            if (!map.containsKey(p) ) {
                map.put(p, new TreeNode(p));
            }
            if (!map.containsKey(c) ) {
                map.put(c, new TreeNode(c));
            }

            if(isLeft == 1) {
                map.get(p).left = map.get(c);
            } else {
                map.get(p).right = map.get(c);
            }
            set.add(c);
        }
        int head = 0;
        for(int key : map.keySet()) {
            if(!set.contains(key)) {
                head = key;
                break;
            }
        }
        return map.get(head);
    }

    public static void main(String[] args) {
        int[][] des = {{20,15,1},{20,17,0},{50,20,1},{50,80,0},{80,19,1}};
        var ans = new Problem_2196_CreateBinaryTree().createBinaryTree(des);

    }
}
