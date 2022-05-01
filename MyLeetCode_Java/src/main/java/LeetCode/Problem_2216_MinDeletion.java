package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_2216_MinDeletion {

    // NOTE: 一对一对检查数组, 如果不相等就跳一步, 相等跳两步
    public int minDeletion(int[] nums) {
        int count = 0;
        int N = nums.length;
        int i = 0;
        while (i < N - 1) {
            if (nums[i] != nums[i + 1]) {
                i += 2;
            } else {
                i += 1;
                count++;
            }
        }
        if( i == N -1) {
            count++;
        }
        return count;
    }
}
