package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_1200_MinAbsPairs {

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr == null || arr.length < 2) {
            return ans;
        }
        Arrays.sort(arr);
        int minGap = arr[1] - arr[0];
        int N = arr.length;
        for (int i = 1; i < N; i++) {
            minGap = Math.min(minGap, arr[i] - arr[i - 1]);
        }
        for (int i = 1; i < N; i++) {
            if (arr[i] - arr[i - 1] == minGap) {
                List<Integer> item = new ArrayList<>();
                item.add(arr[i - 1]);
                item.add(arr[i]);
                ans.add(item);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,2,4};
        var ans = new Problem_1200_MinAbsPairs().minimumAbsDifference(arr);
        System.out.println(ans.toString());
    }
}
