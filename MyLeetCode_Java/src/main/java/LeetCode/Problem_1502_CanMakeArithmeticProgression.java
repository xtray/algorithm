package LeetCode;

import java.util.Arrays;

public class Problem_1502_CanMakeArithmeticProgression {

    public boolean canMakeArithmeticProgression(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return false;
        }
        Arrays.sort(arr);
        int gap = arr[1] - arr[0];
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != gap) {
                return false;
            }
        }
        return true;
    }
}
