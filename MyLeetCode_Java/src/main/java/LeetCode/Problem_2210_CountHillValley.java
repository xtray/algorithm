package LeetCode;

import java.util.LinkedList;

public class Problem_2210_CountHillValley {

    public int countHillValley(int[] nums) {
        if(nums == null || nums.length <=2) {
            return 0;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        stack.addLast(nums[0]);
        int ans = 0;
        for(int i = 1; i + 1< nums.length;i++) {
            int curNum = nums[i];
            int nextNum = nums[i+1];
            if(stack.isEmpty()) {
                stack.addLast(curNum);
                continue;
            }
            int preNum = stack.peekLast(); // 前一个数
            if(preNum < curNum && curNum > nextNum) {
                ans++;
                stack.addLast(curNum);
                continue;
            } else if (preNum > curNum && curNum < nextNum) {
                ans++;
                stack.addLast(curNum);
                continue;
            }
            while (!stack.isEmpty() && stack.peekLast() == curNum) {
                stack.pollLast();
            }
            if(stack.isEmpty()) {
                stack.addLast(curNum);
                continue;
            }
            preNum = stack.peekLast();
            if(preNum < curNum && curNum > nextNum) {
                ans++;
                stack.addLast(curNum);
                continue;
            } else if (preNum > curNum && curNum < nextNum) {
                ans++;
                stack.addLast(curNum);
                continue;
            }
            stack.addLast(curNum);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {2,4,1,1,6,5}; // 3
        // int[] nums = {6,6,5,5,4,1}; // 0
        // int[] nums = {8,2,5,7,7,2,10,3,6,2}; //6
        var ans = new Problem_2210_CountHillValley().countHillValley(nums);
        System.out.println(ans);
    }
}
