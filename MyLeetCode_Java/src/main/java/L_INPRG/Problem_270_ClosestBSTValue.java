package L_INPRG;

import java.util.ArrayList;
import java.util.List;


// IMP: 重要的二分题目


public class Problem_270_ClosestBSTValue {
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

    // 二分法
    public int closestValue(TreeNode root, double target) {
        int closest = root.val; // 假定的值
        TreeNode node = root;
        while (node != null) {
            int val = node.val;
            // 当前来到node节点, 尝试更新closest
            closest = Math.abs(val - target) < Math.abs(closest - target) ? val : closest;
            // 左右两侧二分
            node = target < node.val ? node.left : node.right;
        }
        return closest;
    }

    // 构建中序遍历序列, 在有序序列里查找target
    public void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) {
            return;
        }
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    public int closestValue2(TreeNode root, double target) {
        List<Integer> numList = new ArrayList<>();
        inorder(root, numList); // 构建有序的中序序列
        // return Collections.min(numList, (o1, o2) -> Math.abs(o1 - target) < Math.abs(o2 - target) ? -1 : 1);
        // 在有序序列numList里找离target最近的数
        // >= target最左的数
        int L = 0;
        int R = numList.size();
        int left = -1;
        int right = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (numList.get(mid) >= target) {
                left = mid;
                R = mid - 1;
            } else { // <
                L = mid + 1;
            }
        }
        // <= target最右的数
        L = 0;
        R = numList.size();
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (numList.get(mid) <= target) {
                right = mid;
                L = mid + 1;
            } else { // >
                R = mid - 1;
            }
        }
        return Math.abs(numList.get(left) - target) < Math.abs(numList.get(right) - target) ? numList.get(left): numList.get(right);
    }

    // [1]
    // 4.428571

}
