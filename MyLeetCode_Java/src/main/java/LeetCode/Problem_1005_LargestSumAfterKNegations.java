package LeetCode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Problem_1005_LargestSumAfterKNegations {

    public int largestSumAfterKNegations(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //  数字分成 3 类
        // 1.负数, 按绝对值排序
        // 2. 0
        // 3. 正数
        // 策略:
        // 尽可能的让负数变为正数, 负数个数不够时
        //  a.有 0: 剩下的都安排在 0 上
        //  b.没有 0, 都安排在最小的正整数上
        // 修正: 剩余的次数丢给 负数跟正数绝对值最小的那个数
        PriorityQueue<Integer> minus = new PriorityQueue<>();
        boolean zero = false;
        int sum = 0;
        int minPos = Integer.MAX_VALUE;
        int maxMinus = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num < 0) {
                minus.add(num);
                maxMinus = Math.max(maxMinus, num);
            } else if (num > 0) {
                minPos = Math.min(minPos, num);
                sum += num;
            } else {
                zero = true;
            }
        }

        int gap = maxMinus == Integer.MIN_VALUE ? minPos : Math.min(minPos, -maxMinus);

        int minusCount = minus.size();
        int loop = Math.min(k, minusCount);
        while (loop > 0) {
            sum += -minus.poll();
            loop--;
        }
        while (!minus.isEmpty()) {
            sum += minus.poll();
        }
        if (k <= minusCount || (k > minusCount && zero)) {
            return sum;
        }
        // k > loop
        if (((k - minusCount) & 1) != 0) { // 奇数
            if (gap != minPos) {
                sum -= gap * 2;
            } else {
                sum -= minPos * 2;
            }
        }
        return sum;
    }

    public int largestSumAfterKNegations2(int[] nums, int k) {
        int n = nums.length, idx = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> nums[a] - nums[b]);
        boolean zero = false;
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0) q.add(i);
            if (nums[i] == 0) zero = true;
            if (Math.abs(nums[i]) < Math.abs(nums[idx])) idx = i;
        }
        if (k <= q.size()) {
            while (k-- > 0) nums[q.peek()] = -nums[q.poll()];
        } else {
            while (!q.isEmpty() && k-- > 0) nums[q.peek()] = -nums[q.poll()];
            if (!zero && k % 2 != 0) nums[idx] = -nums[idx];
        }
        int ans = 0;
        for (int i : nums) ans += i;
        return ans;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{-8, 3, -5, -3, -5, -2};
        int k = 6;
        var res = new Problem_1005_LargestSumAfterKNegations().largestSumAfterKNegations(arr, k);
        System.out.println(res);
        var res2 = new Problem_1005_LargestSumAfterKNegations().largestSumAfterKNegations2(arr, k);
        System.out.println(res2);
    }
}
