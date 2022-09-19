package LeetCode;// package _Contest.LC.W311;
//
// import java.util.ArrayDeque;
// import java.util.List;
// import java.util.*;
//
// public class Problem_6182_ReverseBT {
//
//     public static class TreeNode {
//         int val;
//         TreeNode left;
//         TreeNode right;
//
//         TreeNode() {
//         }
//
//         TreeNode(int val) {
//             this.val = val;
//         }
//
//         TreeNode(int val, TreeNode left, TreeNode right) {
//             this.val = val;
//             this.left = left;
//             this.right = right;
//         }
//     }
//
//     // 奇数层把数值反向覆盖一遍
//     public TreeNode reverseOddLevels(TreeNode root) {
//         if (root == null) return null;
//
//         ArrayDeque<TreeNode> queue = new ArrayDeque<>();
//         queue.addLast(root);
//         boolean odd = false;
//         while (!queue.isEmpty()) {
//             int size = queue.size();
//             ArrayDeque<TreeNode> oddNodes = new ArrayDeque<>(); // 收集奇数层的节点
//             for (int i = 0; i < size; i++) {
//                 TreeNode cur = queue.poll();
//                 if (odd) {
//                     oddNodes.addLast(cur);
//                 }
//                 if (cur.left != null) {
//                     queue.add(cur.left);
//                 }
//                 if (cur.right != null) {
//                     queue.add(cur.right);
//                 }
//             }
//             while (!oddNodes.isEmpty()) {
//                 TreeNode front = oddNodes.pollFirst();
//                 TreeNode end = oddNodes.pollLast();
//                 int tmp = front.val;
//                 front.val = end.val;
//                 end.val = tmp;
//             }
//
//             odd = !odd;
//         }
//         return root;
//     }
//
//     // DFS
//     public TreeNode reverseOddLevels1(TreeNode root) {
//         if (root == null) return null;
//         process(root.left, root.right, 1);
//         return root;
//     }
//
//     private void process(TreeNode left, TreeNode right, int level) {
//         if (left == null) return;
//         if ((level & 1) != 0) {
//             int tmp = left.val;
//             left.val = right.val;
//             right.val = tmp;
//         }
//         process(left.left, right.right, level + 1);
//         process(left.right, right.left, level + 1);
//     }
//
//     // 调整指针的做法
//     public TreeNode reverseOddLevels2(TreeNode root) {
//         if (root == null) return null;
//
//         ArrayDeque<TreeNode> faQueue = new ArrayDeque<>();
//         faQueue.addLast(root);
//         boolean odd = false;
//         while (!faQueue.isEmpty()) {
//
//
//         }
//     }
// }
