package LeetCode.Jianzhi;

import java.util.ArrayList;
import java.util.List;

public class Problem_JZ54_KthLargest {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int kthLargest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        return list.get(list.size() - k);
    }


    // 无序链表第K小
    public int kthLargest2(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        return process(list, 0, list.size() - 1, list.size() - k);
    }

    // 找到数组中的第下标k小
    private int process(List<Integer> numList, int L, int R, int index) {
        if (L == R) {
            return numList.get(L);
        }
        int pivot = numList.get(L + (int) (Math.random() * (R - L + 1)));
        int[] range = partition(numList, L, R, pivot);
        if (index > range[1]) {
            process(numList, range[1] + 1, R, index);
        } else if (index < range[0]) {
            process(numList, L, range[0] - 1, index);
        }
        return numList.get(index);
    }

    private int[] partition(List<Integer> list, int l, int r, int pivot) {
        int less = l - 1;
        int more = r + 1;
        int cur = l;
        while (cur < more) {
            if (list.get(cur) < pivot) {
                swap(list, cur++, ++less);
            } else if (list.get(cur) > pivot) {
                swap(list, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    private static void swap(List<Integer> list, int i, int j) {
        int tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    private void inOrder(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        inOrder(root.left, ans);
        ans.add(root.val);
        inOrder(root.right, ans);
    }

    public static void main(String[] args) {
        // TreeNode root = new TreeNode(3);
        // root.left = new TreeNode(1);
        // root.right = new TreeNode(4);
        // root.left.right = new TreeNode(2);
        // int k = 1;
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(1);
        int k = 1;
        var ans = new Problem_JZ54_KthLargest().kthLargest(root, k);
        System.out.println(ans);
    }
}
