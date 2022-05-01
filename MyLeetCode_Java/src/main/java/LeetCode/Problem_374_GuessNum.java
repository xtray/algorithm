package LeetCode;

public class Problem_374_GuessNum {

    private int target;

    public void init(int t) {
        target = t;
    }

    private int guess(int num) {
        if (num > target) {
            return -1;
        } else if (num < target) {
            return 1;
        } else {
            return 0;
        }
    }

    public int guessNumber(int n) {
        int L = 1;
        int R = n;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (guess(mid) == -1) { // 大
                R = mid - 1;
            } else if (guess(mid) == 1) { // 小
                L = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public int guessNumber2(int n) {
        int L = 1;
        int R = n;
        while (L < R) {
            int mid = L + ((R - L) >> 1);
            if (guess(mid) <= 0) { // 大
                R = mid;
            } else {
                L = mid + 1;
            }
        }
        // 此时L == R, 区间只有一个数,就是答案
        return L;
    }

    public static void main(String[] args) {
        Problem_374_GuessNum sl = new Problem_374_GuessNum();
        int n = 10;
        int target = 6;
        sl.init(target);
        var ans = sl.guessNumber(n);
        System.out.println(ans);
    }
}
