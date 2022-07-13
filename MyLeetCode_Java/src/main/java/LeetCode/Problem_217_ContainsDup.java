package LeetCode;

import java.util.HashSet;
import java.util.Set;

public class Problem_217_ContainsDup {

    public boolean containsDuplicate(int[] nums) {
        if(nums == null||nums.length ==0) {
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
