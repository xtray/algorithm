package LeetCode.Jianzhi;

public class Problem_JZ14_I_CutRope {

    public int cuttingRope(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        // 第一个拆的数字从1...n/2尝试
        int res = 0;
        for (int i = 1; i <= n / 2; i++) {
            res = Math.max(res, process(i, n));
        }
        return res;
    }

    // 上一个拆出来的数是pre
    // 还剩rest需要去拆
    // 返回拆解的方法数
    public static int process(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int product = 0;
        for (int first = pre; first <= rest; first++) {
            product = Math.max(product, first * process(first, rest - first));
        }
        return product;
    }

    public static void main(String[] args) {
        int n = 2;
        var ans = new Problem_JZ14_I_CutRope().cuttingRope(n);
        System.out.println(ans);
    }
}
