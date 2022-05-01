package L_INPRG;

public class Problem_367_PerfectSquare {

    public boolean isPerfectSquare(int num) {
        if (num < 1) {
            return false;
        }
        int L = 1;
        int R = num/2; // 因为根号N 比 N/2小
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (mid == num / mid && num % mid == 0) { // 防止溢出
                return true;
            } else if (mid < num / mid) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return num == 1;
    }


    // TODO: 牛顿迭代法


    public static void main(String[] args) {
        int num = 16;
        var ans = new Problem_367_PerfectSquare().isPerfectSquare(num);
        System.out.println(ans);
    }
}
