package LeetCode;

public class Problem_319_BulbSwitch {

    // 暴力尝试
    public static int bulbSwitch(int n) {
        if (n < 2) return n;

        int[] arr = new int[n];
        for (int i = 2; i <= n; i++) { // i 是轮数
            // 第 i轮每隔 i 个就关闭
            for (int j = i - 1; j <= n - 1; j += i) {
                arr[j] ^= 1;
            }
        }

        int sum = 0;
        for (int k = 0; k < n; k++) {
            if (arr[k] == 0) {
                sum++;
            }
        }
        return sum;
    }

    public static int bulbSwitch1(int n) {
        return (int)Math.sqrt(n);
    }



    public static void main(String[] args) {

//        int n  = 5;
//        int ans = bulbSwitch(n);
//        System.out.println(n + ": " +ans);

        System.out.println("测试开始");
        int num = Integer.MAX_VALUE;
        for (int i=0;i<=num;i++) {
            int ans1 = bulbSwitch(i);
            int ans2 = bulbSwitch(i);
            if (ans1 != ans2) {
                System.out.println("Ooops!");
                System.out.printf("num: %d, ans1:%d, ans2:%d.\n", i, ans1, ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}