package L_INPRG;

import java.util.LinkedList;
import java.util.List;

public class Problem_907_SumSubArrayMins_P {

    // 单调栈
    // 以每一个数作为最小值的所有子数组, 找所有左边, 右边比自己小的数
    // 栈底最小, 栈底到栈顶由小到大
    public int sumSubarrayMins(int[] arr) {
        if(arr == null || arr.length ==0) {
            return 0;
        }
        // 有重复值, stack里每一项是一个list
        LinkedList<List<Integer>> stack = new LinkedList<>();
        int sum = 0;
        for(int i = 0; i< arr.length; i++) {
            while (!stack.isEmpty() && stack.peek().get(0) > arr[i] ) {
                // 弹出结算
                List<Integer> popItem = stack.pop();
                int left = stack.size();
            }
        }

        return 0;


    }
}
