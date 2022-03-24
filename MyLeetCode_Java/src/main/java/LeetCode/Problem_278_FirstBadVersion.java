package LeetCode;

public class Problem_278_FirstBadVersion {

    private boolean isBadVersion(int version) {
        return false;
    }

    public int firstBadVersion(int n) {
        int L = 1;
        int R = n;
        while (L < R) { // 必然有一个错误的, 一个数就是答案
            int mid = L + ((R-L)>>1);
            if(isBadVersion(mid)) { // 坏的版本, 之后都是错的, 往前找
                R = mid;
            } else { // 好版本, 要跳过这个, 往后找
                L = mid + 1;
            }
        }
        return L;
    }
}
