package _LintCode;

public class Problem_1046_CountPrimeSetBits {

    private static boolean[] primeSet = new boolean[33];

    static {
        primeSet[2] = true;
        for (int i = 3; i <= 32; i++) {
            if (isPrime(i)) {
                primeSet[i] = true;
            }
        }
    }

    private static boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int countPrimeSetBits(int l, int r) {

        if (r < l) return 0;
        int ans = 0;
        for (int i = l; i <= r; i++) {
            int cnt = getBitCnt(i);
            if (primeSet[cnt]) {
                ans++;
            }
        }
        return ans;
    }

    private int getBitCnt(int num) {
        int cnt = 0;
        for (int i = 0; i < 32; i++) {
            cnt += ((num >> i) & 1);
        }
        return cnt;
    }

    private int getBinaryCount(int num) {
        int cnt = 0;
        while (num > 0) {
            cnt++;
            num -= num & (-num); // 去掉最右侧的1
        }
        return cnt;
    }
}
