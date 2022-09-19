package _LintCode;

public class Problem_730_SubSum {

    // 每个数字出现的次数 N = C(n-1,0) + C(n-1,1) + ... + C(n-1,n-1) = 2 ^ (n - 1)
    // 通过找规律可以发现，每个数出现2^(n - 1)次。
    // 当前n 就等于n-1中的所有集合 加上元素n后 形成的新集合
    public int subSum(int n) {
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum += i;
        }
        // return sum * (int)Math.pow(2, n - 1);
        return (1 + n) * n / 2 * (int) Math.pow(2, n - 1);
    }

    public static void main(String[] args) {
        int n = 3;
        var ans = new Problem_730_SubSum().subSum(n);
        System.out.println(ans);
    }
}
