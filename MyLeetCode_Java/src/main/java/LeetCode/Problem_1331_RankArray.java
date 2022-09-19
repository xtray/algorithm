package LeetCode;

import java.util.Arrays;
import java.util.HashMap;

public class Problem_1331_RankArray {

    public int[] arrayRankTransform(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        HashMap<Integer, Integer> map = new HashMap<>();
        int rank = 1;
        int pre = copy[0];
        map.put(pre, 1);
        for (int i = 1; i < copy.length; i++) {
            if (copy[i] == pre) {
                copy[i] = rank;
            } else {
                pre = copy[i];
                // rank = i + 1;
                rank++;
                map.put(copy[i], rank);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = map.get(arr[i]);
        }
        return arr;
    }

    public int[] arrayRankTransform1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        HashMap<Integer, Integer> map = new HashMap<>();
        int rank = 1;
        for(int num : copy) {
            if(!map.containsKey(num)) {
                map.put(num, rank++);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = map.get(arr[i]);
        }
        return arr;
    }


    public static void main(String[] args) {
        int[] nums = {37, 12, 28, 9, 100, 56, 80, 5, 12};
        var ans = new Problem_1331_RankArray().arrayRankTransform(nums);
        System.out.println(Arrays.toString(ans));
    }
}
