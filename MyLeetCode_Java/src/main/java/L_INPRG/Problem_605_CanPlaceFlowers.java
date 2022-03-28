package L_INPRG;

public class Problem_605_CanPlaceFlowers {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed == null || flowerbed.length < n) {
            return false;
        }
        int N = flowerbed.length;
        // 统计连续的不挨着1的0
        int ans = 0;
        int zeros;
        int L = 0;
        int R = 0;
        while (L < N) {
            while (L < N && flowerbed[L] != 0) {
                L++;
            }
            if (L == N) {
                break;
            }
            R = L;
            while (R < N && flowerbed[R] == 0) {
                R++;
            }
            int minus = ((L == 0 && flowerbed[0] == 0) ? 0 : -1) +
                    ((R == N && flowerbed[N - 1] == 0) ? 0 : -1);
            zeros = R - L + ((R - L == 1) && (minus == -2) ? minus + 1 : minus);
            ans += zeros / 2;
            if ((zeros & 1) != 0) { // 奇数
                ans++;
            }
            L = R;
        }
        return ans >= n;
    }

    // https://leetcode-cn.com/problems/can-place-flowers/solution/fei-chang-jian-dan-de-tiao-ge-zi-jie-fa-nhzwc/
    // NOTE: 类似 点亮str中所有需要点亮的位置至少需要几盏灯 TX14 贪心
    public boolean canPlaceFlowers2(int[] flowerbed, int n) {
        if (flowerbed == null || flowerbed.length < n) {
            return false;
        }
        int N = flowerbed.length;
        for (int i = 0; i < N; ) {
            if (flowerbed[i] == 1) {
                i += 2;
            } else if (i == N - 1 || flowerbed[i + 1] == 0) { // 当前是0, 看是不是边界或者下一个是0, 可以种
                n--;
                i += 2;
            } else { // 当前是0, index+1是1，说明这个位置不能种花且之后两格也不可能种花去+3位置判断
                i += 3;
            }
        }
        return n <= 0;
    }

    public static void main(String[] args) {
        // int[] flowerbed = {1, 0, 0, 0, 1};
        // int n = 2;
        // int[] flowerbed = {1, 0, 0, 0, 0, 1};
        // int n = 1;
        // int[] flowerbed = {1, 0, 1, 0, 1, 0, 1};
        // int n = 1;
        // int[] flowerbed = {1, 0, 0, 0, 1, 0, 0};
        // int n = 2;
        int[] flowerbed = {1, 0};
        int n = 1;
        var ans = new Problem_605_CanPlaceFlowers().canPlaceFlowers(flowerbed, n);
        System.out.println(ans);
    }
}
