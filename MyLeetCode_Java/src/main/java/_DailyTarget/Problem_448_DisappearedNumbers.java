package _DailyTarget;

import java.util.*;

public class Problem_448_DisappearedNumbers {

    public List<Integer> findDisappearedNumbers0(int[] nums) {
        int N = nums.length;
        List<Integer> ans = new ArrayList<>();
        // 0...N-1 --- 1~N
        for (int i = 0; i < N; ) {
            if (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                swap(nums, nums[i] - 1, i);
            } else if (nums[nums[i] - 1] == nums[i]) {
                i++;
            }
        }
        for (int i = 0; i < N; i++) {
            if (nums[i] != i + 1) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        int N = nums.length;
        List<Integer> ans = new ArrayList<>();
        // 0...N-1 --- 1~N
        int R = N;
        for (int i = 0; i < R; ) {
            if (nums[i] == i + 1) {
                i++;
            } else{
                if (nums[nums[i] - 1] != nums[i]) {
                    swap(nums, nums[i] - 1, i);
                } else {
                    i++;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if (nums[i] != i + 1) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

    // IMP: 不能利用firstMissingPositive的垃圾区的办法, 错的!!
    public List<Integer> findDisappearedNumbers1(int[] nums) {
        int N = nums.length;
        List<Integer> ans = new ArrayList<>();
        // 0...N-1 --- 1~N
        int R = N;
        for (int i = 0; i < R; ) {
            if (nums[i] > R || nums[i] < 1) {
                swap(nums, i, --R);
            } else {
                if (nums[i] == i + 1) {
                    i++;
                } else {
                    if (nums[nums[i] - 1] != nums[i]) {
                        swap(nums, nums[i] - 1, i);
                    } else {
                        swap(nums, i, --R);
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if (nums[i] != i + 1) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 遍历到某一个数x, 让 nums[x-1]位置的数+n 表示这个数出现过
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> ans = new ArrayList<Integer>();
        int N = nums.length;
        for (int num : nums) {
            int x = (num - 1) % N; // 某个数可能已经+N了
            nums[x] += N;
        }
        for (int i = 0; i < N; i++) {
            if (nums[i] <= N) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        // int[] nums = {1, 1};
        var ans = new Problem_448_DisappearedNumbers().findDisappearedNumbers1(nums);
        System.out.println(ans);
    }
}
