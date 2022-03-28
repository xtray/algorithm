package LeetCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
    // 看栈底
    // 找左右两侧更大的, 那么栈底就是最大的
    // 由栈底到栈顶由大到小
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[]{};
        }
        int N = nums1.length;
        int M = nums2.length;
        int[] ans = new int[N];
        LinkedList<Integer> stack = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && num > stack.peekFirst()) {
                map.put(stack.peekFirst(), num);
                stack.pollFirst();
            }
            stack.addFirst(num);
        }
        while (!stack.isEmpty()) { //栈里的元素单独结算
            map.put(stack.peekFirst(), -1);
            stack.pollFirst();
        }
        int idx = 0;
        for (int num : nums1) {
            ans[idx++] = map.get(num);
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] nums1 = {4,1,2};
        // int[] nums2 = {1,3,4,2};
        int[] nums1 = {1, 3, 5, 2, 4};
        int[] nums2 = {6, 5, 4, 3, 2, 1, 7};

        var ans = new Problem_496_NextGreatElement().nextGreaterElement(nums1, nums2);
        for (int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
        var ans2 = new Problem_496_NextGreatElement().nextGreaterElement2(nums1, nums2);
        for (int num : ans2) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
