package LeetCode.MianshiJindian;


public class Problem_0402_MinBST {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return process(nums, 0, nums.length - 1);
    }

    // L..R组成BST, 返回头
    private static TreeNode process(int[] nums, int L, int R) {
        if (L > R) {
            return null;
        }
        if (L == R) {
            return new TreeNode(nums[L]);
        }
        int mid = L + ((R - L) >> 1);
        TreeNode head = new TreeNode(nums[mid]);
        head.left = process(nums, L, mid - 1);
        head.right = process(nums, mid + 1, R);
        return head;
    }

    public static void main(String[] args) {
        int[] nums = {-10,-3,0,5,9};
        var ans = sortedArrayToBST(nums);
        System.out.println();
    }

}
