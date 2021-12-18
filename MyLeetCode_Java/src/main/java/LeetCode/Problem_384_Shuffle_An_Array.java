package LeetCode;

import java.util.Arrays;

public class Problem_384_Shuffle_An_Array {

    public class Solution {
        private int[] origNums;
        private int[] shuffNums;
        private int N;

        public Solution(int[] nums) {
            N = nums.length;
            origNums = nums;
            shuffNums = new int[N];
            for(int i=0;i<N;i++) {
                shuffNums[i] = nums[i];
            }
        }

        public int[] reset() {
            return origNums;
        }

        // 每一个数都均等的随机到每一个位置上去
        // 第一次: 0~N-1 上随机一个数跟 N-1 位置交换
        // 第一次: 0~N-2 上随机一个数跟 N-2 位置交换
        // 第一次: 0~N-3 上随机一个数跟 N-3 位置交换
        public int[] shuffle() {
            for(int i=N-1;i>=0;i--) { // 每一回需要交换到的位置
                int index = (int)(Math.random()*(i+1));
                int tmp = shuffNums[index];
                shuffNums[index] = shuffNums[i];
                shuffNums[i] = tmp;
            }
            return shuffNums;

        }
    }


}
