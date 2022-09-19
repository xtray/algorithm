package _LintCode;

import java.util.*;

public class Problem_1665_CalNum {

    public int[] calculateNumber(int num) {
        List<Integer> list = new ArrayList<>();
        int cnt = 0;
        while (num != 0) {
            list.add(num % 2);
            if ((num % 2) != 0) cnt++;
            num /= 2;
        }
        int[] ans = new int[cnt + 1];
        ans[0] = cnt;
        int idx = 0;
        Collections.reverse(list);
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) == 1) {
                ans[++idx] = i + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int num = 10;
        var ans = new Problem_1665_CalNum().calculateNumber(num);
        System.out.println(Arrays.toString(ans));
    }
}
