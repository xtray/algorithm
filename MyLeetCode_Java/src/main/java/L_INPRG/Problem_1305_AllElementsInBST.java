package L_INPRG;

import java.util.ArrayList;
import java.util.List;

public class Problem_1305_AllElementsInBST {

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

    // 笨办法: 先整理为中序遍历的有序数组, 然后合并两个有序数组
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {

        List<Integer> list1 = getOrderList(root1);
        List<Integer> list2 = getOrderList(root2);
        List<Integer> ans = new ArrayList<>();
        int idx1 = 0;
        int idx2 = 0;
        int size1 = list1.size();
        int size2 = list2.size();
        while (idx1 < size1 || idx2 < size2) {
            if(idx1 < size1 && idx2 < size2) {
                if(list1.get(idx1) < list2.get(idx2)) {
                    ans.add(list1.get(idx1++));
                } else {
                    ans.add(list2.get(idx2++));
                }
            } else if(idx1 == size1) {
                ans.add(list2.get(idx2++));
            } else {
                ans.add(list1.get(idx1++));
            }
        }
        return ans;
    }

    private List<Integer> getOrderList(TreeNode root1) {
        List<Integer> ans = new ArrayList<>();
        inOrder(root1, ans);
        return ans;
    }

    private void inOrder(TreeNode root, List<Integer> ans) {
        if(root == null) {
            return;
        }
        inOrder(root.left, ans);
        ans.add(root.val);
        inOrder(root.right, ans);
    }
}
