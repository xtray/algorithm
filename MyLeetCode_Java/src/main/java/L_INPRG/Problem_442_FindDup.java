package L_INPRG;

import java.util.ArrayList;
import java.util.List;

public class Problem_442_FindDup {

    // NOTE: 重复的数字, 第一次去要去的位置, 发现位置占用就改为0, 再次碰到0, 跳到下一个位置处理
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length <= 1) {
            return ans;
        }
        int N = nums.length;
        for (int i = 0; i < N; ) {
            int cur = nums[i];
            // 标记的数字 或者 就是正确的去处, 跳过
            if (cur == 0 || cur == i + 1) {
            // if (cur < 0 || cur == i + 1) {
                i++;
                continue;
            }
            // cur (i) --> (cur - 1)
            if (cur != nums[cur - 1]) {
                swap(nums, i, cur - 1);
            } else { // 重复数字, 收集答案
                ans.add(cur);
                nums[i] = 0;
                // nums[i] *= -1;
                i++;
            }
        }
        return ans;
    }

    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        var ans = new Problem_442_FindDup().findDuplicates(nums);
        for (int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }


}
