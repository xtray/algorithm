package LeetCode;

public class Problem_27_RemoveElement {

    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int size = nums.length;
        for(int i = 0; i < size; ) {
            if(nums[i] != val) {
                i++;
            } else {
                swap(nums, i, --size);
            }
        }
        return size;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
