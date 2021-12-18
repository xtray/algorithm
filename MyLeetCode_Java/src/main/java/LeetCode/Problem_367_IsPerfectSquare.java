package LeetCode;

public class Problem_367_IsPerfectSquare {

    public boolean isPerfectSquare(int num) {
        if (num < 1 ){
            return false;
        }
        // 二分, 1~N/2
        int L = 1;
        int R = num / 2;
        while (L <= R) {
            int mid = L + ((R-L) >>1);
            if (mid > num/mid ) { // 注意越界
                R = mid -1;
            } else if (mid < num/mid) {
                L = mid + 1;
            } else {
                return num % mid == 0;
            }
        }
        return num == 1;
    }

    public static void main(String[] args) {
        int num = 5;
        Problem_367_IsPerfectSquare sl = new Problem_367_IsPerfectSquare();
        boolean ans = sl.isPerfectSquare(num);
        System.out.println(ans);
    }
}
