package LeetCode;

import java.util.Arrays;

public class Problem_1196_MaxApples {

    public int maxNumberOfApples(int[] weight) {
        Arrays.sort(weight);
        int limit = 5000;
        int N = weight.length;
        int i = 0;
        int sum = 0;
        while (i < N) {
            sum += weight[i];
            if (sum > limit) break;
            i++;
        }
        return i;
    }
}
