package LeetCode;

public class Problem_464_CanIWin {

    // 暴力
    public boolean canIWin0(int choose, int total) {
        if (total == 0) {
            return true;
        }
        if (((choose + 1) * choose >> 1) < total) return false; // 大过滤
        int[] nums = new int[choose];
        for (int i = 0; i < choose; i++) {
            nums[i] = i + 1;
        }
        return process0(nums, total);
    }

    // 当前轮到先手
    // 先手只能选择nums中存在的数字 , 剩余要凑的数是rest
    // 返回当前先手能不能赢
    private boolean process0(int[] nums, int rest) {
        if (rest <= 0) {
            return false;
        }
        // 先手去尝试所有不为-1的情况
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (cur != -1) {
                nums[i] = -1;
                boolean next = process0(nums, rest - cur);
                nums[i] = cur; // NOTE: 恢复现场要放在返回之前!!
                if (!next) { // 后序过程中, 后手赢了, 就是当前过程中先手赢了
                    return true;
                }
                // nums[i] = cur; // NOTE: 错!!
            }
        }
        return false;
    }

    // 状态压缩
    public boolean canIWin1(int choose, int total) {
        if (total == 0) {
            return true;
        }
        if ((1 + choose) * choose / 2 < total) {
            return false;
        }
        int status = 0;
        return process1(status, choose, total);
    }

    // 选取数的状态在status
    // status   i位如果为0，代表没拿，当前可以拿
    //          i位为1，代表已经拿过了，当前不能拿
    // 可以选取1~choose的数
    private boolean process1(int status, int choose, int rest) {
        if (rest <= 0) {
            return false;
        }
        for (int i = 1; i <= choose; i++) { // 0 位弃而不用
            if (((1 << i) & status) == 0) {
                boolean next = process1(status | (1 << i), choose, rest - i);
                if (!next) {
                    return true;
                }
            }
        }
        return false;
    }


    // 状态压缩+傻缓存
    // 你拿的数字的状态可以决定rest, 它俩不独立, 一维dp就够了
    public boolean canIWin2(int choose, int total) {
        if (total == 0) {
            return true;
        }
        if ((1 + choose) * choose / 2 < total) {
            return false;
        }

        int[] dp = new int[1 << (choose + 1)]; // NOTE: 注意空间的大小!!, 0位置不用, 0000~1111
        // dp[status] == 1  true
        // dp[status] == -1  false
        // dp[status] == 0  process(status) 没算过！去算！
        return process2(0, choose, total, dp);
    }

    private boolean process2(int status, int choose, int rest, int[] dp) {
        if (dp[status] != 0) {
            return dp[status] == 1;
        }
        boolean ans = false;
        if (rest > 0) {
            for (int i = 1; i <= choose; i++) { // 0 位弃而不用
                if (((1 << i) & status) == 0) {
                    boolean next = process2(status | (1 << i), choose, rest - i, dp);
                    if (!next) {
                        ans = true;
                    }
                }
            }
        }
        dp[status] = ans ? 1 : -1;
        return ans;
    }

    // 另外一种写法
    public boolean canIWin3(int choose, int total) {
        if (total == 0) {
            return true;
        }
        if ((1 + choose) * choose / 2 < total) {
            return false;
        }

        Boolean[] dp = new Boolean[1 << (choose + 1)];
        return process3(0, choose, total, dp);
    }

    private boolean process3(int status, int choose, int rest, Boolean[] dp) {
        if (dp[status] != null) {
            return dp[status];
        }
        boolean ans = false;
        if (rest > 0) {
            for (int i = 1; i <= choose; i++) { // 0 位弃而不用
                if (((1 << i) & status) == 0) {
                    boolean next = process3(status | (1 << i), choose, rest - i, dp);
                    if (!next) {
                        ans = true;
                    }
                }
            }
        }
        dp[status] = ans;
        return ans;
    }

    public static void main(String[] args) {
        int choose = 10;
        int total = 11; // false
        var ans = new Problem_464_CanIWin().canIWin0(choose, total);
        System.out.println(ans);
    }
}
