package Jianzhi;

public class Problem_JZ039_MajorityElement {


    public int majorityElement(int[] nums) {
        int N = nums.length;
        int target = nums[0];
        int HP = 0;

        for(int num : nums) {
            if(num == target) {
                HP++;
            } else {
                HP--;
                if(HP == 0) {
                    target = num;
                    HP++;
                }
            }
        }
        return target;
    }
}
