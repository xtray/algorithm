// package LeetCode.MianshiJindian;
//
// public class Problem_1712_BiNode {
//     public static class TreeNode {
//         int val;
//         TreeNode left;
//         TreeNode right;
//
//         TreeNode(int x) {
//             val = x;
//         }
//     }
//
//     public TreeNode convertBiNode(TreeNode root) {
//         if(root == null) {
//             return root;
//         }
//         TreeNode dummyHead = new TreeNode(-1);
//         process(root, dummyHead);
//         return dummyHead.right;
//     }
//
//     private void process(TreeNode root, TreeNode dummyHead) {
//         if(root == null) {
//             return;
//         }
//         root.left = null;
//         process(root.right, dummyHead);
//         dummyHead.right
//         process(root.right, dummyHead);
//     }
//
//
// }
