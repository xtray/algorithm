package LeetCode;

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

    public static void main(String[] args) {
        // int[] nums = {2,3,1,4,0};
        int[] nums = {1, 3, 0, 2, 4};
        var ans = new Problem_798_BestRotation().bestRotation(nums);
        System.out.println(ans);
    }
}
