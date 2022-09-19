package LeetCode;

import java.util.Arrays;
import java.util.*;

public class Problem_1636_FreqSort {

    public int[] frequencySort(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        int[] cnt = new int[201];
        for (int num : nums) {
            cnt[num + 100]++;
        }
        int N = nums.length;
        // 0...0 有序
        for (int i = 1; i < N; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (cnt[nums[j + 1] + 100] < cnt[nums[j] + 100]) {
                    swap(nums, j, j + 1);
                } else if (cnt[nums[j + 1] + 100] == cnt[nums[j] + 100] && nums[j + 1] > nums[j]) {
                    swap(nums, j, j + 1);
                } else {
                    break;
                }
            }
        }
        return nums;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public int[] frequencySort1(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int num : nums) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }
        List<Integer> list = new ArrayList<Integer>();
        for (int num : nums) {
            list.add(num);
        }
        Collections.sort(list, (a, b) -> {
            int cnt1 = cnt.get(a), cnt2 = cnt.get(b);
            return cnt1 != cnt2 ? cnt1 - cnt2 : b - a;
        });
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            nums[i] = list.get(i);
        }
        return nums;
    }


    public static void main(String[] args) {
        int[] nums = {2,3,1,3,2};
        var ans = new Problem_1636_FreqSort().frequencySort(nums);
        System.out.println(Arrays.toString(ans));
    }
}
