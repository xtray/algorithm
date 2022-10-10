package _DailyTarget;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class Problem_496_NextGreaterElement {


    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int N = nums1.length;
        int[] ans = new int[N];
        Map<Integer, Integer> map = new HashMap<>();
        // 栈底最大 -->小, 当前大出栈
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int M = nums2.length;
        for (int i = 0; i < M; i++) {
            while (!stack.isEmpty() && nums2[i] > nums2[stack.peekLast()]) {
                int top = stack.pollLast();
                map.put(nums2[top], nums2[i]);
            }
            stack.addLast(i);
        }
        while (!stack.isEmpty()) {
            int top = stack.pollLast();
            map.put(nums2[top], -1);
        }
        int idx = 0;
        for (int num : nums1) {
            ans[idx++] = map.get(num);
        }
        return ans;
    }

    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        int N = nums1.length;
        int M = nums2.length;
        int[] ans = new int[N];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        // map里存比当数大的下一个数
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && num > stack.peekLast()) {
                map.put(stack.pollLast(), num);
            }
            stack.addLast(num);
        }
        // while (!stack.isEmpty()) { //栈里的元素单独结算
        //     map.put(stack.peekFirst(), -1);
        //     stack.pollFirst();
        // }
        int idx = 0;
        for (int num : nums1) {
            ans[idx++] = map.getOrDefault(num, -1);
        }
        return ans;
    }
}
