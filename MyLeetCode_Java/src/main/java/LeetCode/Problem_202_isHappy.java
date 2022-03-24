package LeetCode;

import java.util.ArrayList;
import java.util.List;


// TODO: 看官方, 用链表有环的方法

public class Problem_202_isHappy {

    public boolean isHappy(int n) {
        if (n == 0) {
            return false;
        }
        return process(n);
    }

    private boolean process(int n) {
        int len = getLen(n);
        if (len == 1) {
            return n == 1 || n == 7;
        }
        List<Integer> list = new ArrayList<>();
        while (n != 0) {
            list.add(n % 10);
            n /= 10;
        }
        int sum = getSum(list);
        return process(sum);
    }

    private int getLen(int n) {
        int len = 0;
        while (n != 0) {
            len++;
            n /= 10;
        }
        return len;
    }

    private int getSum(List<Integer> list) {
        int sum = 0;
        for (int num : list) {
            sum += Math.pow(num, 2);
        }
        return sum;
    }

    public static void main(String[] args) {
        int n = 100;
        for(int i = 101; i<=200; i++) {
            var ans = new Problem_202_isHappy().isHappy(i);
            if(ans) {
                System.out.println(i + ": " + ans);
            }
        }
    }
}
