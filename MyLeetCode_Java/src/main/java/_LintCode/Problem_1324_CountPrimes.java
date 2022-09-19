package _LintCode;

public class Problem_1324_CountPrimes {

    public int countPrimes(int n) {
        n--;
        // <=n的质数
        if (n <= 1) {
            return 0;
        }
        int cnt = 1;
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                cnt++;
            }
        }
        return cnt;
    }

    private boolean isPrime(int num) {
        if (num < 3) {
            return num == 2;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


    // 埃氏筛法
    public static void main(String[] args) {
        int n = 5;
        var ans = new Problem_1324_CountPrimes().countPrimes(n);
        System.out.println(ans);
    }
}
