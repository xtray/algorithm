package _Exercise;

public class Problem_1545_FindKthBit {

    // https://leetcode.cn/problems/find-kth-bit-in-nth-binary-string/solution/zhao-chu-di-n-ge-er-jin-zhi-zi-fu-chuan-zhong-de-2/
    public char findKthBit(int n, int k) {
        if (k == 1) {
            return '0';
        }
        int mid = 1 << (n - 1); // 2^{n-1}
        if (k == mid) {
            return '1';
        } else if (k < mid) {
            return findKthBit(n - 1, k);
        } else {
            /**
             * k 在后一半, (2^{n-1} - 1) (mid) (2^{n-1} - 1)
             *                                    k
             * k 在后一半的位置就是 k - mid, 逆序就是 一半长度 mid - 1  减去 (k-mid) 再加1
             * mid - 1 -(k-mid) + 1 = mid*2 - k
             */
            k = mid * 2 - k;
            return invert(findKthBit(n - 1, k));
        }
    }

    public char invert(char bit) {
        return (char) ('0' + '1' - bit); // 相当精妙
    }

    public static void main(String[] args) {
        int n = 3;
        int k = 1; // '0'
        // int n = 4;
        // int k = 11; // '1'
        var ans = new Problem_1545_FindKthBit().findKthBit(n, k);
        System.out.println(ans);
    }
}
