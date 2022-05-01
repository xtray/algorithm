package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_728_SelfDivideNum {

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (check(i)) {
                ans.add(i);
            }
        }
        return ans;
    }

    private boolean check(int num) {
        if (num < 10) {
            return true;
        }
        int cnt = num;
        while (cnt != 0) {
            int digit = cnt % 10;
            if (digit == 0 || num % digit != 0) {
                return false;
            }
            cnt /= 10;
        }
        return true;
    }

    public static void main(String[] args) {
        int left = 1;
        int right = 22;
        var ans = new Problem_728_SelfDivideNum().selfDividingNumbers(left, right);
        for (int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

}
