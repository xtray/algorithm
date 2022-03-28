package L_INPRG;

import java.util.LinkedList;

public class Problem_456_Find132Pattern {

    // 单调栈
    // 找比自己小的
    // 栈底最小
    // 栈底到栈顶由小到大
    public boolean find132pattern(int[] nums) {
        if(nums == null || nums.length <3) {
            return false;
        }
        int N = nums.length;
        int minVal = nums[0];
        LinkedList<Integer> stack = new LinkedList<>();
        for(int i = N -1; i>=0; i-- ) {


            while (!stack.isEmpty()&&nums[i] > stack.peekFirst()) {

                stack.pollFirst();
            }
            stack.addFirst(nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {3,5,0,3,4};
        var ans = new Problem_456_Find132Pattern().find132pattern(nums);
        System.out.println(ans);
    }
}
