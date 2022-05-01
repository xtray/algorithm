package LeetCode;

/**
 * 两个数组其中一个数组长度为0的也可以合并
 */
public class Problem_88_MergeTwoSortedList {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(nums1 == null || nums1.length ==0 || nums2 == null) {
            return;
        }
        int index = m + n -1;
        int p1 = m -1;
        int p2 = n -1;
        while (p1>=0 &&p2>=0) {
            nums1[index--] = nums1[p1] > nums2[p2] ? nums1[p1--] : nums2[p2--];
        }
        while (p1>=0) {
            nums1[index--] = nums1[p1--];
        }
        while (p2>=0) {
            nums1[index--] = nums2[p2--];
        }
    }
}
