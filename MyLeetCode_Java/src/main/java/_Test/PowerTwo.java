package _Test;

public class PowerTwo {

    // 离m最近的2的某次幂
    private static int power2(int m) {
        int ans = 0;
        // m >> 1 : m先减半为了退出循环时 ans 不用--
        while ((1 << ans) <= (m >> 1)) {
        // while ((1 << ans) <= m) {
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 5;
        var ans = power2(n);
        System.out.println(ans);
    }
}
