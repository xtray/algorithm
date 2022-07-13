package LeetCode;

public class Problem_668_FindKthNumber {

    // 二分
    public int findKthNumber(int m, int n, int k) {

        int L = 1;
        int R = m * n;
        while (L < R) {
            int mid = L + ((R - L) >> 1);
            int cnt = getCnt(m, n, mid);
            if (cnt < k) { // mid小了
                L = mid + 1;
            } else { // >=
                R = mid;
            }
        }
        return L;
    }

    // 返回<=num的数有多少个
    // 统计乘法表中有多少个小于等于 num 的数目
    // ref: https://leetcode.cn/problems/kth-smallest-number-in-multiplication-table/solution/by-fuxuemingzhu-8eq4/
    int getCnt(int m, int n, int num) {
        int cnt = 0;
        // 统计每行小于等于 k 的数目
        for (int i = 1; i <= m; i++) {
            cnt += Math.min(num / i, n);
        }
        return cnt;
    }

    // 找到小于等于mid的个数
    int getCnt2(int m, int n, int mid) {
        int cnt = 0;
        for (int i = 1; i <= m; i++) {
            if (i * n < mid) {
                // 这一行最大的数小于mid，则这一行都小于mid
                cnt += n;
            } else {
                cnt += mid / i;
            }
        }
        return cnt;
    }


    public static void main(String[] args) {
        // int m = 3;
        // int n = 3;
        // int k = 5;
        int m = 45;
        int n = 12;
        int k = 471;
        var ans = new Problem_668_FindKthNumber().findKthNumber(m, n, k);
        System.out.println(ans);
    }
}
