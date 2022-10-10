package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.*;

public class Problem_870_Shuffle {

    public int[] advantageCount(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        // 找到nums2当前i位置最小的
        int N = nums1.length;
        int[] ans = new int[N];
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(new int[]{nums2[i], i});
        }
        List<Integer> unused = new ArrayList<>();
        list.sort((o1, o2) -> o1[0] - o2[0]);
        int i = 0;
        int j = 0;
        for (int[] item : list) {
            int val = item[0];
            int idx = item[1];
            while (i < N) {
                if (nums1[i] <= val) {
                    unused.add(i);
                    i++;
                } else {
                    break;
                }
            }
            if (i == N) {
                ans[idx] = nums1[unused.get(j++)];
            } else {
                ans[idx] = nums1[i++];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 0, 4, 1, 2};
        int[] nums2 = {1, 3, 0, 0, 2};
        var ans = new Problem_870_Shuffle().advantageCount(nums1, nums2);
        System.out.println(Arrays.toString(ans));
    }
}