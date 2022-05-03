package LeetCode;

public class Problem_464_CanIWin {

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0) {
            return true;
        }
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) {
            return false;
        }

        int[] arr = new int[maxChoosableInteger];
        for (int i = 0; i < maxChoosableInteger; i++) {
            arr[i] = i + 1;
        }
        return process(arr, desiredTotal);
    }

    // 可以选取的数在arr里, 剩余要凑的数是rest, 返回先手会不会赢
    private boolean process(int[] arr, int rest) {
        if (rest <= 0) {
            return false;
        }
        // 先手尝试arr中的每一个数
        for (int i = 0; i < arr.length; i++) {
            boolean next = true;
            if (arr[i] != -1) {
                arr[i] = -1;
                next = process(arr, rest - i - 1);
                arr[i] = i + 1;
            }
            if (!next) { // 后序过程中, 后手赢了, 就是当前过程中先手赢了
                return true;
            }
        }
        return false;
    }

    // 状态压缩
    public boolean canIWin1(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0) {
            return true;
        }
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) {
            return false;
        }
        int status = 0;
        return process1(status, maxChoosableInteger, desiredTotal);
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
    public boolean canIWin2(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0) {
            return true;
        }
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) {
            return false;
        }

        int[] dp = new int[1 << (maxChoosableInteger + 1)];
        // dp[status] == 1  true
        // dp[status] == -1  false
        // dp[status] == 0  process(status) 没算过！去算！
        return process2(0, maxChoosableInteger, desiredTotal, dp);
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
    public boolean canIWin3(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0) {
            return true;
        }
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) {
            return false;
        }

        Boolean[] dp = new Boolean[1 << (maxChoosableInteger + 1)];
        return process3(0, maxChoosableInteger, desiredTotal, dp);
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
}
