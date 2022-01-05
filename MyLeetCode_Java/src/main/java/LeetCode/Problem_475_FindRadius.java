package LeetCode;

import java.util.Arrays;

// https://leetcode-cn.com/problems/heaters/
public class Problem_475_FindRadius {
    public int findRadius(int[] houses, int[] heaters) {
        // 先对房子跟供暖点排序
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int ans = 0;
        // 找每一个房子的最优供暖点
        for (int i = 0, j = 0; i < houses.length; i++) {
            // j是不是此时 i 号房子的最优供暖点, 不是的话,j++
            while (!best(houses, heaters, i, j)) {
                j++;
            }
            ans = Math.max(ans, Math.abs(heaters[j] - houses[i]));
        }
        return ans;
    }

    // houses[i]由 heaters[j]供暖是最优的吗?
    // houses[i]由 heaters[j]供暖, 半径 a
    // houses[i]由 heaters[j+1]供暖, 半径 b
    // 如果 a<b, 说明当前最优是 j, 不需要跳下一个位置
    // 如果 a>=b, 说明当前最优是 j+1, 需要跳下一个位置
    private boolean best(int[] houses, int[] heaters, int i, int j) {
        return j == heaters.length -1|| // 已经到了最后一个供暖点了, 没得选择了, 必然是最优
                Math.abs(heaters[j] - houses[i]) < Math.abs(heaters[j + 1] - houses[i]); // 相等的时候要跳
    }

    public static void main(String[] args) {
        int[] houses = new int[]{1,2,3};
        int[] heaters = new int[]{2};
        int ans = new Problem_475_FindRadius().findRadius(houses,heaters);
        System.out.println(ans);
    }

}
