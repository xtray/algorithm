package LeetCode;

// PENDING: 单调栈的解法

public class Problem_496_NextGreatElement {
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
        for (int i = 0; i< nums2.length; i++) {
            if(idx == -1 && nums2[i] == num1) {
                idx = i;
            }
            if(idx != -1 && nums2[i] > num1) {
                return nums2[i];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};
        var ans = new Problem_496_NextGreatElement().nextGreaterElement(nums1, nums2);
        for(int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
