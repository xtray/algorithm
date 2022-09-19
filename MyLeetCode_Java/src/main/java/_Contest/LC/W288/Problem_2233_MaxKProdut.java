package _Contest.LC.W288;

import java.util.Arrays;

public class Problem_2233_MaxKProdut {

    public int maximumProduct(int[] nums, int k) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0] + k;
        }
        int N = nums.length;
        Arrays.sort(nums);
        while (k > 0) {
            int L = 0;
            int R = 0;
            while (R < N && nums[R] == nums[L]) { // 找到第一个不相等的位置
                R++;
            }
            int levelCnt = R - L;
            if (R == N) { // 到边界了结束
                addOne(nums, L, R - 1, k);
                break;
            }
            // 有下一个不相等的数
            int gap = levelCnt * (nums[R] - nums[L]);
            if(gap >= k) { // 能把k吃掉, 到不了下一个台阶
                addOne(nums, L, R-1, k);
                break;
            } else { // 有多的k
                addOne(nums, L, R-1, gap);
                k -= gap;
            }
        }
        return 0;

    }

    // L...R范围上用1铺平
    private void addOne(int[] nums, int L, int R, int k) {
        int cnt = R - L + 1;
        int addOn = k / cnt;
        if(addOn >= 1) {
            for (int i = L; i <= R; i++) {
                nums[i] += addOn;
            }
        } else {
            for (int i = L; i<=L+k-1; i++) {
                nums[i]++;
            }
        }

        if ((k % cnt) != 0) {
            nums[R] += k % cnt;
        }
    }

    public static void main(String[] args) {
        int[] nums = {6,3,3,2};
        var ans = new Problem_2233_MaxKProdut().maximumProduct(nums, 2);
        System.out.println(ans);
    }

}
