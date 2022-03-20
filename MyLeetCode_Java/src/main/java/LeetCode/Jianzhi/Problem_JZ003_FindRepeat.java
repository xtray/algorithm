package LeetCode.Jianzhi;

import java.util.HashSet;
import java.util.Set;

public class Problem_JZ003_FindRepeat {

    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            if(set.contains(num)) {
                return num;
            }
            set.add(num);
        }
        return -1;
    }
}
