package _LintCode;

import java.util.HashSet;
import java.util.Set;

public class Problem_1320_DupNum {
    public boolean containsDuplicate(int[] nums) {
        if(nums == null || nums.length ==0) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            if(set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }
}
