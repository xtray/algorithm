package _Exercise;

import java.util.*;

public class Problem_496_NextGreatElement {

    // 暴力解法 O(N*M)
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[]{};
        }
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = getUpper(nums1[i], nums2);
        }
        return ans;
    }

    private int getUpper(int num1, int[] nums2) {
        int idx = -1;
        for (int i = 0; i < nums2.length; i++) {
            if (idx == -1 && nums2[i] == num1) {
                idx = i;
            }
            if (idx != -1 && nums2[i] > num1) {
                return nums2[i];
            }
        }
        return -1;
    }

    // 单调栈
    public int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[]{};
        }
        int N = nums1.length;
        int M = nums2.length;
        int[] ans = new int[N];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < M; i++) {
            map.put(nums2[i], i);
        }
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        // 栈顶最大, 由大到小
        int[] nexts = new int[M];
        for (int i = 0; i < M; i++) {
            while (!stack.isEmpty() && nums2[i] > nums2[stack.peekLast()]) {
                int top = stack.pollLast();
                nexts[top] = nums2[i];
            }
            stack.addLast(i);
        }
        while (!stack.isEmpty()) {
            int top = stack.pollLast();
            nexts[top] = -1;
        }
        for (int i = 0; i < N; i++) {
            int pos = map.get(nums1[i]);
            ans[i] = nexts[pos];
        }
        return ans;
    }

    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[]{};
        }
        int N = nums1.length;
        int M = nums2.length;
        int[] ans = new int[N];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        // map里存比当数大的下一个数
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && num > stack.peekFirst()) {
                map.put(stack.peekFirst(), num);
                stack.pollFirst();
            }
            stack.addFirst(num);
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

    public static void main(String[] args) {
        // int[] nums1 = {4,1,2};
        // int[] nums2 = {1,3,4,2};
        int[] nums1 = {1, 3, 5, 2, 4};
        int[] nums2 = {6, 5, 4, 3, 2, 1, 7};
        var ans = new Problem_496_NextGreatElement().nextGreaterElement(nums1, nums2);
        System.out.println(Arrays.toString(ans));
        var ans1 = new Problem_496_NextGreatElement().nextGreaterElement1(nums1, nums2);
        System.out.println(Arrays.toString(ans1));
        var ans2 = new Problem_496_NextGreatElement().nextGreaterElement2(nums1, nums2);
        System.out.println(Arrays.toString(ans2));
    }
}
