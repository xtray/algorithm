package _LintCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_188_InsertFive {

    public int insertFive(int a) {
        boolean negative = a < 0;
        a = negative ? -a : a;
        List<Integer> list = getNumList(a);
        int N = list.size();
        if (N == 0) {
            return 50;
        }
        // 从小到大, 找到最后一个<5的数
        int i = N - 1; // 头部, 最大数
        int j = -1;
        while (i >= 0) {
            if (!negative && list.get(i) < 5) {
                j = i;
                break;
            }
            if (negative && list.get(i) > 5) {
                j = i;
                break;
            }
            i--;
        }
        int cur = 0;
        if (j == -1) {
            // 数字都比5大, 放到最后
            cur = a * 10 + 5;
            return negative ? -cur : cur;
        }
        list.add(j + 1, 5);
        i = N;
        while (i >= 0) {
            cur = cur * 10 + list.get(i);
            i--;
        }
        return negative ? -cur : cur;
    }

    private List<Integer> getNumList(int num) {
        List<Integer> ans = new ArrayList<>();
        while (num != 0) {
            ans.add(num % 10);
            num /= 10;
        }
        return ans;
    }

    public int insertFive1(int a) {
        String num = String.valueOf(a);
        int N = num.length();
        int i = 0;
        if (a >= 0) {
            // 找到第一次<5的位置
            while (i < N && num.charAt(i) >= '5') {
                i++;
            }
        }
        else {
            i = 1; // 跳过负号
            // 找到第一次>5的位置
            while (i < N && num.charAt(i) <= '5') {
                i++;
            }
        }
        return Integer.parseInt(num.substring(0, i) + '5' + num.substring(i));
    }

    public static void main(String[] args) {
        // int n = 234;
        // int n = -4937;
        // int n = 0;
        // int n = 55555;
        int n = -548; // -5458
        var ans = new Problem_188_InsertFive().insertFive(n);
        System.out.println(ans);

        var ans1 = new Problem_188_InsertFive().insertFive1(n);
        System.out.println(ans1);
    }
}
