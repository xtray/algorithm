package LeetCode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/contest/weekly-contest-290/problems/intersection-of-multiple-arrays/
// 6041. 多个数组求交集
public class Problem_2248_Intersection {

    public List<Integer> intersection(int[][] nums) {
        int dup = nums.length;
        int[] cnt = new int[1001];
        for (int[] num : nums) {
            for (int n : num) {
                cnt[n]++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            if (cnt[i] == dup) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[][] nums = {{3,1,2,4,5},{1,2,3,4},{3,4,5,6}};
        int[][] nums = {{7, 34, 45, 10, 12, 27, 13}, {27, 21, 45, 10, 12, 13}};
        var ans = new Problem_2248_Intersection().intersection(nums);
        for (int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
