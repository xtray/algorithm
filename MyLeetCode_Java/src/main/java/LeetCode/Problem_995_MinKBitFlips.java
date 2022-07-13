package LeetCode;

// IMP: 差分

public class Problem_995_MinKBitFlips {

    // 从左往右的尝试模型, TLE
    public int minKBitFlips(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return -1;
        }
        int ans = process(nums, k, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // 当前来到index位置, 返回从index...N-1最小的反转次数, 不可能返回-1
    // 0~index-1 都已经是1了, 不用关心
    private int process(int[] nums, int k, int index) {
        int N = nums.length;
        if (index == N) {
            return 0;
        }
        // 可能性1: 当前位置是1, 不处理, 直接去下一个位置
        int p1 = Integer.MAX_VALUE;
        if (nums[index] != 0) {
            p1 = process(nums, k, index + 1);
        }
        // 可能性2: 当前位置是0, 必须要反转处理
        int p2 = Integer.MAX_VALUE;
        if (nums[index] == 0) {
            if (index + k > N) {
                return Integer.MAX_VALUE;
            }
            reverseBit(nums, k, index);
            int next = process(nums, k, index + 1);
            if (next != Integer.MAX_VALUE) {
                p2 = 1 + next;
            }
            reverseBit(nums, k, index);
        }
        return Math.min(p1, p2);
    }

    // 反转从index位置的k个bit
    private void reverseBit(int[] nums, int k, int index) {
        int N = nums.length;
        if (index + k > N) {
            return;
        }
        for (int i = index; i < index + k; i++) {
            nums[i] ^= 1;
        }
    }

    // 简化的暴力解, TLE
    public int minKBitFlips1(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return -1;
        }
        int N = nums.length;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (nums[i] != 1) {
                if (i + k > N) {
                    return -1;
                }
                for (int j = i; j < i + k; j++) {
                    nums[j] ^= 1;
                }
                ans++;
            }
        }
        return ans;
    }

    // IMP: 差分的解法, 多看!!
    public int minKBitFlips2(int[] nums, int k) {
        int N = nums.length;
        int ans = 0;
        int[] diff = new int[N + 1]; // 差分数组, 记录区间翻转次数
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += diff[i]; // i位置翻转次数: 当前需要使用的前缀和数值, 相当于空间压缩了
            if ((nums[i] + sum) % 2 == 0) { // 当前为0, 需要从当前位置开始做翻转
                if (i + k > N) {
                    return -1;
                }
                // arr[i]位置已经用不到了, 所以从下一个位置开始+1
                // 否则diff[i]++的影响不会带给sum
                diff[i + 1]++;
                diff[i + k]--;
                ans++;
            }
        }
        return ans;
    }

    public int minKBitFlips3(int[] nums, int k) {
        int N = nums.length;
        int ans = 0;
        int[] diff = new int[N + 1]; // 差分数组, 记录区间翻转次数
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += diff[i]; // i位置翻转次数: 当前需要使用的前缀和数值, 相当于空间压缩了
            if ((nums[i] + sum) % 2 == 0) { // 当前为0, 需要从当前位置开始做翻转
                if (i + k > N) {
                    return -1;
                }
                diff[i]++;
                sum++; // 修正sum, 把本次翻转的影响传递下去
                diff[i + k]--;
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] nums = {1, 1, 0};
        // int k = 2;
        int[] nums = {0, 0, 0, 1, 0, 1, 1, 0};
        int k = 3; // 3
        var ans = new Problem_995_MinKBitFlips().minKBitFlips2(nums, k);
        System.out.println(ans);

    }
}
