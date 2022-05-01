package LeetCode.Contest.LCUP2022;

import java.util.Arrays;

public class Problem_002_PerfectMenu {


    // 从左往右的尝试模型, 尝试每一个料理是否制作
    public int perfectMenu(int[] materials, int[][] cookbooks, int[][] attribute, int limit) {
        process(materials, cookbooks, attribute, limit, 0, 0, 0);
        return maxOishi != Integer.MIN_VALUE ? maxOishi : -1;
    }

    private int maxOishi = Integer.MIN_VALUE;

    // 从attribute index 号食物制作出发, 返回材料够用的情况下, 获得的最大美味度
    // preOishi: 之前获得的美味度
    // preStomache: 之前的饱食度
    private void process(int[] materials, int[][] cookbooks, int[][] attribute, int limit, int preOishi, int preStomache, int index) {
        if (index == attribute.length) {
            if (preStomache >= limit) {
                maxOishi = Math.max(maxOishi, preOishi);
            }
            return;
        }
        // 没到最后, 还有的可以选
        // 可能性1: 选择不制作index
        process(materials, cookbooks, attribute, limit, preOishi, preStomache, index + 1);

        // 可能性2: 制作index号实物
        if (check(materials, cookbooks, index)) { // 检查是否可以制作
            int[] leftMaterials = getLeft(materials, cookbooks, index);
            process(leftMaterials, cookbooks, attribute, limit, preOishi + attribute[index][0], preStomache + attribute[index][1], index + 1);
        }
    }

    private int[] getLeft(int[] materials, int[][] cookbooks, int index) {
        int[] left = Arrays.copyOf(materials, materials.length);
        for (int i = 0; i < materials.length; i++) {
            left[i] -= cookbooks[index][i];
        }
        return left;
    }

    // 检查是否可以制作
    private boolean check(int[] materials, int[][] cookbooks, int index) {
        for (int i = 0; i < materials.length; i++) {
            if (materials[i] < cookbooks[index][i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // int[] meterials = {3,2,4,1,2};
        // int[][] cookbooks = {{1,1,0,1,2},{2,1,4,0,0},{3,2,4,1,0}};
        // int[][] attribute = {{3,2},{2,4},{7,6}};
        // int limit = 5;
        int[] meterials = {10, 10, 10, 10, 10};
        int[][] cookbooks = {{1, 1, 1, 1, 1}, {3, 3, 3, 3, 3}, {10, 10, 10, 10, 10}};
        int[][] attribute = {{5, 5}, {6, 6}, {10, 10}};
        int limit = 1;

        var ans = new Problem_002_PerfectMenu().perfectMenu(meterials, cookbooks, attribute, limit);
        System.out.println(ans);
    }
}
