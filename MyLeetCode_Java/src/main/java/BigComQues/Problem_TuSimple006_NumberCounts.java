package BigComQues;

// https://leetcode-cn.com/problems/1axZPc/

// 测试时类名改成Main
// NOTE: 学习从系统输入获取参数的方法!!!

import java.util.Scanner;

public class Problem_TuSimple006_NumberCounts {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        // String s = "abc123abc1a3a1";
        int ans = getNumCnts(s);
        System.out.print(ans);
    }

    private static int getNumCnts(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int cur = 0;
        int ans = 0;
        while (cur < N) {
            // 先跳过字母
            while (cur < N && (str[cur] >= 'a' && str[cur] <= 'z')) {
                cur++;
            }
            int i = cur;
            // 跳过连续的数字
            while (i < N && (str[i] >= '0' && str[i] <= '9')) {
                i++;
            }
            if (i > cur) { // i == cur, 只有在 越界N的位置
                ans++;
                cur = i;
            }
        }
        return ans;
    }
}
