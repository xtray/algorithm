package _DailyTarget;

import java.util.ArrayList;
import java.util.List;

public class Problem_41_FirstMissingPositive {

    // 1~
    public int firstMissingPositive(int[] nums) {
        int N = nums.length;
        List<Integer> ans = new ArrayList<>();
        // 0...N-1 --- 1~N
        int R = N;
        for (int i = 0; i < R; ) {
            if (nums[i] < 1 || nums[i] > R) {
                swap(nums, i, --R);
            } else if (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                swap(nums, nums[i] - 1, i);
            } else if (nums[nums[i] - 1] == nums[i]) {
                i++;
            }
        }
        for (int i = 0; i < N; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return N + 1;
    }

    public int firstMissingPositive1(int[] nums) {
        int N = nums.length;
        List<Integer> ans = new ArrayList<>();
        // 0...N-1 --- 1~N
        int R = N;
        for (int i = 0; i < R; ) {
            if (nums[i] < 1 || nums[i] > R) {
                swap(nums, i, --R);
            } else {
                if (nums[i] == i + 1) {
                    i++;
                } else {
                    if (nums[nums[i] - 1] != nums[i]) {
                        swap(nums, i, nums[i] - 1);
                    } else {
                        swap(nums, i, --R);
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return N + 1;
    }


    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 0};
        // int[] nums = {1};
        var ans = new Problem_41_FirstMissingPositive().firstMissingPositive1(nums);
        System.out.println(ans);
    }

}
