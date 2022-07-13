package LeetCode;

// IMP: 重要基础题, 找后继节点

public class Problem_450_DeleteNodeInBST {
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

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else { // root.val == key
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode mostLeft = root.right;
            // 找右树上的最左节点
            while (mostLeft.left != null) {
                mostLeft = mostLeft.left;
            }
            mostLeft.left = root.left;
            return root.right;
        }
        return root;
    }

    public static TreeNode inorderSuccessor(TreeNode head, Integer key) {
        if (head == null) {
            return null;
        }
        TreeNode cur = head;
        TreeNode mostRight = null;
        TreeNode pre = null;
        TreeNode res = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            if (pre != null && pre.val == key) {
                res = cur;
            } else {
                pre = cur;
            }
            cur = cur.right;
        }
        return res;
    }


    private TreeNode findNode(TreeNode root, int key) {
        if (root.val == key) {
            return root;
        }
        TreeNode find = null;
        if (root.val < key && root.right != null) {
            find = findNode(root.right, key);
        }
        if (root.val > key && root.left != null) {
            find = findNode(root.left, key);
        }
        return find;
    }
}
