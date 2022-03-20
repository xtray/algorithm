package _Misc;

import java.util.*;

public class MinBTPath {

    /**
     * 二叉树存储在一维数组中, 根节点数存在下
     * 标为1数组中, 第n个节点, 左子节点存在下
     * 标为2n中, 右节点存在下标为2n+1中。没有
     * 的节点用-1表示。比如下图存储在数组中
     * 【5, 6, 7, -1, -1, 2, 9】
     * <p>
     * 给定一个二叉树数组, 从下标1开始, 二叉
     * 树不超过7层。求根节点到最小叶子节点所
     * 有节点。比如下图结果为572
     */

    // 复杂度 O(N)
    List<Integer> minPath(int[] arr) {
        // 数组 0 位置弃而不用, 下标从 1 开始
        if (arr == null || arr.length <= 1) {
            return new ArrayList<>();
        }
        // 老老实实遍历数组每一个元素判断是不是叶子节点:
        //   1.左子树下标越界
        //   2.左右子树下标都没越界, 且值都是-1
        int N = arr.length - 1; // 实际元素个数, 0 位置弃而不用
        int min = Integer.MAX_VALUE;
        int minPos = -1;
        for (int i = 1; i <= N; i++) {
            if (arr[i] == -1) continue;
            if (2 * i > N || // 左子树下标越界
                    (2 * i + 1 <= N && arr[i << 1] == -1 && arr[i << 1 | 1] == -1)) {
                // int[值, 位置]
                if (arr[i] < min) {
                    min = arr[i];
                    minPos = i;
                }
            }
        }

        // 输出答案
        List<Integer> ans = new ArrayList<>();
        ans.add(min);
        int pos = minPos;
        while (pos != 1) {
            pos >>= 1;
            ans.add(0, arr[pos]);
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] arr = new int[]{0, 5, 6, 7, -1, -1, 2, 9};
        // int[] arr = new int[]{0, 5, 6, 7, 2};
        // int[] arr = new int[]{0, 1, 2, -1, 4, -1, -1, -1, 8};
        int[] arr = new int[]{0, 5};
        var ans = new MinBTPath().minPath(arr);
        for (int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
