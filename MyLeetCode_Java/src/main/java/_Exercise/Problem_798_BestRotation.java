package _Exercise;

// IMP: 差分!!, 多看!!
// ref: https://leetcode.cn/problems/smallest-rotation-with-highest-score/solution/gong-shui-san-xie-shang-xia-jie-fen-xi-c-p6kh/

public class Problem_798_BestRotation {

    // 数组长度N, nums[i] : 0~N-1
    // 分析原数组的每个nums[i]能够得分的k的取值范围
    // [i-(N-1), i - nums[i]]
    public int bestRotation(int[] nums) {
        int N = nums.length;
        int[] diff = new int[N + 1];
        for (int i = 0; i < N; i++) {
            int L = (i - (N - 1) + N) % N;
            int R = (i - nums[i] + N) % N;
            if (L <= R) {
                add(diff, L, R);
            } else {
                add(diff, 0, R);
                add(diff, L, N - 1);
            }
        }
        // 求前缀和
        for (int i = 1; i <= N; i++) {
            diff[i] += diff[i - 1];
        }
        int ans = 0; // 这个 0 是 index
        for (int i = 1; i < N; i++) {
            // 找大最大的标记值所在的位置, 就是要求的轮调k值
            if (diff[i] > diff[ans]) {
                ans = i;
            }
        }
        return ans;
    }

    private void add(int[] diff, int L, int R) {
        diff[L] += 1;
        diff[R + 1] -= 1;
    }

    public int bestRotation1(int[] nums) {
        int N = nums.length;
        // cnt: 差分数组
        // 加工后:
        // cnt[i] : 向右移动i步的得分, 每个数的i步得分都累加
        int[] cnt = new int[N + 1];
        // 考察每一个数
        for (int i = 0; i < N; i++) {
            if (nums[i] >= N) continue; // 永远无法得分
            // i <N
            // 考察 num[i] 这个数跟当前 i 的关系
            if (nums[i] > i) {
                // 得分下标范围是 nums[i] ~ N -1, 对应向右移动步数:
                // nums[i] - i  ~ N-1-i
                add(cnt, nums[i] - i, N - 1 - i);
            } else { // nums[i] <= i
                // 得分下标范围是 ---> 对应向右移动步数:
                // i ~ N -1      --> 0 ~ N -1 -i
                // num[i] ~ i-1  --> i - num[i], i - (i-1) 取负值 + N
                //   ==> N - i + nums[i],  N-1
                add(cnt, 0, N - 1 - i);
                add(cnt, N - i + nums[i], N - 1);
            }
        }
        // 求前缀和
        for (int i = 1; i <= N; i++) {
            cnt[i] += cnt[i - 1];
        }
        int maxCnt = cnt[0];
        int ans = 0;
        // 向右移动N-1, 对应向左移动1
        // 向右移动1, 对应向左移动N-1
        for (int i = N - 1; i >= 1; i--) {
            if (cnt[i] > maxCnt) {
                maxCnt = cnt[i];
                ans = i;
            }
        }
        return ans == 0 ? 0 : (N - ans);
    }


    public static void main(String[] args) {
        // int[] nums = {2,3,1,4,0};
        int[] nums = {1, 3, 0, 2, 4};
        var ans = new Problem_798_BestRotation().bestRotation(nums);
        System.out.println(ans);
    }
}
