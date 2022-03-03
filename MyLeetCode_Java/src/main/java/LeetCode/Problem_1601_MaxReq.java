package LeetCode;

public class Problem_1601_MaxReq {

    public int maximumRequests(int n, int[][] requests) {
        if (requests == null || requests.length <= 0) {
            return 0;
        }
        // 二进制枚举
        return process(n, requests);
    }

    private int process(int n, int[][] requests) {
        int m = requests.length; // 请求总个数
        int ans = 0;
        for (int i = 0; i < (1 << m); i++) {
            int oneCnt = getCnt(i);
            if (oneCnt > ans && check(requests, n, i)) {
                ans = oneCnt;
            }
        }
        return ans;
    }

    // 检测state方案能不能平账
    private boolean check(int[][] requests, int n, int state) {
        int[] cnt = new int[n]; // 一开始每个人都是 0, 收支平衡
        for (int i = 0; i < 32; i++) {
            if (((state >> i) & 1) != 0) { // 取出被选中的 request
                int from = requests[i][0]; // 转出
                int to = requests[i][1]; // 转入
                --cnt[from];
                ++cnt[to];
            }
        }
        for (int i = 0; i < n; i++) {
            if (cnt[i] != 0) {
                return false;
            }
        }
        return true;
    }

    // 获取 num 中 1 的个数
    private int getCnt(int num) {
        int cnt = 0;
        while (num != 0) {
            cnt++;
            num -= (num & (-num));
        }
        return cnt;
    }
}
