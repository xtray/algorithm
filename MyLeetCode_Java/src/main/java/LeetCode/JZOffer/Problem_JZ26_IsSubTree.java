package LeetCode.JZOffer;

// IMP: 二叉树递归套路不好做, 当一个特列, 记住!!!

public class Problem_JZ26_IsSubTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return process(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    // 以A开头的整棵树是否包含B
    boolean process(TreeNode A, TreeNode B) {
        if (B == null) { // 空树是任何Tree的子树
            return true;
        }
        // 如果A是空树, 不可能包括B(B非空)
        // 或者A, B节点值不一样
        if (A == null || A.val != B.val) {
            return false;
        }
        // 头相等, 对比子树
        // 去A的左树, 右树
        return process(A.left, B.left) && process(A.right, B.right);
    }

    // 二叉树递归套路, 下面错误
    // 以A为头的整棵树是否包含B

    // public static class Info {
    //     public boolean noHead;  // 不以X为头的子树是否包含
    //     public boolean headInclude; // 以X为头的子树是否包含
    //
    //     public Info(boolean no, boolean head) {
    //         noHead = no;
    //         headInclude = head;
    //     }
    // }
    //
    // public boolean isSubStructure2(TreeNode A, TreeNode B) {
    //     if (A == null || B == null) {
    //         return false;
    //     }
    //     return process2(A, B).noHead || process2(A, B).headInclude;
    // }
    //
    // private Info process2(TreeNode A, TreeNode B) {
    //     if (A == null) {
    //         return new Info(false, false);
    //     }
    //     if (B == null) {
    //         return new Info(false, true);
    //     }
    //     Info leftInfo = process2(A.left, B);
    //     Info rightInfo = process2(A.right, B);
    //     // 不以X为头是否包含, 左右有一个就行
    //     boolean noHead = leftInfo.noHead || rightInfo.noHead || leftInfo.headInclude || rightInfo.headInclude;
    //     // 必须以X为头
    //     boolean headInclude = true;
    //     if (A.val != B.val) {
    //         headInclude = false;
    //     }
    //     // A左树必须B的左树, A右树必须包含B的右树
    //     // 左树不包含或者右树不包含
    //     Info leftInfo2 = process2(A.left, B.left);
    //     Info rightInfo2 = process2(A.right, B.right);
    //     if(!leftInfo2.headInclude || !rightInfo2.headInclude) {
    //         headInclude = false;
    //     }
    //     return new Info(noHead, headInclude);
    // }


    // NOTE: 错误做法!!, 字符串序列化并不能解决此问题, 啥序都不行
    // 例子:
    // [10,12,6,8,3,11]
    // [10,12,6,8]
    // 代码返回false, 事实上是true
    public boolean isSubStructureWA(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        String aStr = preSerialize(A);
        String bStr = preSerialize(B);
        return aStr.contains(bStr);
    }

    private String preSerialize(TreeNode node) {
        if (node == null) {
            return "#";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(node.val);
        sb.append("_");
        sb.append(preSerialize(node.left));
        sb.append("_");
        sb.append(preSerialize(node.right));
        sb.append("_");
        return sb.toString();
    }

}
