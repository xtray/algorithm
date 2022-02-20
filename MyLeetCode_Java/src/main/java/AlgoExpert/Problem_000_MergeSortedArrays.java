package AlgoExpert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Problem_000_MergeSortedArrays {

    public static List<Integer> mergeSortedArrays(List<List<Integer>> arrays) {
        List<Integer> ans = new ArrayList<>();
        if (arrays == null || arrays.size() == 0) {
            return ans;
        }
        int N = arrays.size();
        // int[value, row, col]
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        for (int i = 0; i < N; i++) {
            List<Integer> curList = arrays.get(i);
            pq.add(new int[]{curList.get(0), i, 0});
        }
        while (!pq.isEmpty()) {
            int[] item = pq.poll();
            int value = item[0];
            int row = item[1];
            int col = item[2];
            ans.add(value);
            if (col + 1 < arrays.get(row).size()) {
                pq.add(new int[]{arrays.get(row).get(col + 1), row, col + 1});
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        List<List<Integer>> arrays = new ArrayList<List<Integer>>();
        arrays.add(Arrays.asList(new Integer[]{1, 5, 9, 21}));
        arrays.add(Arrays.asList(new Integer[]{-1, 0}));
        arrays.add(Arrays.asList(new Integer[]{-124, 81, 121}));
        arrays.add(Arrays.asList(new Integer[]{3, 6, 12, 20, 150}));
        var ans = mergeSortedArrays(arrays);
        for (int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
