package LeetCode.Contest.DW76;

import java.util.ArrayList;
import java.util.List;

public class Problem_6060_ClosetZero {
    public int findClosestNumber(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        list.sort((o1, o2) -> Math.abs(o1) == Math.abs(o2) ? o1 - o2 : Math.abs(o1) - Math.abs(o2));
        int min = list.get(0);
        // 找距离 <= min的最右侧
        int L = 0;
        int R = list.size() - 1;
        int ans = min;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (Math.abs(list.get(mid)) <= Math.abs(min)) {
                ans = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return list.get(ans);
    }

    public static void main(String[] args) {
        // int[] nums = {-4, -2, 1, 4,1 8};
        // int[] nums = {0};
        // int[] nums = {2, -1, 1};
        int[] nums = {2,1,1,-1,100000};
        var ans = new Problem_6060_ClosetZero().findClosestNumber(nums);
        System.out.println(ans);
    }
}
